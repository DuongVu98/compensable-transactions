package com.cseiu.compensabletransaction.compensable;

import com.cseiu.compensabletransaction.events.DomainEvent;

public interface Compensable {
    void reverse() throws Exception;
}
