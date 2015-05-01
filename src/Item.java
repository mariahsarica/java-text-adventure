/**
 * Item.java - Item class
 * @author Mariah Molenaer
 *
 */

public class Item {
	
	private int id;              // Id of item (corresponds with index in items ArrayList)
	private String name;         // Name of item
	private String description;  // Description of item
	private boolean takable;     // Determines whether this item is capable of being added to the cart (people may not be added to cart but are still part of Item class)
	private boolean taken;       // Specifies whether the item has been taken
	public int locId;            // Location Id of item (corresponds with index in locations ArrayList - specifies which location the item can be found in)
	
	
	/**
	 * Constructor
	 * @param id Id of item
	 * @param name Name of item
	 * @param takable Whether or not the item can be taken
	 */
	public Item(int id, String name, boolean takable) {
		this.id = id;
		this.name = name;
		this.takable = takable;
		this.taken = false;
	}
	 
	
	// Getter and setter methods
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescrip() {
		return this.description + "\n\n";
	}
	public void setDescrip(String descrip) {
		this.description = descrip;
	}
	
	public boolean getTakable() {
		return this.takable;
	}
	
	public boolean getTaken() {
		return this.taken;
	}
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	
	public int getLodId() {
		return this.locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	
}
