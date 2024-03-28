/* Extends Critter. Chicken starts out white. Every step, Chicken randomly
 * chooses to move forward or makes a random turn in any of the 8 compass
 * directions. While the Chicken is young, it has a 1 in 20 percent chance
 * of laying an egg. After 200 steps, the Chicken is middled aged and moves
 * or turns only every other step. After 280 steps, the Chicken is elderly
 * so it starts to gray and only moves every fourth steps. After 300 steps,
 * the Chicken dies and is replaced by a tombstone.
 *
 * @author Karen Ke
 * @since March 27, 2024
 */

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import java.util.ArrayList;

import java.awt.Color;

public class Chicken extends Critter {
	private int steps;	// number of steps Chicken has lived for
	private int age;  // number of steps before each move
					  // 1 = young, 2 = middle age, 4 = elderly
	private int nthStep;	// number of steps since Chicken last moved
  
	public Chicken() {
		steps = 0;
		age = 1;	///////////////////////////////////////////////////////
		nthStep = 0;
		setColor(Color.WHITE);
	}
	
	/**
     * Overrides processActors() in Critter. Removes all adjacent actors
     * that are not Rocks, Critters, or Eggs.
     * @param actors	the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Critter) && 
				!(a instanceof Egg))
                a.removeSelfFromGrid();
        }
    }
    
	/** Overrides getMoveLocations() in Critter. Only looks at the location
	 *  immediately in front of the Chicken, adds it to the ArrayList if
	 *  it is valid, and returns the ArrayList.
	 *  @return moveLocations	Array List with the forward location if
	 * 							it is valid, empty otherwise
	 */
	public ArrayList<Location> getMoveLocations() {
		ArrayList<Location> moveLocations = new ArrayList<Location>();
		Location forward = getLocation().getAdjacentLocation(getDirection());
		
		if (getGrid().isValid(forward))
			moveLocations.add(forward);
		
		return moveLocations;
	}
	
	/** Overrides makeMove() in Critter. If the Chicken is supposed to 
	 *  move this step, gets a random number from 0-8 and moves forward
	 *  or turns in a random direction accordingly. If the Chicken is
	 *  elderly, it gets grayer. Also checks if number of steps match a
	 *  landmark for a new age and changes it, or dies accordingly.
	 *  @param loc		location in front of chicken, if it is valid
	 * 					null otherwise
	 */
	public void makeMove(Location loc) {
		if (nthStep == age) {
			int randNum = (int)(Math.random()*10);
			if (randNum == 9 && loc != null && getGrid().get(loc) == null) {
				Location locBeforeMove = getLocation();
				moveTo(loc);
				if (age == 1) {  // if young, has 1 in 20 change of laying egg
					int randNum2 = (int)(Math.random()*20);
					if (randNum2 == 2) {
						Egg egg = new Egg();
						egg.putSelfInGrid(getGrid(), locBeforeMove);
					}
				}
			}
			else 
				setDirection((randNum * 45) % 360);
			nthStep = 1;
		}
		else
			nthStep++;

		if (age == 4) {    // if elderly, gets grayer
			Color c = getColor();
			int avg = (int)(c.getRed() + c.getGreen() + c.getBlue()) / 3;
			if (avg == c.getRed() && avg == c.getGreen() && avg == c.getBlue())
				avg = (int)(c.getRed() / 3);
			int red = c.getRed() - (int)((c.getRed() - avg) * 0.05);
			int green = c.getGreen() - (int)((c.getGreen() - avg) * 0.05);
			int blue = c.getBlue() - (int)((c.getBlue() - avg) * 0.05);
			setColor(new Color(red, green, blue));
		}

		steps++;
		if (steps == 200)
			age = 2;
		else if (steps == 280)
			age = 4;
		else if (steps == 300) {
			Tombstone ts = new Tombstone();
			ts.putSelfInGrid(getGrid(), getLocation());
		}
	}
}
