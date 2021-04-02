package com.cseiu.compensabletransaction.usecases.decorators;

import com.cseiu.compensabletransaction.domain.commands.BaseCommand;
import com.cseiu.compensabletransaction.domain.commands.Command;
import com.cseiu.compensabletransaction.usecases.executors.CommandExecutor;
import com.cseiu.compensabletransaction.domain.models.Account;
import com.cseiu.compensabletransaction.domain.repositories.AccountRepository;
import com.cseiu.compensabletransaction.usecases.services.AggregateBackupService;
import lombok.Builder;

import javax.transaction.Transactional;
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
    public Command getCommandDetail() {
        return commandExecutor.getCommandDetail();
    }

    @Override
    @Transactional
    public void execute() {
        BaseCommand command = (BaseCommand) getCommandDetail();
        backupAggregate(command.getAggregateId());
        commandExecutor.execute();
    }

    private void backupAggregate(String accountId) {
        Optional<Account> currentAccountOptional = accountRepository.findById(accountId);
        currentAccountOptional.ifPresent(aggregateBackupService::store);
    }
}
