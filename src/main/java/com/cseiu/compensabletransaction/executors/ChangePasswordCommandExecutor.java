package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.ChangePasswordCommand;
import com.cseiu.compensabletransaction.commands.Command;
import com.cseiu.compensabletransaction.models.Account;
import com.cseiu.compensabletransaction.repositories.AccountRepository;
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
