package com.cseiu.compensabletransaction.events;

import com.cseiu.compensabletransaction.commands.BaseCommand;
import com.cseiu.compensabletransaction.commands.UpdateAccountCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountUpdatedEvent extends DomainEvent {
    private String username;
    private String email;

    @Builder
    public AccountUpdatedEvent(String accountId, String username, String email) {
        super(accountId);
        this.username = username;
        this.email = email;
    }
}
