package com.pavlenko.jarvel.game;

import com.pavlenko.jarvel.game.character.impl.UserCharacter;
import com.pavlenko.jarvel.game.event.method.EventKey;
import com.pavlenko.jarvel.game.map.GameMap;

/**
 * {@code GameContext} is a core class for storing all session data
 * 
 * designed with Singleton in mind
 * 
 * contains {@link UserCharacter.class} to control user character creation and
 * operations
 * 
 * contains {@link GameMap.class} to control map operations
 * 
 * contains {@link EventKey} keys to control user flow state
 * 
 * @author Rostislav P.A.
 *
 */
public final class GameContext {
	private static GameContext instance;

	private UserCharacter userCharacter;
	private GameMap gameMap;
	private EventKey currentKey;
	private EventKey followupKey;

	private GameContext() {
	}

	public static GameContext getInstance() {
		if (instance == null)
			instance = new GameContext();
		return instance;
	}

	public UserCharacter getUserCharacter() {
		return userCharacter;
	}

	public void setUserCharacter(UserCharacter userCharacter) {
		this.userCharacter = userCharacter;
	}

	public GameMap getGameMap() {
		return gameMap;
	}

	public void setGameMap(GameMap gameMap) {
		this.gameMap = gameMap;
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

	public static void setInstance(GameContext instance) {
		GameContext.instance = instance;
	}
}
