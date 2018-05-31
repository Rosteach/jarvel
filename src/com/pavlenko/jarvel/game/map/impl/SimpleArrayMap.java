package com.pavlenko.jarvel.game.map.impl;

import java.util.List;
import java.util.Random;

import com.pavlenko.jarvel.game.character.GameCharacter;
import com.pavlenko.jarvel.game.map.GameMap;

public class SimpleArrayMap implements GameMap {
	private ArrayMapItem[][] items;
	private int mapSize;

	public SimpleArrayMap(int mapSize) {
		this.mapSize = mapSize;
		this.items = new ArrayMapItem[mapSize][mapSize];
	}

	public ArrayMapItem[][] getItems() {
		return items;
	}

	public void setItems(ArrayMapItem[][] items) {
		this.items = items;
	}

	public int getMapSize() {
		return mapSize;
	}

	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}

	@Override
	public ArrayMapItem navigate(int x, int y) {
		return items[x][y];
	}

	@Override
	public ArrayMapItem findRandom() {
		final Random random = new Random();
		return navigate(random.nextInt(mapSize - 1), random.nextInt(mapSize - 1));
	}

	@Override
	public void fillWithCharacters(final List<GameCharacter> characters) {
		final Random random = new Random();
		int i = 0;
		while (i == characters.size()) {
			int x = random.nextInt(mapSize - 1);
			int y = random.nextInt(mapSize - 1);

			if (items[x][y] == null) {
				items[x][y] = new ArrayMapItem(characters.get(i));
				++i;
			}
		}
	}

	@Override
	public boolean isEmpty() {
		return this.mapSize == 0;
	}

}
