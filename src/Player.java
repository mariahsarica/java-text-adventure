/**
 * Player.java - Player class
 * @author Mariah Molenaer
 */

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;


public class Player {

	public static int currentLoc;									  // Current location of the player
	public static ArrayList<Item> inventory = new ArrayList<Item>();  // ArrayList of type Item to hold items present in the player's inventory
	public static int totalMoves = 0;                                 // Keeps track of total number of moves player has made throughout the game
	public static String name = getUserName();						  // Name of player
	
	private static int playerHealth = 100;
	private static int bossHealth = 150;
	
	
	/**
	 * The getUserName method prompts the user to enter a name.
	 */
	private static String getUserName() {
		String name;
		name = JOptionPane.showInputDialog(GameEngine.frame, "Enter your name below.", "Enter your name", JOptionPane.PLAIN_MESSAGE);
		while (name == null || name.equals("")) {
			name = JOptionPane.showInputDialog(GameEngine.frame, "Enter your name below.", "Enter your name", JOptionPane.PLAIN_MESSAGE);
		}
		return name.trim();
	}
	
	public static void displayHealthStats() {
		GameEngine.output.append("Your Health: " + playerHealth + "\nBoss Health: " + bossHealth + "\n\n");
		if (bossHealth < 1) {
			GameEngine.informationMessage("YOU WIN!! YOU HAVE DEFEATED THE CABBAGE CRUSHER AND SAVED NATURE'S PANTRY!!");
			GameEngine.quit();
		}
		if (playerHealth < 1) {
			GameEngine.informationMessage("Dead");
			GameEngine.quit();
		}
	}
	
	
	/**
	 * The takeCart method is a special case of the takeItem method, as the
	 * cart must be taken before all other items.
	 * @param cart 
	 */
	public static void takeCart(Item cart) {
		if (World.locs.get(0).hasViewed == true) {
			if (cart.getTaken() == false) {
				if (currentLoc == 0) {
					GameEngine.output.append(cart.getDescrip());
					cart.setTaken(true);
					GameEngine.inv.setText("Your cart currently has: \n\n");
				} else {
					GameEngine.informationMessage("Not in this room!");
				}
			} else {
				GameEngine.informationMessage("You already have a cart!");
			}
		} else {
			GameEngine.informationMessage("You must look around the room before you can take any items.");
		}
	}
	
	
	/**
	 * The takeItem method takes items and adds it to the player's inventory.
	 * @param item Index of item to be taken.
	 */
	public static void takeItem(Item item) {
		if (World.locs.get(currentLoc).hasViewed == true) {
			if (item.getTakable() == true) {
				if (item.getTaken() == false) {
					if (World.items.get(0).getTaken() == true) {
						if (currentLoc == item.locId) {
							addToInv(item);
							GameEngine.output.append(item.getDescrip());
						} else {
							GameEngine.errorMessage();
						}
					} else {
						GameEngine.informationMessage("You have nothing to put your groceries in!");
					}
				} else {
					GameEngine.informationMessage("Check your cart, you already have this item!");
				}
			} else {
				GameEngine.errorMessage();
			}
		} else {
			GameEngine.informationMessage("You must look around the room before you can take any items.");
		}
	}
	
	
	/**
	 * The addToInv method adds an item to the inventory and sets it as taken.
	 * @param item Index of item to add to the inventory.
	 */
	public static void addToInv(Item item) {
		inventory.add(item);
		item.setTaken(true);
		GameEngine.inv.append(item.getName() + "\n");
	}
	
	
	/**
	 * The removeFromInv removes an item from the inventory and sets it as not taken.
	 * @param item Index of item to remove from the inventory.
	 */
	public static void removeFromInv(Item item) {
		inventory.remove(item);
		item.setTaken(false);
		String i = GameEngine.inv.getText();
		GameEngine.inv.setText(i.replace(item.getName() + "\n", ""));
	}
	
	
	/**
	 * The drop method allows a player to drop an item and removes it from the inventory.
	 * @param item Index of item player wants to drop.
	 */
	public static void drop(Item item) {
		if (item.getTakable() == true) {
			if (item.getTaken() == true) {
				removeFromInv(item);
				GameEngine.output.append("You have dropped the " + item.getName().toLowerCase() + ".\n\n");
			} else {
				GameEngine.informationMessage("You don't have this item.");
			}
		} else {
			GameEngine.errorMessage();
		}
	}
	
	
	/**
	 * The backtrack method allows the player to backtrack a specified number of
	 * steps until they reach the beginning.
	 * @param steps Number of steps player wants to backtrack.
	 */
	public static void backtrack(int steps) {
		if (BreadCrumbTrail.stepsFromBeg == 0) {
			GameEngine.informationMessage("You are already at the beginning of your experience in NATURE'S PANTRY!");
		} else if (steps > BreadCrumbTrail.stepsFromBeg) {
			GameEngine.warningMessage("You cannot backtrack more steps than you've taken! \n"
					+ "Refer to the value listed next to \"Steps from Beginning\" \n"
					+ "for the maximum number of steps you may backtrack.");
		} else if (steps <= 0) {
			GameEngine.warningMessage("Enter a positive number of steps to backtrack.");
		} else {
			BreadCrumbTrail.remove(steps);
			if (BreadCrumbTrail.last == null) {
				currentLoc = 0;
			} else {
				currentLoc = BreadCrumbTrail.last.value;
			}
			totalMoves = totalMoves + 1; 
			GameEngine.output.append(World.locs.get(currentLoc).getLoc());
			GameEngine.updateStatus();
		}
	}
	
