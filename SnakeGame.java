/**
 *	Snake Game - Uses singly linked lists to create the snake game. Snake
 *  	(@ for head and * for each tail segment) moves around board and eats
 *  	target (+). If user moves snake to a space on the board that is not
 * 	 empty or to a space off the board, the snake dies and they lose. Snake
 *  	is moved using WASD keys.
 * 
 *	@author	Karen Ke
 *	@since	May 7, 2024
 */
 ///////////////// if new coordinates are invalid and can't have negative
				// coords then just return old coord and figure it out in snakeDies()
public class SnakeGame {
	
	private Snake snake;		// the snake in the game
	private SnakeBoard board;	// the game board
	private Coordinate target;	// the target for the snake
	private int score;			// the score of the game

	/*	Constructor	*/
	public SnakeGame() { 
		board = new SnakeBoard(20, 25);
		
		int sRow = (int)(Math.random()*(board.getHeight() - 5));
		int sCol = (int)(Math.random()*board.getWidth());
		snake = new Snake(new Coordinate(sRow, sCol));
		
		int tRow = sRow;
		int tCol = sCol;
		while (tRow == sRow && tCol == sCol) {
			tRow = (int)(Math.random()*board.getHeight());
			tCol = (int)(Math.random()*board.getWidth());
		}
		target = new Coordinate(tRow, tCol);
	}
	
	/*	Main method	*/
	public static void main(String[] args) {
		SnakeGame sg = new SnakeGame();
		sg.run();
	}
	
	/** Runs the game: prints the intro, then the board, as long as the
	 *  game is not over. Also prompts the player for moves and prints
	 *  the help menu if needed. */
	public void run() {
		printIntroduction();
		
		// while game is not over:
		board.printBoard(snake, target);
		String guide = "Score: " + score + " (w - North, s - South, " +
			"d - East, a - West, h - help)";
		char move = ' ';
		while (move != 'w' || move != 's' || move != 'd' || move != 'a' ||
			move != 'h') {
			 Prompt.getChar(guide);
		 }
		
		Coordinate last = snake.get(snake.size() - 1).getValue();
		Coordinate newHead = snake.get(0);
		if (move == 'w')
			newHead = getNorth(newHead);
		else if (move == 's')
			newHead = getSouth(newHead);
		else if (move == 'd')
			newHead = moveEast(newHead);
		else if (move == 'a')
			newHead = getWest(newHead);
		else 
			helpMenu();
			
		if(newHead.equals(target)) {
				snake.add(last);
				///////////////////////////////////////////////////////////////////////////
		}
		// move somehow
		
		if (snakeDies(move)) {
			System.out.println("Thanks for playing SnakeGame!!!");
			// end game
		}
			
		// game is not over
	}
	
	/**	Print the game introduction	*/
	public void printIntroduction() {
		System.out.println("  _________              __            ________");
		System.out.println(" /   _____/ ____ _____  |  | __ ____  /  _____/_____    _____   ____");
		System.out.println(" \\_____  \\ /    \\\\__  \\ |  |/ // __ \\/   \\  ___\\__  \\  /     \\_/ __ \\");
		System.out.println(" /        \\   |  \\/ __ \\|    <\\  ___/\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/");
		System.out.println("/_______  /___|  (____  /__|_ \\\\___  >\\______  (____  /__|_|  /\\___  >");
		System.out.println("        \\/     \\/     \\/     \\/    \\/        \\/     \\/      \\/     \\/");
		System.out.println("\nWelcome to SnakeGame!");
		System.out.println("\nA snake @****** moves around a board " +
							"eating targets \"+\".");
		System.out.println("Each time the snake eats the target it grows " +
							"another * longer.");
		System.out.println("The objective is to grow the longest it can " +
							"without moving into");
		System.out.println("itself or the wall.");
		System.out.println("\n");
	}
	
	/**	Print help menu	*/
	public void helpMenu() {
		System.out.println("\nCommands:\n" +
							"  w - move north\n" +
							"  s - move south\n" +
							"  d - move east\n" +
							"  a - move west\n" +
							"  h - help\n" +
							"  f - save game to file\n" +
							"  r - restore game from file\n" +
							"  q - quit");
		Prompt.getString("Press enter to continue");
	}
	
	/** Returns the coordinate north of a given coordinate.
	 *  @param coord		the given coordinate
	 *  @return north		the coordinate north of the snake's head
	*/
	public Coordinate getNorth(Coordinate coord) {
		
	}
	
	/** Returns the coordinate south of a given coordinate.
	 *  @param coord		the given coordinate
	 *  @return south		 the coordinate south of the snake's head */
	public Coordinate getSouth(Coordinate coord) {
		
	}
	
	/** Returns the coordinate east of a given coordinate.
	 *  @param coord		the given coordinate
	 *  @return east		 the coordinate east of the snake's head */
	public Coordinate getEast(Coordinate coord) {
		
	}
	
	/** Returns the coordinate west of a given coordinate.
	 *  @param coord		the given coordinate
	 *  @return west		 the coordinate west of the snake's head */
	public Coordinate getWest(Coordinate coord) {
		
	}
	
	/** Checks if the snake dies. Snake dies if: 
	 * 	  - the location where its head is is off the board or is
	 * 		occupied by another part of the snake
	 * 	  - there are only 5 empty grid spaces left
	 * 	  - the snake's head is surrounded by the borders of the board
	 *		and / or other parts of the snake (the snake's head has
	 * 		nowhere to go)
	 *  @param move			user input
	 *  @return 			whether or not snake dies
	 */
	public boolean snakeDies(char move){
		if (move == 'q') {
			char confirmation = Prompt.getChar("Do you really want to " + 
				"quit? (y or n)");
			if (confirmation == 'y')
				return true;
		}
		
		else {
			int r = newHead.getRow();
			int c = newHead.getCol();
			if (r >= board.getHeight() || r < 0 || c >= board.getWidth() ||
				c < 0)
				return true;
			else if (getNearbyValidSpaces() == 0)
				return true;
			else if (geTotaltValidSpaces() <= 5)
				return true;
		}
		return false;
	}
	
	/** Returns number of valid spaces in the NSEW direction of the 
	 * 	snake's head using isValid().
	 *  @return		number of valid spaces
	 */
	 public int getNearbyValidSpaces() {/////////////////////////////
		Coordinate head = snake.get(0).getValue();
		int numSpaces = 0;
		if (isValid(getNorth(head)))
			numSpaces++;
		if (isValid(getSouth(head)))
			numSpaces++;
		if (isValid(getEast(head)))
			numSpaces++;
		if (isValid(getWest(head)))
			numSpaces++;
		
		return numSpaces++;
	 }
	 
	 /** Returns whether or not given coordinate is valid. Space is 
	  *  valid if it is empty or occupied only by the target.
	  *  @return	whether or not space is valid
	  */
	 public boolean isValid(Coordinate coord) {
		 // get row and col adn check for off grid
		 // check for repeats in snake after the head
	 }
	 
	 /** Returns the total number of valid spaces on the board using isValid().
	  *  @return	number of valid spaces on board
	  */
	 public int getTotalValidSpaces() {
		 
	 }
}
