/**
 * Player.java - Player class
 * @author Mariah Molenaer
 */

import java.util.ArrayList;

public class Player {

	public static int currentLoc;
	public static ArrayList<Item> inventory = new ArrayList<Item>();
	public static int moves = 0;                                      // Keeps track of number of moves player has made
	
	public Player() {
	}
	
	// Cart must be taken before other items
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
	
	
	// Takes item and adds to inventory
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
	
	// Adds item to inventory and sets it as taken
	public static void addToInv(Item item) {
		inventory.add(item);
		item.setTaken(true);
	}
	
	// Removes item from inventory and sets it as not taken
	public static void removeFromInv(Item item) {
		inventory.remove(item);
		item.setTaken(false);
	}
	
	
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
	
	
	// Allows player to check inventory
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
	
	// Allows player to move to different locations
	public static void move(int dir) {
		int newLoc = World.nav[currentLoc][dir];
		if (newLoc < 0) {
			System.out.println("You cannot go that way.");
		} else {
			currentLoc = newLoc;
			moves = moves + 1;
		}
	   
	    System.out.println(World.locs.get(currentLoc).getText());    
	}
}