/** Create a Fox class that extends the Critter class. Set its color to be null. The Fox
always moves to an open neighboring location at random and faces the direction it
has moved. If it cannot move, then it turns in a random direction.

Make the Fox run after the closest Chicken. Find the closest Chicken by using the
distance formula then move to the next empty location in that
direction. If there are no Chickens on the grid, then make a move like 1 above. If several
Chickens are equidistant, then pick one of those directions at random. If there is no open location
in the direction of the closest Chicken, then move to an open neighboring location at random.

3. Make the Fox eat Chickens. The Chicken must be in a neighboring location to the Fox. If more
than one Chicken is a neighbor, then the Fox picks one Chicken at random to eat. The Fox
“eats” by replacing the Chicken with a Tombstone. After the Chicken is eaten, the Fox’s
stomach is full and it takes a “nap” in which it does not move or eat for 10 steps. After the nap, the
Fox is hungry again and chases after Chickens like in 2 above.

Make the Fox die. A Fox dies when it has been hungry for too long. A new Fox always starts as
though it just finished napping and is hungry. Keep track of how many steps the Fox takes while
hungry (after nap). After 20 steps of being hungry, the Fox has now starved to death and is
replaced in the same location with a Tombstone.
*/
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import java.awt.Color;

COMMENTS

public class Fox extends Critter {
	private Location locOfNearestChicken;
	private boolean nearestChickenExists;
	private boolean move;
	private int nap;
	private int hungry;
  
	public Fox() {
		setColor(null);
		locOfNearestChicken = new Location();
		nearestChickenExists = false;
		move = true;
		nap = 0;
		hungry = 0;
	}

	public void processActors(ArrayList<Actor> actors) {
		ArrayList<Chicken> chickens = new ArrayList<Chicken>();
		for (Actor a : actors) {
			if (a instanceOf Chicken)
				chickens.add(a);
		}
	
		if (chickens.size() != 0) {
			nearestChickenExists = true;
			double longestDistance = 0;
			for (int c = 0; c < chickens.size(); c++) {
				Chicken chick = chickens.get(c);
				Location chickLoc = chick.getLocation();
				double rowVal = Math.pow(chickLoc.getRow() - getRow(), 2);
				double colVal = Math.pow(chickLoc.getCol() - getCol(), 2);
				double distance = Math.squr(rowVal + colVal);
				if (distance > longestDistance) {
					longestDistance = distance;
					locOfNearestChicken = chick.getLocation();
				}
	
				else if (distance == longestDistance) {
					int randNum = (int)(Math.random()*2)
						if (randNum == 1)
						locOfNearestChicken = chick.getLocation();
				}
			}
		}
	}
	
	public void move(ArrayList<Location> locs) {
		if (hungry == 20) {
			Tombstone ts = new Tombstone();
			ts.putSelfInGrid(getGrid(), getLocation());
		}
		if (nap == 0) {
			Location loc1 = getLocation();
			if (nearestChickenExists) {
				Direction dirToNC = loc1.getDirectionTowards(locOfNearestChicken);
				Location locToNC = loc1.getAdjacentLocation(toNC);
				  
				if (getGrid().isValid(locToNC)) {
					Actor adjActor = getGrid().get(locToNC);
					if (adjActor instanceOf Chicken) {
						adjActor.removeSelfFromGrid();
						Tombstone ts = new Tombstone();
						ts.putSelfInGrid(getGrid(), locToNC);
						nap = 10;
						hungry = 0;
					}
					else {
						setDirection(dirToNC);
						moveTo(locToNC);
						hungry++;
					}
				}
				else {
					nearestChickenExists = false;
					hungry++;
				}
			}
		    
			if (move && !nearestChickenExists) {
				boolean dirWorks = false;
				int[] randDirections = makeRandomDirections();
				int d = -1;
				do {
					d++;
					int dir = randDirections[d];
					Location locInDir = loc1.getAdjacentLocation(dir);
					if (getGrid().isValid(locInDir) && getGrid().get(locInDir) == null) {
						dirWorks = true;
						setDirection(dir);
						moveTo(locInDir);
					}
				} while (d < randDirections.length && !directionWorks);
		
				if (!dirWorks)
					move = false;
			}
		
			if (!move) {
				int randDir = (int)(Math.random()*7)
				setDirection(randDir * 45);
				move = true;
			}
		}
		else
			nap--;
	}
	
	public int[] makeRandomDirections() {
		int[] randomDirections = new int[8];
		for (int i = 0; i < 8; i++) {
			int ind = 0;
			while (randomDirections[ind] != 0) {
				ind = (int)(Math.random() * 9);
			}
			randomDirections[ind] = i;
		}
		return randomDirections;
	}
}
