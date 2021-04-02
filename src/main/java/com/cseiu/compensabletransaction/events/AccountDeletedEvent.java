package com.cseiu.compensabletransaction.events;

import com.cseiu.compensabletransaction.commands.DeleteAccountCommand;
import lombok.Getter;

@Getter
public class AccountDeletedEvent extends DomainEvent<DeleteAccountCommand> {
    public AccountDeletedEvent(DeleteAccountCommand command) {
        super(command);
    }
}
