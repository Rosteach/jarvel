package com.pavlenko.jarvel.game.event;

import com.pavlenko.jarvel.game.event.model.EventKey;

/**
 * basic abstraction to process all user requests
 * 
 * @author Rostyslav P.A.
 *
 */
public interface EventProcessor {
	void processEvent(String textEvent);

	void processEvent(EventKey key);
}
