package com.pavlenko.jarvel.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public enum CharachterSetupModel {
	STEP_1("new,new game"),

	STEP_2("rosteach"),

	STEP_3("human,criptonian,dwarf,elf,beast"),

	STEP_4("male,female,it"),

	STEP_5("1,2,3,4"),

	STEP_6("1,2,3,4"),

	STEP_7("1,2,3,4"),

	STEP_8("damage,healing,armor");

	private static final String SPLITTERATOR = ",";
	private static final Random RANDOM = new Random();

	private String vars;

	private CharachterSetupModel(String vars) {
		this.vars = vars;
	}

	public String[] varArray() {
		return this.vars.split(SPLITTERATOR);
	}

	public static List<String> createMicroModel() {
		return Arrays.stream(values()).map(v -> {
			final String[] array = v.varArray();
			return array[RANDOM.nextInt(array.length)];
		}).collect(Collectors.toList());
	}
}
