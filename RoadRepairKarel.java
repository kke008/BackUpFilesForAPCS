/*
 * File: RoadRepairKarel.java
 * -----------------------------
 * The RoadRepairKarel class extends the basic Karel class
 * so that Karel fills a pothole with a beeper.
 */

import stanford.karel.*;

public class RoadRepairKarel extends SuperKarel {

	public void run() {
		//for (int a = 0; a < 5; a++) {
		while (frontIsClear()) {	
			move();
			fillPothole();
			move();
		}
	}
	
	public void fillPothole() {		// decomposition - break up program
		turnRight();
		move();
		putBeeper();
		turnAround();
		move();
		turnRight();
	}
    
}
