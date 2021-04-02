package com.cseiu.compensabletransaction.domain.commands;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateAccountCommand extends BaseCommand implements Command {
    private String username;
    private String password;

    @Builder
    public CreateAccountCommand(String aggregateId, String username, String password) {
        super(aggregateId);
        this.username = username;
        this.password = password;
    }
}
