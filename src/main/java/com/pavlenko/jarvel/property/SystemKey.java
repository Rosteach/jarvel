package com.pavlenko.jarvel.property;

public enum SystemKey {
	USER_DIR("user.dir"),

	USER_NAME("user.name");

	private String key;

	private SystemKey(String key) {
		this.key = key;
	}

	public String key() {
		return this.key;
	}
}
