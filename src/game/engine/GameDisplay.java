package game.engine;

import game.entities.Monster;
import game.entities.PlayerCharacter;

/**
 * Non-instanciable class containing methods used to
 * display specific messages to the user based on different
 * game states.
 *
 * @author DorianBoel
 */
public final class GameDisplay {
	
	/**
	 * Don't let anyone instantiate this class
	 */
	private GameDisplay() {}
	
	/**
	 * Displays a specific message based on a current given game state
	 * represented by a flag argument.
	 * <p>
	 * 		The flags are the following:
	 * </p>
	 * <ul>
	 * 		<li>
	 * 			<strong>confirm-new-character</strong>: Asks the user to confirm before replacing
	 * 			the current player character with a new one.
	 * 		</li>
	 * 		<li>
	 * 			<strong>confirm-quit</strong>: Asks the user to confirm before quitting the game.
	 * 		</li>
	 * 		<li>
	 * 			<strong>exit</strong>: The app has stopped.
	 * 		</li>
	 * 		<li>
	 * 			<strong>fight-menu</strong>: The menu allowing the user to select a monster to fight.
	 * 		</li>
	 * 		<li>
	 * 			<strong>invalid</strong>: User input does not match any available options.
	 * 		</li>
	 * 		<li>
	 * 			<strong>menu</strong>: The main game menu.
	 * 		</li>
	 * 		<li>
	 * 			<strong>start</strong>: The game has started.
	 * 		</li>
	 * 		<li>
	 * 			<strong>undefined-character</strong>: Asks the user to create a 
	 * 			new character before continuing.
	 * 		</li>
	 * </ul>
	 * 
	 * @param state A flag describing a specific game state determining
	 * which message to display
	 */
	public static void displayState(String state) {
		switch (state) {
			case ("confirm-new-character") :
				System.out.println("Votre personnage actuel sera remplacé. Voulez-vous vraiment continuer ? (Y/N)");
				break;
			case ("confirm-quit") :
				System.out.println("Voulez-vous vraiment quitter le jeu ? (Y/N)");
				break;
			case ("exit") :
				System.out.println("Arrêt du jeu");
				return;
			case ("fight-menu") :
				System.out.println("Choisissez quel monstre combattre (loup/gobelin/troll)");
				System.out.println(" Loup: PV 5-10, Force 3-8, +1 point si vaincu");
				System.out.println(" Gobelin: PV 10-15, Force 5-10, +2 points si vaincu");
				System.out.println(" Troll: PV 20-30, Force 10-15, +5 points si vaincu");
				System.out.println();
				System.out.println("N pour annuler");
				break;
			case ("invalid"):
				System.out.println("Option invalide");
				System.out.println();
				break;
			case ("menu") :
				System.out.println("Choisissez une option: ");
				System.out.println(" 1. Créer un nouveau personnage");
				System.out.println(" 2. Combattre un monstre");
				System.out.println(" 3. Afficher les infos du personnage");
				System.out.println(" 4. Quitter le jeu");
				System.out.println();
				System.out.println("M pour réafficher ce menu");
				break;
			case ("start") :
				System.out.println("Démarrage du jeu");
				System.out.println("_________________");
				break;
			case ("undefined-character") :
				System.out.println("Veuillez d'abord créer un personnage.");
				break;
			default :
				return;
		}
		System.out.println();
	}
	
	/**
	 * Displays a specific message based on a current given game state
	 * represented by a flag argument and regarding the 
	 * current player character and/or monster.
	 * <p>
	 * 		The flags are the following:
	 * </p>
	 * <ul>
	 * 		<li>
	 * 			<strong>fight-start</strong>: A fight against a new monster has been initiated.
	 * 		</li>
	 * 		<li>
	 * 			<strong>game-over</strong>: Displays character info after a player defeat.
	 * 		</li>
	 * 		<li>
	 * 			<strong>new-character</strong>: A new player character has been created.
	 * 		</li>
	 * 		<li>
	 * 			<strong>player-defeat</strong>: The player character has been defeated.
	 * 		</li>
	 * 		<li>
	 * 			<strong>player-info</strong>: Displays current player character info.
	 * 		</li>
	 * 		<li>
	 * 			<strong>player-win</strong>: Displays fight results after a player victory.
	 * 		</li>
	 * 		<li>
	 * 			<strong>resist-attack</strong>: A round during the fight ended in a draw.
	 * 		</li>
	 * </ul>
	 * 
	 * @param state A flag describing on a specific game state determining
	 * which message to display
	 * @param player The current player character in use for which to display info
	 * @param monster The current monster being fought for which to display info
	 */
	public static void displayState(String state, PlayerCharacter player, Monster monster) {
		switch (state) {
			case ("fight-start") :
				System.out.println("Vous engagez le combat contre un " + monster.getType() + ".");
				break;
			case ("game-over") :
				System.out.println("-- GAME OVER --");
				System.out.println("Score final obtenu: " + player.getScore() + " points");
				System.out.println("Créez un nouveau personnage pour commencer une nouvelle partie.");
				break;
			case ("new-character") :
				System.out.println("Nouveau personnage créé:");
				System.out.println("  PV: " + player.getHealth());
				System.out.println("  Force: " + player.getStrength());
				break;
			case ("player-defeat") :
				System.out.println("Le " + monster.getType() + " vous a porté un coup fatal !");
				System.out.println("Vous êtes mort.");
				break;
			case ("player-info"):
				System.out.println("Personnage actuel:");
				System.out.println("  PV: " + player.getHealth());
				System.out.println("  Force: " + player.getStrength());
				System.out.println("  Score actuel: " + player.getScore());
				break;
			case ("player-win") :
				System.out.println("Vous avez vaincu le " + monster.getType() + " !");
				System.out.println("Votre score actuel est de " + player.getScore() + " points.");
				System.out.println("Il vous reste " + player.getHealth() + " PV.");
				System.out.println();
				System.out.println("2 pour combattre un nouveau monstre");
				System.out.println("M pour réafficher le menu");
				break;
			case ("resist-attack") :
				System.out.println("Le " + monster.getType() + " vous attaque mais vous ne subissez aucun dégâts.");
				break;
			default :
				return;
		}
		System.out.println();
	}
	
	/**
	 * Displays a specific message based on a current given game state
	 * represented by a flag argument and regarding the 
	 * current player character and/or monster taking a 
	 * given amount of damage.
	 * <p>
	 * 		The flags are the following:
	 * </p>
	 * <ul>
	 * 		<li>
	 * 			<strong>monster-attack</strong>: The monster has won a round and inflicts damage to the player character.
	 * 		</li>
	 * 		<li>
	 * 			<strong>player-attack</strong>: The player character has won a round and inflicts damage to the monster.
	 * 		</li>
	 * </ul>
	 * 
	 * @param state A flag describing on a specific game state determining
	 * which message to display
	 * @param player The current player character in use for which to display info
	 * @param monster The current monster being fought for which to display info
	 * @param damage The amount of damage being dealt to one entity during a round
	 */
	public static void displayState(String state, PlayerCharacter player, Monster monster, int damage) {
		switch (state) {
			case ("monster-attack") :
				System.out.println("Le " + monster.getType() + " vous attaque et vous inflige " + damage + " points de dégâts.");
				break;
			case ("player-attack") :
				System.out.println("Vous attaquez le " + monster.getType() + " et lui infligez " + damage + " points de dégâts.");
				break;
			default :
				return;
		}
		System.out.println();
	}
	
}
