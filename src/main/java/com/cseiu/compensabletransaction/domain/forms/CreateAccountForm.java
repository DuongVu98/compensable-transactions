package com.cseiu.compensabletransaction.domain.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAccountForm {
    private String username;
    private String password;
}
