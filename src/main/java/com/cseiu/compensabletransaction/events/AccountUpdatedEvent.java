package com.cseiu.compensabletransaction.events;

import lombok.Getter;

@Getter
public class AccountUpdatedEvent extends DomainEvent {
    private String username;
    private String email;

    public AccountUpdatedEvent(String accountId, String username, String email) {
        super(accountId);
        this.username = username;
        this.email = email;
    }
}
