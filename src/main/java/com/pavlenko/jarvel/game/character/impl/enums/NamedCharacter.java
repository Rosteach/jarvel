package com.pavlenko.jarvel.game.character.impl.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public enum NamedCharacter {
	SUPERMAN("Superman", Race.CRIPTONIAN, Gender.MALE, SuperPowerType.DAMAGE),

	ZORG("General Zorg", Race.CRIPTONIAN, Gender.MALE, SuperPowerType.DAMAGE),

	DOOMSDAY("Doomsday", Race.CRIPTONIAN, Gender.MALE, SuperPowerType.DAMAGE),

	// human male names
	AQUAMAN("Aquaman", Race.HUMAN, Gender.MALE, SuperPowerType.DAMAGE),

	BANE("Bane", Race.HUMAN, Gender.MALE, SuperPowerType.DAMAGE),

	BATMAN("Batman", Race.HUMAN, Gender.MALE, SuperPowerType.DAMAGE),

	JOKER("Joker", Race.HUMAN, Gender.MALE, SuperPowerType.DAMAGE),

	FLASH("Flash", Race.HUMAN, Gender.MALE, SuperPowerType.DAMAGE),

	LOBO("Lobo", Race.HUMAN, Gender.MALE, SuperPowerType.DAMAGE),

	// human female names
	CATWOMAN("Catwoman", Race.HUMAN, Gender.FEMALE, SuperPowerType.DAMAGE),

	HARLEY_QUINN("Harley Quinn", Race.HUMAN, Gender.FEMALE, SuperPowerType.DAMAGE),

	WONDER_WOMAN("Wonder Woman", Race.HUMAN, Gender.FEMALE, SuperPowerType.DAMAGE),

	STARFIRE("Starfire", Race.HUMAN, Gender.FEMALE, SuperPowerType.DAMAGE),

	// elf male names
	ZEVRAN("Zevran", Race.ELF, Gender.MALE, SuperPowerType.HEALING),

	STALLION("Stallion", Race.ELF, Gender.MALE, SuperPowerType.HEALING),

	LEGOLAS("Legolas", Race.ELF, Gender.MALE, SuperPowerType.HEALING),

	XAN("Xan", Race.ELF, Gender.MALE, SuperPowerType.HEALING),

	MALFURION("Malfurion", Race.ELF, Gender.MALE, SuperPowerType.HEALING),

	// elf female name
	TYRANDE("Tyrande", Race.ELF, Gender.FEMALE, SuperPowerType.HEALING),

	FIRIONA_VIE("Firiona Vie", Race.ELF, Gender.FEMALE, SuperPowerType.HEALING),

	ARWEN_EVENSTAR("Arwen Evenstar", Race.ELF, Gender.FEMALE, SuperPowerType.HEALING),

	GALADRIEL("Galadriel", Race.ELF, Gender.FEMALE, SuperPowerType.HEALING),

	// dwarf male names
	GIMLI("Gimli", Race.DWARF, Gender.MALE, SuperPowerType.HEALING),

	GLOIN("Gloin", Race.DWARF, Gender.MALE, SuperPowerType.HEALING),

	RONRUG("Ronrug", Race.DWARF, Gender.MALE, SuperPowerType.HEALING),

	TANGRIM("Tangrim", Race.DWARF, Gender.MALE, SuperPowerType.HEALING),

	TANWIN("Tanwin", Race.DWARF, Gender.MALE, SuperPowerType.HEALING),

	XALWIN("Xalwin", Race.DWARF, Gender.MALE, SuperPowerType.HEALING),

	// dwarf female names
	NYMIPHI("Nymiphi", Race.DWARF, Gender.FEMALE, SuperPowerType.HEALING),

	LORINIANI("Loriniana", Race.DWARF, Gender.FEMALE, SuperPowerType.HEALING),

	RETINA("Retina", Race.DWARF, Gender.FEMALE, SuperPowerType.HEALING),

	VENXIS("Venxis", Race.DWARF, Gender.FEMALE, SuperPowerType.HEALING),

	// beast names
	RAT("Rat", Race.BEAST, Gender.IT, SuperPowerType.DAMAGE),

	WOLF("Wolf", Race.BEAST, Gender.IT, SuperPowerType.DAMAGE),

	WILD_BOAR("Wild boar", Race.BEAST, Gender.IT, SuperPowerType.DAMAGE),

	GORILLA_GRODD("Gorilla Grodd", Race.BEAST, Gender.IT, SuperPowerType.DAMAGE),

	SWAMP_THING("Swamp thing", Race.BEAST, Gender.IT, SuperPowerType.DAMAGE),

	DARKSEID("Darkseid", Race.BEAST, Gender.IT, SuperPowerType.DAMAGE),

	BLUE_BEETLE("Blue Beetle", Race.BEAST, Gender.IT, SuperPowerType.HEALING),

	SCARE_CROW("Scarecrow", Race.BEAST, Gender.IT, SuperPowerType.DAMAGE),

	LONELYWINGS("Lonelywings", Race.BEAST, Gender.IT, SuperPowerType.DAMAGE),

	JAGGEDBODY("Jaggedbody", Race.BEAST, Gender.IT, SuperPowerType.DAMAGE),

	AGITATEDHAG("Agitatedhag", Race.BEAST, Gender.IT, SuperPowerType.DAMAGE),

	SKELETALWING("Skeletalwing", Race.BEAST, Gender.IT, SuperPowerType.HEALING);

	private static final NamedCharacter[] VALUES = values();
	private static final Random RANDOM = new Random();

	private String name;
	private Race race;
	private Gender gender;
	private SuperPowerType superPowerType;

	private NamedCharacter(String name, Race race, Gender gender, SuperPowerType superPowerType) {
		this.name = name;
		this.race = race;
		this.gender = gender;
		this.superPowerType = superPowerType;
	}

	public String getName() {
		return name;
	}

	public Race getRace() {
		return race;
	}

	public Gender getGender() {
		return gender;
	}

	public SuperPowerType getSuperPowerType() {
		return superPowerType;
	}

	public static NamedCharacter randomOf(Race... races) {
		final List<NamedCharacter> list = Arrays.stream(VALUES)
				.filter(v -> Arrays.stream(races).anyMatch(r -> r.equals(v.getRace()))).collect(Collectors.toList());
		return list.get(RANDOM.nextInt(list.size()));
	}
}
