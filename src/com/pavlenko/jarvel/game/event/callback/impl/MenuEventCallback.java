package com.pavlenko.jarvel.game.event.callback.impl;

import java.util.List;
import java.util.Optional;

import com.pavlenko.jarvel.game.character.Gender;
import com.pavlenko.jarvel.game.character.Race;
import com.pavlenko.jarvel.game.character.SuperPower;
import com.pavlenko.jarvel.game.character.SuperPowerType;
import com.pavlenko.jarvel.game.character.impl.UserCharacter;
import com.pavlenko.jarvel.game.character.inventory.Inventory;
import com.pavlenko.jarvel.game.event.callback.EventRepositoryCallback;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity.CallbackKey;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventMapping;
import com.pavlenko.jarvel.game.event.model.EventKey;
import com.pavlenko.jarvel.game.event.model.Text;
import com.pavlenko.jarvel.game.impl.SimpleGameContext;
import com.pavlenko.jarvel.property.PropertyKey;
import com.pavlenko.jarvel.repository.Repository;
import com.pavlenko.jarvel.utils.CharacterUtils;
import com.pavlenko.jarvel.utils.ContextUtils;
import com.pavlenko.jarvel.utils.StringUtils;

@EventCallbackEntity(callbackKey = CallbackKey.MENU)
public class MenuEventCallback implements EventRepositoryCallback {
	private Repository repository;
	private SimpleGameContext context;

