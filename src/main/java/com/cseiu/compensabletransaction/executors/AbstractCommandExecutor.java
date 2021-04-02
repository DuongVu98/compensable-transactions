package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.BaseCommand;
import com.cseiu.compensabletransaction.commands.Command;

public abstract class AbstractCommandExecutor implements CommandExecutor {

    protected Command command;

    public AbstractCommandExecutor(Command command) {
        this.command = command;
    }

    public abstract void execute();
}
