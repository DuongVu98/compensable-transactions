package com.cseiu.compensabletransaction.usecases.bus;

import com.cseiu.compensabletransaction.domain.events.DomainEvent;

public interface DomainEventBus {
    void send(DomainEvent event);
    void register(Object o);
}
