package com.cseiu.compensabletransaction.usecases.decorators;

import com.cseiu.compensabletransaction.usecases.bus.DomainEventBus;
import com.cseiu.compensabletransaction.domain.commands.*;
import com.cseiu.compensabletransaction.domain.events.AccountCreatedEvent;
import com.cseiu.compensabletransaction.domain.events.AccountDeletedEvent;
import com.cseiu.compensabletransaction.domain.events.AccountUpdatedEvent;
import com.cseiu.compensabletransaction.domain.events.PasswordChangedEvent;
import com.cseiu.compensabletransaction.usecases.executors.CommandExecutor;
import lombok.Builder;

import javax.transaction.Transactional;

public class EventEmitterDecorator extends CommandExecutorDecorator{
    private final DomainEventBus eventBus;

    @Builder
    public EventEmitterDecorator(CommandExecutor commandExecutor, DomainEventBus eventBus) {
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

            eventBus.send(event);
        } else if (command instanceof UpdateAccountCommand) {
            UpdateAccountCommand updateAccountCommand = (UpdateAccountCommand) command;
            AccountUpdatedEvent event = new AccountUpdatedEvent(updateAccountCommand.getAggregateId(), updateAccountCommand.getUsername(), updateAccountCommand.getEmail());

            eventBus.send(event);
        } else if (command instanceof ChangePasswordCommand) {
            ChangePasswordCommand changePasswordCommand = (ChangePasswordCommand) command;
            PasswordChangedEvent event = new PasswordChangedEvent(changePasswordCommand.getAggregateId(), changePasswordCommand.getCurrentPassword(), changePasswordCommand.getNewPassword());

            eventBus.send(event);
        } else if (command instanceof DeleteAccountCommand) {
            DeleteAccountCommand deleteAccountCommand = (DeleteAccountCommand) command;
            AccountDeletedEvent event = new AccountDeletedEvent(deleteAccountCommand.getAggregateId());

            eventBus.send(event);
        }
    }
}
