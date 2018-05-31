package com.pavlenko.jarvel.game.fight.impl;

import java.util.Random;

import com.pavlenko.jarvel.game.character.GameCharacter;
import com.pavlenko.jarvel.game.fight.FightAction;
import com.pavlenko.jarvel.game.fight.FightProcessor;

public class SimpleFightProcessor implements FightProcessor {
	@Override
	public void processAttack(final GameCharacter attacker, final GameCharacter defender, final FightAction action) {
		defender.setStrength(defender.getStrength() - calculateDamage(attacker.getStrength()));
	}

	private int calculateDamage(int strength) {
		int rate = strength / 4;
		System.out.println(rate);
		Random random = new Random();
		return strength + (random.nextBoolean() ? (-rate) : rate);
	}

}
