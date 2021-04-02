package com.cseiu.compensabletransaction.decorators;

import com.cseiu.compensabletransaction.executors.AbstractCommandExecutor;
import com.cseiu.compensabletransaction.executors.CommandExecutor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class CommandExecutorDecorator implements CommandExecutor {
    protected CommandExecutor commandExecutor;
}
