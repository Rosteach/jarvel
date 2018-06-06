package com.pavlenko.jarvel.game.event.callback.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.pavlenko.jarvel.game.GameContext;
import com.pavlenko.jarvel.game.event.callback.EventRepositoryCallback;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity.CallbackKey;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventMapping;
import com.pavlenko.jarvel.game.event.method.EventKey;
import com.pavlenko.jarvel.game.event.method.Text;
import com.pavlenko.jarvel.game.proxy.ContextProxy;
import com.pavlenko.jarvel.property.PropertyKey;
import com.pavlenko.jarvel.property.SystemKey;
import com.pavlenko.jarvel.repository.Repository;

@EventCallbackEntity(callbackKey = CallbackKey.DEFAULT)
public class DefaultEventCallback implements EventRepositoryCallback {

	@EventMapping(key = EventKey.GREETING)
	public void greeting() {
		callbackFormat(Text.GREETING, System.getProperty(SystemKey.USER_NAME.key()));
	}

	@EventMapping(key = EventKey.DEFAULT_FALLBACK)
	public void fallback() {
		callback(Text.DEFAULT_FALLBACK);
	}

	@EventMapping(key = EventKey.DEFAULT_ENTRY)
	public void entry() {
		repositoryCallbackText(PropertyKey.REPOSITORY_BANNER);

		final String sysName = System.getProperty(SystemKey.USER_NAME.key());
		final String resultName = (sysName == null || sysName.length() == 0) ? Text.UNKNOWN.val() : sysName;
		callback(Text.DEFAULT_ENTRY.valFormat(resultName));
		menu();
	}

	@EventMapping(key = EventKey.DEFAULT_MENU)
	public void menu() {
		repositoryCallbackText(PropertyKey.REPOSITORY_MENU);
	}

	@EventMapping(key = EventKey.NEW_GAME, followupKey = EventKey.ASK_RACE)
	public void newGame() {
		callback(Text.NEW_GAME);
		callback(Text.ASK_NAME);
	}

	@EventMapping(key = EventKey.LOAD_GAME, followupKey = EventKey.LOAD_GAME_SNAPSHOT)
	public void loadGame() {
		final Optional<List<String>> optGameList = Repository.getInstance().findAllSavedGames();

		if (optGameList.isPresent()) {
			callback(Text.LOAD_GAME);
			final List<String> list = optGameList.get();
			Collections.reverse(list);
			list.forEach(this::callback);
			callback(Text.LOAD_GAME_SNAPSHOT);
		} else
			callback(Text.GAME_LIST_IS_EMPTY);
	}

	@EventMapping(key = EventKey.LOAD_GAME_SNAPSHOT)
	public void loadGameSnapshot(String text) {
		final Optional<ContextProxy> optProxy = Repository.getInstance().loadGame(text);
		if (optProxy.isPresent()) {
			final ContextProxy proxy = optProxy.get();

			GameContext.getInstance().setUserCharacter(proxy.getUserCharacter());
			GameContext.getInstance().setCurrentKey(proxy.getCurrentKey());
			GameContext.getInstance().setFollowupKey(proxy.getFollowupKey());
			GameContext.getInstance().setGameMap(proxy.getGameMap());
			callback(Text.LOAD_WITH_SUCCESS);
			callback(GameContext.getInstance().getCurrentKey().name());
			callback(GameContext.getInstance().getFollowupKey().name());
			callback(GameContext.getInstance().getUserCharacter().toString());
			callback(GameContext.getInstance().getGameMap().getPrettyPrint());
		} else
			callback(Text.INVALID_LOAD_SNAPSHOT);
	}

	@EventMapping(key = EventKey.SAVE_GAME)
	public void saveGame() {
		Repository.getInstance().saveGame();
		callbackFormat(Text.SAVED_WITH_SUCCESS);
		callback(Text.ASK_WHAT_TO_DO_NEXT);
	}

	@EventMapping(key = EventKey.DEFAULT_HELP)
	public void help() {
		repositoryCallbackText(PropertyKey.REPOSITORY_HELP);
	}

}
