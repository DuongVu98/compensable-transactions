package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.BaseCommand;
import com.cseiu.compensabletransaction.commands.Command;
import com.cseiu.compensabletransaction.commands.DeleteAccountCommand;
import com.cseiu.compensabletransaction.compensable.Compensable;
import com.cseiu.compensabletransaction.models.Account;
import com.cseiu.compensabletransaction.repositories.AccountRepository;
import com.cseiu.compensabletransaction.services.AggregateBackupService;
import lombok.Builder;

import java.util.Optional;


public class DeleteAccountCommandExecutor extends AbstractCommandExecutor implements Compensable {
    private final AccountRepository accountRepository;
    private final AggregateBackupService aggregateBackupService;

    @Builder
    public DeleteAccountCommandExecutor(Command command, AccountRepository accountRepository, AggregateBackupService aggregateBackupService) {
        super(command);
        this.accountRepository = accountRepository;
        this.aggregateBackupService = aggregateBackupService;
    }

    @Override
    public void execute() {
        DeleteAccountCommand deleteAccountCommand = (DeleteAccountCommand) command;
        Optional<Account> currentAccountOptional = accountRepository.findById(deleteAccountCommand.getAggregateId());
        if (currentAccountOptional.isPresent()) {
            accountRepository.deleteById(deleteAccountCommand.getAggregateId());
        }
    }

    @Override
    public void reverse() {
        BaseCommand command = (BaseCommand) this.getCommand();
        Account backupAccount = aggregateBackupService.getAccount(command.getAggregateId());
        accountRepository.save(backupAccount);
        aggregateBackupService.remove(backupAccount);
    }
}
