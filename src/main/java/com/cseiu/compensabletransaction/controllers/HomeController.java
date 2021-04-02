package com.cseiu.compensabletransaction.controllers;

import com.cseiu.compensabletransaction.commands.CreateAccountCommand;
import com.cseiu.compensabletransaction.executors.AbstractCommandExecutor;
import com.cseiu.compensabletransaction.executors.CommandExecutor;
import com.cseiu.compensabletransaction.factories.CommandExecutorFactory;
import com.cseiu.compensabletransaction.factories.DecoratorFactory;
import com.cseiu.compensabletransaction.forms.CreateUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class HomeController {

    private final CommandExecutorFactory commandExecutorFactory;
    private final DecoratorFactory decoratorFactory;

    @Autowired
    public HomeController(CommandExecutorFactory commandExecutorFactory, DecoratorFactory decoratorFactory) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.decoratorFactory = decoratorFactory;
    }

    @GetMapping(value = "/")
    public String getHome(){
        return "Welcome";
    }

    @PostMapping(value = "/create-account")
    public void createAccount(@RequestBody CreateUserForm form) throws Exception {
        CreateAccountCommand command = CreateAccountCommand.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .build();

        CommandExecutor commandExecutor = commandExecutorFactory.provide(command);
        commandExecutor = decoratorFactory.provide(commandExecutor);
        commandExecutor.execute();
    }
}
