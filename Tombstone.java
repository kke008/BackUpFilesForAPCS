/*
 * Extends Actor. Each Tombstone has a lifetime of 20 steps, after which it removes
 * itself from the grid.
 * 
 * @author Karen Ke
 * @since March 27, 2024
*/

import info.gridworld.actor.Actor;

public class Tombstone extends Actor {
	private int lifetime;	// lifetime of tombstone
	
	public Tombstone() {
		lifetime = 10;
	}
	
	/** Overrides act() in Actor. Decrements lifetime and checks if the decremented
	 *  lifetime is 0. If so, removes itself from the grid.
	 */
	public void act() {
		lifetime--;
		if (lifetime == 0)
			removeSelfFromGrid();
	}
}
