/** Extends Critter. Foxes try to chase after and eat Chickens. If it eats,
 *  they take a nap during which they don't move. If they do not eat, they
 *  slowly become hungry until they die of starvation or eat. If there is
 *  no nearest Chicken for the Fox to chase after, it moves randomly or
 *  turns randomly.
 * @author Karen Ke
 * @since March 28, 2024
 */
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import java.awt.Color;
import java.util.ArrayList;

public class Fox extends Critter {
	private Location locOfNearestChicken;
	private boolean nearestChickenExists;
	private boolean move;
	private int nap;
	private int hungry;
  
	/** Foxes are null in color*/
	public Fox() {
		setColor(null);
		locOfNearestChicken = getLocation();
		nearestChickenExists = false;
		move = true;
		nap = 0;
		hungry = 0;
	}
	
	/** Overrides getActors() in Critter. Returns ArrayList of all the
	 *  actors in the grid.
	 *  @return actors	list of all actors in grid
	 */
	public ArrayList<Actor> getActors() {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		
		for (int r = 0; r < getGrid().getNumRows(); r++) {
			for (int c = 0; c < getGrid().getNumCols(); c++) {
				Actor a = getGrid().get(new Location(r, c));
				if (a != null)
					actors.add(a);
			}
		}
		return actors;
	}
	
	/** Overrides processActors() in Critter. Makes an array of nearby
	 *  Chickens, uses the array to find the nearest Chicken (if it exists),
	 *  and sets the location of that Chicken. If there are multiple
	 *  "nearest" Chickens, a random one is chosen.
	 *  @param actors		list of all nearby actors; used to make list
	 * 						of nearby Chickens
	 */
	public void processActors(ArrayList<Actor> actors) {
		ArrayList<Actor> chickens = new ArrayList<Actor>();
		for (Actor a : actors) {
			if (a instanceof Chicken)
				chickens.add(a);
		}
		
		if (chickens.size() != 0) {		// if there are chickens nearby
			nearestChickenExists = true;
			double longestDistance = 0;
			for (int c = 0; c < chickens.size(); c++) {
				Actor chick = chickens.get(c);
				Location chickLoc = chick.getLocation();
				double diffRow = chickLoc.getRow() - getLocation().getRow();
				double diffCol = chickLoc.getCol() - getLocation().getCol();
				double rowVal = Math.pow(diffRow, 2);
				double colVal = Math.pow(diffCol, 2);
				double distance = Math.sqrt(rowVal + colVal);
				if (distance > longestDistance) {
					longestDistance = distance;
					locOfNearestChicken = chick.getLocation();
				}
	
				else if (distance == longestDistance) {
					int randNum = (int)(Math.random()*2);
						if (randNum == 1)
						locOfNearestChicken = chick.getLocation();
				}
			}
			setDirection(getLocation().getDirectionToward(locOfNearestChicken));
		}
	}
	
	/** Overrides makeMove() in Critter. If the fox has been hungry for 
	 *  too long, it "dies". Otherwise, if the fox is not takng a nap,
	 *  checks if nearest Chicken exists. If it does and the adjacent
	 *  grid space in that location is valid, fox moves there and eats
	 *  any Chicken that is there. If it eats, the hungry var is reset, 
	 *  otherwise it is incremented. If the location is not valid, hungry
	 *  is incremented and nearestChickenExists is reset. If the nearest
	 *  Chicken doesn't exist and the chicken isn't napping, it moves to 
	 *  a randomly selected location. If the Chikcen does not move, it 
	 *  turns in a random direction. If the Chicken is napping, nap is
	 *  decremented.
	 * @param loc		the random location the Chicken may or may not move to
	 */
	public void makeMove(Location loc) {
		if (hungry == 20) {
			Tombstone ts = new Tombstone();
			ts.putSelfInGrid(getGrid(), getLocation());
		}
		else if (nap == 0) {
			Location loc1 = getLocation();
			System.out.println(nearestChickenExists);	/////////////////////////////
			if (nearestChickenExists) {
				Location locToNC = loc1.getAdjacentLocation(getDirection());
				  
				if (locToNC.equals(locOfNearestChicken)) {
						System.out.println("is a chicken");	//////////////////////
						getGrid().get(locOfNearestChicken).removeSelfFromGrid();
						nearestChickenExists = false;
						Tombstone ts = new Tombstone();
						ts.putSelfInGrid(getGrid(), locToNC);
						nap = 10;
						hungry = 0;
				}
				moveTo(locToNC);
				hungry++;
			}
		    
			if (move && !nearestChickenExists)
				moveTo(loc);
		
			if (!move) {
				setDirection((int)(Math.random()*7) * 45);
				move = true;
			}
		}
		else
			nap--;
	}
}
