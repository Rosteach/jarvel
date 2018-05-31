package com.pavlenko.jarvel.game.character;

import java.util.Arrays;

/**
 * @author Rostyslav P.A.
 * 
 *         Enum Gender contains all available gender types,has influence on the
 *         result character ability
 */
public enum Gender {
	MALE(2, 1, 0),

	FEMALE(0, 2, 1),

	IT(2, 2, -1);

	private int strengthAddon;
	private int agilityAddon;
	private int mindsetAddon;

	private Gender(int strengthAddon, int agilityAddon, int mindsetAddon) {
		this.strengthAddon = strengthAddon;
		this.agilityAddon = agilityAddon;
		this.mindsetAddon = mindsetAddon;
	}

	public int getStrengthAddon() {
		return strengthAddon;
	}

	public int getAgilityAddon() {
		return agilityAddon;
	}

	public int getMindsetAddon() {
		return mindsetAddon;
	}

	/**
	 * method to get enum value based on string representation return Gender.IT
	 * if none match
	 * 
	 * @param text
	 * @return Gender
	 */
	public static Gender of(String text) {
		return Arrays.stream(values()).filter(v -> v.name().equalsIgnoreCase(text)).findFirst().orElse(IT);
	}
}
