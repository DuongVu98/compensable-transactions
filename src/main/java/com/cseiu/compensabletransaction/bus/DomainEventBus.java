package com.cseiu.compensabletransaction.bus;


import com.cseiu.compensabletransaction.events.DomainEvent;
import com.cseiu.compensabletransaction.handlers.EventHandler;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DomainEventBus {
    private final PublishSubject<DomainEvent> bus = PublishSubject.create();

    @Autowired
    public DomainEventBus(EventHandler eventHandler) {
        bus.subscribe(eventHandler);
    }

    public void send(DomainEvent event) {
        bus.onNext(event);
    }

    public Observable<DomainEvent> toObservable() {
        return bus;
    }
}
