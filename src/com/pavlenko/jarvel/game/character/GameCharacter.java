package com.pavlenko.jarvel.game.character;

/**
 * @author Rostyslav P.A.
 * 
 *         Class GameCharacter is a parent class for all character based
 *         implementations
 */
public class GameCharacter {
	private String name;
	private Race race;
	private Gender gender;
	private int health;
	private int level;
	private int strength;
	private int agility;
	private int mindset;
	private SuperPower superPower;

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

	public SuperPower getSuperPower() {
		return superPower;
	}

	public void setSuperPower(SuperPower superPower) {
		this.superPower = superPower;
	}

	@Override
	public String toString() {
		return "GameCharacter [name=" + name + ", race=" + race + ", gender=" + gender + ", health=" + health
				+ ", level=" + level + ", strength=" + strength + ", agility=" + agility + ", mindset=" + mindset
				+ ", superPower=" + superPower + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String name;
		private Race race;
		private Gender gender;
		private int health;
		private int level;
		private int strength;
		private int agility;
		private int mindset;
		private SuperPower superPower;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder race(Race race) {
			this.race = race;
			return this;
		}

		public Builder gender(Gender gender) {
			this.gender = gender;
			return this;
		}

		public Builder health(int health) {
			this.health = health;
			return this;
		}

		public Builder level(int level) {
			this.level = level;
			return this;
		}

		public Builder strength(int strength) {
			this.strength = strength;
			return this;
		}

		public Builder agility(int agility) {
			this.agility = agility;
			return this;
		}

		public Builder mindset(int mindset) {
			this.mindset = mindset;
			return this;
		}

		public Builder superPower(SuperPower superPower) {
			this.superPower = superPower;
			return this;
		}

		public GameCharacter build() {
			final GameCharacter character = new GameCharacter();
			character.setName(name);
			character.setRace(race);
			character.setGender(gender);
			character.setHealth(health);
			character.setLevel(level);
			character.setStrength(strength);
			character.setAgility(agility);
			character.setMindset(mindset);
			character.setSuperPower(superPower);
			return character;
		}
	}
}
