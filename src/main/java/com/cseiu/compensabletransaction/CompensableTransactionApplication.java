package com.cseiu.compensabletransaction;

import com.cseiu.compensabletransaction.handlers.AnotherEventHandler;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompensableTransactionApplication implements CommandLineRunner {

	@Autowired
	private EventBus eventBus;

	@Autowired
	private AnotherEventHandler anotherEventHandler;

	public static void main(String[] args) {
		SpringApplication.run(CompensableTransactionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		eventBus.register(anotherEventHandler);
	}
}
