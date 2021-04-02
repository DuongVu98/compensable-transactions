package com.cseiu.compensabletransaction.domain.commands;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteAccountCommand extends BaseCommand implements Command{

    @Builder
    public DeleteAccountCommand(String aggregateId) {
        super(aggregateId);
    }
}
