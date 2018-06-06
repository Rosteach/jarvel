package com.pavlenko.jarvel.game.character;

import java.io.Serializable;

import com.pavlenko.jarvel.game.character.impl.enums.Gender;
import com.pavlenko.jarvel.game.character.impl.enums.Race;
import com.pavlenko.jarvel.game.exception.GameException;

/**
 * {@code GameCharacter.class} is a parent class for all character based
 * implementations
 * 
 * it contains all basic settings that default character should have and also
 * the initial values for new instances
 * 
 * @author Rostyslav P.A.
 * 
 */
public class GameCharacter implements Serializable {
	private static final long serialVersionUID = -6222594219852226642L;

	private String name;
	private Race race;
	private Gender gender;
	private int level;
	private int health;
	private int strength;
	private int agility;
	private int mindset;

	public GameCharacter() {
	}

	public GameCharacter(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getMindset() {
		return mindset;
	}

	public void setMindset(int mindset) {
		this.mindset = mindset;
	}

	public void refreshAfterFight() {
		this.health = 100;
		levelsUp(this.level);
	}

	/**
	 * method to force initial abilities setup based on Race and Gender
	 */
	public void defaultInitialization() {

		if (this.race == null || this.gender == null)
			throw new GameException("Race or Gender should not be null!");

		this.strength = this.race.getBasicStrength() + this.gender.getStrengthAddon();
		this.agility = this.race.getBasicAgility() + this.gender.getAgilityAddon();
		this.mindset = this.race.getBasicMindset() + this.gender.getMindsetAddon();
	}

	/**
	 * Causes the renewal of all key properties of this character up to the
	 * defined level
	 * 
	 * @param targetLevel
	 */
	public void levelsUp(int targetLevel) {
		for (int i = 1; i < targetLevel; i++)
			levelUp();
	}

	/**
	 * Causes the renewal of all key properties of this character to the next
	 * level
	 */
	public void levelUp() {
		this.level += 1;
		this.health = (this.health * 5) / 4;
		this.strength += 4;
		this.agility += 4;
		this.mindset += 4;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder("Character name : %1$s;");
		builder.append("\ngender: %2$s;");
		builder.append(" race: %3$s;");
		builder.append("\nlevel: %4$d;");
		builder.append(" health: %5$d;");
		builder.append("\nstrength: %6$d;");
		builder.append(" agility: %7$d;");
		builder.append(" mindset: %8$d;\n");
		return String.format(builder.toString(), name, race, gender, level, health, strength, agility, mindset);
	}
}
