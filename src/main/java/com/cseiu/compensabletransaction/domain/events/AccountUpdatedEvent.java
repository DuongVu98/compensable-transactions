package com.cseiu.compensabletransaction.domain.events;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountUpdatedEvent extends DomainEvent {
    private String username;
    private String email;

    public AccountUpdatedEvent(String accountId, String username, String email) {
        super(accountId);
        this.username = username;
        this.email = email;
    }
}
