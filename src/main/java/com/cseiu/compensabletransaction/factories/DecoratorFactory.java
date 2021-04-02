package com.cseiu.compensabletransaction.factories;

import com.cseiu.compensabletransaction.decorators.CommandExecutorDecorator;
import com.cseiu.compensabletransaction.decorators.EventEmitterDecorator;
import com.cseiu.compensabletransaction.executors.AbstractCommandExecutor;
import com.cseiu.compensabletransaction.executors.CommandExecutor;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DecoratorFactory {

    private final EventBus eventBus;

    @Autowired
    public DecoratorFactory(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public CommandExecutorDecorator provide(CommandExecutor commandExecutor) {
        return EventEmitterDecorator.builder()
                .commandExecutor(commandExecutor)
                .eventBus(this.eventBus)
                .build();
    }
}
