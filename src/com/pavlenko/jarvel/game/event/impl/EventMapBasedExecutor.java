package com.pavlenko.jarvel.game.event.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.pavlenko.jarvel.game.event.EventExecutor;
import com.pavlenko.jarvel.game.event.model.EventKey;
import com.pavlenko.jarvel.game.event.model.EventMethod;
import com.pavlenko.jarvel.game.impl.SimpleGameContext;
import com.pavlenko.jarvel.utils.ContextUtils;

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
	public void execute(final SimpleGameContext context, final EventKey key, final Object... objects) {
		final EventMethod eventMethod = eventHolder.get(key);
		try {
			ContextUtils.updateEventKeys(context, eventMethod);
			eventMethod.getMethod().invoke(eventMethod.getInvoker(), objects);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
