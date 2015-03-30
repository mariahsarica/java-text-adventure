/**
 * GameEngine.java - NATURE'S PANTRY Game Engine
 * @author Mariah Molenaer
 */

import java.util.Scanner;

public class GameEngine {	
	
	private static boolean stillPlaying = true; // For game loop
	private static Scanner input;				// Gets user input
	private static String command;              // Converts input to String

	
	public static void main(String[] args) {
		
		World.init();
		
		welcomeMsg();

		// Game loop
		while(stillPlaying) {
			getCommand();
			commandReader();
		}
	
	}
	
	// Welcome message & starts player in lobby
	private static void welcomeMsg() {
		System.out.println("Welcome to NATURE’S PANTRY, your favorite \n"
				+ "alternative grocery store! \n"
				+ "What was it that I needed to get again? Oh yeah, \n"
				+ "almond milk and tofu. \n"
				+ "Type 'h' for help or 'q' to quit. \n" 
				+ World.locs.get(Player.currentLoc).getText());
	}
	
	// Quits game
	private static void quit() {
		stillPlaying = false;
		System.out.println("GAME OVER.\nCopyright 2015. Mariah Molenaer.");
	}
	
	
	// Gets command from user
	private static void getCommand() {
		input = new Scanner(System.in);
		command = input.next();       
		command = command.toLowerCase();
	}
	
	// Reads user command and performs proper action
	private static void commandReader() {
		
		       if (command.equals("n")) {
			Player.move(0);
		} else if (command.equals("s")) {
			Player.move(1);
		} else if (command.equals("e")) {
			Player.move(2);
		} else if (command.equals("w")) {
			Player.move(3);
		} else if (command.equals("i")) {
			Player.checkInv();
		} else if (command.equals("m")) {
			World.map();
		} else if (command.equals("h")) {
			help();
		} else if (command.equals("q")) {
			quit();
		} else if (command.equals("t")) {
			String item = input.next();
			if (item.equals("cart")) {
				Player.takeCart(World.items.get(0));
			} else if (search(item)) {
				int index = getIndex(item);
				Player.takeItem(World.items.get(index));
			} else {
				System.out.println("Invalid command.");
			}
		} else if (command.equals("d")) {
			String item = input.next();
			if (search(item)) {
				int index = getIndex(item);
				Player.drop(World.items.get(index));
			} else {
				System.out.println("Invalid command.");
			}
		} else {
			System.out.println("Invalid command.");
		}
	
	
	}
	
	// Used in take & drop actions - Searches ArrayList<Item> to see if it contains the item the user wants to take or drop
	private static boolean search(String item) {
		for (int i = 0; i < World.items.size(); i++) {
			if (item.equalsIgnoreCase(World.items.get(i).getName())) {
				return true;
			}
		}
		return false;
	}
	
	// Used in take & drop actions - Gets the index of the item the user wants to take or drop in ArrayList<Item>
	private static int getIndex(String item) {
		for (int i = 0; i < World.items.size(); i++) {
			if (item.equalsIgnoreCase(World.items.get(i).getName())) {
				return i;
			}
		}
		return -1;	
	}
	
	// Displays list of commands available to user
	private static void help() {
		System.out.println("Items available for interaction in each room are \n"
				+ "listed in CAPTIAL letters. The following commands \n"
				+ "are permitted: \n"
				+ "N - move north \n"
				+ "S - move south \n"
				+ "E - move east \n"
				+ "W - move west \n"
				+ "T [ITEM_NAME] - take item \n"
				+ "D [ITEM_NAME] - drop item \n"
				+ "M - view map \n"
				+ "I - view inventory \n"
				+ "H - display these help instructions \n"
				+ "Q - quit game");
	}
	
	

}
