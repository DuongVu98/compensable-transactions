package com.cseiu.compensabletransaction.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class DomainEvent {
    protected String accountId;
}
