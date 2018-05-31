package com.pavlenko.jarvel.game.event.callback;

import java.text.MessageFormat;
import java.util.Optional;

import com.pavlenko.jarvel.game.event.model.Text;
import com.pavlenko.jarvel.property.PropertyKey;
import com.pavlenko.jarvel.repository.Repository;

/**
 * EventCallback implementation to print event callback based on file system
 * 
 * @author Rostyslav P.A.
 *
 */
public interface EventRepositoryCallback extends EventCallback {
	default void repositoryCallbackText(Repository repository, PropertyKey targetKey, final Text defaultText) {
		final Optional<String> optCallbackText = repository.findCallbackText(targetKey);
		if (optCallbackText.isPresent()) {
			callback(optCallbackText.get());
		} else
			callback(defaultText);
	}

	default void repositoryCallbackText(Repository repository, PropertyKey targetKey) {
		repositoryCallbackText(repository, targetKey, Text.DEFAULT_ERROR);
	}

	default void repositoryFormatText(Repository repository, PropertyKey targetKey, Object... objects) {
		final Optional<String> optCallbackText = repository.findCallbackText(targetKey);
		if (optCallbackText.isPresent()) {
			callback(MessageFormat.format(optCallbackText.get(), objects));
		} else
			callback(Text.DEFAULT_ERROR);
	}
}
