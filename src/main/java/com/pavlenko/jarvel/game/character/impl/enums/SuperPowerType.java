package com.pavlenko.jarvel.game.character.impl.enums;

import java.util.Arrays;
import java.util.Optional;

import com.pavlenko.jarvel.game.character.impl.MapCharacter;

/**
 * {@code SuperPowerType}
 * 
 * @author Rostislav P.A.
 */
public enum SuperPowerType {
	HEALING {
		@Override
		public void useSuperPower(MapCharacter attacker, MapCharacter defender) {
			attacker.setHealth(attacker.getHealth() + attacker.getSuperPower().getValue());
			attacker.getSuperPower().setValue(0);
		}
	},
	DAMAGE {
		@Override
		public void useSuperPower(MapCharacter attacker, MapCharacter defender) {
			int newHealth = defender.getHealth() - attacker.getSuperPower().getValue();
			defender.setHealth(newHealth <= 0 ? 0 : newHealth);
			attacker.getSuperPower().setValue(0);
		}
	};

	public abstract void useSuperPower(MapCharacter attacker, MapCharacter defender);

	public static Optional<SuperPowerType> optional(String text) {
		return Arrays.stream(values()).filter(v -> text.toLowerCase().contains(v.name().toLowerCase())).findFirst();
	}
}
