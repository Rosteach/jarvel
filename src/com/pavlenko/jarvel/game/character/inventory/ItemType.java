package com.pavlenko.jarvel.game.character.inventory;

import java.util.Arrays;

public enum ItemType {
	HEALTH, STRENGTH, AGILITY, MINDSET;

	public static ItemType of(String key) {
		return Arrays.stream(values()).filter(v -> v.name().equalsIgnoreCase(key)).findFirst().orElse(HEALTH);
	}
}
