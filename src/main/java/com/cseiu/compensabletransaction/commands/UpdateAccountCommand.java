package com.cseiu.compensabletransaction.commands;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateAccountCommand extends BaseCommand implements Command {
    private String username;
    private String email;

    @Builder
    public UpdateAccountCommand(String aggregateId, String username, String email) {
        super(aggregateId);
        this.username = username;
        this.email = email;
    }
}
