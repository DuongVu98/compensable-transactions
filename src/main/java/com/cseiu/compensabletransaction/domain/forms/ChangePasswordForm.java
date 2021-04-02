package com.cseiu.compensabletransaction.domain.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangePasswordForm {
    private String currentPassword;
    private String newPassword;
}
