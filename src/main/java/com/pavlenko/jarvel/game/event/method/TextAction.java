package com.pavlenko.jarvel.game.event.method;

public enum TextAction {
	RANDOM;

	public boolean matches(String text) {
		return text.toLowerCase().contains(this.name().toLowerCase());
	}
}
