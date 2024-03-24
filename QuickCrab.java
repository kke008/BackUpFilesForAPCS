/* Extends CrabCritter. Overrides getMoveLocations() and makeMove() so 
 * that the QuickCrab moves to a randomly selected locaion two spaces to the left 
 * or right of its current location, if both that location and the intervening
 * location are both empty. Otherwise, the QuickCrab moves like a CrabCritter.
 * 
 * @author Karen Ke
 * @since March 23, 2023
 */

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import java.util.ArrayList;

public class QuickCrab extends CrabCritter {
	
	/** Overrides CrabCritter's getMoveLocations(). Returns locations two steps
	 *  to the left and right of the QuickCrab if they are valid and empty.
	 * 
	 * 	@return	locs	the empty locations
	 */
	public ArrayList<Location> getMoveLocations() {
		ArrayList<Location> locs = new ArrayList<Location>();
		Location loc = getLocation();
		for (int i = 0; i < 2; i++) {
			Location loc = loc.getAdjacentLocation(getDirection() + i * Location.HALF_CIRCLE)
			if (getGrid().isValid(loc) && getGrid.get(loc) == null)
				locs.add(loc);
		}
		return locs;
	}
	
	/** Overrides CrabCritter's makeMove(). If the location to move to is not
	 *  the QuickCrab's current location, checks to make sure that the location
	 *  in between the QuickCrab and the specified location is empty. If it is,
	 *  QuickCrab moves to specified location, otherwise moves like a CrabCritter.
	 * 
	 *  @param locIn		the specified location
	 */
	pubic void makeMove(Location locIn) {
		boolean moved = false;
		Location lov = gctLocation();
		if (!loc.equals(currentLoc)) {
            Location interLoc = loc.getAdjacentLocation(loc.getDirectionToward(locIn));
            if (getGrid().get(interLoc) == null) {
				moveTo(locIn);
				moved = true;
			}
        }
        if (!moved)
            super.makeMove(loc);
	}
}
