package com.pavlenko.jarvel.game.character;

import java.util.Arrays;

/**
 * @author Rostyslav P.A.
 * 
 *         Enum Gender contains all available race types, defines character
 *         basic ability value
 */
public enum Race {
	HUMAN(3, 3, 3),

	ELF(1, 3, 5),

	DWARF(3, 4, 2),

	CRIPTONIAN(5, 2, 2);

	private int basicStrength;
	private int basicAgility;
	private int basicMindset;

	private Race(int basicStrength, int basicAgility, int basicMindset) {
		this.basicStrength = basicStrength;
		this.basicAgility = basicAgility;
		this.basicMindset = basicMindset;
	}

	public int getBasicStrength() {
		return basicStrength;
	}

	public int getBasicAgility() {
		return basicAgility;
	}

	public int getBasicMindset() {
		return basicMindset;
	}

	/**
	 * method to get enum value based on string representation return Race.HUMAN
	 * if none match
	 * 
	 * @param text
	 * @return Gender
	 */
	public static Race of(String text) {
		return Arrays.stream(values()).filter(v -> v.name().equalsIgnoreCase(text)).findFirst().orElse(HUMAN);
	}
}
