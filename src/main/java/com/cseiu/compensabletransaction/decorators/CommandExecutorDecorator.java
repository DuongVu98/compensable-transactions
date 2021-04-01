package com.cseiu.compensabletransaction.decorators;

import com.cseiu.compensabletransaction.commands.BaseCommand;
import com.cseiu.compensabletransaction.executors.CommandExecutor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class CommandExecutorDecorator implements CommandExecutor<BaseCommand> {
    protected CommandExecutor<BaseCommand> commandExecutor;
}
