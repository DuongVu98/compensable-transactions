package com.cseiu.compensabletransaction.decorators;

import com.cseiu.compensabletransaction.commands.BaseCommand;
import com.cseiu.compensabletransaction.executors.CommandExecutor;

public class EventEmitterDecorator extends CommandExecutorDecorator{
    public EventEmitterDecorator(CommandExecutor<BaseCommand> commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public void execute(BaseCommand command) {
        commandExecutor.execute(command);

    }
}
