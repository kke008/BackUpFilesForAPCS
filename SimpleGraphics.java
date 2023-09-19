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
	private GOval circle;	// circles to be drawn
	private final double RADIUS = 300;	// radius of biggest circle
	
	private GRect brick;	// bricks to be drawn
	private final double WIDTH = 50;	// width of each brick
	private final double HEIGHT = 20;	// height of each brick
	
	private double center;		// x coordinate of the center of the window
	private double yCoord;		// x coordinate to draw circles and bricks
	
	/**	The init() method is executed before the run() method.
	 *	All initialization steps should be performed here.
	 */
	public void init() {
		GCanvas canvas = getGCanvas();
		center = canvas.getWidth() / 2.0 - RADIUS;
		yCoord = canvas.getHeight() - RADIUS;
		circle = new GOval(center, yCoord, 2 * RADIUS, 2 * RADIUS);
		brick = new GRect(center, yCoord, WIDTH, HEIGHT);
	}

	/**	The run() method is executed after init().
	 *	Draws the target and the brick wall
	 *	Exercise hint: Use one-dimensional arrays for the GOval's and GRect's.
	 */
	public void run() {
		for (int i = 1; i < 6; i++) {
			circle.setFilled(true);
			if (i % 2 == 1)
				circle.setFillColor(Color.RED);
			else
				circle.setFillColor(Color.WHITE);
			add (circle);
			
			double newRadius = 2 * (RADIUS - (40 * i));
			center = canvas.getWidth() / 2.0 - newRadius;
			circle = new GOval(center, yCoord, newRadius, newRadius);
			
		}
		
		
		/*circle = new GOval(100, 100, RADIUS * 2, RADIUS * 2);
		circle.setFilled(true);
		circle.setFillColor(Color.RED);
		
		square = new GRect(300, 100, SIDE, SIDE);
		square.setFilled(true);
		square.setFillColor(Color.BLUE);
		
		add(circle);
		add(square);
		*/
	}
}
