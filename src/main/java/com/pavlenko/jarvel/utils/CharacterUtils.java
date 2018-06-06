package com.pavlenko.jarvel.utils;

import java.util.LinkedList;
import java.util.List;

import com.pavlenko.jarvel.game.character.impl.MapCharacter;
import com.pavlenko.jarvel.game.character.impl.SuperPower;
import com.pavlenko.jarvel.game.character.impl.enums.NamedCharacter;
import com.pavlenko.jarvel.game.character.impl.enums.Race;

/**
 * {@code CharacterUtils} class has special utility nature
 * 
 * handle specific cases
 * 
 * @author Rostislav P.A.
 */
public final class CharacterUtils {
	private CharacterUtils() {
	}

	/**
	 * Causes random generation of MapCharacter instances based on user
	 * character level, predefined quantity, and Race belonging
	 * 
	 * @param userCLevel
	 * @param size
	 * @param races
	 * @return List<MapCharacter>
	 */
	public static List<MapCharacter> createRandomMapCharactersByRace(int userCLevel, int size, Race... races) {
		final List<MapCharacter> characters = new LinkedList<>();

		for (int i = 0; i < size; i++)
			characters.add(createRandomMapCharachter(CharacterFunctions.calculateRandomValueWithRange(userCLevel, 2),
					NamedCharacter.randomOf(races)));

		return characters;
	}

	/**
	 * Causes explicit creation of MapCharacter instance with defined
	 * level,race,gender,super power, etc
	 * 
	 * @param userCLevel
	 * @param size
	 * @param races
	 * @return List<MapCharacter>
	 */
	private static MapCharacter createRandomMapCharachter(int rLevel, NamedCharacter nCharacter) {
		final MapCharacter mapCharacter = MapCharacter.builder().name(nCharacter.getName()).race(nCharacter.getRace())
				.gender(nCharacter.getGender()).level(1).health(100)
				.superPower(new SuperPower(nCharacter.getSuperPowerType())).build();
		mapCharacter.defaultInitialization();
		mapCharacter.levelsUp(rLevel);
		return mapCharacter;
	}
}
