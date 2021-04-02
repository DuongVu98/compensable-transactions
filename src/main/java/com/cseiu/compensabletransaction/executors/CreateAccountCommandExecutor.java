package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.Command;
import com.cseiu.compensabletransaction.commands.CreateAccountCommand;
import com.cseiu.compensabletransaction.compensable.Compensable;
import com.cseiu.compensabletransaction.models.Account;
import com.cseiu.compensabletransaction.repositories.AccountRepository;
import lombok.Builder;

import javax.transaction.Transactional;
import java.util.Optional;

public class CreateAccountCommandExecutor extends AbstractCommandExecutor implements Compensable {

    private final AccountRepository accountRepository;

    @Builder
    public CreateAccountCommandExecutor(Command command, AccountRepository accountRepository) {
        super(command);
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void execute() {
        CreateAccountCommand createAccountCommand = (CreateAccountCommand) getCommandDetail();
        Account newAccount = Account.builder()
                .username(createAccountCommand.getUsername())
                .password(createAccountCommand.getPassword())
                .build();

        accountRepository.save(newAccount);
    }

    @Override
    public void reverse() throws Exception {
        CreateAccountCommand createAccountCommand = (CreateAccountCommand) getCommandDetail();
        Optional<Account> currentAccountOptional = accountRepository.findById(createAccountCommand.getAggregateId());

        if (currentAccountOptional.isPresent()) {
            accountRepository.deleteById(createAccountCommand.getAggregateId());
        } else {
            throw new Exception();
        }
    }
}
