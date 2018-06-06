package com.pavlenko.jarvel.game.proxy;

import java.io.Serializable;

import com.pavlenko.jarvel.game.GameContext;
import com.pavlenko.jarvel.game.character.impl.UserCharacter;
import com.pavlenko.jarvel.game.event.method.EventKey;
import com.pavlenko.jarvel.game.map.GameMap;

/**
 * class ContextProxy hold all data that should be stored and then retrieved and
 * injected within current game context if one is present or create new as an
 * exact copy of this class
 * 
 * @author Rostislav P.A.
 */
public class ContextProxy implements Serializable {

	private static final long serialVersionUID = 2042903159707802475L;

	private UserCharacter userCharacter;
	private GameMap gameMap;
	private EventKey currentKey;
	private EventKey followupKey;

	public ContextProxy() {
	}

	public ContextProxy(GameContext gameContext) {
		this.userCharacter = gameContext.getUserCharacter();
		this.currentKey = gameContext.getCurrentKey();
		this.followupKey = gameContext.getFollowupKey();
		this.gameMap = gameContext.getGameMap();
	}

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
	public String toString() {
		return "ContextProxy [userCharacter=" + userCharacter + ", gameMap=" + gameMap + ", currentKey=" + currentKey
				+ ", followupKey=" + followupKey + "]";
	}
}
