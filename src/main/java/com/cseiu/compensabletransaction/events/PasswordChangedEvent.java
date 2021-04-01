package com.cseiu.compensabletransaction.events;

import lombok.Getter;

@Getter
public class PasswordChangedEvent extends DomainEvent {
    private String currentPassword;
    private String newPassword;

    public PasswordChangedEvent(String accountId, String currentPassword, String newPassword) {
        super(accountId);
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
