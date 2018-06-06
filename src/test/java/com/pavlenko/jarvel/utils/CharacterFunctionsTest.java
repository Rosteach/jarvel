package com.pavlenko.jarvel.utils;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.pavlenko.jarvel.game.exception.GameException;

public class CharacterFunctionsTest {

	@Test
	public void testCalculateRandomValueWithRange() {
		int levels = 100;
		int ranges = 10;

		for (int level = 0; level < levels; level++) {
			for (int range = 0; range < ranges; range++) {
				int random = CharacterFunctions.calculateRandomValueWithRange(level, range);
				if (random < 0)
					fail("result value should be positive");

				if (random > level + range || random < level - range)
					fail("result is out of bounds");

			}
		}
	}

	@Test(expected = GameException.class)
	public void testCalculateExperience() {
		int[] healths = { 50, 100, 300, 200, 33, 88, 0, -1 };
		for (int health : healths) {
			int exp = CharacterFunctions.calculateExperience(health);
			int minExp = health / 2;
			int maxExp = health + minExp;
			if (exp < 0)
				fail("result experience should be > 0");

			if (exp < minExp || exp > maxExp)
				fail("result experience is out of bounds");
		}

	}

}
