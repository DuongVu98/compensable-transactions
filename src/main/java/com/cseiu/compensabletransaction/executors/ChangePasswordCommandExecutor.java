package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.ChangePasswordCommand;
import com.cseiu.compensabletransaction.models.Account;
import com.cseiu.compensabletransaction.repositories.AccountRepository;
import lombok.Builder;

import java.util.Optional;

@Builder
public class ChangePasswordCommandExecutor implements CommandExecutor<ChangePasswordCommand>{
    private final AccountRepository accountRepository;

    @Override
    public void execute(ChangePasswordCommand command) {
        Optional<Account> currentAccountOptional = accountRepository.findById(command.getAggregateId());
        if (currentAccountOptional.isPresent()) {
            Account currentAccount = currentAccountOptional.get();

            if (command.getCurrentPassword().equals(currentAccount.getPassword())) {
                currentAccount.setPassword(command.getNewPassword());
                accountRepository.save(currentAccount);
            }
        }
    }
}
