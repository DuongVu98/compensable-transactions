package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.Command;

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