	public MenuEventCallback(Repository repository, SimpleGameContext context) {
		this.repository = repository;
		this.context = context;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public SimpleGameContext getContext() {
		return context;
	}

	public void setContext(SimpleGameContext context) {
		this.context = context;
	}

	@EventMapping(key = EventKey.NEW_GAME)
	public void newGame() {
		callback(Text.NEW_GAME);
		askName();
		context.setFollowupKey(EventKey.ASK_RACE);
	}

	@EventMapping(key = EventKey.ASK_NAME, followupKey = EventKey.ASK_RACE)
	public void askName() {
		callback(Text.ASK_NAME);
	}

	@EventMapping(key = EventKey.ASK_RACE, followupKey = EventKey.ASK_GENDER)
	public void askRace(String text) {
		context.initializeUserCharachter(text);
		callback(Text.ASK_RACE);
		repositoryCallbackText(repository, PropertyKey.REPOSITORY_ASK_RACE);
	}

	@EventMapping(key = EventKey.ASK_GENDER, followupKey = EventKey.ASK_STRENGTH)
	public void askGender(String text) {
		context.getUserCharacter().setRace(Race.of(text));
		callback(Text.ASK_GENDER);
		repositoryCallbackText(repository, PropertyKey.REPOSITORY_ASK_GENDER);
	}

	@EventMapping(key = EventKey.ASK_STRENGTH, followupKey = EventKey.ASK_AGILITY)
	public void askStrength(String text) {
		final UserCharacter character = context.getUserCharacter();
		character.setGender(Gender.of(text));
		character.setStrength(character.getRace().getBasicStrength() + character.getGender().getStrengthAddon());
		character.setAgility(character.getRace().getBasicAgility() + character.getGender().getAgilityAddon());
		character.setMindset(character.getRace().getBasicMindset() + character.getGender().getMindsetAddon());
		repositoryFormatText(repository, PropertyKey.REPOSITORY_SKILLS_DESCRIPTION, character.getStrength(),
				character.getAgility(), character.getMindset());
		callback(Text.ASK_STRENGTH_INFO, Text.ASK_STRENGTH);
	}

	@EventMapping(key = EventKey.ASK_AGILITY, followupKey = EventKey.ASK_MINDSET)
	public void askAgility(String text) {
		if (StringUtils.matchesAbilityRate(text)) {
			final UserCharacter character = context.getUserCharacter();
			character.setStrength(character.getStrength() + Integer.valueOf(text));
			callback(Text.ASK_AGILITY);
		} else
			invalidUserInput(EventKey.ASK_STRENGTH, EventKey.ASK_AGILITY, Text.INVALID_CHARACTER_BONUS);
	}

	@EventMapping(key = EventKey.ASK_MINDSET, followupKey = EventKey.ASK_SUPER_POWER)
	public void askMindset(String text) {
		if (StringUtils.matchesAbilityRate(text)) {
			final UserCharacter character = context.getUserCharacter();
			character.setAgility(character.getAgility() + Integer.valueOf(text));
			callback(Text.ASK_MINDSET);
		} else
			invalidUserInput(EventKey.ASK_AGILITY, EventKey.ASK_MINDSET, Text.INVALID_CHARACTER_BONUS);
	}

	@EventMapping(key = EventKey.ASK_SUPER_POWER, followupKey = EventKey.COMPLETE_CHARACTER_SETUP)
	public void askSuperPower(String text) {
		if (StringUtils.matchesAbilityRate(text)) {
			final UserCharacter character = context.getUserCharacter();
			character.setMindset(character.getMindset() + Integer.valueOf(text));
			callback(Text.ASK_SUPER_POWER);
			repositoryCallbackText(repository, PropertyKey.REPOSITORY_ASK_SUPER_POWER);
		} else
			invalidUserInput(EventKey.ASK_MINDSET, EventKey.ASK_SUPER_POWER, Text.INVALID_CHARACTER_BONUS);
	}

	@EventMapping(key = EventKey.COMPLETE_CHARACTER_SETUP)
	public void completeCharacterSetup(String text) {
		final Optional<SuperPowerType> optType = SuperPowerType.optionalOf(text);
		if (optType.isPresent()) {
			// character final setup
			final UserCharacter character = context.getUserCharacter();
			character.setHealth(100);
			character.setSuperPower(new SuperPower(optType.get(), CharacterUtils.calculateSuperPowerValue(character)));
			character.setInventory(new Inventory(CharacterUtils.createDefaultInventoryItems(
					repository.getProps().getPropertyAsMap(PropertyKey.INVENTORY_DEFAULT_SETUP))));

			// callback propagation
			callback(Text.COMPLETE_CHARACTER_SETUP);
			callback(character.toString());
			callback(Text.ASK_WHAT_TO_DO_NEXT);
			repositoryCallbackText(repository, PropertyKey.REPOSITORY_CHARACTER_ACTIONS);
		} else {
			invalidUserInput(EventKey.ASK_SUPER_POWER, EventKey.COMPLETE_CHARACTER_SETUP, Text.UNEXPECETD_INPUT);
			repositoryCallbackText(repository, PropertyKey.REPOSITORY_ASK_SUPER_POWER);
		}

	}

	@EventMapping(key = EventKey.LOAD_GAME, followupKey = EventKey.LOAD_GAME_SNAPSHOT)
	public void loadGame() {
		final Optional<List<String>> optGameList = repository.findAllSavedGames();
		if (optGameList.isPresent()) {
			callback(Text.LOAD_GAME);
			optGameList.get().forEach(this::callback);
		} else
			callback(Text.GAME_LIST_IS_EMPTY);
	}

	@EventMapping(key = EventKey.LOAD_GAME_SNAPSHOT)
	public void loadGameSnapshot(String text) {

		// if (StringUtils.matchesDigit(text)) {
		//
		// if (optGameList.isPresent()) {
		// callback(Text.LOAD_GAME);
		// optGameList.get().forEach(this::callback);
		// } else
		// callback(Text.GAME_LIST_IS_EMPTY);
	}

	@EventMapping(key = EventKey.SAVE_GAME)
	public void saveGame() {
		final Optional<String> snapshotName = repository.saveGameContext(context);
		if (snapshotName.isPresent()) {
			callbackFormat(Text.SAVED_WITH_SUCCESS, snapshotName.get());
		} else
			callback(Text.DEFAULT_ERROR);

		callback(Text.ASK_WHAT_TO_DO_NEXT);
	}

	@EventMapping(key = EventKey.CHARACTER)
	public void characterInfo() {
		callback(context.getUserCharacter().toString());
		callback(Text.ASK_WHAT_TO_DO_NEXT);
	}

	@EventMapping(key = EventKey.INVENTORY)
	public void inventoryInfo() {
		callback(context.getUserCharacter().getInventory().toString());
	}

	private void invalidUserInput(final EventKey key, final EventKey followupKey, final Text text) {
		ContextUtils.updateEventKeys(context, key, followupKey);
		callback(text);
	}
}
