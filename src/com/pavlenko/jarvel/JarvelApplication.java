package com.pavlenko.jarvel;

import java.util.Scanner;

import com.pavlenko.jarvel.game.GameProcessor;
import com.pavlenko.jarvel.game.event.EventProcessor;
import com.pavlenko.jarvel.game.event.callback.impl.SimpleEventCallbackFactory;
import com.pavlenko.jarvel.game.event.impl.EventMapBasedExecutor;
import com.pavlenko.jarvel.game.event.impl.SimpleEventProcessor;
import com.pavlenko.jarvel.game.event.mapping.EventMapper;
import com.pavlenko.jarvel.game.impl.SimpleGameContext;
import com.pavlenko.jarvel.game.impl.SimpleGameProcessor;

/**
 * @author Rostyslav P.A.
 */
public class JarvelApplication {

	/**
	 * @param main
	 *            method to initialize all game components and startup the game
	 */
	public static void main(String[] args) {
		final SimpleGameContext simpleGameContext = new SimpleGameContext();
		final EventMapper eventMapper = new EventMapper(new SimpleEventCallbackFactory(), simpleGameContext);
		final EventProcessor eventProcessor = new SimpleEventProcessor(
				new EventMapBasedExecutor(eventMapper.createEventMap()), simpleGameContext);
		final GameProcessor gameProcessor = new SimpleGameProcessor(new Scanner(System.in), eventProcessor);
		gameProcessor.process();
	}

}
