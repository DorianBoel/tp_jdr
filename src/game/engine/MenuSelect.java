package game.engine;

import java.util.Arrays;
import java.util.Scanner;

public class MenuSelect {

	public static String acceptInput(Scanner scanner, String ...options) {
		String option = "0";
		while (!Arrays.asList(options).contains(option)) {
			option = scanner.nextLine();
		}
		return option;
	}

}
