package com.pavlenko.jarvel.game.event.mapping.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EventCallbackEntity {
	public CallbackKey callbackKey();

	public enum CallbackKey {
		DEFAULT, EXPLORE, FIGHT, MENU;
	}
}
