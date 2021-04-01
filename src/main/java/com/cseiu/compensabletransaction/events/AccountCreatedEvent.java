package com.cseiu.compensabletransaction.events;

import lombok.Getter;

@Getter
public class AccountCreatedEvent extends DomainEvent {
    private String username;
    private String password;

    public AccountCreatedEvent(String accountId, String username, String password) {
        super(accountId);
        this.username = username;
        this.password = password;
    }
}