package com.pavlenko.jarvel.game.character.impl;

import com.pavlenko.jarvel.game.character.GameCharacter;
import com.pavlenko.jarvel.game.character.inventory.Inventory;

public class UserCharacter extends GameCharacter {
	private int experience;
	private Inventory inventory;

	public UserCharacter(String name) {
		super(name);
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "UserCharacter [" + super.toString() + ", experience=" + experience + ", inventory=" + inventory + "]";
	}
}
