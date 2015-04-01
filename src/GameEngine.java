/**
 * GameEngine.java - NATURE'S PANTRY - A text adventure game
 * @author Mariah Molenaer
 */

import java.util.Scanner;

public class GameEngine {	
	
	private static boolean stillPlaying = true;  // For game loop
	private static Scanner input;				 // Gets user input
	private static String command;               // Converts input to String

	
	public static void main(String[] args) {
		
		World.init();
		
		welcomeMsg();

		// Game loop
		while(stillPlaying) {
			getCommand();
			commandReader();
		}
	
	}
	
	
	/**
	 * The welcomeMsg method prints out a welcome message to the user and 
	 * starts the player in the lobby
	 */
	private static void welcomeMsg() {
		System.out.println("Welcome to NATURE’S PANTRY, your favorite \n"
				+ "alternative grocery store! \n"
				+ "What was it that I needed to get again? Oh yeah, \n"
				+ "almond milk and tofu. \n"
				+ "Type 'h' for help or 'q' to quit. \n" 
				+ World.locs.get(Player.currentLoc).getText());
	}
	
	
	/**
	 * The quit method quits the game and prints out credits to the user.
	 */
	private static void quit() {
		stillPlaying = false;
		System.out.println("GAME OVER.\nCopyright 2015. Mariah Molenaer.");
	}
	
	
	/**
	 * The getCommand method gets the command from the users input.
	 */
	private static void getCommand() {
		input = new Scanner(System.in);
		command = input.next();       
		command = command.toLowerCase();
	}
	
	
	/**
	 * The commandReader method reads the users command and performs the proper action.
	 */
	private static void commandReader() {
		
		       if (command.equals("n")) {    				// Moves north
			Player.move(0);
		} else if (command.equals("s")) {    			    // Moves south
			Player.move(1);
		} else if (command.equals("e")) {    				// Moves east
			Player.move(2);
		} else if (command.equals("w")) {    				// Moves west
			Player.move(3);
		} else if (command.equals("i")) {    				// Checks inventory
			Player.checkInv();
		} else if (command.equals("m")) {    				// Displays map
			World.map();
		} else if (command.equals("h")) {    				// Displays help menu
			help();
		} else if (command.equals("q")) {    				// Quits game
			quit();
		} else if (command.equals("t")) {    				// Takes item
			String item = input.next();
			if (item.equals("cart")) {
				Player.takeCart(World.items.get(0));
			} else if (search(item)) {
				int index = getIndex(item);
				Player.takeItem(World.items.get(index));
			} else {
				System.out.println("Invalid command.");
			}
			
		} else if (command.equals("d")) {					// Drops item
			String item = input.next();
			if (search(item)) {
				int index = getIndex(item);
				Player.drop(World.items.get(index));
			} else {
				System.out.println("Invalid command.");
			}
			
		} else if (command.equals("b")) {					// Backtracks
			String steps = input.nextLine();
			if (steps.equals("")) {
				Player.backtrack(1);
			} else {
				try {
					steps = steps.replaceAll("\\s", "");
					Integer s = new Integer(steps);
					Player.backtrack(s);
				} catch (NumberFormatException nfe){
					System.out.println("Invalid command.");
				}
			}
		} else {
			System.out.println("Invalid command.");
		}
	
	}
	
	
	/**
	 * The search method is used in the take and drop actions. It searches the ArrayList of items
	 * to see if it contains the item the user wants to take/drop.
	 * @param item Name of item user wants to take/drop
	 * @return true if the item is in the ArrayList
	 * 		   false if it is not
	 */
	private static boolean search(String item) {
		for (int i = 0; i < World.items.size(); i++) {
			if (item.equalsIgnoreCase(World.items.get(i).getName())) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * The getIndex method is used in the take and drop actions. It gets the index of the item in the
	 * ArrayList of items that the user wants to take/drop.
	 * @param item Name of item user wants to take/drop
	 * @return Index i of item in ArrayList or
	 *         -1 if it is not present
	 */
	private static int getIndex(String item) {
		for (int i = 0; i < World.items.size(); i++) {
			if (item.equalsIgnoreCase(World.items.get(i).getName())) {
				return i;
			}
		}
		return -1;	
	}
	
	
	/**
	 * The help method displays a list of commands available to the user.
	 */
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
				+ "B [NUMBER_OF_STEPS] - backtrack a specified number of steps \n"
				+ "M - view map \n"
				+ "I - view inventory \n"
				+ "H - display these help instructions \n"
				+ "Q - quit game");
	}
	
}
