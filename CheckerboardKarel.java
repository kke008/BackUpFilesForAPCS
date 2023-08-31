/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment Karel4.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	
	public void run() {
		putBeeper();
		while (leftIsClear() || rightIsClear())	
			makeRow();
			turnLeft();
			checkBeeper();
			turnLeft();
			makeRow();
			turnRight();
			checkBeeper();
			turnRight();
	}
	
	public void makeRow() {
		while (frontIsClear()) {
			move();
			if (frontIsClear()) {
				move();
				putBeeper();
			}
		}
	}
	
	public void checkBeeper() {
		if (beepersPresent())
			move();
			
		else {
			move();
			putBeeper();
		}
	}
}
