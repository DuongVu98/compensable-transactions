package com.cseiu.compensabletransaction.usecases.executors;

import com.cseiu.compensabletransaction.domain.commands.ChangePasswordCommand;
import com.cseiu.compensabletransaction.domain.commands.Command;
import com.cseiu.compensabletransaction.domain.models.Account;
import com.cseiu.compensabletransaction.domain.repositories.AccountRepository;
import lombok.Builder;

import javax.transaction.Transactional;
import java.util.Optional;

public class ChangePasswordCommandExecutor extends AbstractCommandExecutor {
    private final AccountRepository accountRepository;

    @Builder
    public ChangePasswordCommandExecutor(Command command, AccountRepository accountRepository) {
        super(command);
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void execute() {
        ChangePasswordCommand changePasswordCommand = (ChangePasswordCommand) getCommandDetail();
        Optional<Account> currentAccountOptional = accountRepository.findById(changePasswordCommand.getAggregateId());
        if (currentAccountOptional.isPresent()) {
            Account currentAccount = currentAccountOptional.get();

            if (changePasswordCommand.getCurrentPassword().equals(currentAccount.getPassword())) {
                currentAccount.setPassword(changePasswordCommand.getNewPassword());
                accountRepository.save(currentAccount);
            }
        }
    }
}
