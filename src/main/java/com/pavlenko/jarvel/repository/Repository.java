package com.pavlenko.jarvel.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.pavlenko.jarvel.game.GameContext;
import com.pavlenko.jarvel.game.character.impl.UserCharacter;
import com.pavlenko.jarvel.game.proxy.ContextProxy;
import com.pavlenko.jarvel.property.PropertyHolder;
import com.pavlenko.jarvel.property.PropertyKey;

/**
 * {@code Repository} class is a simple implementation of data storage
 * connection
 * 
 * provides data access based on system file architecture basically used for
 * fetch/store/update files
 * 
 * @author Rostislav P.A.
 */
public final class Repository {
	private static Repository instance;
	private static final Logger LOGGER = Logger.getLogger(Repository.class.getName());

	private Repository() {
	}

	public static Repository getInstance() {
		if (instance == null)
			instance = new Repository();

		return instance;
	}

	/**
	 * method for text extraction
	 * 
	 * @param key
	 * @return
	 */
	public Optional<String> findCallbackText(final PropertyKey key) {
		final Optional<byte[]> optBytes = readBytesFrom(PropertyKey.REPOSITORY_CALLBACKS, key);
		return optBytes.isPresent() ? Optional.of(new String(optBytes.get())) : Optional.empty();
	}

	private Optional<byte[]> readBytesFrom(final PropertyKey... keys) {
		try {
			return Optional.of(Files.readAllBytes(Paths.get(PropertyHolder.getInstance().getFilePath(keys))));
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

		return Optional.empty();
	}

	/**
	 * Causes game storing
	 * 
	 * defining new directory path and make a serialization over game {#link
	 * ContextProxy} class
	 * 
	 */
	public void saveGame() {
		try (FileOutputStream inputStream = new FileOutputStream(prepareSnapshotPath(), true)) {
			final ObjectOutputStream oos = new ObjectOutputStream(inputStream);
			oos.writeObject(new ContextProxy(GameContext.getInstance()));
			oos.flush();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	/**
	 * Causes game loading
	 * 
	 * lookup over all saved snapshots for the exact match by name and make a
	 * deserialization over game {#link ContextProxy} class
	 * 
	 * @param text
	 * @return
	 */
	public Optional<ContextProxy> loadGame(String text) {
		final Optional<List<String>> optSavedList = findAllSavedGames();
		if (optSavedList.isPresent() && optSavedList.get().contains(text)) {
			final String snapshotPath = PropertyHolder.getInstance().getFilePath(PropertyKey.REPOSITORY_SNAPSHOT)
					.concat(text);
			try (FileInputStream inputStream = new FileInputStream(snapshotPath)) {
				final ObjectInputStream oos = new ObjectInputStream(inputStream);
				ContextProxy ctxp = (ContextProxy) oos.readObject();
				return Optional.of(ctxp);
			} catch (IOException | ClassNotFoundException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
		}

		return Optional.empty();
	}

	/**
	 * support method to prepare path where new snapshot would be located
	 * 
	 * @return
	 * @throws IOException
	 */
	private String prepareSnapshotPath() throws IOException {
		final StringBuilder snapshotDirectory = new StringBuilder(
				PropertyHolder.getInstance().getFilePath(PropertyKey.REPOSITORY_SNAPSHOT));
		Files.createDirectories(Paths.get(snapshotDirectory.toString()));
		// add sequence
		final Optional<List<String>> optSavedList = findAllSavedGames();
		snapshotDirectory.append(optSavedList.isPresent() ? optSavedList.get().size() + 1 : 1).append("_");
		// add user identity
		final UserCharacter uCharachter = GameContext.getInstance().getUserCharacter();
		if (uCharachter != null)
			snapshotDirectory.append(uCharachter.getName()).append("_");

		snapshotDirectory.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")))
				.append(".ser");
		return snapshotDirectory.toString();
	}

	/**
	 * Causes lookup for all saved snapshots
	 * 
	 * simple return list with names
	 * 
	 * @return
	 */
	public Optional<List<String>> findAllSavedGames() {
		final File[] files = new File(PropertyHolder.getInstance().getFilePath(PropertyKey.REPOSITORY_SNAPSHOTS))
				.listFiles();
		return (files == null || files.length == 0) ? Optional.empty()
				: Optional.of(Arrays.stream(files).map(File::getName).collect(Collectors.toList()));
	}
}
