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
		while (frontIsClear() && leftIsClear()) {   
			makeRow();
			left();

			makeRow();
		      if (rightIsClear())
		         right();
		   }
		   
		   if (rightIsClear())
		      makeRow();
	}
	
	function makeRow() {
	   while (frontIsClear()) {
	      putBeeper();
	      move();
	      if (frontIsClear())
	         move();
	   }
	   
	   turnAround();
	   move();
	   turnAround();
	   if (beepersPresent())
	      move();
	   
	   else {
	      move();
	      putBeeper();
	   }
	}
	
	function left() {
	   turnLeft();
	   if (beepersPresent()) {
	      move();
	      turnLeft();
	   }
	   
	   else {
	      move();
	      putBeeper();
	      turnLeft();
	      move();  
	   }
	   
	   move();
	}
	
	function right() {
	   turnRight();
	   if (beepersPresent()) {
	      move();
	      turnRight();
	   }
	   
	   else {
	      move();
	      putBeeper();
	      turnRight();
	      move();  
	   }
	   
	   move();
	}
}
