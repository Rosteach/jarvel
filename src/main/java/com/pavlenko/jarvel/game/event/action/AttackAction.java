package com.pavlenko.jarvel.game.event.action;

import java.util.Random;

import com.pavlenko.jarvel.game.character.impl.MapCharacter;
import com.pavlenko.jarvel.game.event.callback.EventCallback;
import com.pavlenko.jarvel.game.event.method.Text;
import com.pavlenko.jarvel.utils.CharacterUtils;

public enum AttackAction implements EventCallback {
	SIMPLE_ATTACK("simple attack") {
		@Override
		public void doAttack(MapCharacter attacker, MapCharacter defender) {
			updateCharacters(attacker, defender, attacker.getStrength());
		}
	},
	POWERFUL_ATTACK("powerful attack") {
		@Override
		public void doAttack(MapCharacter attacker, MapCharacter defender) {
			if (RANDOM.nextBoolean()) {
				updateCharacters(attacker, defender, attacker.getStrength() * 2);
			} else
				callback(Text.FIGHT_POWERFUL_ATTACK_FAILED);
		}
	};

	private static final AttackAction[] VALUES = values();
	private static final Random RANDOM = new Random();

	private String action;

	private AttackAction(String action) {
		this.action = action;
	}

	public String action() {
		return this.action;
	}

	/**
	 * abstract method just to give behavior per each specifi attack
	 * 
	 * @param attacker
	 * @param defender
	 */
	public abstract void doAttack(MapCharacter attacker, MapCharacter defender);

	/**
	 * method to update characters after damage is calculated
	 * 
	 * @param attacker
	 * @param defender
	 * @param value
	 */
	public void updateCharacters(MapCharacter attacker, MapCharacter defender, int value) {
		int damage = CharacterUtils.calculateDamage(value);
		int newHealth = defender.getHealth() - damage;
		defender.setHealth(newHealth <= 0 ? 0 : newHealth);
		CharacterUtils.updateSuperPower(attacker, damage);
	}

	public static AttackAction randomAttack() {
		return VALUES[RANDOM.nextInt(VALUES.length - 1)];
	}
}
