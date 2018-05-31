package com.pavlenko.jarvel.game.fight;

import com.pavlenko.jarvel.game.character.GameCharacter;

public interface FightProcessor {
	void processAttack(GameCharacter attacker, GameCharacter defender, FightAction action);
}
