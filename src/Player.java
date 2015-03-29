/**
 * Player.java - Player class
 * @author Mariah Molenaer
 */

import java.util.ArrayList;

public class Player {

	public static int currentLoc;
	public static ArrayList<Item> inventory = new ArrayList<Item>();
	
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
		if (World.items.get(0).getTaken() == true) {
			if (item.getTaken() == false) {
				if (currentLoc == item.locId) {
					System.out.println(item.getDescrip());
					item.setTaken(true);
					addToInv(item);
				} else {
					System.out.println("Not in this room!");
				}
			} else {
				System.out.println("Check your cart, you already have this item!");
			}
		} else {
			System.out.println("You have nothing to put your groceries in!");
		}
		
	}
	
	// Adds item to inventory
	public static void addToInv(Item item) {
		inventory.add(item);
	}
	
	
	// Allows player to check inventory
	public static void checkInv() {
		if (inventory.size() > 0) {
			System.out.println("Your cart currently has: ");
		    for (int i = 0; i < inventory.size(); i++) {
		    	System.out.println(inventory.get(i).getName());
		    }
		} else {
			System.out.println("You haven't added any items to your cart yet!");
		}		
	}
	
	// Allows player to move to different locations
	public static void move(int dir) {
		int newLoc = World.nav[currentLoc][dir];
		if (newLoc < 0) {
			System.out.println("You cannot go that way.");
		} else {
			currentLoc = newLoc;
		}
	   
	    System.out.println(World.locs.get(currentLoc).getText());    
	}
}