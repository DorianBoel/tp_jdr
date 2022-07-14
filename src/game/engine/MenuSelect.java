package game.engine;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Non-instanciable class containing methods used to
 * prompt the user and retrieve the input.
 *
 * @author DorianBoel
 */
public final class MenuSelect {

	/**
	 * Don't let anyone instantiate this class
	 */
	private MenuSelect() {}
	
	/**
	 * Prompts the user for input as long as the received input is not
	 * inside the given list of valid options.
	 * 
	 * @param scanner The main prompt {@link java.util.Scanner Scanner}
	 * @param options Variable arguments representing the list 
	 * of valid expected user inputs
	 * @return The first valid user input
	 */
	public static String acceptInput(Scanner scanner, String ...options) {
		String option = "0";
		while (!Arrays.asList(options).contains(option)) {
			option = scanner.nextLine();
		}
		return option;
	}

}
