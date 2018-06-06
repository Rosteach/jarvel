package com.pavlenko.jarvel.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.pavlenko.jarvel.game.character.GameCharacter;
import com.pavlenko.jarvel.game.character.impl.MapCharacter;
import com.pavlenko.jarvel.game.character.impl.SuperPower;
import com.pavlenko.jarvel.game.character.impl.enums.NamedCharacter;

public final class CharacterUtils {
	private static final Random RANDOM = new Random();

	private CharacterUtils() {
	}

	public static List<MapCharacter> createRandomNamedCharachters(final GameCharacter character,
			final List<NamedCharacter> nCharacters, int size) {
		final List<MapCharacter> characters = new LinkedList<>();

		for (int i = 0; i < size; i++) {
			Collections.shuffle(nCharacters);
			characters.add(createRandomNamedCharachter(nCharacters.get(0), calculateRandomLevel(character, RANDOM)));
		}

		return characters;
	}

	private static int calculateRandomLevel(GameCharacter character, Random random) {
		int cLevel = character.getLevel();
		int minLevel = cLevel < 3 ? cLevel : cLevel - 2;
		int maxLevel = cLevel + 2;
		// return new character random level [-2,2] from cLevel
		return random.nextInt((maxLevel - minLevel) + 1) + minLevel;
	}

	private static MapCharacter createRandomNamedCharachter(final NamedCharacter nCharacter, final int rLevel) {
		final MapCharacter mapCharacter = MapCharacter.builder().name(nCharacter.getName()).race(nCharacter.getRace())
				.gender(nCharacter.getGender()).level(1).health(100)
				.superPower(new SuperPower(nCharacter.getSuperPowerType())).build();
		mapCharacter.defaultInitialization();
		mapCharacter.levelsUp(rLevel);
		mapCharacter.setExperience(mapCharacter.getHealth() / 2);
		return mapCharacter;
	}

	public static int calculateDamage(int strength) {
		int rate = strength / 4;
		return strength + RANDOM.nextInt(rate);
	}

	public static void updateSuperPower(MapCharacter character, int damage) {
		int curr = character.getSuperPower().getValue();
		character.getSuperPower().setValue(curr + damage);
	}
}
