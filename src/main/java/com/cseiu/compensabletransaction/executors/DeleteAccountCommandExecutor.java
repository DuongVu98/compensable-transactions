package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.DeleteAccountCommand;
import com.cseiu.compensabletransaction.compensable.Compensable;
import com.cseiu.compensabletransaction.events.AccountDeletedEvent;
import com.cseiu.compensabletransaction.models.Account;
import com.cseiu.compensabletransaction.repositories.AccountRepository;
import lombok.Builder;

import java.util.Optional;

@Builder
public class DeleteAccountCommandExecutor implements CommandExecutor<DeleteAccountCommand>, Compensable<AccountDeletedEvent> {
    private final AccountRepository accountRepository;

    @Override
    public void execute(DeleteAccountCommand command) {
        Optional<Account> currentAccountOptional = accountRepository.findById(command.getAggregateId());
        if (currentAccountOptional.isPresent()) {
            accountRepository.deleteById(command.getAggregateId());
        }
    }

    @Override
    public void reverse(AccountDeletedEvent event) {

    }
}
