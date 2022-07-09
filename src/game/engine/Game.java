package game.engine;

import java.util.Scanner;

import game.entities.Monster;
import game.entities.PlayerCharacter;

/**
 * Non-instantiable class containing all major methods 
 * directly called from the main menu based on user prompt,
 * as well as secondary private methods. 
 * Also contains static attributes used to 
 * store the current game entities.
 *
 * @author DorianBoel
 */
public class Game {

	/**
	 * The player character currently in use.
	 * 
	 * @see game.entities.PlayerCharacter
	 */
	private static PlayerCharacter playerCharacter;
	
	/**
	 * The monster currently being fought.
	 * 
	 * @see game.entities.Monster
	 */
	private static Monster currentMonster;
	
	/**
	 * Displays the game start message and the main menu
	 */
	public static void start() {
		GameDisplay.displayState("start");
		GameDisplay.displayState("menu");
	}
	
	/**
	 * Creates a new player character after checking if 
	 * a character already exists and is still alive.
	 * If that's the case the user is prompted to confirm using a Y/N input
	 * before overriding the current player character.
	 * 
	 * @param scanner The main prompt {@link java.util.Scanner Scanner}
	 */
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
	
	/**
	 * Checks if a player character is defined and is alive, then
	 * prompts the user to input which type of monster to fight between
	 * "loup", "gobelin" and "troll".
	 * 
	 * @param scanner The main prompt {@link java.util.Scanner Scanner}
	 */
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
			case ("n") :
				displayMenu();
				break;
			default :
				GameDisplay.displayState("invalid");
		}
	}
	
	/**
	 * Displays the current player character's stats 
	 * and score if one is defined, otherwise displays
	 * a message asking the user to create a new character.
	 */
	public static void displayCharacterInfo() {
		if (!Game.playerCharIsDefined()) {						
			GameDisplay.displayState("undefined-character");
			return;
		}
		GameDisplay.displayState("player-info", playerCharacter, currentMonster);
	}
	
	/**
	 * Displays the main menu
	 */
	public static void displayMenu() {
		GameDisplay.displayState("menu");
	}
	/**
	 * Prompts the user with a Y/N input to confirm
	 * closing the game. 
	 * 
	 * @param scanner The main prompt {@link java.util.Scanner Scanner}
	 * @return {@code true} if the user confirms the Y/N prompt, and {@code false} otherwise.
	 */
	public static boolean quitGame(Scanner scanner) {
		GameDisplay.displayState("confirm-quit");
		String confirmQuit = MenuSelect.acceptInput(scanner, "Y", "N", "");
		if (confirmQuit.equals("Y")) {
			GameDisplay.displayState("exit");
			return true;
		}
		return false;
	}
	
	/**
	 * Displays a message stating a received user input is 
	 * not recognized as a valid option.
	 */
	public static void defaultOption() {
		GameDisplay.displayState("invalid");
	}
	
	/**
	 * Checks if the current player character is not null.
	 * 
	 * @return {@code true} if the current player character is not null,
	 * and {@code false} otherwise.
	 */
	private static boolean playerCharIsDefined() {
		return playerCharacter != null;
	}
	
	/**
	 * Checks if the current player character has health points above 0.
	 * 
	 * @return {@code true} if the current player character's health is above 0,
	 * and {@code false} otherwise.
	 */
	private static boolean playerCharIsAlive() {
		return playerCharacter.getHealth() > 0;
	}
	
	/**
	 * Instantiates then stores a new player character,
	 * then displays its combat statistics.
	 * 
	 * @see game.entities.PlayerCharacter#PlayerCharacter()
	 */
	private static void generateCharacter() {
		playerCharacter = new PlayerCharacter();
		GameDisplay.displayState("new-character", playerCharacter, currentMonster);;
	}
	
	
	/**
	 * Instantiates a new monster of the given type, then initiates
	 * a fight with it by calling {@link game.engine.Game#fightRound() fightRound}
	 * until the player defeats the monster or is defeated.
	 * Once the fight is over, displays the results by showing the new score in case of a player win,
	 * or a game over in case of a loss.
	 * 
	 * @param type The type of the monster to fight
	 */
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
	
	/**
	 * Compares the strengths of both the current player character 
	 * and the current monster after adding a random value between 1 and 10,
	 * then calculates the difference between those two values.
	 * The entity with the lower strength takes damage equal to the difference.
	 * In case of a draw, neither side takes any damage.<br>
	 * The results of the round are then displayed.
	 */
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
}
