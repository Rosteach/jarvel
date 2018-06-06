package com.pavlenko.jarvel.game.event.impl;

import com.pavlenko.jarvel.game.GameContext;
import com.pavlenko.jarvel.game.event.EventExecutor;
import com.pavlenko.jarvel.game.event.EventProcessor;
import com.pavlenko.jarvel.game.event.callback.EventCallback;
import com.pavlenko.jarvel.game.event.method.EventKey;
import com.pavlenko.jarvel.game.event.method.Text;
import com.pavlenko.jarvel.utils.StringUtils;

/**
 * @author Rostyslav P.A.
 * 
 *         Processor class for any income event from user, the key goal is to
 *         detect EventKey and delegate it to the execution stage
 * 
 */
public class SimpleEventProcessor implements EventProcessor, EventCallback {
	private EventExecutor eventExecutor;

	public SimpleEventProcessor(EventExecutor eventExecutor) {
		this.eventExecutor = eventExecutor;
	}

	public EventExecutor getEventExecutor() {
		return eventExecutor;
	}

	public void setEventExecutor(EventExecutor eventExecutor) {
		this.eventExecutor = eventExecutor;
	}

	/**
	 * method to process any user event, define user intent based on EventKey
	 * enumeration and execute with EventExecutor implementation
	 */
	@Override
	public void processEvent(final String event) {
		if (StringUtils.validateInput(event)) {
			final EventKey eventKey = EventKey.prepare(event);
			final EventKey followupKey = GameContext.getInstance().getFollowupKey();
			if (eventKey.isDefault() && !followupKey.isDefault()) {
				eventExecutor.execute(followupKey, event);
			} else
				processEvent(eventKey);
		} else
			callback(Text.DEFAULT_INVALID_INPUT);
	}

	/**
	 * method to process EventKey directly in case you know what Event to
	 * execute
	 */
	@Override
	public void processEvent(final EventKey key) {
		eventExecutor.execute(key);
	}
}
