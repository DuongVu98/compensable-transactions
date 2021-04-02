package com.cseiu.compensabletransaction.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DomainEvent {
    private String accountId;
}
