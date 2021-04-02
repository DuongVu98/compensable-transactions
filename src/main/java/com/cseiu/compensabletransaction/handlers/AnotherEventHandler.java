package com.cseiu.compensabletransaction.handlers;

import com.cseiu.compensabletransaction.events.AccountCreatedEvent;
import com.cseiu.compensabletransaction.events.AccountDeletedEvent;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "[AnotherEventHandler]")
public class AnotherEventHandler {

    @Subscribe
    public void on(AccountCreatedEvent event){
        log.info("log event from another handler --> {}", event.toString());
    }

    @Subscribe
    public void on(AccountDeletedEvent event){
        log.info("log event from another handler --> {}", event.toString());
    }
}
