package com.pavlenko.jarvel.game.event.callback;

import java.text.MessageFormat;
import java.util.Optional;

import com.pavlenko.jarvel.game.event.method.Text;
import com.pavlenko.jarvel.property.PropertyKey;
import com.pavlenko.jarvel.repository.Repository;

/**
 * {@code EventRepositoryCallback} implementation to fetch text content from
 * repository
 * 
 * @author Rostyslav P.A.
 */
public interface EventRepositoryCallback extends EventCallback {
	default void repositoryCallbackText(PropertyKey targetKey, Text defaultText) {
		final Optional<String> optCallbackText = Repository.getInstance().findCallbackText(targetKey);
		if (optCallbackText.isPresent()) {
			callback(optCallbackText.get());
		} else
			callback(defaultText);
	}

	default void repositoryCallbackText(PropertyKey targetKey) {
		repositoryCallbackText(targetKey, Text.DEFAULT_ERROR);
	}

	default void repositoryFormatText(PropertyKey targetKey, Object... objects) {
		final Optional<String> optCallbackText = Repository.getInstance().findCallbackText(targetKey);
		if (optCallbackText.isPresent()) {
			callback(MessageFormat.format(optCallbackText.get(), objects));
		} else
			callback(Text.DEFAULT_ERROR);
	}

}
