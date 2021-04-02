package com.cseiu.compensabletransaction.events;

import com.cseiu.compensabletransaction.commands.BaseCommand;
import com.cseiu.compensabletransaction.commands.CreateAccountCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountCreatedEvent extends DomainEvent {
    private String username;
    private String password;

    @Builder
    public AccountCreatedEvent(String accountId, String username, String password) {
        super(accountId);
        this.username = username;
        this.password = password;
    }

}
