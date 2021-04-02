package com.cseiu.compensabletransaction.factories;

import com.cseiu.compensabletransaction.commands.*;
import com.cseiu.compensabletransaction.executors.*;
import com.cseiu.compensabletransaction.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandExecutorFactory {
    private final AccountRepository accountRepository;

    @Autowired
    public CommandExecutorFactory(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public AbstractCommandExecutor provide(Command command) throws Exception {
        if (command instanceof CreateAccountCommand) {
            return provideCommandExecutor((CreateAccountCommand) command);
        } else if (command instanceof UpdateAccountCommand) {
            return provideCommandExecutor((UpdateAccountCommand) command);
        } else if (command instanceof ChangePasswordCommand) {
            return provideCommandExecutor((ChangePasswordCommand) command);
        } else if (command instanceof DeleteAccountCommand) {
            return provideCommandExecutor((DeleteAccountCommand) command);
        } else {
            throw new Exception();
        }
    }

    private AbstractCommandExecutor provideCommandExecutor(CreateAccountCommand command) {
        return CreateAccountCommandExecutor.builder()
                .command(command)
                .accountRepository(this.accountRepository)
                .build();
    }

    private AbstractCommandExecutor provideCommandExecutor(UpdateAccountCommand command) {
        return UpdateAccountCommandExecutor.builder()
                .command(command)
                .accountRepository(this.accountRepository)
                .build();
    }

    private AbstractCommandExecutor provideCommandExecutor(ChangePasswordCommand command) {
        return ChangePasswordCommandExecutor.builder()
                .command(command)
                .accountRepository(this.accountRepository)
                .build();
    }

    private AbstractCommandExecutor provideCommandExecutor(DeleteAccountCommand command) {
        return DeleteAccountCommandExecutor.builder()
                .command(command)
                .accountRepository(this.accountRepository)
                .build();
    }
}
