package com.cseiu.compensabletransaction.commands;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChangePasswordCommand extends BaseCommand {
    private String currentPassword;
    private String newPassword;

    @Builder
    public ChangePasswordCommand(String aggregateId, String currentPassword, String newPassword) {
        super(aggregateId);
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
