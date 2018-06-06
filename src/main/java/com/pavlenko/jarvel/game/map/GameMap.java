package com.pavlenko.jarvel.game.map;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import com.pavlenko.jarvel.game.character.impl.MapCharacter;

public class GameMap implements Serializable {
	private static final long serialVersionUID = -4559392553681527148L;
	private int mapSize;
	private MapItem[][] items;
	private MapItem currentItem;

	public GameMap(int mapSize) {
		this.mapSize = mapSize;
		this.items = new MapItem[mapSize][mapSize];
	}

	public int getMapSize() {
		return mapSize;
	}

	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}

	public MapItem[][] getItems() {
		return items;
	}

	public void setItems(MapItem[][] items) {
		this.items = items;
	}

	public MapItem getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(MapItem currentItem) {
		this.currentItem = currentItem;
	}

	public MapItem navigate(int x, int y) {
		return items[x][y];
	}

	public MapItem navigateRandom() {
		final Random random = new Random();
		return navigate(random.nextInt(mapSize - 1), random.nextInt(mapSize - 1));
	}

	public void fillMap(final List<MapCharacter> characters) {
		fillWithCharacters(characters);
		fillEmptyItems();
	}

	/**
	 * Causes fulfillment of all empty items with default settings
	 */
	private void fillEmptyItems() {
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[i].length; j++) {
				if (items[i][j] == null)
					items[i][j] = new MapItem(10);
			}
		}
	}

	/**
	 * Causes random fulfillment of map array with character based items
	 * 
	 * @param characters
	 */
	private void fillWithCharacters(final List<MapCharacter> characters) {
		final Random random = new Random();
		int i = 0;
		while (i != characters.size()) {
			int x = random.nextInt(mapSize - 1);
			int y = random.nextInt(mapSize - 1);

			if (items[x][y] == null) {
				final MapCharacter character = characters.get(i);
				items[x][y] = new MapItem(character.getHealth(), character);
				++i;
			}
		}
	}

	public boolean hasEnemy() {
		return this.currentItem != null && this.currentItem.getMapCharacter() != null;
	}

	public String getPrettyPrint() {
		final StringBuilder builder = new StringBuilder(this.mapSize);
		builder.append("  0  ");
		for (int s = 1; s < mapSize; s++) {
			builder.append(s).append("  ");
		}
		builder.append("\n");
		for (int i = 0; i < items.length; i++) {
			builder.append(i).append(" ");
			for (int j = 0; j < items[i].length; j++) {

				if (items[i][j].getMapCharacter() == null) {
					builder.append("💰      ");
				} else
					builder.append("👻      ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
