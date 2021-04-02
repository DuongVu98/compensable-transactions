package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.BaseCommand;
import com.cseiu.compensabletransaction.commands.Command;

public interface CommandExecutor{
    void execute(Command command);
}
