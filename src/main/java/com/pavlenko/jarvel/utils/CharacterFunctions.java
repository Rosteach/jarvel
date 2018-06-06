package com.pavlenko.jarvel.utils;

import java.util.Random;

import com.pavlenko.jarvel.game.exception.GameException;

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
	public static int calculateRandomValueWithRange(int value, int range) {
		int diff = value - range;
		int min = diff < 0 ? value : diff;
		int max = value + range;
		return calculateRandomValueBetween(max, min);
	}

	/**
	 * simply generate random value between max and min values
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static int calculateRandomValueBetween(int first, int second) {
		int min = first < second ? first : second;
		int max = first > second ? first : second;
		return RANDOM.nextInt((max - min) + 1) + min;
	}

	/**
	 * Causes calculation of random damage based on attacker strength
	 * 
	 * random range is based on fourth part from input value
	 * 
	 * @param strength
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
	 * @param health
	 * @return
	 */
	public static int calculateExperience(int health) {
		if (health <= 0)
			throw new GameException("Invalid input! Character health should be > 0");

		int minExp = health / 2;
		int maxExp = health + minExp;
		return calculateRandomValueBetween(maxExp, minExp);
	}
}
