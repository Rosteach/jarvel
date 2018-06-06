package com.pavlenko.jarvel.game.map;

import java.io.Serializable;

import com.pavlenko.jarvel.game.character.impl.MapCharacter;

public class MapItem implements Serializable {
	private static final long serialVersionUID = -2028979719944248876L;

	private MapCharacter mapCharacter;
	private int gold;

	public MapItem(int gold) {
		this.gold = gold;
	}

	public MapItem(int gold, MapCharacter mapCharacter) {
		this.gold = gold;
		this.mapCharacter = mapCharacter;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public MapCharacter getMapCharacter() {
		return mapCharacter;
	}

	public void setMapCharacter(MapCharacter mapCharacter) {
		this.mapCharacter = mapCharacter;
	}

	@Override
	public String toString() {
		return "MapItem [mapCharacter=" + mapCharacter + ", gold=" + gold + "]";
	}
}
