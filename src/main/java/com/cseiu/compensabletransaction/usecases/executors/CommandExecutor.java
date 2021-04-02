package com.cseiu.compensabletransaction.usecases.executors;

import com.cseiu.compensabletransaction.domain.commands.Command;

public interface CommandExecutor {
    void execute();
    Command getCommandDetail();
}
