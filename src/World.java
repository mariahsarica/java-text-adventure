/**
 * World.java - World class
 * @author Mariah Molenaer
 */

import java.util.ArrayList;

public class World {
	
	public static ArrayList<Location> locs = new ArrayList<Location>();
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static int[][] nav;

	// Set up instances of Location & Item classes and create navigation matrix
	public static void init() {
		
		// ITEMS
		// Cart
		Item cart = new Item(0, "CART");
		cart.setDescrip("You now have something to put your groceries in.");
		cart.setLocId(0);
		items.add(cart);
		
		// Flyer
		Item flyer = new Item(1, "FLYER");
		flyer.setDescrip("You skim the flyer... Weekly Specials: 'gross' ... 'eww' \n"
				+ "oooo! QUINOA on sale in bulk for $1.99/lb!! and discount organic \n"
				+ "CELERY for 75¢!!! Don't miss out on these KILLER deals!!!");
		flyer.setLocId(0);
		items.add(flyer);
		
		// Map
		Item map = new Item(2, "MAP");
		map.setDescrip("You have picked up a map of the store! Key in 'm' to view it.");
		map.setLocId(0);
		items.add(map);
		
		// Cashier
		Item cashier = new Item(3, "CASHIER");
		cashier.setDescrip("Oooooo, a dirty look, ouch!");
		cashier.setLocId(1);
		items.add(cashier);
		
		// Quinoa
		Item quinoa = new Item(4, "QUINOA");;
		quinoa.setDescrip("Sweet! I can't wait to make some quinoa salad later!!");
		quinoa.setLocId(3);
		items.add(quinoa);
		
		// Celery
		Item celery = new Item(5, "CELERY");
		celery.setDescrip("I can't believe this celery is only 75¢!!");
		celery.setLocId(2);
		items.add(celery);
		
		// Crazy Guy
		Item crazyGuy = new Item(6, "CRAZY GUY");
		crazyGuy.setDescrip("Crazy Guy: DON'T GO TO THE DELI");
		crazyGuy.setLocId(4);
		items.add(crazyGuy);
		
		
		
		// LOCATIONS
		// Lobby
		Location lobby = new Location(0, "Lobby");
		lobby.setDescrip("You are in the lobby. There is a row of shopping CARTS to your right \n"
				+ "and a stand of FLYERS and MAPS to your left.");
		lobby.setDir("North-Produce, West-Cash Registers");
		locs.add(lobby);
		
		// Cash Registers
		Location registers = new Location(1, "Cash Registers");
		registers.setDescrip("You are by the cash registers. You lock eyes with the CASHIER, quick, make a move.");
		registers.setDir("North-Aisle 3, East-Lobby");
		locs.add(registers);
		
		// Produce
		Location produce = new Location(2, "Produce");
		produce.setDescrip("You are in the produce section. Ahh there is that really \n"
				+ "cheap organic CELERY!");
		produce.setDir("North-Bulk, West-Aisle 3, South-Lobby");
		locs.add(produce);
		
		// Bulk
		Location bulk = new Location(3, "Bulk");
		bulk.setDescrip("You are in the bulk section. Oooo so many options, I really \n"
				+ "only came in for almond milk and tofu.... but the QUINOA is such a great deal!");
		bulk.setDir("South-Produce");
		locs.add(bulk);
		
		// Aisle 3
		Location aisle3 = new Location(4, "Aisle 3");
		aisle3.setDescrip("You are in Aisle 3. A CRAZY GUY starts running down the aisle!");
		aisle3.setDir("North-Deli, West-Aisle 2, East-Produce, South-Cash Registers");
		locs.add(aisle3);
		
		// Deli
		Location deli = new Location(5, "Deli");
		deli.setDescrip("You are in the deli section. No one seems to be around. There is a \n"
				+ "sign that reads, 'RING BELL for service'.");
		deli.setDir("West-Dairy, South-Aisle 3");
		locs.add(deli);
		
		// Dairy
		Location dairy = new Location(6, "Dairy");
		dairy.setDescrip("You are in the dairy section. There is the ALMOND MILK and TOFU /n"
				+ "I came in for!");
		dairy.setDir("East-Deli, South-Aisle 2");
		locs.add(dairy);
		
		// Aisle 2
		Location aisle2 = new Location(7, "Aisle 2");
		aisle2.setDescrip("You are in Aisle 2. Ahhh gluten free FLOUR! Might /n"
				+ "as well get it while I'm here.");
		aisle2.setDir("North-Dairy, East-Aisle 3");
		locs.add(aisle2);
	
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
	
	// Map of the world
	public static void map() {
		System.out.println(
				"---------------------------%%%%%%%%%%%%%---------------------------\n"+
                "---------------------------%%%  MAP  %%%---------------------------\n"+
                "---------------------------%%%%%%%%%%%%%---------------------------\n"+
                "-------------------------------------------------------------------\n"+
                "-------------------------------------------------------------------\n"+
                "-------*************-------*************-------*************-------\n"+
                "-------*           *-------*           *-------*           *-------\n"+
                "-------*   DAIRY   *#######*   DELI    *-------*   BULK    *-------\n"+
                "-------*           *-------*           *-------*           *-------\n"+
                "-------*************-------*************-------*************-------\n"+
                "-------------#-------------------#-------------------#-------------\n"+
                "-------------#-------------------#-------------------#-------------\n"+
                "-------------#-------------------#-------------------#-------------\n"+
                "-------*************-------*************-------*************-------\n"+
                "-------*           *-------*           *-------*           *-------\n"+
                "-------*  AISLE 2  *#######*  AISLE 3  *#######*  PRODUCE  *-------\n"+
                "-------*           *-------*           *-------*           *-------\n"+
                "-------*************-------*************-------*************-------\n"+
                "---------------------------------#-------------------#-------------\n"+
                "---------------------------------#-------------------#-------------\n"+
                "---------------------------------#-------------------#-------------\n"+
                "---------------------------*************-------*************-------\n"+
                "---------------------------*   CASH    *-------*           *-------\n"+
                "---------------------------* REGISTERS *#######*   LOBBY   *-------\n"+
                "---------------------------*           *-------*           *-------\n"+
                "---------------------------*************-------*************-------\n"+
                "-------------------------------------------------------------------\n"+
                "-------------------------------------------------------------------\n");
	}

}