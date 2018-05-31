package com.pavlenko.jarvel.game.character.inventory.item;

import com.pavlenko.jarvel.game.character.inventory.InventoryItem;
import com.pavlenko.jarvel.game.character.inventory.ItemType;

/**
 * base class Elixir for all elixir based items
 * 
 * @author Rostislav P.A.
 *
 */
public class Elixir implements InventoryItem {
	private ItemType type;
	private int points;

	public Elixir(ItemType type, int points) {
		super();
		this.type = type;
		this.points = points;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Elixir [points=" + points + ", type=" + type + "]";
	}
}
