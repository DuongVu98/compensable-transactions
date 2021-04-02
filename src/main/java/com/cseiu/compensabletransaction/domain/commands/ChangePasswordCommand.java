package com.cseiu.compensabletransaction.domain.commands;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChangePasswordCommand extends BaseCommand implements Command {
    private String currentPassword;
    private String newPassword;

    @Builder
    public ChangePasswordCommand(String aggregateId, String currentPassword, String newPassword) {
        super(aggregateId);
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
