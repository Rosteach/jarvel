package com.pavlenko.jarvel.game.character.impl;

/**
 * {@code UserCharacter} implementation for user character
 * 
 * contains user specific data that could be only with user character
 * 
 * {@link MapCharacter}
 * 
 * @author Rostislav P.A.
 */
public class UserCharacter extends MapCharacter {
	private static final long serialVersionUID = 2050507294409794952L;
	private int gold;

	public UserCharacter(String name) {
		super(name);
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder(super.toString());
		builder.append("Gold: %1$d;\n");
		return String.format(builder.toString(), gold);
	}

	/**
	 * method to add gold value
	 * 
	 * @param gold
	 */
	public void addGold(int gold) {
		setGold(getGold() + gold);
	}

	/**
	 * method to add experience
	 * 
	 * @param experience
	 */
	public void addExperience(int experience) {
		setExperience(getExperience() + experience);
	}
}
