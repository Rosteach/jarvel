package com.pavlenko.jarvel.game.event.callback.impl;

import java.util.List;

import com.pavlenko.jarvel.game.GameContext;
import com.pavlenko.jarvel.game.character.GameCharacter;
import com.pavlenko.jarvel.game.character.impl.MapCharacter;
import com.pavlenko.jarvel.game.character.impl.UserCharacter;
import com.pavlenko.jarvel.game.character.impl.enums.Race;
import com.pavlenko.jarvel.game.event.callback.EventRepositoryCallback;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity.CallbackKey;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventMapping;
import com.pavlenko.jarvel.game.event.method.EventKey;
import com.pavlenko.jarvel.game.event.method.Text;
import com.pavlenko.jarvel.game.event.method.TextAction;
import com.pavlenko.jarvel.game.map.GameMap;
import com.pavlenko.jarvel.game.map.MapItem;
import com.pavlenko.jarvel.property.PropertyHolder;
import com.pavlenko.jarvel.property.PropertyKey;
import com.pavlenko.jarvel.utils.CharacterUtils;
import com.pavlenko.jarvel.utils.StringUtils;

@EventCallbackEntity(callbackKey = CallbackKey.EXPLORE)
public class ExploreEventCallback implements EventRepositoryCallback {

	@EventMapping(key = EventKey.EXPLORE, followupKey = EventKey.MAP_NAVIGATION)
	public void explore() {
		if (GameContext.getInstance().getUserCharacter() != null) {
			executeMapCallback();
			callback(Text.EXPLORE_ASK_COORDINATES);
		} else
			callback(Text.EXPLORE_NOT_POSSIBLE);
	}

	private void executeMapCallback() {
		if (GameContext.getInstance().getGameMap() == null) {
			int mapSize = PropertyHolder.getInstance().getIntProperty(PropertyKey.MAP_SIZE);
			int cLevel = GameContext.getInstance().getUserCharacter().getLevel();
			// create beast characters
			final List<MapCharacter> mapCharacters = CharacterUtils.createRandomMapCharactersByRace(cLevel,
					PropertyHolder.getInstance().getIntProperty(PropertyKey.MAP_CHARACTERS_BEAST_SIZE), Race.BEAST);

			// create and add heroes characters
			mapCharacters.addAll(CharacterUtils.createRandomMapCharactersByRace(cLevel,
					PropertyHolder.getInstance().getIntProperty(PropertyKey.MAP_CHARACTERS_HEROES_SIZE), Race.HUMAN,
					Race.CRIPTONIAN, Race.DWARF, Race.ELF));

			final GameMap newMap = new GameMap(mapSize);
			newMap.fillMap(mapCharacters);
			GameContext.getInstance().setGameMap(newMap);
		}
		repositoryCallbackText(PropertyKey.REPOSITORY_MAP);
		callback(GameContext.getInstance().getGameMap().getPrettyPrint());
	}

	@EventMapping(key = EventKey.MAP_NAVIGATION, followupKey = EventKey.MAP_NAVIGATION)
	public void navigation(final String text) {
		if (TextAction.RANDOM.matches(text)) {
			// search randomly
			executeMapItemCallback(GameContext.getInstance().getGameMap().navigateRandom());
		} else if (StringUtils.matchesCoordinates(text)) {
			// search by provided coordinates
			String[] coors = text.split(",");
			int x = Integer.parseInt(coors[0]);
			int y = Integer.parseInt(coors[1]);
			executeMapItemCallback(GameContext.getInstance().getGameMap().navigate(x, y));
		} else
			callback(Text.INVALID_MAP_COORDINATES);
	}

	private void executeMapItemCallback(final MapItem item) {
		final GameCharacter character = item.getMapCharacter();
		if (character == null) {
			GameContext.getInstance().getUserCharacter().addGold(item.getGold());
			callbackFormat(Text.EXPLORE_MAP_EMPTY_ITEM, item.getGold());
		} else {
			final UserCharacter uCharacter = GameContext.getInstance().getUserCharacter();
			repositoryFormatText(PropertyKey.REPOSITORY_ASK_FOR_FIGHT, uCharacter.getName(), uCharacter.getLevel(),
					uCharacter.getHealth(), uCharacter.getStrength(), uCharacter.getAgility(), uCharacter.getMindset(),
					character.getName(), character.getLevel(), character.getHealth(), character.getStrength(),
					character.getAgility(), character.getMindset());
		}
		GameContext.getInstance().getGameMap().setCurrentItem(item);
	}
}
