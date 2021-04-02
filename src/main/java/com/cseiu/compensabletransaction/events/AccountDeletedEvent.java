package com.cseiu.compensabletransaction.events;

import com.cseiu.compensabletransaction.commands.DeleteAccountCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountDeletedEvent extends DomainEvent {

    @Builder
    public AccountDeletedEvent(String accountId) {
        super(accountId);
    }
}
