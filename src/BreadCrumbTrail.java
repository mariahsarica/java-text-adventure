/**
 * BreadCrumbTrail.java - BreadCrumbTrail
 * @author Mariah Molenaer
 */

public class BreadCrumbTrail {

	
	public static class BreadCrumb {
		
		int value;
		BreadCrumb next;
		
		BreadCrumb(int val, BreadCrumb n) {
			value = val;
			next = n;
		}
		
		BreadCrumb(int val) {
			this(val, null);
		}
		
	}
	
	
	static BreadCrumb first;
	static BreadCrumb last;
	public static int stepsFromBeg = 0;
	
	public BreadCrumbTrail() {
		first = null;
		last = null;
	}
	
	/**
	 * The isEmpty method checks to see if the bread crumb
	 * trail is empty.
	 * @return true if the trail is empty,
	 * 		   false if it is not.
	 */
	public static boolean isEmpty() {
		if (first == null) {
			return true;
		}
		return false;
	}
	
	
	/** 
	 * The add method adds a bread crumb to the 
	 * end of the bread crumb trail.
	 * @param currentLoc The index of the current location (each bread crumb is a location)
	 */
	public static void add(int currentLoc) {
		if (isEmpty()) {
			first = new BreadCrumb(currentLoc);
			last = first;
			stepsFromBeg = stepsFromBeg + 1;
		} else {
			last.next = new BreadCrumb(currentLoc);
			last = last.next;
			stepsFromBeg = stepsFromBeg + 1;
		}
	}
	
	
	/**
	 * The remove method removes the last bread crumb from
	 * the bread crumb trail while looping over the number
	 * of steps the user wants to backtrack (number of bread 
	 * crumbs that need to be removed).
	 * @param steps The number of steps the user wants backtrack.
	 */
	public static void remove(int steps) {
		if (last == null) {
			return;
		} else if (last == first || steps == stepsFromBeg) {
			first = null;
			last = null;
			stepsFromBeg = 0;
		} else {
			for (int i = 0; i < steps; i++) {
				BreadCrumb prev = first;
				while (prev.next != last) {
					prev = prev.next;
				}
				last = prev;
				last.next = null;
				stepsFromBeg = stepsFromBeg - 1;
			}
		}
	}
	

}
