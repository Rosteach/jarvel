package com.pavlenko.jarvel.game.map;

import java.util.List;

import com.pavlenko.jarvel.game.character.GameCharacter;

public interface GameMap {
	void fillWithCharacters(List<GameCharacter> characters);

	GameMapItem navigate(int x, int y);

	GameMapItem findRandom();

	boolean isEmpty();
}
