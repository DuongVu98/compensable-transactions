package com.cseiu.compensabletransaction.events;

import com.cseiu.compensabletransaction.commands.ChangePasswordCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PasswordChangedEvent extends DomainEvent {

    private String currentPassword;
    private String newPassword;

    @Builder
    public PasswordChangedEvent(String accountId, String currentPassword, String newPassword) {
        super(accountId);
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
