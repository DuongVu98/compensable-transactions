package com.cseiu.compensabletransaction.events;

import com.cseiu.compensabletransaction.commands.ChangePasswordCommand;
import lombok.Getter;

@Getter
public class PasswordChangedEvent extends DomainEvent<ChangePasswordCommand> {

    public PasswordChangedEvent(ChangePasswordCommand command) {
        super(command);
    }

    public String getCurrentPassword() {
        return this.command.getCurrentPassword();
    }

    public String getNewPassword() {
        return this.command.getNewPassword();
    }
}
