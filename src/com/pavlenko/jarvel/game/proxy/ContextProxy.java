package com.pavlenko.jarvel.game.proxy;

import java.io.Serializable;

import com.pavlenko.jarvel.game.character.Gender;
import com.pavlenko.jarvel.game.character.Race;
import com.pavlenko.jarvel.game.character.impl.UserCharacter;
import com.pavlenko.jarvel.game.event.model.EventKey;
import com.pavlenko.jarvel.game.impl.SimpleGameContext;

/**
 * class ContextProxy hold all data that should be stored and then retrieved and
 * injected within current game context if one is present or create new as an
 * exact copy of this class
 * 
 * @author Rostislav P.A.
 */
public class ContextProxy implements Serializable {

	private static final long serialVersionUID = 2042903159707802475L;

	private String name;
	private Race race;
	private Gender gender;
	private int health;
	private int experience;
	private int strength;
	private int agility;
	private int mindset;
	private EventKey currentKey;
	private EventKey followupKey;

	public ContextProxy() {
	}

	public ContextProxy(SimpleGameContext simpleGameContext) {
		final UserCharacter userCharacter = simpleGameContext.getUserCharacter();
		if (userCharacter != null) {
			this.name = userCharacter.getName();
			this.race = userCharacter.getRace();
			this.gender = userCharacter.getGender();
			this.experience = userCharacter.getExperience();
			this.health = userCharacter.getHealth();
			this.strength = userCharacter.getStrength();
			this.agility = userCharacter.getAgility();
			this.mindset = userCharacter.getMindset();
		}

		this.currentKey = simpleGameContext.getCurrentKey();
		this.followupKey = simpleGameContext.getFollowupKey();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
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

	public EventKey getCurrentKey() {
		return currentKey;
	}

	public void setCurrentKey(EventKey currentKey) {
		this.currentKey = currentKey;
	}

	public EventKey getFollowupKey() {
		return followupKey;
	}

	public void setFollowupKey(EventKey followupKey) {
		this.followupKey = followupKey;
	}
}
