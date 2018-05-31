package com.pavlenko.jarvel.game.event;

import com.pavlenko.jarvel.game.event.model.EventKey;
import com.pavlenko.jarvel.game.impl.SimpleGameContext;

/**
 * basic abstraction to execute all user requests
 * 
 * @author  Rostyslav P.A.
 *
 */
public interface EventExecutor {
	void execute(SimpleGameContext context, EventKey prepare, Object... objects);
}
