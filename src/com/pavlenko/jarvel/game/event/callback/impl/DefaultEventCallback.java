package com.pavlenko.jarvel.game.event.callback.impl;

import com.pavlenko.jarvel.game.event.callback.EventRepositoryCallback;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity.CallbackKey;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventMapping;
import com.pavlenko.jarvel.game.event.model.EventKey;
import com.pavlenko.jarvel.game.event.model.Text;
import com.pavlenko.jarvel.property.PropertyKey;
import com.pavlenko.jarvel.property.SystemKey;
import com.pavlenko.jarvel.repository.Repository;

@EventCallbackEntity(callbackKey = CallbackKey.DEFAULT)
public class DefaultEventCallback implements EventRepositoryCallback {
	private Repository repository;

	public DefaultEventCallback(Repository repository) {
		this.repository = repository;
	}

	public Repository getRepo() {
		return repository;
	}

	public void setRepo(Repository repository) {
		this.repository = repository;
	}

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
		repositoryCallbackText(repository, PropertyKey.REPOSITORY_BANNER);

		final String sysName = System.getProperty(SystemKey.USER_NAME.key());
		final String resultName = (sysName == null || sysName.length() == 0) ? Text.UNKNOWN.val() : sysName;
		callback(Text.DEFAULT_ENTRY.valFormat(resultName));
		menu();
	}

	@EventMapping(key = EventKey.DEFAULT_MENU)
	public void menu() {
		repositoryCallbackText(repository, PropertyKey.REPOSITORY_MENU);
	}

	@EventMapping(key = EventKey.DEFAULT_HELP)
	public void help() {
		repositoryCallbackText(repository, PropertyKey.REPOSITORY_HELP);
	}
}
