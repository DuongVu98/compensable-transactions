package com.cseiu.compensabletransaction.domain.events;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PasswordChangedEvent extends DomainEvent {

    private String currentPassword;
    private String newPassword;

    public PasswordChangedEvent(String accountId, String currentPassword, String newPassword) {
        super(accountId);
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
