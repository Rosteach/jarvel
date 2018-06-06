package com.pavlenko.jarvel.game.character.impl;

import com.pavlenko.jarvel.game.character.GameCharacter;
import com.pavlenko.jarvel.game.character.impl.enums.Gender;
import com.pavlenko.jarvel.game.character.impl.enums.Race;

/**
 * {@code MapCharacter} implementation for any map related character
 * 
 * designed with Builder pattern in mind to separate creation behavior from
 * business logic
 * 
 * {@link GameCharacter}
 * 
 * @author Rostislav P.A.
 */
public class MapCharacter extends GameCharacter {
	private static final long serialVersionUID = -149327433429749775L;
	private int experience;
	private SuperPower superPower;

	public MapCharacter() {
	}

	public MapCharacter(String name) {
		super(name);
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public SuperPower getSuperPower() {
		return superPower;
	}

	public void setSuperPower(SuperPower superPower) {
		this.superPower = superPower;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder(super.toString());
		builder.append("experience: %1$d;");
		builder.append("\nSuperPower %2$s;");
		return String.format(builder.toString(), experience, superPower);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String name;
		private Race race;
		private Gender gender;
		private int level;
		private int health;
		private int strength;
		private int agility;
		private int mindset;
		private int experience;
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

		public Builder level(int level) {
			this.level = level;
			return this;
		}

		public Builder health(int health) {
			this.health = health;
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

		public Builder experience(int experience) {
			this.experience = experience;
			return this;
		}

		public Builder superPower(SuperPower superPower) {
			this.superPower = superPower;
			return this;
		}

		public MapCharacter build() {
			final MapCharacter character = new MapCharacter();
			character.setName(name);
			character.setRace(race);
			character.setGender(gender);
			character.setHealth(health);
			character.setLevel(level);
			character.setStrength(strength);
			character.setAgility(agility);
			character.setMindset(mindset);
			character.setExperience(experience);
			character.setSuperPower(superPower);
			return character;
		}
	}

	@Override
	public void refreshAfterFight() {
		super.refreshAfterFight();
		this.superPower.setValue(0);
	}

	@Override
	public void levelUp() {
		super.levelUp();
	}

	public void receiveDamage(int damage) {
		int newHealth = super.getHealth() - damage;
		super.setHealth(newHealth <= 0 ? 0 : newHealth);
	}

	public void receiveSuperPower(int damage) {
		int curr = superPower.getValue();
		superPower.setValue(curr + damage);
	}
}
