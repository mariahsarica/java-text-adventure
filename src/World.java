/**
 * World.java - World class
 * @author Mariah Molenaer
 */


import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class World {
	
	public static ArrayList<Location> locs = new ArrayList<Location>();  // ArrayList of type Locations that stores locations
	public static ArrayList<Item> items = new ArrayList<Item>();         // ArrayList of type Item that stores items
	public static int[][] nav;											 // Two-dimensional array to hold the navigation matrix

	
	/**
	 * The init method sets up instances of the Location and Item classes,
	 * adds them to their appropriate ArrayLists, and creates the navigation
	 * matrix.
	 */
	public static void init() {
		
		/**
		 * ITEMS
		 */
		// Cart
		Item cart = new Item(0, "CART", true);
		cart.setDescrip("You now have something to put your groceries in.");
		cart.setLocId(0);
		items.add(cart);
		
		// Flyer
		Item flyer = new Item(1, "FLYER", true);
		flyer.setDescrip("You skim the flyer... \nWeekly Specials: 'gross' ... 'eww' ... "
				+ "Oooo! QUINOA on sale in bulk for $1.99/lb!! and discount organic "
				+ "CELERY for 75¢!!! Don't miss out on these KILLER deals!!!");
		flyer.setLocId(0);
		items.add(flyer);
		
		// Map
		Item map = new Item(2, "MAP", true);
		map.setDescrip("You have picked up a map of the store! Key in 'M' to view it.");
		map.setLocId(0);
		items.add(map);
		
		// Cashier
		Item cashier = new Item(3, "CASHIER", false);
		cashier.setDescrip("Oooooo, a dirty look, ouch!");
		cashier.setLocId(1);
		items.add(cashier);
		
		// Quinoa
		Item quinoa = new Item(4, "QUINOA", true);;
		quinoa.setDescrip("Sweet! I can't wait to make some quinoa salad later!!");
		quinoa.setLocId(3);
		items.add(quinoa);
		
		// Celery
		Item celery = new Item(5, "CELERY", true);
		celery.setDescrip("I can't believe this celery is only 75¢!!");
		celery.setLocId(2);
		items.add(celery);
		
		// Crazy Guy
		Item crazyGuy = new Item(6, "CRAZY GUY", false);
		crazyGuy.setDescrip("Crazy Guy: DON'T GO TO THE DELI");
		crazyGuy.setLocId(4);
		items.add(crazyGuy);
		
		// Bell
		Item bell = new Item(7, "BELL", false);
		bell.setDescrip("A shadowy figure emerges from the back... AHHHH IT'S THE CABBAGE CRUSHER!! \n"
				+ "No turning back now, you must defeat him! \n"
				+ "To fight using your grocery items type \"Use [ITEM_NAME]\", or \n"
				+ "type 'P' to punch!");
		bell.setLocId(5);
		items.add(bell);
		
		// GF Flour
		Item flour = new Item(8, "FLOUR", true);
		flour.setDescrip("*Checks flour off list*");
		flour.setLocId(7);
		items.add(flour);
		
		
		/**
		 * LOCATIONS
		 */
		// Lobby
		Location lobby = new Location(0, "Lobby");
		lobby.setLoc("You are in the lobby.");
		lobby.setDescrip("There is a row of shopping CARTS to your right "
				+ "and a stand of FLYERS and MAPS to your left.");
		lobby.setDir("North-Produce, West-Cash Registers");
		locs.add(lobby);
		
		// Cash Registers
		Location registers = new Location(1, "Cash Registers");
		registers.setLoc("You are by the cash registers.");
		registers.setDescrip("You lock eyes with the CASHIER, quick, make a move. \nEnter \"smile\" to smile.");
		registers.setDir("North-Aisle 3, East-Lobby");
		locs.add(registers);
		
		// Produce
		Location produce = new Location(2, "Produce");
		produce.setLoc("You are in the produce section.");
		produce.setDescrip("Ahh there is that really cheap organic CELERY!");
		produce.setDir("North-Bulk, West-Aisle 3, South-Lobby");
		locs.add(produce);
		
		// Bulk
		Location bulk = new Location(3, "Bulk");
		bulk.setLoc("You are in the bulk section.");
		bulk.setDescrip("Oooo so many options, I really only came in for flour and tofu.... but the QUINOA is such a great deal!");
		bulk.setDir("South-Produce");
		locs.add(bulk);
		
		// Aisle 3
		Location aisle3 = new Location(4, "Aisle 3");
		aisle3.setLoc("You are in Aisle 3");
		aisle3.setDescrip("A CRAZY GUY starts running down the aisle! \nEnter \"talk\" to see what he has to say.");
		aisle3.setDir("North-Deli, West-Aisle 2, East-Produce, South-Cash Registers");
		locs.add(aisle3);
		
		// Deli
		Location deli = new Location(5, "Deli");
		deli.setLoc("You are in the deli section.");
		deli.setDescrip("No one seems to be around. There is a sign that reads, 'RING BELL for service'. \n"
				+ "Enter \"ring bell\" to ring the bell..... If you dare.");
		deli.setDir("West-Dairy, South-Aisle 3");
		locs.add(deli);
		
		// Dairy
		Location dairy = new Location(6, "Dairy");
		dairy.setLoc("You are in the dairy section.");
		dairy.setDescrip("There is the TOFU I came in for!");
		dairy.setDir("East-Deli, South-Aisle 2");
		locs.add(dairy);
		
		// Aisle 2
		Location aisle2 = new Location(7, "Aisle 2");
		aisle2.setLoc("You are in Aisle 2");
		aisle2.setDescrip("Ahhh gluten free FLOUR! And for the low price of $2.31! This store never ceases to surprise me :)");
		aisle2.setDir("North-Dairy, East-Aisle 3");
		locs.add(aisle2);
	
		/**
		 * Navigation Matrix
		 */
		nav = new int[][] {
			/* N   S   E   W */
			/* 0   1   2   3 */
			{  2, -1, -1,  1 }, // Lobby
			{  4, -1,  0, -1 }, // Cash Registers
			{  3,  0, -1,  4 }, // Produce
			{ -1,  2, -1, -1 }, // Bulk
			{  5,  1,  2,  7 }, // Aisle 3
			{ -1,  4, -1,  6 }, // Deli
			{ -1,  7,  5, -1 }, // Dairy
			{  6, -1,  4, -1 }, // Aisle 2
		};
		
	}
	
	/**
	 * The map method displays a map of NATURE'S PANTRY (only if the user has picked it up).
	 */
	public static void map() {
		if (items.get(2).getTaken() == true) {
			JLabel map = new JLabel(new ImageIcon("map.jpg"));
			JOptionPane.showMessageDialog(null, map, "Map", JOptionPane.PLAIN_MESSAGE, null);
		} else {
			GameEngine.informationMessage("You don't have a map.");
		}
	}

}