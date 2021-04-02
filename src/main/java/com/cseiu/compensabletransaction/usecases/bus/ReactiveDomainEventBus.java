package com.cseiu.compensabletransaction.usecases.bus;


import com.cseiu.compensabletransaction.domain.events.DomainEvent;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import org.springframework.stereotype.Component;

@Component(value = "reactive-event-bus")
public class ReactiveDomainEventBus implements DomainEventBus {
    private final PublishSubject<DomainEvent> bus = PublishSubject.create();

    public void send(DomainEvent event) {
        bus.onNext(event);
    }

    public void register(Object o) {
        bus.subscribe((Consumer<? super DomainEvent>) o);
    }
}
