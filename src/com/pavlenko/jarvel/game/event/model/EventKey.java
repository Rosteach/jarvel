package com.pavlenko.jarvel.game.event.model;

import java.util.Arrays;

public enum EventKey {
	DEFAULT_ENTRY,

	DEFAULT_FALLBACK,

	DEFAULT_HELP(new String[] { "help", "support" }),

	EXPLORE(new String[] { "investigate","explore" }),

	GREETING(new String[] { "hi", "hello" }),

	DEFAULT_MENU(new String[] { "dashboard", "menu" }),

	NEW_GAME(new String[] { "new", "start" }),

	LOAD_GAME(new String[] { "load" }),

	SAVE_GAME(new String[] { "save" }),

	ASK_NAME,

	ASK_GENDER,

	ASK_STRENGTH,

	ASK_AGILITY,

	ASK_MINDSET,

	ASK_RACE,
	
	ASK_SUPER_POWER,

	COMPLETE_CHARACTER_SETUP,

	CHARACTER(new String[] { "info" }),

	INVENTORY(new String[] { "inventory" }),
	
	LOAD_GAME_SNAPSHOT, 
	
	FIGHT;

	private String[] keywords;

	private EventKey() {
	}

	private EventKey(String[] keywords) {
		this.keywords = keywords;
	}

	private String[] getKeywords() {
		return this.keywords == null ? new String[] { name() } : this.keywords;
	}

	public static EventKey prepare(String textEvent) {
		return Arrays.stream(values()).filter(v -> Arrays.stream(v.getKeywords()).anyMatch(textEvent::contains))
				.findFirst().orElse(EventKey.DEFAULT_FALLBACK);
	}

	public boolean isDefault() {
		return this.equals(DEFAULT_FALLBACK);
	}

}
