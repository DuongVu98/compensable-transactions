package com.cseiu.compensabletransaction.executors;

import com.cseiu.compensabletransaction.commands.BaseCommand;

public interface CommandExecutor<COMMAND extends BaseCommand>{
    void execute(COMMAND command);
}
