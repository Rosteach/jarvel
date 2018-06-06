package com.pavlenko.jarvel.game.exception;

import java.text.MessageFormat;

/**
 * {@code GameException} is the superclass of all game custom exceptions
 * 
 * @author Rostyslav P.A.
 *
 */
public class GameException extends RuntimeException {

	private static final long serialVersionUID = -1961769604331935900L;

	public GameException(String message, Object... objs) {
		super(MessageFormat.format(message, objs));
	}
}
