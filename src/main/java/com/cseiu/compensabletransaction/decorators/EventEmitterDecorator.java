package com.cseiu.compensabletransaction.decorators;

import com.cseiu.compensabletransaction.commands.*;
import com.cseiu.compensabletransaction.events.AccountCreatedEvent;
import com.cseiu.compensabletransaction.events.AccountDeletedEvent;
import com.cseiu.compensabletransaction.events.AccountUpdatedEvent;
import com.cseiu.compensabletransaction.events.PasswordChangedEvent;
import com.cseiu.compensabletransaction.executors.CommandExecutor;

public class EventEmitterDecorator extends CommandExecutorDecorator{
    public EventEmitterDecorator(CommandExecutor<BaseCommand> commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public void execute(BaseCommand command) {
        commandExecutor.execute(command);
        emitFromCommand(command);
    }

    private void emitFromCommand(BaseCommand command) {
        if (command instanceof CreateAccountCommand) {
            AccountCreatedEvent event = new AccountCreatedEvent((CreateAccountCommand) command);
        } else if (command instanceof UpdateAccountCommand) {
            AccountUpdatedEvent event = new AccountUpdatedEvent((UpdateAccountCommand) command);
        } else if (command instanceof ChangePasswordCommand) {
            PasswordChangedEvent event = new PasswordChangedEvent((ChangePasswordCommand) command);
        } else if (command instanceof DeleteAccountCommand) {
            AccountDeletedEvent event = new AccountDeletedEvent((DeleteAccountCommand) command);
        }
    }
}
