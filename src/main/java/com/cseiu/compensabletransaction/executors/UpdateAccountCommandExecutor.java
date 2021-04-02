package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.Command;
import com.cseiu.compensabletransaction.commands.UpdateAccountCommand;
import com.cseiu.compensabletransaction.models.Account;
import com.cseiu.compensabletransaction.repositories.AccountRepository;
import lombok.Builder;

import java.util.Optional;

public class UpdateAccountCommandExecutor extends AbstractCommandExecutor {

    private final AccountRepository accountRepository;

    @Builder
    public UpdateAccountCommandExecutor(Command command, AccountRepository accountRepository) {
        super(command);
        this.accountRepository = accountRepository;
    }

    @Override
    public void execute() {
        UpdateAccountCommand updateAccountCommand = (UpdateAccountCommand) command;
        Optional<Account> currentAccountOptional = accountRepository.findById(updateAccountCommand.getAggregateId());
        if (currentAccountOptional.isPresent()) {
            Account currentAccount = currentAccountOptional.get();

            currentAccount.setUsername(updateAccountCommand.getUsername());
            currentAccount.setEmail(updateAccountCommand.getEmail());

            accountRepository.save(currentAccount);
        }
    }
}
