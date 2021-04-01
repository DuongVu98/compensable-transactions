package com.cseiu.compensabletransaction.commands;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BaseCommand {
    private String aggregateId;
}
