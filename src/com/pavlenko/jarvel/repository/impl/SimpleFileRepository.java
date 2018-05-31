package com.pavlenko.jarvel.repository.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pavlenko.jarvel.game.impl.SimpleGameContext;
import com.pavlenko.jarvel.game.proxy.ContextProxy;
import com.pavlenko.jarvel.property.PropertyHolder;
import com.pavlenko.jarvel.property.PropertyKey;
import com.pavlenko.jarvel.repository.Repository;

public class SimpleFileRepository implements Repository {
	private PropertyHolder propHolder;

	public SimpleFileRepository(PropertyHolder propHolder) {
		this.propHolder = propHolder;
	}

	@Override
	public PropertyHolder getProps() {
		return propHolder;
	}

	public void setProps(PropertyHolder propHolder) {
		this.propHolder = propHolder;
	}

	@Override
	public Optional<String> findCallbackText(final PropertyKey key) {
		final Optional<byte[]> optBytes = readBytesFrom(PropertyKey.REPOSITORY_CALLBACKS, key);
		return optBytes.isPresent() ? Optional.of(new String(optBytes.get())) : Optional.empty();
	}

	@Override
	public Optional<String> saveGameContext(final SimpleGameContext context) {
		final String newSnapshotPath = prepareSnapshotPath(context);
		try (FileOutputStream inputStream = new FileOutputStream(newSnapshotPath, true)) {
			final ObjectOutputStream oos = new ObjectOutputStream(inputStream);
			oos.writeObject(new ContextProxy(context));
			oos.flush();
			return Optional.of(newSnapshotPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	private String prepareSnapshotPath(SimpleGameContext context) {
		final StringBuilder snapshotDirectory = new StringBuilder(
				propHolder.getFilePath(PropertyKey.REPOSITORY_SNAPSHOT));
		if (context.getUserCharacter() != null)
			snapshotDirectory.append(context.getUserCharacter().getName()).append("_");

		snapshotDirectory.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")))
				.append(".out");
		return snapshotDirectory.toString();
	}

	@Override
	public Optional<List<String>> findAllSavedGames() {
		final File[] files = new File(propHolder.getFilePath(PropertyKey.REPOSITORY_SNAPSHOTS)).listFiles();
		return (files == null || files.length == 0) ? Optional.empty()
				: Optional.of(Arrays.stream(files).map(File::getName).collect(Collectors.toList()));
	}

	private Optional<byte[]> readBytesFrom(final PropertyKey... keys) {
		try {
			return Optional.of(Files.readAllBytes(Paths.get(propHolder.getFilePath(keys))));
		} catch (IOException e) {
			// TODO add logger
			e.printStackTrace();
		}
		return Optional.empty();
	}

}
