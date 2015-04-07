/**
 * Player.java - Player class
 * @author Mariah Molenaer
 */

import java.util.ArrayList;

public class Player {

	public static int currentLoc;									  // Current location of the player
	public static ArrayList<Item> inventory = new ArrayList<Item>();  // ArrayList of type Item to hold items present in the player's inventory
	public static int totalMoves = 0;                                 // Keeps track of total number of moves player has made throughout the game
	
	public Player() {
	}
	
	/**
	 * The takeCart method is a special case of the takeItem method, as the
	 * cart must be taken before all other items.
	 * @param cart 
	 */
	public static void takeCart(Item cart) {
		if (cart.getTaken() == false) {
			if (currentLoc == 0) {
				System.out.println(cart.getDescrip());
				cart.setTaken(true);
			} else {
				System.out.println("Not in this room!");
			}
		} else {
			System.out.println("You already have a cart!");
		}
	}
	
	
	/**
	 * The takeItem method takes items and adds it to the player's inventory.
	 * @param item Index of item to be taken.
	 */
	public static void takeItem(Item item) {
		if (item.getTakable() == true) {
			if (World.items.get(0).getTaken() == true) {
				if (item.getTaken() == false) {
					if (currentLoc == item.locId) {
						addToInv(item);
						System.out.println(item.getDescrip());
					} else {
						System.out.println("Not in this room!");
					}
				} else {
					System.out.println("Check your cart, you already have this item!");
				}
			} else {
				System.out.println("You have nothing to put your groceries in!");
			}
		} else {
			System.out.println("Invalid action.");
		}
	}
	
	
	/**
	 * The addToInv method adds an item to the inventory and sets it as taken.
	 * @param item Index of item to add to the inventory.
	 */
	public static void addToInv(Item item) {
		inventory.add(item);
		item.setTaken(true);
	}
	
	
	/**
	 * The removeFromInv removes an item from the inventory and sets it as not taken.
	 * @param item Index of item to remove from the inventory.
	 */
	public static void removeFromInv(Item item) {
		inventory.remove(item);
		item.setTaken(false);
	}
	
	
	/**
	 * The drop method allows a player to drop an item and removes it from the inventory.
	 * @param item Index of item player wants to drop.
	 */
	public static void drop(Item item) {
		if (item.getTakable() == true) {
			if (item.getTaken() == true) {
				removeFromInv(item);
				System.out.println("You have dropped the " + item.getName().toLowerCase() + ".");
			} else {
				System.out.println("You don't have this item.");
			}
		} else {
			System.out.println("Invalid action.");
		}
	}
	
	
	/**
	 * The checkInv method prints out the player's current inventory.
	 */
	public static void checkInv() {
		if (World.items.get(0).getTaken() == true) {
			if (inventory.size() > 0) {
				System.out.println("Your cart currently has: ");
				for (int i = 0; i < inventory.size(); i++) {
					System.out.println(inventory.get(i).getName());
				}
			} else {
				System.out.println("You haven't added any items to your cart yet!");
			}	
		} else {
			System.out.println("You don't have anything!");
		}
	}
	
	
	/**
	 * The backtrack method allows the player to backtrack a specified number of
	 * steps until they reach the beginning.
	 * @param steps Number of steps player wants to backtrack.
	 */
	public static void backtrack(int steps) {
		if (BreadCrumbTrail.stepsFromBeg == 0) {
			System.out.println("You are already at the beginning of your experience in NATURE'S PANTRY!");
		} else if (steps > BreadCrumbTrail.stepsFromBeg) {
			System.out.println("You cannot backtrack more steps than you've taken! Refer to the value listed next to \n"
					+ "\"Steps from Beginning\" for the maximum number of steps you may backtrack.");
		} else if (steps <= 0) {
			System.out.println("Enter a positive number of steps to backtrack.");
		} else {
			BreadCrumbTrail.remove(steps);
			if (BreadCrumbTrail.last == null) {
				currentLoc = 0;
			} else {
				currentLoc = BreadCrumbTrail.last.value;
			}
			totalMoves = totalMoves + 1; 
			System.out.println(World.locs.get(currentLoc).getText());
		}
	}
	
	
	/**
	 * The move method allows the player to move north, south, east, or
	 * west (if direction is available) to a different location.
	 * @param dir Index of direction the player wants to move in.
	 */
	public static void move(int dir) {
		int newLoc = World.nav[currentLoc][dir];
		if (newLoc < 0) {
			System.out.println("You cannot go that way.");
		} else {
			currentLoc = newLoc;
			totalMoves = totalMoves + 1;
			BreadCrumbTrail.add(currentLoc);
		}
	   
	    System.out.println(World.locs.get(currentLoc).getText());    
	}
	
}