	/**
	 * The look method displays the description of the location.
	 */
	public static void look() {
		GameEngine.output.append(World.locs.get(currentLoc).getDescrip());
	}
	
	
	/**
	 * The move method allows the player to move north, south, east, or
	 * west (if direction is available) to a different location.
	 * @param dir Index of direction the player wants to move in.
	 */
	public static void move(int dir) {
		if (totalMoves != 20) {
			int newLoc = World.nav[currentLoc][dir];
			if (newLoc < 0) {
				GameEngine.warningMessage("You cannot go that way.");
			} else {
				currentLoc = newLoc;
				totalMoves = totalMoves + 1;
				BreadCrumbTrail.add(currentLoc);
			}
			GameEngine.output.append(World.locs.get(currentLoc).getLoc());  
			GameEngine.updateStatus();
		} else {
			maxMovesReached();
		}
	}
	
	/**
	 * The interactWithSpecialItem method allows the user interact with characters that pop up in the game and other special items.
	 * @param itemLocId Id of the location that the item is present in
	 * @param itemId Id of the item in the items ArrayList
	 */
	public static void interactWithSpecialItem(int itemLocId, int itemId) {
		if (currentLoc == itemLocId) {
			if (World.locs.get(itemLocId).hasViewed == true) {
				GameEngine.output.append(World.items.get(itemId).getDescrip());
			} else {
				GameEngine.errorMessage();
			}
		} else {
			GameEngine.errorMessage();
		}
	}
	
	private static void maxMovesReached() {
		GameEngine.warningMessage("Oh no! You have reached 20 moves! Game Over.");
		GameEngine.quit();
	}
	
	/**
	 * The randomNum method generates a random number from 1-20.
	 */
	public static int randomNum() {
	    Random num = new Random();
	    int randomNum = num.nextInt((20 - 1) + 1) + 1;
	    return randomNum;
	}
	
	
	private static void attack(int damage, String attackMsg) {
		
		int playerAttack = damage;
		GameEngine.output.append(attackMsg + playerAttack + " damage!!" + "\n");
		
		int bossAttack = 10 + randomNum();
		GameEngine.output.append("BOOM! The Cabbage Crusher counters! -" + bossAttack + " health" + "\n\n");
		
		playerHealth = playerHealth - bossAttack;
		bossHealth = bossHealth - playerAttack;
		
		
	}

	public static void punch() {
		attack(randomNum(), "POW! ");
		
		displayHealthStats();
	}
	
	public static void use(Item item) {
		
		if (item.getName() == "CELERY") {
			attack(30, "BAM! You take out each stalk of celery and bash them over his cabbage head!! ");
		
		} else if (item.getName() == "QUINOA") {
			attack(40, "WOOSH! You rip open your bag of quinoa and pour it all over the floor to make him fall on his cabbage butt! ");
		
		} else if (item.getName() == "TOFU") {
			attack(50, "SLAAPPP!!! You open up your tofu and slap it all in his veggie face! ");
			
		} else if (item.getName() == "FLOUR") {
			attack(50, "POOOFFF!! You tear open your bag of gluten free flour and it gets right in his brusselly eyes!! ");
			
		}
			
		removeFromInv(item);
		displayHealthStats();
			
		
	}
	
}