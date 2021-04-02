package com.cseiu.compensabletransaction.usecases.executors;

import com.cseiu.compensabletransaction.domain.commands.BaseCommand;
import com.cseiu.compensabletransaction.domain.commands.Command;
import com.cseiu.compensabletransaction.domain.commands.DeleteAccountCommand;
import com.cseiu.compensabletransaction.usecases.compensable.Compensable;
import com.cseiu.compensabletransaction.domain.models.Account;
import com.cseiu.compensabletransaction.domain.repositories.AccountRepository;
import com.cseiu.compensabletransaction.usecases.services.AggregateBackupService;
import lombok.Builder;

import javax.transaction.Transactional;
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
    @Transactional
    public void execute() {
        DeleteAccountCommand deleteAccountCommand = (DeleteAccountCommand) getCommandDetail();
        Optional<Account> currentAccountOptional = accountRepository.findById(deleteAccountCommand.getAggregateId());
        if (currentAccountOptional.isPresent()) {
            accountRepository.deleteById(deleteAccountCommand.getAggregateId());
        }
    }

    @Override
    public void reverse() {
        BaseCommand command = (BaseCommand) getCommandDetail();
        Account backupAccount = aggregateBackupService.getAccount(command.getAggregateId());
        accountRepository.save(backupAccount);
        aggregateBackupService.remove(backupAccount);
    }
}
