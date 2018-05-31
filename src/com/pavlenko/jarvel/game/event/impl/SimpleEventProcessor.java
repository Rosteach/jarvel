package com.pavlenko.jarvel.game.event.impl;

import com.pavlenko.jarvel.game.event.EventExecutor;
import com.pavlenko.jarvel.game.event.EventProcessor;
import com.pavlenko.jarvel.game.event.model.EventKey;
import com.pavlenko.jarvel.game.impl.SimpleGameContext;

/**
 * @author Rostyslav P.A.
 * 
 *         Processor class for any income event from user, the key goal is to
 *         detect EventKey and delegate it to the execution stage
 * 
 */
public class SimpleEventProcessor implements EventProcessor {
	private EventExecutor eventExecutor;
	private SimpleGameContext simpleGameContext;

	public SimpleEventProcessor(EventExecutor eventExecutor, SimpleGameContext simpleGameContext) {
		this.eventExecutor = eventExecutor;
		this.simpleGameContext = simpleGameContext;
	}

	public EventExecutor getEventExecutor() {
		return eventExecutor;
	}

	public void setEventExecutor(EventExecutor eventExecutor) {
		this.eventExecutor = eventExecutor;
	}

	public SimpleGameContext getGameContext() {
		return simpleGameContext;
	}

	public void setGameContext(SimpleGameContext simpleGameContext) {
		this.simpleGameContext = simpleGameContext;
	}

	/**
	 * method to process any user event, define user intent based on EventKey
	 * enumeration and execute with EventExecutor implementation
	 */
	@Override
	public void processEvent(final String event) {
		final EventKey eventKey = EventKey.prepare(event);
		final EventKey followupKey = simpleGameContext.getFollowupKey();
		if (eventKey.isDefault() && !followupKey.isDefault()) {
			eventExecutor.execute(simpleGameContext, followupKey, event);
		} else
			processEvent(eventKey);
	}

	/**
	 * method to process EventKey directly in case you know what Event to
	 * execute
	 */
	@Override
	public void processEvent(final EventKey key) {
		eventExecutor.execute(simpleGameContext, key);
	}
}
