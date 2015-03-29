/**
 * Location.java - Location class
 * @author Mariah Molenaer
 *
 */

public class Location {

	private int id;				// Unique id value for location
	private String name;		// Name of location
	private String description; // Description of location
	private String directions;  // Movable directions avail from current location
	
	// Constructor
	public Location(int id, String name) {
		this.id = id;
		this.name = name;
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
	
	public String getDir() {
		return this.directions;
	}
	public void setDir(String dir) {
		this.directions = dir;
	}
	
	public String getText() {
		return this.description + "\n" + this.getDir() + "\nMoves: " + Player.moves;
	}

}
