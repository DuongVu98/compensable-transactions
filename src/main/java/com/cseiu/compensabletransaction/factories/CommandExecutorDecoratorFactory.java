package com.cseiu.compensabletransaction.factories;

import com.cseiu.compensabletransaction.decorators.AggregateBackupDecorator;
import com.cseiu.compensabletransaction.decorators.EventEmitterDecorator;
import com.cseiu.compensabletransaction.executors.CommandExecutor;
import com.cseiu.compensabletransaction.repositories.AccountRepository;
import com.cseiu.compensabletransaction.services.AggregateBackupService;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandExecutorDecoratorFactory {

    public enum DecoratorType {
        EVENT_EMITTER,
        AGGREGATE_BACKUP
    }
    private final AccountRepository accountRepository;
    private final AggregateBackupService aggregateBackupService;
    private final EventBus eventBus;

    @Autowired
    public CommandExecutorDecoratorFactory(AccountRepository accountRepository, AggregateBackupService aggregateBackupService, EventBus eventBus) {
        this.accountRepository = accountRepository;
        this.aggregateBackupService = aggregateBackupService;
        this.eventBus = eventBus;
    }

    public CommandExecutor provide(CommandExecutor commandExecutor, DecoratorType type) {
        switch (type) {
            case EVENT_EMITTER:
                return provideEventEmitter(commandExecutor);
            case AGGREGATE_BACKUP:
                return provideAggregateBackup(commandExecutor);
            default:
                return commandExecutor;
        }
    }

    private CommandExecutor provideEventEmitter(CommandExecutor commandExecutor) {
        return EventEmitterDecorator.builder()
                .commandExecutor(commandExecutor)
                .eventBus(this.eventBus)
                .build();
    }

    private CommandExecutor provideAggregateBackup(CommandExecutor commandExecutor) {
        return AggregateBackupDecorator.builder()
                .commandExecutor(commandExecutor)
                .accountRepository(this.accountRepository)
                .aggregateBackupService(this.aggregateBackupService)
                .build();
    }
}
