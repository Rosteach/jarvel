package com.pavlenko.jarvel.game.event;

import com.pavlenko.jarvel.game.event.method.EventKey;

/**
 * basic abstraction to execute all user requests
 * 
 * @author  Rostyslav P.A.
 *
 */
public interface EventExecutor {
	void execute(EventKey prepare, Object... objects);
}
