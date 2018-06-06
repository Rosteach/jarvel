package com.pavlenko.jarvel.game.event.callback;

/**
 * basic interface to create EventCallback implementation, based on Factory
 * pattern
 * 
 * @author Rostyslav P.A.
 *
 */
public interface EventCallbackFactory {
	Object createEventCallback(Class<?> klass);
}
