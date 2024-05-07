/**
 *	Game board for the snake game, stored as a 2D array. Displays the
 * 	snake and the target (prints it to screen). Has a border that is
 *  not included in the board array.
 *
 *	@author	Karen Ke
 *	@since	May 7, 2024
 */
public class SnakeBoard {
	
	/*	fields	*/
	private char[][] board;			// The 2D array to hold the board
	private int h;	// height of the board
	private int w;	// width of the board
	
	/*	Constructor	*/
	public SnakeBoard(int height, int width) {
		h = height;
		w = width;
	}
	
	/**
	 *	Print the board to the screen.
	 * 	Assumes that snake's Coordinates and target's Coordinate are valid
	 *  @param snake	the list of coordinates of the snake
	 * 	@param target	location of the target
	 */
	public void printBoard(Snake snake, Coordinate target) {
		/*	implement here	*/
		updateBoard(snake, target);
		
		printRow();
		for (int r = 0; r < h; r++) {
			System.out.print("|");
			for (int c = 0; c < w; c++) {
				System.out.print(board[r][c]);
			}
			System.out.println("|");
		}
		printRow();
	}
	
	/* Helper methods go here	*/
	/** sets height of board. */
	private void setHeight(int height) { h = height; }
	/** sets width of board. */
	private void setWidth(int width) { w = width; }
	
	/**
	 *	Updates the values of board with the coordiantes of the snake and
	 * 	of the target.
	 *  @param snake	list of coordinates of the snake
	 * 	@param target	coordinate of the target
	 */
	public void updateBoard(Snake snake, Coordinate target) {
		board = new char[h][w];
		int firstR = snake.get(0).getValue().getRow();
		int firstC = snake.get(0).getValue().getCol();
		
		board[firstR][firstC] = '@';
		for (int i = 1; i < snake.size(); i++) {
			int r = snake.get(i).getValue().getRow();
			int c = snake.get(i).getValue().getCol();
			board[r][c] = '*';
		}
		
		int targetR = target.getRow();
		int targetC = target.getCol();
		board[targetR][targetC] = '+';
	}
	
	/**
	 *  Print the top and bottom row of the game board.
	 */
	public void printRow() {
		String row = "+";
		for (int i = 0; i < w; i++) {
			row += "-";
		}
		row += "+";
		System.out.println(row);
	}
	
	/*public boolean isValid(Coordinate coord) {
		int r = coord.getRow();
		int c = coord.getCol();
		if (r >= 0 && r < h && c >= 0 && c < w && board[r][c] == null)
			return true;
		return false;
	}
	*/
	
	/*	Accessor methods	*/
	/** returns height of board. */
	public int getHeight() { return h; }
	/** returns width of board. */
	public int getWidth() { return w; }

	
	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/
	
	public static void main(String[] args) {
		// Create the board
		int height = 10, width = 15;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(new Coordinate(3, 3));
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}
}
