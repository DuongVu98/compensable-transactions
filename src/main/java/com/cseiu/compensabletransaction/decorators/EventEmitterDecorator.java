package com.cseiu.compensabletransaction.decorators;

import com.cseiu.compensabletransaction.commands.*;
import com.cseiu.compensabletransaction.events.AccountCreatedEvent;
import com.cseiu.compensabletransaction.events.AccountDeletedEvent;
import com.cseiu.compensabletransaction.events.AccountUpdatedEvent;
import com.cseiu.compensabletransaction.events.PasswordChangedEvent;
import com.cseiu.compensabletransaction.executors.CommandExecutor;
import com.google.common.eventbus.EventBus;
import lombok.Builder;

public class EventEmitterDecorator extends CommandExecutorDecorator{
    private final EventBus eventBus;

    @Builder
    public EventEmitterDecorator(CommandExecutor commandExecutor, EventBus eventBus) {
        super(commandExecutor);
        this.eventBus = eventBus;
    }

    @Override
    public void execute() {
        commandExecutor.execute();
//        emitFromCommand();
    }

//    private void emitFromCommand() {
//        if (command instanceof CreateAccountCommand) {
//            AccountCreatedEvent event = new AccountCreatedEvent((CreateAccountCommand) command);
//            eventBus.post(event);
//        } else if (command instanceof UpdateAccountCommand) {
//            AccountUpdatedEvent event = new AccountUpdatedEvent((UpdateAccountCommand) command);
//        } else if (command instanceof ChangePasswordCommand) {
//            PasswordChangedEvent event = new PasswordChangedEvent((ChangePasswordCommand) command);
//        } else if (command instanceof DeleteAccountCommand) {
//            AccountDeletedEvent event = new AccountDeletedEvent((DeleteAccountCommand) command);
//        }
//    }
}
