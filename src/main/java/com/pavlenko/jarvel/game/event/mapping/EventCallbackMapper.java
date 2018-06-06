package com.pavlenko.jarvel.game.event.mapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.pavlenko.jarvel.game.event.callback.EventCallbackFactory;
import com.pavlenko.jarvel.game.event.callback.impl.CharacterEventCallback;
import com.pavlenko.jarvel.game.event.callback.impl.DefaultEventCallback;
import com.pavlenko.jarvel.game.event.callback.impl.ExploreEventCallback;
import com.pavlenko.jarvel.game.event.callback.impl.FightEventCallback;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventMapping;
import com.pavlenko.jarvel.game.event.method.EventKey;
import com.pavlenko.jarvel.game.event.method.EventMethod;
import com.pavlenko.jarvel.utils.Pair;

/**
 * {@code EventCallbackMapper}
 * 
 * @author Admin
 */
public class EventCallbackMapper {
	private EventCallbackFactory eventCallbackFactory;

	public EventCallbackMapper(EventCallbackFactory eventCallbackFactory) {
		this.eventCallbackFactory = eventCallbackFactory;
	}

	public EventCallbackFactory getEventCallbackFactory() {
		return eventCallbackFactory;
	}

	public void setEventCallbackFactory(EventCallbackFactory eventCallbackFactory) {
		this.eventCallbackFactory = eventCallbackFactory;
	}

	/**
	 * entry method to create a map with all event methods
	 * 
	 * @return
	 */
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

	/**
	 * method to find all methods that take a part in mapping
	 * 
	 * @param eventClass
	 * @return
	 */

	private Method[] prepareEventMethods(Class<?> eventClass) {
		final Method[] methods = eventClass.getDeclaredMethods();
		return methods.length == 0 ? methods
				: Arrays.stream(methods).filter(m -> m.isAnnotationPresent(EventMapping.class))
						.toArray(length -> new Method[length]);
	}

	/**
	 * method to create Set of classes and map with instances that take a part
	 * in mapping
	 * 
	 * @param eventClass
	 * @return
	 */
	private Pair<Map<String, Object>, Set<Class<?>>> prepareEventPair() {
		final Set<Class<?>> eventClasses = new HashSet<>(Arrays.asList(DefaultEventCallback.class,
				ExploreEventCallback.class, CharacterEventCallback.class, FightEventCallback.class)).stream()
						.filter(c -> c.isAnnotationPresent(EventCallbackEntity.class)).collect(Collectors.toSet());
		final Map<String, Object> eventObjects = eventClasses.stream()
				.collect(Collectors.toMap(Class::getName, eventCallbackFactory::createEventCallback));

		return new Pair<>(eventObjects, eventClasses);
	}
}
