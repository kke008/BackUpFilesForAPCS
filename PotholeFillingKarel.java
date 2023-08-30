/*
 * File: PotholeFillingKarel.java
 * -----------------------------
 * The PotholeFillingKarel class extends the basic Karel class
 * so that Karel fills a pothole with a beeper.
 */

import stanford.karel.*;

public class PotholeFillingKarel extends SuperKarel {

	public void run() {
		move();
		fillPothole();
		move();
		
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
