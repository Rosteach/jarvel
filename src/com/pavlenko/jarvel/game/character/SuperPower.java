package com.pavlenko.jarvel.game.character;

/**
 * Class SuperPower is a simple representation of character specific power
 * should be unique per each character type
 * 
 * @author Rostyslav P.A.
 */
public class SuperPower {
	private SuperPowerType type;
	private int value;

	public SuperPower(SuperPowerType type, int value) {
		this.type = type;
		this.value = value;
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
		return "SuperPower [type=" + type + ", value=" + value + "]";
	}
}
