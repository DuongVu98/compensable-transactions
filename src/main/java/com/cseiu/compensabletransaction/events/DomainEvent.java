package com.cseiu.compensabletransaction.events;

import com.cseiu.compensabletransaction.commands.BaseCommand;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DomainEvent<COMMAND extends BaseCommand> {
    protected COMMAND command;

    public DomainEvent(COMMAND command) {
        this.command = command;
    }

    public COMMAND getCommand(){
        return this.command;
    }

    public String getAccountId(){
        return this.command.getAggregateId();
    }

}
