package com.cseiu.compensabletransaction.usecases.bus;

import com.cseiu.compensabletransaction.domain.events.DomainEvent;
import com.google.common.eventbus.EventBus;
import org.springframework.stereotype.Component;

@Component(value = "google-event-bus")
public class GoogleDomainEventBus implements DomainEventBus{
    private final EventBus eventBus = new EventBus();


    @Override
    public void send(DomainEvent event) {
        eventBus.post(event);
    }

    @Override
    public void register(Object o) {
        eventBus.register(o);
    }
}
