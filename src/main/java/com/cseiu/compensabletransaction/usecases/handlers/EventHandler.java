package com.cseiu.compensabletransaction.usecases.handlers;


import com.cseiu.compensabletransaction.domain.events.DomainEvent;
import io.reactivex.functions.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "[EventHandler]")
public class EventHandler implements Consumer<DomainEvent> {

    @Override
    public void accept(DomainEvent domainEvent) throws Exception {
        log.info("log event");
    }
}
