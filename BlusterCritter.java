/*
 * Extends Critter. Overrides Critter's getActors() and processActors() so that 
 * the color of the BlusterCritter increases or decreases based on whether or not
 * the number of critters withing 2 steps of its location is less than its courage.
 * Its courage is set in its constructor.
 * 
 * @author Karen Ke
 * @since March 23, 2024
 */
 
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import java.util.ArrayList;
import java.awt.Color;

public class BlusterCritter extends Critter {
	private double darkeningFactor;		// how much the color increases/decreases
	private int courage;		// the BlusterCritter's courage
	
	public BlusterCritter (int courageIn) {
		darkeningFactor = 0.05;
		courage = courageIn;
	}
	
	/** Overrides the getActors() method in Critter. Returns all of the actors
	 *  within 2 steps of the BlusterCritter.
	 * 	
	 * 	@return actors		all of the actors within 2 steps
	 */
	public ArrayList<Actor> getActors() {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		for (int i = 0; i < 8; i++) {
			int direction = getDirection() + 45*i;
			Location loc = getLocation();
			Location next = loc.getAdjacentLocation(direction);
			int j = 0;
			while (getGrid().isValid(next) && j < 2) {
				actors.add(getGrid().get(loc));
				next = next.getAdjacentLocation(direction);
			}
		}
		return actors;
	}
	
	/** Overrides the processActors in Critter. Counts the number of critters
	 * 	within 2 steps of the BlusterCritter. If this number is bigger than the
	 *  BlusterCreater's courage value, the BlusterCritter's color darkens. If
	 *  the number is smaller, its color brightens.
	 */
	public void processActors(ArrayList<Actor> actors) {
		int numOfCritters = 0;
		for (Actor a : actors) {
			if (a instanceof Critter)
				numOfCritters++;
		}
		
		Color c = getColor();
		if (numOfCritters < courage) {
			darkeningFactor = -0.05;
		}
		
		int red = (int) (c.getRed() * (1 - darkeningFactor));
		int green = (int) (c.getGreen() * (1 - darkeningFactor));
		int blue = (int) (c.getBlue() * (1 - darkeningFactor));

		setColor(new Color(red, green, blue));
	}
}
