package com.pavlenko.jarvel.game.impl;

import java.util.List;

import com.pavlenko.jarvel.game.GameContext;
import com.pavlenko.jarvel.game.character.GameCharacter;
import com.pavlenko.jarvel.game.character.impl.UserCharacter;
import com.pavlenko.jarvel.game.event.model.EventKey;
import com.pavlenko.jarvel.game.map.GameMap;
import com.pavlenko.jarvel.game.map.impl.SimpleArrayMap;

/**
 * @author Rostyslav P.A.
 * 
 *         class SimpleGameContext is simple implementation of Global
 *         GameContext class to control user character identity, game map to
 *         explore, context event key to define which callback to trigger
 */
public class SimpleGameContext implements GameContext {
	private UserCharacter userCharacter;
	private GameMap gameMap;
	private EventKey currentKey;
	private EventKey followupKey;

	public UserCharacter getUserCharacter() {
		return userCharacter;
	}

	public void setUserCharacter(UserCharacter userCharacter) {
		this.userCharacter = userCharacter;
	}

	public EventKey getCurrentKey() {
		return currentKey;
	}

	public void setCurrentKey(EventKey currentKey) {
		this.currentKey = currentKey;
	}

	public EventKey getFollowupKey() {
		return followupKey;
	}

	public void setFollowupKey(EventKey followupKey) {
		this.followupKey = followupKey;
	}

	public GameMap getGameMap() {
		return gameMap;
	}

	public void setGameMap(GameMap gameMap) {
		this.gameMap = gameMap;
	}

	@Override
	public void initializeMap(int size, List<GameCharacter> charachters) {
		if (this.gameMap == null || this.gameMap.isEmpty()) {
			this.gameMap = new SimpleArrayMap(size);
			this.gameMap.fillWithCharacters(charachters);
		}
	}

	@Override
	public void initializeUserCharachter(String name) {
		if (this.userCharacter == null) {
			this.userCharacter = new UserCharacter(name);
		}
	}
}
