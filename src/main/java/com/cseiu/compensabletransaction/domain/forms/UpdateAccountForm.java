package com.cseiu.compensabletransaction.domain.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateAccountForm {
    private String username;
    private String email;
}
