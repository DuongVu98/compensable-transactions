package com.cseiu.compensabletransaction.usecases.factories;

import com.cseiu.compensabletransaction.usecases.bus.DomainEventBus;
import com.cseiu.compensabletransaction.usecases.decorators.AggregateBackupDecorator;
import com.cseiu.compensabletransaction.usecases.decorators.EventEmitterDecorator;
import com.cseiu.compensabletransaction.usecases.executors.CommandExecutor;
import com.cseiu.compensabletransaction.domain.repositories.AccountRepository;
import com.cseiu.compensabletransaction.usecases.services.AggregateBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommandExecutorDecoratorFactory {

    public enum DecoratorType {
        EVENT_EMITTER,
        AGGREGATE_BACKUP
    }
    private final AccountRepository accountRepository;
    private final AggregateBackupService aggregateBackupService;
    private final DomainEventBus domainEventBus;

    @Autowired
    public CommandExecutorDecoratorFactory(AccountRepository accountRepository, AggregateBackupService aggregateBackupService, @Qualifier("reactive-event-bus") DomainEventBus domainEventBus) {
        this.accountRepository = accountRepository;
        this.aggregateBackupService = aggregateBackupService;
        this.domainEventBus = domainEventBus;
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
                .eventBus(this.domainEventBus)
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
