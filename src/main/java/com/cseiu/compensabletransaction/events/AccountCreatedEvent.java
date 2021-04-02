package com.cseiu.compensabletransaction.events;

import com.cseiu.compensabletransaction.commands.BaseCommand;
import com.cseiu.compensabletransaction.commands.CreateAccountCommand;
import lombok.Getter;

@Getter
public class AccountCreatedEvent extends DomainEvent<CreateAccountCommand> {

    public AccountCreatedEvent(CreateAccountCommand command) {
        super(command);
    }

    public String getUsername() {
        return this.command.getUsername();
    }

    public String getPassword(){
        return this.command.getPassword();
    }
}
