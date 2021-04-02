package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.Command;
import com.cseiu.compensabletransaction.commands.DeleteAccountCommand;
import com.cseiu.compensabletransaction.models.Account;
import com.cseiu.compensabletransaction.repositories.AccountRepository;
import lombok.Builder;

import java.util.Optional;


public class DeleteAccountCommandExecutor extends AbstractCommandExecutor {
    private final AccountRepository accountRepository;

    @Builder
    public DeleteAccountCommandExecutor(Command command, AccountRepository accountRepository) {
        super(command);
        this.accountRepository = accountRepository;
    }

    @Override
    public void execute() {
        DeleteAccountCommand deleteAccountCommand = (DeleteAccountCommand) command;
        Optional<Account> currentAccountOptional = accountRepository.findById(deleteAccountCommand.getAggregateId());
        if (currentAccountOptional.isPresent()) {
            accountRepository.deleteById(deleteAccountCommand.getAggregateId());
        }
    }
}
