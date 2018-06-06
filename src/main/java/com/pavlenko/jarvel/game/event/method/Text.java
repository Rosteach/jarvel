package com.pavlenko.jarvel.game.event.method;

import java.text.MessageFormat;

public enum Text {
	DEFAULT_ENTRY("Welcome {0}"),

	DEFAULT_ERROR("Sorry something is going wrong! Please check configuration or ping support!"),

	DEFAULT_FALLBACK("Sorry, I missed that. Could you rephrase that?\n If you need help, simply ask me about!"),

	DEFAULT_INVALID_INPUT("Invalid input, should be not empty and have length less than 100 characters!"),

	NEW_GAME("Gotham, march 2006\nUptown sewerage\nFirstly you need to create the character."),

	ASK_NAME("What would be your name?"),

	ASK_RACE("Please choose the race:"),

	ASK_GENDER("Please define your gender:"),

	ASK_STRENGTH_INFO(
			"Now you need to distribute 12 points between your innate abilities(4 points max per each ability)."),

	ASK_STRENGTH("So, the strength is first:"),

	ASK_AGILITY("Make your movements more precise, improve your agility:"),

	ASK_MINDSET("Impact your mindset to make your superpower stronger:"),

	ASK_SUPER_POWER("The last is your super power:"),

	LOAD_GAME("Cool, here is a list saved snapshots:"),

	LOAD_GAME_SNAPSHOT("Please defined an exact name of snapshot to load:"),

	EXPLORE("Where do you want to go?"),

	GREETING("Hello {0}, what do you want to do next?"),

	UNKNOWN("Unknown"),

	GAME_LIST_IS_EMPTY("You have no saved games, please create one with 'NEW GAME' option!"),

	INVALID_CHARACTER_BONUS("Invalid input, should be digit [1-4]"),

	UNEXPECETD_INPUT("Please define one of the following options."),

	COMPLETE_CHARACTER_SETUP("Well done! Your character was created:"),

	ASK_WHAT_TO_DO_NEXT("What do you want to do next?"),

	SAVED_WITH_SUCCESS("Your game was successfully saved!"),

	EXPLORE_NOT_POSSIBLE("In order to explore the map, you need to create or load your character!"),

	EXPLORE_ASK_COORDINATES("Try figure out where do you want to go?"),

	EXPLORE_MAP_EMPTY_ITEM("Hooora!! You find {0} gold coins!"),

	INVALID_MAP_COORDINATES(
			"You provided invalid coordinates, please follow this pattern 0,0 as an example or use 'random'!"),

	INVALID_FIGHT_ACTION_USAGE("You have no enemy to fight, please explore the map to find one."),

	FIGHT_POWERFUL_ATTACK_FAILED("POWER ATTACK FAILED!"),

	FIGHT_LEVEL_UP("LEVEL UP"),

	INVALID_LOAD_SNAPSHOT("I couldn't find your snapshot, please provide correct snapshot name!"), 
	
	LOAD_WITH_SUCCESS("Your game has loaded with success!");

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
