/* Runner for Tombstone.java
 *
 * @author Karen Ke
 * @since March 27, 2024
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;

public class TombstoneRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		Tombstone ts = new Tombstone();
		world.add(new Location(1,1), ts);
		world.show();		
	}
}
