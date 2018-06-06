package com.pavlenko.jarvel.game.character;

import java.util.Arrays;
import java.util.Optional;

public enum SuperPowerType {
	HEALING, ARMOR, DAMAGE;

	public static Optional<SuperPowerType> optionalOf(String text) {
		return Arrays.stream(values()).filter(v -> text.toLowerCase().contains(v.name().toLowerCase())).findFirst();
	}
}
