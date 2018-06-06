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
		return calculateRandomValueBetween(maxLevel, minLevel);
	}

	/**
	 * simply generate random value between max and min values
	 * 
	 * @param max
	 * @param min
	 * @return
	 */
	public static int calculateRandomValueBetween(int max, int min) {
		return RANDOM.nextInt((max - min) + 1) + min;
	}

	/**
	 * Causes calculation of random damage based on attacker strength
	 * 
	 * random range is based on fourth part from input value
	 * 
	 * @param cLevel
	 * @param range
	 * @return
	 */
	public static int calculateDamage(int strength) {
		int range = strength / 4;
		return strength + RANDOM.nextInt(range);
	}

	/**
	 * Causes calculation of random experience that user receive if win the
	 * battle
	 * 
	 * experience would be randomly generated between health/2 and health*1.5
	 * 
	 * @param cLevel
	 * @param range
	 * @return
	 */
	public static int calculateExperience(int health) {
		int minExp = health / 2;
		int maxExp = health + minExp;
		return calculateRandomValueBetween(maxExp, minExp);
	}
}
