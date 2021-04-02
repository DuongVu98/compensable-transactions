package com.cseiu.compensabletransaction.events;

import com.cseiu.compensabletransaction.commands.BaseCommand;
import com.cseiu.compensabletransaction.commands.UpdateAccountCommand;
import lombok.Getter;

@Getter
public class AccountUpdatedEvent extends DomainEvent<UpdateAccountCommand> {

    public AccountUpdatedEvent(UpdateAccountCommand command) {
        super(command);
    }

    public String getUsername() {
        return this.command.getUsername();
    }

    public String getEmail(){
        return this.command.getEmail();
    }
}
