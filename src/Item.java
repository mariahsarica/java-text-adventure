/**
 * Item.java - Item class
 * @author Mariah Molenaer
 *
 */

public class Item {
	
	private int id;              // Id of item (corresponds to index in ArrayList
	private String name;         // Name of item
	private String description;  // Description of item
//	private Boolean takable;     // Determines whether this item is capable of being added to the cart (people may not be added to cart but are still part of item class)
	private Boolean taken;       // Specifies whether the item has been taken
	public int locId;            // Location Id of item (correspond to index of Locations array
	
	// Constructor
	public Item(int id, String name) {
		this.id = id;
		this.name = name;
	//	this.takable = takable;
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
		return this.description;
	}
	public void setDescrip(String descrip) {
		this.description = descrip;
	}
	
	public Boolean getTaken() {
		return this.taken;
	}
	public void setTaken(Boolean taken) {
		this.taken = taken;
	}
	
	public int getLodId() {
		return this.locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	
	
	
}
