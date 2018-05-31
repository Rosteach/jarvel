package com.pavlenko.jarvel.utils;

import com.pavlenko.jarvel.game.event.mapping.annotation.EventMapping;
import com.pavlenko.jarvel.game.event.model.EventKey;
import com.pavlenko.jarvel.game.event.model.EventMethod;
import com.pavlenko.jarvel.game.impl.SimpleGameContext;

public final class ContextUtils {
	private ContextUtils() {
	}

	public static void updateEventKeys(final SimpleGameContext context, final EventMethod eventMethod) {
		final EventMapping eventMapping = eventMethod.getMethod().getAnnotation(EventMapping.class);
		updateEventKeys(context, eventMapping.key(), eventMapping.followupKey());
	}

	public static void updateEventKeys(final SimpleGameContext context, final EventKey currentKey,
			final EventKey followupKey) {
		context.setCurrentKey(currentKey);
		context.setFollowupKey(followupKey);
	}
}
