package com.cseiu.compensabletransaction.usecases.decorators;

import com.cseiu.compensabletransaction.usecases.executors.CommandExecutor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class CommandExecutorDecorator implements CommandExecutor {
    protected CommandExecutor commandExecutor;
}
