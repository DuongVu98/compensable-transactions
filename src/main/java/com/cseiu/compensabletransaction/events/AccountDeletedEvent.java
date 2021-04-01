package com.cseiu.compensabletransaction.events;

import lombok.Getter;

@Getter
public class AccountDeletedEvent extends DomainEvent {
    public AccountDeletedEvent(String accountId) {
        super(accountId);
    }
}
