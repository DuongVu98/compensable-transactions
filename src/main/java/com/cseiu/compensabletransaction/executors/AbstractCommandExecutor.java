package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.Command;
import lombok.Getter;

public abstract class AbstractCommandExecutor implements CommandExecutor {

    @Getter
    protected Command command;

    public AbstractCommandExecutor(Command command) {
        this.command = command;
    }
}
