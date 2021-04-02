package com.cseiu.compensabletransaction.controllers;

import com.cseiu.compensabletransaction.commands.ChangePasswordCommand;
import com.cseiu.compensabletransaction.commands.CreateAccountCommand;
import com.cseiu.compensabletransaction.commands.DeleteAccountCommand;
import com.cseiu.compensabletransaction.commands.UpdateAccountCommand;
import com.cseiu.compensabletransaction.forms.ChangePasswordForm;
import com.cseiu.compensabletransaction.forms.CreateAccountForm;
import com.cseiu.compensabletransaction.forms.UpdateAccountForm;
import com.cseiu.compensabletransaction.gateways.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
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
