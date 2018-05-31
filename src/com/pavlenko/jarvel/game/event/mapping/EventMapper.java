package com.pavlenko.jarvel.game.event.mapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.pavlenko.jarvel.game.event.callback.EventCallbackFactory;
import com.pavlenko.jarvel.game.event.callback.impl.DefaultEventCallback;
import com.pavlenko.jarvel.game.event.callback.impl.ExploreEventCallback;
import com.pavlenko.jarvel.game.event.callback.impl.MenuEventCallback;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventMapping;
import com.pavlenko.jarvel.game.event.model.EventKey;
import com.pavlenko.jarvel.game.event.model.EventMethod;
import com.pavlenko.jarvel.game.impl.SimpleGameContext;
import com.pavlenko.jarvel.utils.Pair;

public class EventMapper {
	private EventCallbackFactory eventCallbackFactory;
	private SimpleGameContext simpleGameContext;

	public EventMapper(EventCallbackFactory eventCallbackFactory, SimpleGameContext simpleGameContext) {
		this.eventCallbackFactory = eventCallbackFactory;
		this.simpleGameContext = simpleGameContext;
	}

	public EventCallbackFactory getEventCallbackFactory() {
		return eventCallbackFactory;
	}

	public void setEventCallbackFactory(EventCallbackFactory eventCallbackFactory) {
		this.eventCallbackFactory = eventCallbackFactory;
	}

	public SimpleGameContext getGameContext() {
		return simpleGameContext;
	}

	public void setGameContext(SimpleGameContext simpleGameContext) {
		this.simpleGameContext = simpleGameContext;
	}

	public Map<EventKey, EventMethod> createEventMap() {
		final Map<EventKey, EventMethod> eventMap = new EnumMap<>(EventKey.class);
		final Pair<Map<String, Object>, Set<Class<?>>> eventPair = prepareEventPair();

		for (Class<?> eventClass : eventPair.getSecond()) {
			for (Method eventMethod : prepareEventMethods(eventClass)) {
				eventMap.put(eventMethod.getAnnotation(EventMapping.class).key(),
						new EventMethod(eventPair.getFirst().get(eventClass.getName()), eventMethod));
			}
		}
		return eventMap;
	}

	private Method[] prepareEventMethods(Class<?> eventClass) {
		final Method[] methods = eventClass.getDeclaredMethods();
		return methods.length == 0 ? methods
				: Arrays.stream(methods).filter(m -> m.isAnnotationPresent(EventMapping.class))
						.toArray(length -> new Method[length]);
	}

	private Pair<Map<String, Object>, Set<Class<?>>> prepareEventPair() {
		final Set<Class<?>> eventClasses = new HashSet<>(
				Arrays.asList(DefaultEventCallback.class, ExploreEventCallback.class, MenuEventCallback.class)).stream()
						.filter(c -> c.isAnnotationPresent(EventCallbackEntity.class)).collect(Collectors.toSet());
		final Map<String, Object> eventObjects = eventClasses.stream().collect(
				Collectors.toMap(Class::getName, c -> eventCallbackFactory.createEventCallback(c, simpleGameContext)));

		return new Pair<>(eventObjects, eventClasses);
	}
}
