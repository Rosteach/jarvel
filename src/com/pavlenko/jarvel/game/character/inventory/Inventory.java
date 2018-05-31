package com.pavlenko.jarvel.game.character.inventory;

import java.util.List;

/**
 * Class represents inventory to hold armor,poison, artifacts, etc
 * 
 * @author Rostislav P.A.
 *
 */
public class Inventory {
	private List<InventoryItem> items;

	public Inventory() {
	}

	public Inventory(List<InventoryItem> items) {
		this.items = items;
	}

	public List<InventoryItem> getItems() {
		return items;
	}

	public void setItems(List<InventoryItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Inventory [items=" + items + "]";
	}
}
