/*
 * File: MazeSolvingKarel.java
 * -----------------------------
 * The MazeSolvingKarelFinal class extends the basic Karel class
 * so that Karel solves a maze.
 */

import stanford.karel.*;

public class MazeSolvingKarel extends SuperKarel {

	public void run() {
		while (noBeepersPresent()) {
			while (rightIsBlocked() && frontIsClear()) {
				move();
			}
			
			if (rightIsClear()) {
				turnRight();
				move();
			}
				
			else {
				turnLeft();
			}
		}
	}
	    
}
