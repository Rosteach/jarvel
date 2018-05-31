package com.pavlenko.jarvel.game;

import java.util.List;

import com.pavlenko.jarvel.game.character.GameCharacter;

public interface GameContext {
	void initializeMap(int size, List<GameCharacter> charachters);

	void initializeUserCharachter(String name);
}
