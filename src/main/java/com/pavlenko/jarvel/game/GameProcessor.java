package com.pavlenko.jarvel.game;

import java.util.Scanner;

import com.pavlenko.jarvel.game.event.EventProcessor;
import com.pavlenko.jarvel.game.event.method.EventKey;

/**
 * {@code GameProcessor} is an entry point for processing any user action
 * 
 * designed with Singleton in mind
 * 
 * contains {@link Scanner.class} as a console pipeline
 * 
 * contains {@link EventProcessor} to delegate, process and finally execute user
 * request
 * 
 * @author Rostislav P.A.
 */
public final class GameProcessor {
	private static GameProcessor instance;
	private Scanner scanner;
	private EventProcessor eventProcessor;

	private GameProcessor() {
	}

	public static GameProcessor getInstance() {
		if (instance == null)
			instance = new GameProcessor();
		return instance;
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
	 * entry method to listen for user request and execute event callback
	 */
	public void process() {
		eventProcessor.processEvent(EventKey.DEFAULT_ENTRY);
		while (scanner.hasNext())
			eventProcessor.processEvent(scanner.nextLine());
	}
}
