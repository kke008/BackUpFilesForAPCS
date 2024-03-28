/** Create an Egg class that extends the Actor class. An Egg never moves. It starts as
 * Color.WHITE and darkens in color with each step. When the number of steps reaches
 * 45, the Egg turns Color.RED. When the Egg reaches 50 steps, it is replaced by a new
 * Chicken
*/

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

public class Egg extends Actor {
	private int steps;
	private static final double DARKENING_FACTOR = 0.05;
	public Egg() {
		steps = 0;
		setColor(Color.WHITE);
	}

    /* Overrides act() in Actor. Decrement steps. If steps has reached 45,
     * sets it to Color.RED. If steps has reached 50, replaces the Egg with
     * a Chicken. Darkens color otherwise.
     */
    public void act() {
		steps++;
		if (steps == 45) 
			setColor(Color.RED);
			
		else if (steps == 50) {
			Chicken chick = new Chicken();
			chick.putSelfInGrid(getGrid(), getLocation());
		}
		
		else {
			Color c = getColor();
			int red = (int)(c.getRed() * (1 - DARKENING_FACTOR));
			int green = (int)(c.getGreen() * (1 - DARKENING_FACTOR));
			int blue = (int)(c.getBlue() * (1 - DARKENING_FACTOR));
	
			setColor(new Color(red, green, blue));
		}
	}
}
