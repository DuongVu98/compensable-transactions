package com.cseiu.compensabletransaction.commands;

import lombok.*;


@Getter
@AllArgsConstructor
public class BaseCommand {
    protected String aggregateId;
}
