package com.pavlenko.jarvel.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.pavlenko.jarvel.game.exception.GameException;

public final class PropertyHolder {
	private static PropertyHolder instance;
	private Properties properties;

	private PropertyHolder(Properties properties) {
		this.properties = properties;
	}

	public static PropertyHolder getInstance() {
		if (instance == null) {
			final Properties props = new Properties();
			try (FileInputStream fis = new FileInputStream("application.properties")) {
				props.load(fis);
				instance = new PropertyHolder(props);
				return instance;
			} catch (IOException e) {
				throw new GameException(
						"application.properties does not exist, please locate properties file under project path!", e);
			}
		}
		return instance;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getProperty(PropertyKey key) {
		return properties.getProperty(key.getKey());
	}

	public int getIntProperty(PropertyKey key) {
		return Integer.valueOf(getProperty(key));
	}

	public String[] getPropertyAsArray(PropertyKey key) {
		final String prop = getProperty(key);
		return prop.split(",");
	}

	public Map<String, String> getPropertyAsMap(PropertyKey key) {
		final String[] array = getPropertyAsArray(key);
		final Map<String, String> map = new HashMap<>();
		for (String s : array) {
			final String[] kV = s.split(":");
			map.put(kV[0], kV[1]);
		}
		return map;
	}

	public String formatProperty(final PropertyKey key, final Object... objects) {
		return MessageFormat.format(properties.getProperty(key.getKey()), objects);
	}

	public String getFilePath(final PropertyKey... keys) {
		final StringBuilder builder = new StringBuilder(
				formatProperty(PropertyKey.REPOSITORY_PATH, System.getProperty(SystemKey.USER_DIR.key())));
		for (PropertyKey key : keys)
			builder.append(getProperty(key));
		return builder.toString();
	}
}
