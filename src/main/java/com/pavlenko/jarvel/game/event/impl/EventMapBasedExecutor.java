package com.pavlenko.jarvel.game.event.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.pavlenko.jarvel.game.GameContext;
import com.pavlenko.jarvel.game.event.EventExecutor;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventMapping;
import com.pavlenko.jarvel.game.event.method.EventKey;
import com.pavlenko.jarvel.game.event.method.EventMethod;
import com.pavlenko.jarvel.game.exception.GameException;

public class EventMapBasedExecutor implements EventExecutor {
	private Map<EventKey, EventMethod> eventHolder;

	public EventMapBasedExecutor(Map<EventKey, EventMethod> eventHolder) {
		this.eventHolder = eventHolder;
	}

	public Map<EventKey, EventMethod> getEventHolder() {
		return eventHolder;
	}

	public void setEventHolder(Map<EventKey, EventMethod> eventHolder) {
		this.eventHolder = eventHolder;
	}

	@Override
	public void execute(final EventKey key, final Object... objects) {
		final EventMethod eventMethod = eventHolder.get(key);
		try {
			final EventMapping annotation = eventMethod.getMethod().getAnnotation(EventMapping.class);
			GameContext.getInstance().setCurrentKey(annotation.key());
			GameContext.getInstance().setFollowupKey(annotation.followupKey());
			eventMethod.getMethod().invoke(eventMethod.getInvoker(), objects);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new GameException("Invalid mapping rules, please checkout your EventMapping methods!", e);
		}
	}
}
