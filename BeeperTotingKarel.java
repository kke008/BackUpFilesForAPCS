/*
 * File: BeeperTotingKarel.java
 * -----------------------------
 * The BeeperTotingKarel class extends the basic Karel class
 * so that Karel picks up a beeper from 1st Street and then
 * carries that beeper to the center of a ledge on 2nd Street.
 */

import stanford.karel.*;

public class BeeperTotingKarel extends SuperKarel {

	public void run() {
		move();
		pickBeeper();
		move();
		
		// Add your lines here
		turnLeft();
		move();
		//turnLeft();
		//turnLeft();
		//turnLeft();
		turnRight();	// encapsulation - puts functions in other place
		move();
		move();
		putBeeper();
		move();
		
	}
    
}
