/**
 *	SimpleGraphics.java
 *
 *	To compile Linux:	javac -cp .:acm.jar SimpleGraphics.java
 *	To execute Linux:	java -cp .:acm.jar SimpleGraphics
 *	To compile MS Powershell:	javac -cp ".;acm.jar" SimpleGraphics.java
 *	To execute MS Powershell:	java -cp ".;acm.jar" SimpleGraphics
 *
 *	@author	Karen Ke
 *	@since	September 19, 2023
 */
 
/*	All package classes should be imported before the class definition.
 *	"java.awt.Color" means package java.awt contains class Color. */
import java.awt.Color;

/*	The following libraries are in the acm.jar file. */
import acm.program.GraphicsProgram;
import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class SimpleGraphics extends GraphicsProgram {
	
	/*	All fields and constants should be declared here.
	 *	Only constants (final) are initialized here. */
	
	private GRect brick;	// bricks to be drawn
	private final double WIDTH = 50;	// width of each brick
	private final double HEIGHT = 20;	// height of each brick
	
	private double center;	// x coordinate of the center of the window	
	private double height;	// height of window
	
	/**	The init() method is executed before the run() method.
	 *	All initialization steps should be performed here.
	 */
	public void init() {
		GCanvas canvas = getGCanvas();
		center = canvas.getWidth() / 2.0;
		height = canvas.getHeight();
	}

	/**	The run() method is executed after init().
	 *	Draws the target and the brick wall
	 *	Exercise hint: Use one-dimensional arrays for the GOval's and GRect's.
	 */
	public void run() {
		//drawTarget();
		drawBricks();
	}
	
	/** drawTarget() draws 5 alternating red and white rings in the shape
	 * 	of a target at the bottom of the screen.
	 * 	Only half of the target is shown, the other half is cut off by
	 * 	the screen.
	 */
	public void drawTarget() {
		int radius = 200;	// radius of circles (biggest = 200)
		double targetX = center - radius;	// x coordinate to draw circles
		double targetY = height - radius; 	// y coordinate to draw 
												//circles
		GOval circle = new GOval(targetX, targetY, 2 * radius, 2 * radius);	
		// "ring" of the target
		
		for (int i = 0; i < 5; i++) {
			circle.setFilled(true);
			if (i % 2 == 0)
				circle.setFillColor(Color.RED);
			else
				circle.setFillColor(Color.WHITE);
				
			add(circle);
			radius -= 30;
			targetX += 30;
			targetY += 30;
			circle = new GOval(targetX, targetY, 2 * radius, 2 * radius);
		}
	}
	 
	 /** drawBricks() draws rectangular 50 x 20 bricks in an upside
	  *  down pyramid centered at the top of the screen.
	  *  The pyramid will have 10 rows.
	  */
	 public void drawBricks() {
		double brickY; // y coordinate of each brick
		double brickX; // x coordinate of each brick
		
		for (int j = 0; j < ROWS; j++) {
			brickY = j * HEIGHT;
			brickX = center - (ROWS - j) * WIDTH / 2;
			for (int i = 0; i < (ROWS - j); i++) {
				brickX += WIDTH;
				brick = new GRect(brickX, brickY, WIDTH, HEIGHT);
				add(brick);
			}
		}
	 }
}
