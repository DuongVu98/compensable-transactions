package com.cseiu.compensabletransaction.compensable;

import com.cseiu.compensabletransaction.events.DomainEvent;

public interface Compensable<EVENT extends DomainEvent> {
    public void reverse(EVENT event);
}
