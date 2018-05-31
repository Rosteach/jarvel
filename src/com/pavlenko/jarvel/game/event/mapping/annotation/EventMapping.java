package com.pavlenko.jarvel.game.event.mapping.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.pavlenko.jarvel.game.event.model.EventKey;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface EventMapping {
	public EventKey key();

	public EventKey followupKey() default EventKey.DEFAULT_FALLBACK;
}
