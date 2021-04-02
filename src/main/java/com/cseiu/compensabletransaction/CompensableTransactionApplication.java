package com.cseiu.compensabletransaction;

import com.cseiu.compensabletransaction.usecases.bus.DomainEventBus;
import com.cseiu.compensabletransaction.usecases.handlers.AnotherEventHandler;

import com.cseiu.compensabletransaction.usecases.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompensableTransactionApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("google-event-bus")
	private DomainEventBus domainEventBus1;

	@Autowired
	@Qualifier("reactive-event-bus")
	private DomainEventBus domainEventBus2;

	@Autowired
	private AnotherEventHandler anotherEventHandler;

	@Autowired
	private EventHandler eventHandler;

	public static void main(String[] args) {
		SpringApplication.run(CompensableTransactionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		domainEventBus1.register(anotherEventHandler);
		domainEventBus2.register(eventHandler);
	}
}
