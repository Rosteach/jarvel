package com.pavlenko.jarvel.property;

public enum PropertyKey {
	REPOSITORY_PATH("repository.path"),

	REPOSITORY_CALLBACKS("repository.callbacks"),

	REPOSITORY_BANNER("repository.callback.banner"),

	REPOSITORY_MENU("repository.callback.menu"),

	REPOSITORY_HELP("repository.callback.help"),

	REPOSITORY_SNAPSHOTS("repository.snapshots"),

	REPOSITORY_SNAPSHOT("repository.snapshot"),

	REPOSITORY_ASK_GENDER("repository.callback.gender"),

	REPOSITORY_ASK_RACE("repository.callback.race"),
	
	REPOSITORY_ASK_SUPER_POWER("repository.callback.super.power"),

	REPOSITORY_CHARACTER_ACTIONS("repository.callback.character.action"),

	REPOSITORY_SKILLS_DESCRIPTION("repository.callback.skills.description"),

	REPOSITORY_MAP("repository.callback.map"),

	MAP_SIZE("map.size"),

	MAP_CHARACTERS_SIZE("map.charachters.size"), 
	
	INVENTORY_DEFAULT_SETUP("character.default.setup");

	private String key;

	private PropertyKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}
}
