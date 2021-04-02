package com.cseiu.compensabletransaction.decorators;

import com.cseiu.compensabletransaction.commands.*;
import com.cseiu.compensabletransaction.events.AccountCreatedEvent;
import com.cseiu.compensabletransaction.events.AccountDeletedEvent;
import com.cseiu.compensabletransaction.events.AccountUpdatedEvent;
import com.cseiu.compensabletransaction.events.PasswordChangedEvent;
import com.cseiu.compensabletransaction.executors.AbstractCommandExecutor;
import com.cseiu.compensabletransaction.executors.CommandExecutor;
import com.cseiu.compensabletransaction.models.Account;
import com.google.common.eventbus.EventBus;
import lombok.Builder;

import javax.transaction.Transactional;

public class EventEmitterDecorator extends CommandExecutorDecorator{
    private final EventBus eventBus;

    @Builder
    public EventEmitterDecorator(CommandExecutor commandExecutor, EventBus eventBus) {
        super(commandExecutor);
        this.eventBus = eventBus;
    }

    @Override
    @Transactional
    public void execute() {
        commandExecutor.execute();

        Command command = getCommandDetail();
        emitFromCommand(command);
    }

    @Override
    public Command getCommandDetail() {
        return commandExecutor.getCommandDetail();
    }

    private void emitFromCommand(Command command) {
        if (command instanceof CreateAccountCommand) {
            CreateAccountCommand createAccountCommand = (CreateAccountCommand) command;
            AccountCreatedEvent event = new AccountCreatedEvent(createAccountCommand.getAggregateId(), createAccountCommand.getUsername(), createAccountCommand.getPassword());

            eventBus.post(event);
        } else if (command instanceof UpdateAccountCommand) {
            UpdateAccountCommand updateAccountCommand = (UpdateAccountCommand) command;
            AccountUpdatedEvent event = new AccountUpdatedEvent(updateAccountCommand.getAggregateId(), updateAccountCommand.getUsername(), updateAccountCommand.getEmail());

            eventBus.post(event);
        } else if (command instanceof ChangePasswordCommand) {
            ChangePasswordCommand changePasswordCommand = (ChangePasswordCommand) command;
            PasswordChangedEvent event = new PasswordChangedEvent(changePasswordCommand.getAggregateId(), changePasswordCommand.getCurrentPassword(), changePasswordCommand.getNewPassword());

            eventBus.post(event);
        } else if (command instanceof DeleteAccountCommand) {
            DeleteAccountCommand deleteAccountCommand = (DeleteAccountCommand) command;
            AccountDeletedEvent event = new AccountDeletedEvent(deleteAccountCommand.getAggregateId());

            eventBus.post(event);
        }
    }
}
