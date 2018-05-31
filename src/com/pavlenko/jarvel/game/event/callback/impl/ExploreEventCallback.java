package com.pavlenko.jarvel.game.event.callback.impl;

import java.util.Arrays;

import com.pavlenko.jarvel.game.event.callback.EventRepositoryCallback;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity.CallbackKey;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventMapping;
import com.pavlenko.jarvel.game.event.model.EventKey;
import com.pavlenko.jarvel.game.event.model.Text;
import com.pavlenko.jarvel.game.fight.FightProcessor;
import com.pavlenko.jarvel.game.impl.SimpleGameContext;
import com.pavlenko.jarvel.property.PropertyKey;
import com.pavlenko.jarvel.repository.Repository;
import com.pavlenko.jarvel.utils.CharacterUtils;

@EventCallbackEntity(callbackKey = CallbackKey.EXPLORE)
public class ExploreEventCallback implements EventRepositoryCallback {
	private Repository repository;
	private SimpleGameContext context;
	private FightProcessor fightProcessor;

	public ExploreEventCallback(Repository repository, SimpleGameContext context, FightProcessor fightProcessor) {
		super();
		this.repository = repository;
		this.context = context;
		this.fightProcessor = fightProcessor;
	}

	@EventMapping(key = EventKey.EXPLORE)
	public void explore() {
		if (context.getUserCharacter() == null) {
			callback(Text.EXPLORE_NOT_POSSIBLE);
		} else
			initExploreCallback();
	}

	private void initExploreCallback() {
		int mapSize = repository.getProps().getIntProperty(PropertyKey.MAP_SIZE);
		context.initializeMap(mapSize, Arrays.asList(CharacterUtils.createRandomCharachter()));
		repositoryFormatText(repository, PropertyKey.REPOSITORY_MAP, mapSize, mapSize);
	}
}
