package com.pavlenko.jarvel.game.map.impl;

import com.pavlenko.jarvel.game.character.GameCharacter;
import com.pavlenko.jarvel.game.map.GameMapItem;

public class ArrayMapItem implements GameMapItem {
	private GameCharacter gameCharacter;

	public ArrayMapItem(GameCharacter gameCharacter) {
		this.gameCharacter = gameCharacter;
	}

	public GameCharacter getGameCharacter() {
		return gameCharacter;
	}

	public void setGameCharacter(GameCharacter gameCharacter) {
		this.gameCharacter = gameCharacter;
	}
}
