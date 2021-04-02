package com.cseiu.compensabletransaction.decorators;

import com.cseiu.compensabletransaction.commands.BaseCommand;
import com.cseiu.compensabletransaction.executors.AbstractCommandExecutor;
import com.cseiu.compensabletransaction.executors.CommandExecutor;
import com.cseiu.compensabletransaction.models.Account;
import com.cseiu.compensabletransaction.repositories.AccountRepository;
import com.cseiu.compensabletransaction.services.AggregateBackupService;
import lombok.Builder;

import java.util.Optional;

public class AggregateBackupDecorator extends CommandExecutorDecorator{

    private final AccountRepository accountRepository;
    private final AggregateBackupService aggregateBackupService;

    @Builder
    public AggregateBackupDecorator(CommandExecutor commandExecutor, AccountRepository accountRepository, AggregateBackupService aggregateBackupService) {
        super(commandExecutor);
        this.accountRepository = accountRepository;
        this.aggregateBackupService = aggregateBackupService;
    }

    @Override
    public void execute() {
        BaseCommand command = (BaseCommand) ((AbstractCommandExecutor) commandExecutor).getCommand();
        backupAggregate(command.getAggregateId());
        commandExecutor.execute();
    }

    private void backupAggregate(String accountId) {
        Optional<Account> currentAccountOptional = accountRepository.findById(accountId);
        currentAccountOptional.ifPresent(aggregateBackupService::store);
    }
}
