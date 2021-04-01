package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.CreateAccountCommand;
import com.cseiu.compensabletransaction.compensable.Compensable;
import com.cseiu.compensabletransaction.events.AccountCreatedEvent;
import com.cseiu.compensabletransaction.models.Account;
import com.cseiu.compensabletransaction.repositories.AccountRepository;
import lombok.Builder;

import java.util.Optional;

@Builder
public class CreateAccountCommandExecutor implements CommandExecutor<CreateAccountCommand>, Compensable<AccountCreatedEvent> {

    private final AccountRepository accountRepository;

    @Override
    public void execute(CreateAccountCommand command) {
        Account newAccount = Account.builder()
                .username(command.getUsername())
                .password(command.getPassword())
                .build();

        accountRepository.save(newAccount);
    }

    @Override
    public void reverse(AccountCreatedEvent event) {
        Optional<Account> currentAccountOptional = accountRepository.findById(event.getAccountId());
        if (currentAccountOptional.isPresent()) {
            accountRepository.deleteById(event.getAccountId());
        }
    }
}
