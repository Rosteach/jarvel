package com.pavlenko.jarvel.game.event.callback.impl;

import java.lang.reflect.InvocationTargetException;

import com.pavlenko.jarvel.game.event.callback.EventCallbackFactory;
import com.pavlenko.jarvel.game.exception.GameException;

public class SimpleEventCallbackFactory implements EventCallbackFactory {

	@Override
	public Object createEventCallback(Class<?> klass) {
		try {
			return klass.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new GameException("Please check your EventCallback entity configuration!", e);
		}
	}

}
