package com.cseiu.compensabletransaction.domain.events;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountDeletedEvent extends DomainEvent {

    public AccountDeletedEvent(String accountId) {
        super(accountId);
    }
}
