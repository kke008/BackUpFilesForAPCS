/*
 *  Modifies ChameleonCritter so that objects change color to the color 
 *  of one of the actors immediately in front or behind. If there is no 
 *  actor in either of these locations, then the ChameleonKid darkens.
 * 
 *  @author Karen Ke
 *  @since March 21, 2024
 */
 
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import info.gridworld.grid.BoundedGrid;

import java.util.ArrayList;
import java.awt.Color;

public class ChameleonKid extends ChameleonCritter {
	/**
     * Gets the actors immediately in front or behind the ChameleonKid
     * @return a list of actors for this ChameleonKid to process.
     */
    public ArrayList<Actor> getActors()
    {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		Location loc = getLocation();
		Location front = loc.getAdjacentLocation(getDirection());
		if (getGrid().isValid(front) && getGrid().get(front) != null)
			actors.add(getGrid().get(front));
		
		Location back = loc.getAdjacentLocation(getDirection() + Location.HALF_CIRCLE);
		if (getGrid().isValid(back) && getGrid().get(back) != null)
			actors.add(getGrid().get(back));
		
        return actors;
    }
}
