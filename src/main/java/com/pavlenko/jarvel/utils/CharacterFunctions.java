package com.pavlenko.jarvel.utils;

import java.util.Random;

public final class CharacterFunctions {
	private static final Random RANDOM = new Random();

	private CharacterFunctions() {
	}

	/**
	 * Causes calculation of random level with range
	 * 
	 * @param cLevel
	 * @param range
	 * @return
	 */
	public static int calculateRandomValueWithRange(int cLevel, int range) {
		int minLevel = cLevel < 3 ? cLevel : cLevel - range;
		int maxLevel = cLevel + range;
		return RANDOM.nextInt((maxLevel - minLevel) + 1) + minLevel;
	}

	/**
	 * Causes calculation of random damage based on attacker strength
	 * 
	 * random range is calculated based on fourth part from input value
	 * 
	 * @param cLevel
	 * @param range
	 * @return
	 */
	public static int calculateDamage(int strength) {
		int range = strength / 4;
		return strength + RANDOM.nextInt(range);
	}
}
