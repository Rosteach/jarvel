package com.pavlenko.jarvel.game.impl;

import java.util.Scanner;

import com.pavlenko.jarvel.game.GameProcessor;
import com.pavlenko.jarvel.game.event.EventProcessor;
import com.pavlenko.jarvel.game.event.model.EventKey;

/**
 * @author Rostyslav P.A.
 * 
 *         Class SimpleGameProcessor is entry point to process all user requests.
 *         Contains pipeline based on Scanner.class to detect user request and
 *         EventExecutor.class to delegate and execute user request
 */
public class SimpleGameProcessor implements GameProcessor{
	private Scanner scanner;
	private EventProcessor eventProcessor;

	public SimpleGameProcessor(Scanner scanner, EventProcessor eventProcessor) {
		this.scanner = scanner;
		this.eventProcessor = eventProcessor;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public EventProcessor getEventProcessor() {
		return eventProcessor;
	}

	public void setEventProcessor(EventProcessor eventProcessor) {
		this.eventProcessor = eventProcessor;
	}

	/**
	 * main method to listen for user request and execute the correct callback
	 */
	public void process() {
		eventProcessor.processEvent(EventKey.DEFAULT_ENTRY);
		while (scanner.hasNext())
			eventProcessor.processEvent(scanner.nextLine());
	}
}
