package com.cseiu.compensabletransaction.events;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DomainEvent {
    private String accountId;
}
