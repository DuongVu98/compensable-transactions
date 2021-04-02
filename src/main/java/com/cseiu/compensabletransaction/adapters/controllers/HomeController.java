package com.cseiu.compensabletransaction.adapters.controllers;

import com.cseiu.compensabletransaction.domain.commands.ChangePasswordCommand;
import com.cseiu.compensabletransaction.domain.commands.CreateAccountCommand;
import com.cseiu.compensabletransaction.domain.commands.DeleteAccountCommand;
import com.cseiu.compensabletransaction.domain.commands.UpdateAccountCommand;
import com.cseiu.compensabletransaction.domain.forms.ChangePasswordForm;
import com.cseiu.compensabletransaction.domain.forms.CreateAccountForm;
import com.cseiu.compensabletransaction.domain.forms.UpdateAccountForm;
import com.cseiu.compensabletransaction.adapters.gateways.CommandGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
@Slf4j(topic = "[HomeController]")
public class HomeController {

    private final CommandGateway commandGateway;

    @Autowired
    public HomeController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @GetMapping(value = "/")
    public String getHome(){
        return "Welcome";
    }

    @PostMapping(value = "/create-account")
    public void createAccount(@RequestBody CreateAccountForm form) throws Exception {
        CreateAccountCommand command = CreateAccountCommand.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .build();

        commandGateway.handle(command);
        log.info("created");
    }

    @DeleteMapping(value = "/delete-account")
    public void deleteAccount(@RequestParam("accountId") String accountId) throws Exception {
        DeleteAccountCommand command = DeleteAccountCommand.builder()
                .aggregateId(accountId)
                .build();

        commandGateway.handle(command);
    }

    @PutMapping(value = "/update-account")
    public void updateAccount(@RequestParam("accountId") String accountId, @RequestBody UpdateAccountForm form) throws Exception {
        UpdateAccountCommand command = UpdateAccountCommand.builder()
                .aggregateId(accountId)
                .username(form.getUsername())
                .email(form.getEmail())
                .build();

        commandGateway.handle(command);
    }

    @PutMapping(value = "/change-password")
    public void changePassword(@RequestParam("accountId") String accountId, @RequestBody ChangePasswordForm form) throws Exception {
        ChangePasswordCommand command = ChangePasswordCommand.builder()
                .aggregateId(accountId)
                .currentPassword(form.getCurrentPassword())
                .newPassword(form.getNewPassword())
                .build();

        commandGateway.handle(command);
    }
}
