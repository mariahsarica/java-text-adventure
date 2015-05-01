/**
 * Location.java - Location class
 * @author Mariah Molenaer
 *
 */

public class Location {

	private int id;				  // Unique id value for location (corresponds with index in locs ArrayList)
	private String name;		  // Name of location
	private String description;   // Description of location
	private String directions;    // Movable directions avail from current location
	
	/**
	 * Constructor
	 * @param id Id of location
	 * @param name Name of location
	 */
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
		return this.description + "\n\n";
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
		return this.getDescrip() + this.getDir() + "\nTotal Moves: " + Player.totalMoves 
				+ "\nSteps from Beginning: " + BreadCrumbTrail.stepsFromBeg + "\n\n";
	}

}
