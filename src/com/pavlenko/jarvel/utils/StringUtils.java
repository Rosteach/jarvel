package com.pavlenko.jarvel.utils;

public final class StringUtils {
	private static final String ABILITY_RATE = "[0-4]";
	private static final String DIGIT = "[0-9]+";

	private StringUtils() {
	}

	public static boolean matchesAbilityRate(String text) {
		return text.matches(ABILITY_RATE);
	}

	public static boolean matchesDigit(String text) {
		return text.matches(DIGIT);
	}
}
