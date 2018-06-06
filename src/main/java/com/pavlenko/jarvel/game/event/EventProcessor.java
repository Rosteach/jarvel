package com.pavlenko.jarvel.game.event;

import com.pavlenko.jarvel.game.event.method.EventKey;

/**
 * basic abstraction to process all user requests
 * 
 * @author Rostyslav P.A.
 *
 */
public interface EventProcessor {
	void processEvent(String text);

	void processEvent(EventKey key);
}
