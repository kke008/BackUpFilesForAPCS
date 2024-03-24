/*
 * Extends critter and moves like a critter. Overrides Critter's processActors()
 * so that all the rocks in the list of actors is removed from the grid.
 * 
 * @author Karen Ke
 * @since March 23, 2023
 */
 
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import java.util.ArrayList;

public class RockHound extends Critter {
	
	/** Overides the procesActors() method in Critters. Looks through all the 
	 *  Actors in adjacent locations and removes any that are Rocks form the grid.
	 *  
	 *  @param actors		list of adjacent actors
	 */
	public void processActors(ArrayList<Actors> actors) {
		for (int i = 0; i < actors.size(); i++) {
			Actor actor = actors.get(i);
			if (actor instanceof Rock)
				actor.removeSelfFromGrid();
		}
	}
}
