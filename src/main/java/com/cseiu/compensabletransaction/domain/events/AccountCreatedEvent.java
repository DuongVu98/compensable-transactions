package com.cseiu.compensabletransaction.domain.events;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountCreatedEvent extends DomainEvent {
    private String username;
    private String password;

    public AccountCreatedEvent(String accountId, String username, String password) {
        super(accountId);
        this.username = username;
        this.password = password;
    }

}
