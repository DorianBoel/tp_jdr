package game;

import java.util.Scanner;
import game.engine.Game;

public class TestJDR {

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
