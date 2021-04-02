package com.cseiu.compensabletransaction.domain.commands;

import lombok.*;


@Getter
@AllArgsConstructor
public class BaseCommand {
    protected String aggregateId;
}
