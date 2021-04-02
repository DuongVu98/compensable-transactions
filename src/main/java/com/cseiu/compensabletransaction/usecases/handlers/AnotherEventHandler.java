package com.cseiu.compensabletransaction.usecases.handlers;

import com.cseiu.compensabletransaction.domain.events.AccountCreatedEvent;
import com.cseiu.compensabletransaction.domain.events.AccountDeletedEvent;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "[AnotherEventHandler]")
public class AnotherEventHandler {

    @Subscribe
    public void on(AccountCreatedEvent event){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("log event from another handler --> {}", event.toString());
    }

    @Subscribe
    public void on(AccountDeletedEvent event){
        log.info("log event from another handler --> {}", event.toString());
    }
}
