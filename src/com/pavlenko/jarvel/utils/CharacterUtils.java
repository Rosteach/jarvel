package com.pavlenko.jarvel.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.pavlenko.jarvel.game.character.GameCharacter;
import com.pavlenko.jarvel.game.character.Race;
import com.pavlenko.jarvel.game.character.impl.UserCharacter;
import com.pavlenko.jarvel.game.character.inventory.InventoryItem;
import com.pavlenko.jarvel.game.character.inventory.ItemType;
import com.pavlenko.jarvel.game.character.inventory.item.Elixir;

public final class CharacterUtils {
	private CharacterUtils() {
	}

	// TODO implement creation of character list based on mapsize
	public static List<GameCharacter> createMapCharachters(final UserCharacter character, int mapSize) {
		return null;
	}

	public static int calculateSuperPowerValue(final UserCharacter character) {
		final Race race = character.getRace();
		int coefficient = character.getHealth() / 10;
		switch (race) {
		case ELF:
			return race.getBasicAgility() * coefficient;
		case DWARF:
			return race.getBasicMindset() * coefficient;
		default:
			return race.getBasicStrength() * coefficient;
		}
	}

	public static List<InventoryItem> createDefaultInventoryItems(final Map<String, String> map) {
		if (map.isEmpty())
			return Collections.emptyList();

		return map.entrySet().stream().map(entry -> createInventoryItem(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}

	// TODO refactor to check wether the key,value pair is valid or no
	private static InventoryItem createInventoryItem(final String key, final String value) {
		return new Elixir(ItemType.of(key), Integer.valueOf(value));
	}

	public static GameCharacter createRandomCharachter() {
		// TODO Auto-generated method stub
		return null;
	}
}
