package com.pavlenko.jarvel.utils;

public final class StringUtils {
	private static final String ABILITY_RATE = "[1-4]";
	private static final String DIGIT = "[0-9]+";
	private static final String COORDINATES = "^[0-9](,[0-9])*$";

	private StringUtils() {
	}

	public static boolean matchesAbilityRate(String text) {
		return text.matches(ABILITY_RATE);
	}

	public static boolean matchesDigit(String text) {
		return text.matches(DIGIT);
	}

	public static boolean validateInput(String text) {
		return !text.isEmpty() && text.length() < 100;
	}

	public static boolean matchesCoordinates(String text) {
		return text.matches(COORDINATES);
	}
}
