package com.pavlenko.jarvel.game.event.callback.impl;

import java.util.Optional;

import com.pavlenko.jarvel.game.GameContext;
import com.pavlenko.jarvel.game.character.impl.SuperPower;
import com.pavlenko.jarvel.game.character.impl.UserCharacter;
import com.pavlenko.jarvel.game.character.impl.enums.Gender;
import com.pavlenko.jarvel.game.character.impl.enums.Race;
import com.pavlenko.jarvel.game.character.impl.enums.SuperPowerType;
import com.pavlenko.jarvel.game.event.callback.EventRepositoryCallback;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity.CallbackKey;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventMapping;
import com.pavlenko.jarvel.game.event.method.EventKey;
import com.pavlenko.jarvel.game.event.method.Text;
import com.pavlenko.jarvel.property.PropertyKey;
import com.pavlenko.jarvel.utils.StringUtils;

@EventCallbackEntity(callbackKey = CallbackKey.CREATE_CHARACTER)
public class CharacterEventCallback implements EventRepositoryCallback {

	@EventMapping(key = EventKey.ASK_RACE, followupKey = EventKey.ASK_GENDER)
	public void askRace(String text) {
		GameContext.getInstance().setUserCharacter(new UserCharacter(text));
		callback(Text.ASK_RACE);
		repositoryCallbackText(PropertyKey.REPOSITORY_ASK_RACE);
	}

	@EventMapping(key = EventKey.ASK_GENDER, followupKey = EventKey.ASK_STRENGTH)
	public void askGender(String text) {
		GameContext.getInstance().getUserCharacter().setRace(Race.of(text));
		callback(Text.ASK_GENDER);
		repositoryCallbackText(PropertyKey.REPOSITORY_ASK_GENDER);
	}

	@EventMapping(key = EventKey.ASK_STRENGTH, followupKey = EventKey.ASK_AGILITY)
	public void askStrength(String text) {
		final UserCharacter character = GameContext.getInstance().getUserCharacter();
		character.setGender(Gender.of(text));
		character.defaultInitialization();
		repositoryFormatText(PropertyKey.REPOSITORY_SKILLS_DESCRIPTION, character.getStrength(), character.getAgility(),
				character.getMindset());
		callback(Text.ASK_STRENGTH_INFO, Text.ASK_STRENGTH);
	}

	@EventMapping(key = EventKey.ASK_AGILITY, followupKey = EventKey.ASK_MINDSET)
	public void askAgility(String text) {
		if (StringUtils.matchesAbilityRate(text)) {
			final UserCharacter character = GameContext.getInstance().getUserCharacter();
			character.setStrength(character.getStrength() + Integer.valueOf(text));
			callback(Text.ASK_AGILITY);
		} else
			invalidUserInput(EventKey.ASK_STRENGTH, EventKey.ASK_AGILITY, Text.INVALID_CHARACTER_BONUS);
	}

	@EventMapping(key = EventKey.ASK_MINDSET, followupKey = EventKey.ASK_SUPER_POWER)
	public void askMindset(String text) {
		if (StringUtils.matchesAbilityRate(text)) {
			final UserCharacter character = GameContext.getInstance().getUserCharacter();
			character.setAgility(character.getAgility() + Integer.valueOf(text));
			callback(Text.ASK_MINDSET);
		} else
			invalidUserInput(EventKey.ASK_AGILITY, EventKey.ASK_MINDSET, Text.INVALID_CHARACTER_BONUS);
	}

	@EventMapping(key = EventKey.ASK_SUPER_POWER, followupKey = EventKey.COMPLETE_CHARACTER_SETUP)
	public void askSuperPower(String text) {
		if (StringUtils.matchesAbilityRate(text)) {
			final UserCharacter character = GameContext.getInstance().getUserCharacter();
			character.setMindset(character.getMindset() + Integer.valueOf(text));
			callback(Text.ASK_SUPER_POWER);
			repositoryCallbackText(PropertyKey.REPOSITORY_ASK_SUPER_POWER);
		} else
			invalidUserInput(EventKey.ASK_MINDSET, EventKey.ASK_SUPER_POWER, Text.INVALID_CHARACTER_BONUS);
	}

	@EventMapping(key = EventKey.COMPLETE_CHARACTER_SETUP)
	public void completeCharacterSetup(String text) {
		final Optional<SuperPowerType> optType = SuperPowerType.optional(text);
		if (optType.isPresent()) {
			// character final setup
			final UserCharacter character = GameContext.getInstance().getUserCharacter();
			character.setSuperPower(new SuperPower(optType.get()));
			character.setLevel(1);
			character.setHealth(100);

			// callback propagation
			callback(Text.COMPLETE_CHARACTER_SETUP);
			callback(character.toString());
			callback(Text.ASK_WHAT_TO_DO_NEXT);
			repositoryCallbackText(PropertyKey.REPOSITORY_CHARACTER_ACTIONS);
		} else {
			invalidUserInput(EventKey.ASK_SUPER_POWER, EventKey.COMPLETE_CHARACTER_SETUP, Text.UNEXPECETD_INPUT);
			repositoryCallbackText(PropertyKey.REPOSITORY_ASK_SUPER_POWER);
		}

	}

	@EventMapping(key = EventKey.CHARACTER)
	public void characterInfo() {
		callback(GameContext.getInstance().getUserCharacter().toString());
		callback(Text.ASK_WHAT_TO_DO_NEXT);
	}

	private void invalidUserInput(final EventKey key, final EventKey followupKey, final Text text) {
		final GameContext context = GameContext.getInstance();
		context.setCurrentKey(key);
		context.setFollowupKey(followupKey);
		callback(text);
	}
}
