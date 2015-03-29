/**
 * GameEngine.java - NATURE'S PANTRY Game Engine
 * @author Mariah Molenaer
 */

import java.util.Scanner;

public class GameEngine {	
	
	private static boolean stillPlaying = true; // For game loop
	private static String command;              // Converts user input to String
	private static Scanner input;				// Gets user input
	
	public static void main(String[] args) {
		
		World.init();
		
		// Welcome message & starts player in lobby
		System.out.println("Welcome to NATURE’S PANTRY, your favorite \n"
				+ "alternative grocery store! \n"
				+ "What was it that I needed to get again? Oh yeah, \n"
				+ "almond milk and tofu. \n"
				+ "Type 'h' for help or 'q' to quit. \n" 
				+ World.locs.get(Player.currentLoc).getText());

		// Game loop
		while(stillPlaying) {
			getCommand();
			commandReader();
		}
	
	}
	
	// Quits game
	private static void quit() {
		stillPlaying = false;
		System.out.println("GAME OVER.\nCopyright 2015. Mariah Molenaer.");
	}
	
	// Gets command from user
	private static void getCommand() {
		input = new Scanner(System.in);
		command = input.nextLine();
	}
	
	// Reads user command and performs proper action
	private static void commandReader() {
		
		       if (command.equalsIgnoreCase("n")) {
			Player.move(0);
		} else if (command.equalsIgnoreCase("s")) {
			Player.move(1);
		} else if (command.equalsIgnoreCase("e")) {
			Player.move(2);
		} else if (command.equalsIgnoreCase("w")) {
			Player.move(3);
		} else if (command.equalsIgnoreCase("t cart")) {
			Player.takeCart(World.items.get(0));
		} else if (command.equalsIgnoreCase("t flyer")) {
			Player.takeItem(World.items.get(1));
		} else if (command.equalsIgnoreCase("t map")) {
			Player.takeItem(World.items.get(2));
		} else if (command.equalsIgnoreCase("t quinoa")) {
			Player.takeItem(World.items.get(4));
		} else if (command.equalsIgnoreCase("t celery")) {
			Player.takeItem(World.items.get(5));
		} else if (command.equalsIgnoreCase("i")) {
			Player.checkInv();
		} else if (command.equalsIgnoreCase("m")) {
			World.map();
		} else if (command.equalsIgnoreCase("h")) {
			help();
		} else if (command.equalsIgnoreCase("q")) {
			quit();
		}
	
	}
	
	// Displays list of commands available to user
	private static void help() {
		System.out.println("Items available for interraction in each room are \n"
				+ "listed in CAPTIAL letters. The following commands \n"
				+ "are permitted: \n"
				+ "N - move north \n"
				+ "S - move south \n"
				+ "E - move east \n"
				+ "W - move west \n"
				+ "T [ITEM_NAME] - take item \n"
				+ "M - view map \n"
				+ "I - view inventory \n"
				+ "H - display these help instructions \n"
				+ "Q - quit game");
	}
	
	

}
