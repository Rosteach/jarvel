package com.pavlenko.jarvel.game.event.callback.impl;

import com.pavlenko.jarvel.game.GameContext;
import com.pavlenko.jarvel.game.character.impl.MapCharacter;
import com.pavlenko.jarvel.game.character.impl.UserCharacter;
import com.pavlenko.jarvel.game.event.action.AttackAction;
import com.pavlenko.jarvel.game.event.callback.EventRepositoryCallback;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity.CallbackKey;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventMapping;
import com.pavlenko.jarvel.game.event.method.EventKey;
import com.pavlenko.jarvel.game.event.method.Text;
import com.pavlenko.jarvel.game.map.MapItem;
import com.pavlenko.jarvel.property.PropertyKey;

@EventCallbackEntity(callbackKey = CallbackKey.FIGHT)
public class FightEventCallback implements EventRepositoryCallback {

	@EventMapping(key = EventKey.FIGHT)
	public void fight() {
		if (mapHasNoEnenmy())
			return;

		final UserCharacter user = GameContext.getInstance().getUserCharacter();
		final MapCharacter enemy = GameContext.getInstance().getGameMap().getCurrentItem().getMapCharacter();
		repositoryFormatText(PropertyKey.REPOSITORY_ASK_FOR_FIGHT_ACTION, user.getHealth(),
				user.getSuperPower().getValue(), enemy.getHealth());

	}

	@EventMapping(key = EventKey.FIGHT_SIMPLE_ATTACK)
	public void simpleAttack() {
		if (mapHasNoEnenmy())
			return;

		AttackAction.SIMPLE_ATTACK.doAttack(GameContext.getInstance().getUserCharacter(),
				GameContext.getInstance().getGameMap().getCurrentItem().getMapCharacter());
		attackCallbackAction();
	}

	@EventMapping(key = EventKey.FIGHT_POWERFUL_ATTACK)
	public void powerAttack() {
		if (mapHasNoEnenmy())
			return;

		AttackAction.POWERFUL_ATTACK.doAttack(GameContext.getInstance().getUserCharacter(),
				GameContext.getInstance().getGameMap().getCurrentItem().getMapCharacter());
		attackCallbackAction();
	}

	@EventMapping(key = EventKey.FIGHT_SUPER_POWER)
	public void superPower() {
		if (mapHasNoEnenmy())
			return;

		GameContext.getInstance().getUserCharacter().getSuperPower().getType().useSuperPower(
				GameContext.getInstance().getUserCharacter(),
				GameContext.getInstance().getGameMap().getCurrentItem().getMapCharacter());
		attackCallbackAction();
	}

	@EventMapping(key = EventKey.FIGHT_HEALTH_ELIXIR)
	public void elixir() {
		if (mapHasNoEnenmy())
			return;

	}

	private void attackCallbackAction() {
		final MapCharacter mCharacter = GameContext.getInstance().getGameMap().getCurrentItem().getMapCharacter();
		if (mCharacter.getHealth() == 0) {
			winCallback();
			return;
		} else {
			final UserCharacter uCharacter = GameContext.getInstance().getUserCharacter();
			AttackAction.randomAttack().doAttack(mCharacter, uCharacter);
			if (uCharacter.getHealth() == 0) {
				uCharacter.refreshAfterFight();
				mCharacter.refreshAfterFight();
				repositoryCallbackText(PropertyKey.REPOSITORY_FIGHT_DEFEATED);
				return;
			}
		}
		fight();
	}

	private void winCallback() {
		final UserCharacter uCharacter = GameContext.getInstance().getUserCharacter();
		final MapItem mapItem = GameContext.getInstance().getGameMap().getCurrentItem();
		final MapCharacter mCharacter = mapItem.getMapCharacter();

		uCharacter.refreshAfterFight();
		uCharacter.addExperience(mCharacter.getExperience());
		uCharacter.addGold(mapItem.getGold());
		mCharacter.setExperience(0);
		mapItem.setGold(0);

		repositoryCallbackText(PropertyKey.REPOSITORY_FIGHT_WINNER);

		if (uCharacter.getExperience() >= uCharacter.getHealth() * 3) {
			uCharacter.levelUp();
			callback(Text.FIGHT_LEVEL_UP);
		}

		callback(uCharacter.toString());
	}

	private boolean mapHasNoEnenmy() {
		if (GameContext.getInstance().getGameMap() != null && GameContext.getInstance().getGameMap().hasEnemy())
			return false;

		callback(Text.INVALID_FIGHT_ACTION_USAGE);
		return true;
	}
}
