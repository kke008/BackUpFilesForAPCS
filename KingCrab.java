/* Extends CrabCritter. Overrides CrabCritter's processActors() method so that
 * the chosen actor is either moved one location in the direction away from the 
 * KingCrab or, if it can't be moved, is removed from the grid.
 * 
 * @author Karen Ke
 * @since March 24, 2024
*/

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import java.util.ArrayList;

public class KingCrab exteds CrabCritter {
	/** Overrides CrabCritter's processActors(). Processes Actor by trying to 
	 *  make it move one location further from the KingCrab. If it can't, it 
	 *  removes the Actor form the grid.
	 * 
	 * @param actors	list of actors to choose from to process
	 */
	public void processActors(ArrayList<Actor> actors) {
        if (actors.size() != 0) {
            i = (int) (Math.random() * n);
			Actor actor = actors.get(i);
			Location loc = actor.getLocation();
			int direction = getLocation().getDirectionToward(loc);
			Location oneAway = loc.getAdjacentLocation(direction);
			if (actor.getGrid().isValid(oneAway))
				actor.moveTo(oneAway);
			else
				actor.removeSelfFromGrid();
		}
	}
}
