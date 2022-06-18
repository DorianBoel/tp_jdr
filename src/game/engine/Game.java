package game.engine;

import java.util.Scanner;

import game.entities.Monster;
import game.entities.PlayerCharacter;

public class Game {

	//Instance attributes
	private static PlayerCharacter playerCharacter;
	private static Monster currentMonster;
	
	//Instance methods
	private static void generateCharacter() {
		playerCharacter = new PlayerCharacter();
		GameDisplay.displayState("new-character", playerCharacter, currentMonster);;
	}
	
	private static void fight(String type) {
		if (playerCharacter.getHealth() <= 0) {
			GameDisplay.displayState("game-over", playerCharacter, currentMonster);
			return;
		}
		currentMonster = new Monster(type);
		GameDisplay.displayState("fight-start", playerCharacter, currentMonster);
		while (playerCharacter.getHealth() > 0 && currentMonster.getHealth() > 0) {
			fightRound();
		}
		if (playerCharacter.getHealth() <= 0) {
			GameDisplay.displayState("player-defeat", playerCharacter, currentMonster);
			GameDisplay.displayState("game-over", playerCharacter, currentMonster);
			return;
		}
		playerCharacter.addScore(currentMonster.getPoints());
		GameDisplay.displayState("player-win", playerCharacter, currentMonster);
	}
	
	private static void fightRound() {
		int playerAttack = playerCharacter.getStrength() + (int) Math.floor(Math.random() * (10 - 1) + 1);
		int monsterAttack = currentMonster.getStrength() + (int) Math.floor(Math.random() * (10 - 1) + 1);
		int diff = playerAttack - monsterAttack;
		if (diff > 0) {
			currentMonster.receiveDamage(diff);
			GameDisplay.displayState("player-attack", playerCharacter, currentMonster, diff);
		} else if (diff < 0) {
			playerCharacter.receiveDamage(-diff);
			GameDisplay.displayState("monster-attack", playerCharacter, currentMonster, -diff);
		} else {
			GameDisplay.displayState("resist-attack", playerCharacter, currentMonster);
		}
	}
	
	public static void start() {
		GameDisplay.displayState("start");
		GameDisplay.displayState("menu");
	}
	
	public static void createCharacter(Scanner scanner) {
		if (Game.playerCharIsDefined() && Game.playerCharIsAlive()) {
			GameDisplay.displayState("confirm-new-character");
			String confirmCharacterOverride = MenuSelect.acceptInput(scanner, "Y", "N", "");
			if (confirmCharacterOverride.equals("Y")) {
				generateCharacter();
			}
			return;
		}
		generateCharacter();
	}
	
	public static void fightMonster(Scanner scanner) {
		if (!playerCharIsDefined()) {
			GameDisplay.displayState("undefined-character");
			return;
		}
		if (!playerCharIsAlive()) {
			GameDisplay.displayState("game-over", playerCharacter, currentMonster);
			return;
		}
		GameDisplay.displayState("fight-menu");
		String monsterSelect = MenuSelect.acceptInput(scanner, "loup", "gobelin", "troll", "N", "");
		switch (monsterSelect.toLowerCase()) {
			case ("loup") :
			case ("gobelin") :
			case ("troll") :
				fight(monsterSelect.toLowerCase());
				break;
			case ("") :
			case ("N") :
				displayMenu();
				break;
			default :
				GameDisplay.displayState("invalid");
		}
	}
	
	public static void displayCharacterInfo() {
		if (!Game.playerCharIsDefined()) {						
			GameDisplay.displayState("undefined-character");
			return;
		}
		GameDisplay.displayState("player-info", playerCharacter, currentMonster);
	}
	
	public static void displayMenu() {
		GameDisplay.displayState("menu");
	}
	
	public static boolean quitGame(Scanner scanner) {
		GameDisplay.displayState("confirm-quit");
		String confirmQuit = MenuSelect.acceptInput(scanner, "Y", "N", "");
		if (confirmQuit.equals("Y")) {
			GameDisplay.displayState("exit");
			return true;
		}
		return false;
	}
	
	public static void defaultOption() {
		GameDisplay.displayState("invalid");
	}
	
	private static boolean playerCharIsDefined() {
		return playerCharacter != null;
	}
	
	private static boolean playerCharIsAlive() {
		return playerCharacter.getHealth() > 0;
	}
	
}
