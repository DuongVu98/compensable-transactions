package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.Command;
import com.cseiu.compensabletransaction.commands.CreateAccountCommand;
import com.cseiu.compensabletransaction.models.Account;
import com.cseiu.compensabletransaction.repositories.AccountRepository;
import lombok.Builder;

public class CreateAccountCommandExecutor extends AbstractCommandExecutor {

    private final AccountRepository accountRepository;

    @Builder
    public CreateAccountCommandExecutor(Command command, AccountRepository accountRepository) {
        super(command);
        this.accountRepository = accountRepository;
    }

    @Override
    public void execute() {
        CreateAccountCommand createAccountCommand = (CreateAccountCommand) command;
        Account newAccount = Account.builder()
                .username(createAccountCommand.getUsername())
                .password(createAccountCommand.getPassword())
                .build();

        accountRepository.save(newAccount);
    }
}
