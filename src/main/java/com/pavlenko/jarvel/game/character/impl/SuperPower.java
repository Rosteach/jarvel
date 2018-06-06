package com.pavlenko.jarvel.game.character.impl;

import java.io.Serializable;

import com.pavlenko.jarvel.game.character.impl.enums.SuperPowerType;

/**
 * {@code SuperPower} class is a simple representation of character specific
 * power
 * 
 * should be unique per each character type
 * 
 * @author Rostyslav P.A.
 */
public class SuperPower implements Serializable{
	
	private static final long serialVersionUID = -8878382128827328238L;
	
	private SuperPowerType type;
	private int value;

	public SuperPower(SuperPowerType type) {
		this.type = type;
	}

	public SuperPowerType getType() {
		return type;
	}

	public void setType(SuperPowerType type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		final String pattern = "type: %1$s; value:%2$d";
		return String.format(pattern, type, value);
	}
}
