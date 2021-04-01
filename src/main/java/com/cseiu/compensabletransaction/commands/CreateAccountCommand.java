package com.cseiu.compensabletransaction.commands;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateAccountCommand extends BaseCommand {
    private String username;
    private String password;

    @Builder
    public CreateAccountCommand(String aggregateId, String username, String password) {
        super(aggregateId);
        this.username = username;
        this.password = password;
    }
}
