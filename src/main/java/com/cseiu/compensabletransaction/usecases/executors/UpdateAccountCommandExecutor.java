package com.cseiu.compensabletransaction.usecases.executors;

import com.cseiu.compensabletransaction.domain.commands.Command;
import com.cseiu.compensabletransaction.domain.commands.UpdateAccountCommand;
import com.cseiu.compensabletransaction.domain.models.Account;
import com.cseiu.compensabletransaction.domain.repositories.AccountRepository;
import lombok.Builder;

import javax.transaction.Transactional;
import java.util.Optional;

public class UpdateAccountCommandExecutor extends AbstractCommandExecutor {

    private final AccountRepository accountRepository;

    @Builder
    public UpdateAccountCommandExecutor(Command command, AccountRepository accountRepository) {
        super(command);
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void execute() {
        UpdateAccountCommand updateAccountCommand = (UpdateAccountCommand) getCommandDetail();
        Optional<Account> currentAccountOptional = accountRepository.findById(updateAccountCommand.getAggregateId());
        if (currentAccountOptional.isPresent()) {
            Account currentAccount = currentAccountOptional.get();

            currentAccount.setUsername(updateAccountCommand.getUsername());
            currentAccount.setEmail(updateAccountCommand.getEmail());

            accountRepository.save(currentAccount);
        }
    }
}
