package com.cseiu.compensabletransaction.usecases.executors;

import com.cseiu.compensabletransaction.domain.commands.Command;

public abstract class AbstractCommandExecutor implements CommandExecutor {

    private Command command;

    public AbstractCommandExecutor(Command command) {
        this.command = command;
    }

    @Override
    public Command getCommandDetail() {
        return this.command;
    }
}
