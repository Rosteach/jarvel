package com.pavlenko.jarvel.game.event.model;

import java.text.MessageFormat;

public enum Text {
	DEFAULT_ENTRY("Welcome {0}"),

	DEFAULT_ERROR("Sorry something is going wrong! Please check configuration or ping support!"),

	DEFAULT_FALLBACK("Sorry, I missed that. Could you rephrase that?\n If you need help, simply ask me about!"),

	NEW_GAME("Gotham, march 2006\nUptown sewerage\nFirstly you need to create the character."),

	ASK_NAME("What would be your name?"),
	
	ASK_RACE("Please choose the race:"),

	ASK_GENDER("Please define your gender:"),
	
	ASK_STRENGTH_INFO("Now you need to distribute 12 points between your innate abilities(4 points max per each ability)."),
	
	ASK_STRENGTH("So, the strength is first:"),
	
	ASK_AGILITY("Make your movements more precise, improve your agility:"),
	
	ASK_MINDSET("Impact your mindset to make your superpower stronger:"),
	
	ASK_SUPER_POWER("The last is your super power:"),
	
	LOAD_GAME("Cool, here is a list saved snapshots:"),

	EXPLORE("Where do you want to go?"),

	GREETING("Hello {0}, what do you want to do next?"),

	UNKNOWN("Unknown"),

	GAME_LIST_IS_EMPTY("You have no saved games, please create one with 'NEW GAME' option!"),
	
	INVALID_CHARACTER_BONUS("Invalid input, should be digit [0-4]"),
	
	UNEXPECETD_INPUT("Please define one of the following options."),
	
	COMPLETE_CHARACTER_SETUP("Well done! Your character was created:"),
	
	ASK_WHAT_TO_DO_NEXT("What do you want to do next?"),
	
	SAVED_WITH_SUCCESS("Your game was successfully saved under {0}"), 
	
	EXPLORE_NOT_POSSIBLE("In order to explore the map, you need to create or load your character!");

	private String value;

	private Text(String value) {
		this.value = value;
	}

	public String val() {
		return this.value;
	}

	public String valFormat(Object... objects) {
		return MessageFormat.format(this.value, objects);
	}
}
