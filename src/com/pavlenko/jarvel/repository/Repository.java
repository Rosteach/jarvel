package com.pavlenko.jarvel.repository;

import java.util.List;
import java.util.Optional;

import com.pavlenko.jarvel.game.impl.SimpleGameContext;
import com.pavlenko.jarvel.property.PropertyHolder;
import com.pavlenko.jarvel.property.PropertyKey;

public interface Repository {
	Optional<String> findCallbackText(PropertyKey key);

	Optional<List<String>> findAllSavedGames();

	Optional<String> saveGameContext(SimpleGameContext context);

	PropertyHolder getProps();
}
