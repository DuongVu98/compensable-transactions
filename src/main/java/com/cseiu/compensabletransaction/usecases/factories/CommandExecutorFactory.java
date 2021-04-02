package com.cseiu.compensabletransaction.usecases.factories;

import com.cseiu.compensabletransaction.domain.commands.*;
import com.cseiu.compensabletransaction.usecases.executors.*;
import com.cseiu.compensabletransaction.domain.repositories.AccountRepository;
import com.cseiu.compensabletransaction.usecases.services.AggregateBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandExecutorFactory {
    private final AccountRepository accountRepository;
    private final AggregateBackupService aggregateBackupService;

    @Autowired
    public CommandExecutorFactory(AccountRepository accountRepository, AggregateBackupService aggregateBackupService) {
        this.accountRepository = accountRepository;
        this.aggregateBackupService = aggregateBackupService;
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
                .aggregateBackupService(this.aggregateBackupService)
                .build();
    }
}
