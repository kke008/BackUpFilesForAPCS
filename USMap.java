/**
 *	Map of US cities
 *
 *	@author	Karen Ke
 *	@since	September 5, 2023
 */

/** Set up the canvas size and scale */

public class USMap {
	public static void main (String[] args) {
		USMap ump = new USMap();
		ump.setupCanvas();
	}
	
	public void setupCanvas() {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
	}
	
	public void topTen() {
		FileUtils utils = new FileUtils();
		
		int xLong = new int(-1);
		int yLat = new int(-1);
		
		
		
	}

}
