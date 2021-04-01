package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.UpdateAccountCommand;
import com.cseiu.compensabletransaction.models.Account;
import com.cseiu.compensabletransaction.repositories.AccountRepository;
import lombok.Builder;

import java.util.Optional;

@Builder
public class UpdateAccountCommandExecutor implements CommandExecutor<UpdateAccountCommand> {

    private final AccountRepository accountRepository;

    @Override
    public void execute(UpdateAccountCommand command) {
        Optional<Account> currentAccountOptional = accountRepository.findById(command.getAggregateId());
        if (currentAccountOptional.isPresent()) {
            Account currentAccount = currentAccountOptional.get();

            currentAccount.setUsername(command.getUsername());
            currentAccount.setEmail(command.getEmail());

            accountRepository.save(currentAccount);
        }
    }
}
