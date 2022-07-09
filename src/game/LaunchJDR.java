package game;

import java.util.Scanner;
import game.engine.Game;

/**
 * App entry point.
 *
 * @author DorianBoel
 */
public class LaunchJDR {

	/**
	 * Main method for launching the app. Starts the game loop then
	 * asks the user for a prompt using a
	 * {@link java.util.Scanner Scanner},
	 * then calls the corresponding method from the 
	 * {@link game.engine.Game Game} controller
	 * as long as the user doesn't choose to quit the program.
	 * <p>
	 * 		The valid prompts are as follows:
	 * </p>
	 * <ul>
	 * 		<li>
	 * 			"M" : display the menu (calls
	 * 			{@link game.engine.Game#displayMenu Game.displayMenu})
	 * 		</li>
	 * 		<li>
	 * 			"1" : create a new character (calls
	 * 			{@link game.engine.Game#createCharacter Game.createCharacter})
	 * 		</li>
	 * 		<li>
	 * 			"2" : choose a monster to fight (calls
	 * 			{@link game.engine.Game#fightMonster Game.fightMonster})
	 * 		</li>
	 * 		<li>
	 * 			"3" : display the current character's statistics (calls
	 * 			{@link game.engine.Game#displayCharacterInfo Game.displayCharacterInfo})
	 * 		</li>
	 * 		<li>
	 * 			"4" : exit the game loop and close the program
	 * 		</li>
	 * </ul>
	 * 
	 * @param args Array of command line arguments
	 */
	public static void main(String[] args) {
		
		Scanner menuPrompt = new Scanner(System.in);
		String menuSelect =  "0";
		
		Game.start();
		
		while (!menuSelect.equals("4")) {
			menuSelect = menuPrompt.nextLine();
			
			switch (menuSelect) {
				case ("M") :
					Game.displayMenu();
					break;
				case ("1") :
					Game.createCharacter(menuPrompt);
					break;
				case ("2") :
					Game.fightMonster(menuPrompt);
					break;
				case ("3") :
					Game.displayCharacterInfo();
					break;
				case ("4") :
					if (Game.quitGame(menuPrompt)) {
						continue;
					}
					menuSelect = "0";
					break;
				default :
					Game.defaultOption();
			}
			
		}
		menuPrompt.close();
		
	}

}
