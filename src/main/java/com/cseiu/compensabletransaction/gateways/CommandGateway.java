package com.cseiu.compensabletransaction.gateways;

import com.cseiu.compensabletransaction.commands.ChangePasswordCommand;
import com.cseiu.compensabletransaction.commands.CreateAccountCommand;
import com.cseiu.compensabletransaction.commands.DeleteAccountCommand;
import com.cseiu.compensabletransaction.commands.UpdateAccountCommand;
import com.cseiu.compensabletransaction.executors.CommandExecutor;
import com.cseiu.compensabletransaction.factories.CommandExecutorDecoratorFactory;
import com.cseiu.compensabletransaction.factories.CommandExecutorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandGateway {

    private final CommandExecutorFactory commandExecutorFactory;
    private final CommandExecutorDecoratorFactory commandExecutorDecoratorFactory;

    @Autowired
    public CommandGateway(CommandExecutorFactory commandExecutorFactory, CommandExecutorDecoratorFactory commandExecutorDecoratorFactory) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.commandExecutorDecoratorFactory = commandExecutorDecoratorFactory;
    }

    public void handle(CreateAccountCommand command) throws Exception {
        CommandExecutor commandExecutor = commandExecutorFactory.provide(command);
        CommandExecutor eventEmitterCommandExecutor = commandExecutorDecoratorFactory.provide(commandExecutor, CommandExecutorDecoratorFactory.DecoratorType.EVENT_EMITTER);

        eventEmitterCommandExecutor.execute();
    }

    public void handle(UpdateAccountCommand command) throws Exception {
        CommandExecutor commandExecutor = commandExecutorFactory.provide(command);

        commandExecutor.execute();
    }

    public void handle(ChangePasswordCommand command) throws Exception {
        CommandExecutor commandExecutor = commandExecutorFactory.provide(command);

        commandExecutor.execute();
    }

    public void handle(DeleteAccountCommand command) throws Exception {
        CommandExecutor commandExecutor = commandExecutorFactory.provide(command);
        CommandExecutor eventEmitterCommandExecutor = commandExecutorDecoratorFactory.provide(commandExecutor, CommandExecutorDecoratorFactory.DecoratorType.EVENT_EMITTER);
        CommandExecutor aggregateBackupCommandExecutor = commandExecutorDecoratorFactory.provide(eventEmitterCommandExecutor, CommandExecutorDecoratorFactory.DecoratorType.AGGREGATE_BACKUP);

        aggregateBackupCommandExecutor.execute();
    }
}
