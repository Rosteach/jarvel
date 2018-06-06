package com.pavlenko.jarvel;

import java.util.Scanner;

import com.pavlenko.jarvel.game.GameProcessor;
import com.pavlenko.jarvel.game.event.EventProcessor;
import com.pavlenko.jarvel.game.event.callback.impl.SimpleEventCallbackFactory;
import com.pavlenko.jarvel.game.event.impl.EventMapBasedExecutor;
import com.pavlenko.jarvel.game.event.impl.SimpleEventProcessor;
import com.pavlenko.jarvel.game.event.mapping.EventCallbackMapper;

/**
 * @author Rostyslav P.A.
 */
public class JarvelApplication {

	/**
	 * @param main
	 *            method to initialize all game components and startup the game
	 */
	public static void main(String[] args) {
		final EventCallbackMapper eventCallbackMapper = new EventCallbackMapper(new SimpleEventCallbackFactory());
		final EventProcessor eventProcessor = new SimpleEventProcessor(
				new EventMapBasedExecutor(eventCallbackMapper.createEventMap()));
		final GameProcessor gameProcessor = GameProcessor.getInstance();
		gameProcessor.setEventProcessor(eventProcessor);
		gameProcessor.setScanner(new Scanner(System.in));
		gameProcessor.process();
	}

}
