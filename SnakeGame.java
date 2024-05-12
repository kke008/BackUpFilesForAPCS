import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
		
		boolean gameOver = false;
		while (!gameOver) {
			board.printBoard(snake, target);
			String guide = "Score: " + score + " (w - North, s - South, " +
				"d - East, a - West, h - help)";
			char move = ' ';
			while (move != 'w' && move != 's' && move != 'd' && move != 'a' &&
				move != 'h' && move != 'q' && move != 'f' && move != 'r') {
				move = Prompt.getChar(guide);
			 }
			
			Coordinate newHead = snake.get(0).getValue();
			if (move == 'w')
				newHead = getNorth(newHead);
			else if (move == 's')
				newHead = getSouth(newHead);
			else if (move == 'd')
				newHead = getEast(newHead);
			else if (move == 'a')
				newHead = getWest(newHead);
			else if (move == 'h')
				helpMenu();
			else {
				String fileName = "snakeGameSave.txt";
				File f = new File(fileName);
				if (move == 'f')
					saveGame(fileName);
				else if (move == 'r')
					restoreGame(fileName);
			}
			
			if(newHead.equals(target)) {
				score++;
				Coordinate last = snake.get(snake.size() - 1).getValue();
				snake.add(last);
				int tRow = target.getRow();
				int tCol = target.getCol();
				while (tRow == target.getRow() && tCol == target.getCol()) {
					tRow = (int)(Math.random()*board.getHeight());
					tCol = (int)(Math.random()*board.getWidth());
				}
				target = new Coordinate(tRow, tCol);
			}
			
			if (snakeWillDie(move, newHead)) {
				System.out.println("Thanks for playing SnakeGame!!!");
				gameOver = true;
			}
			
			else if (move != 'q' && move != 'f' && move != 'r') {
				for (int s = snake.size() - 1; s > 0; s--)
					snake.set(s, snake.get(s - 1).getValue());
				snake.set(0, newHead);
			}
		}
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
	
	/** Saves all the coordinates of the snake in order, the location of the
	 *  target, and the score into a file.
	 *  @param fileName		name of file to store game info into
	 */
	public void saveGame(String fileName) {
		PrintWriter writer = FileUtils.openToWrite(fileName);
		writer.println("Snake coordinates:");
		for (int s = 0; s < snake.size(); s++)
			writer.println(snake.get(s).getValue());
		writer.println("target: " + target);
		writer.println("score: " + score);
	}
	
	/** reads the file that the game was stored into and sets current game info
	 *  accordingly
	 *  @param fileName		name of file to read
	 */
	public void restoreGame(String fileName) {
		Scanner reader = FileUtils.openToRead(fileName);
		int i = 0;
		String next = reader.next();
		while(!next.equals("target")) {
			int row = reader.nextInt();
			int col = reader.nextInt();
			reader.nextLine();
			if (i < snake.size())
				snake.set(i, new Coordinate(row, col));
			else
				snake.add(new Coordinate(row, col));
			i++;
			next = reader.next();
		}
		int r = reader.nextInt();
		int c = reader.nextInt();
		target = new Coordinate(r, c);
		reader.nextLine();
		int newScore = reader.nextInt();
		score = newScore;
	}
	
	/** Returns the coordinate north of the snake's head.
	 *  @param coord		the given coordinate
	 *  @return north		the coordinate north of the snake's head
	*/
	public Coordinate getNorth(Coordinate coord) {
		Coordinate head = snake.get(0).getValue();
		int newR = head.getRow() - 1;
		Coordinate newCoord = new Coordinate(newR, head.getCol());
		return newCoord;
	}
	
	/** Returns the coordinate south of the snake's head.
	 *  @param coord		the given coordinate
	 *  @return south		 the coordinate south of the snake's head */
	public Coordinate getSouth(Coordinate coord) {
		Coordinate head = snake.get(0).getValue();
		int newR = head.getRow() + 1;
		Coordinate newCoord = new Coordinate(newR, head.getCol());
		return newCoord;
	}
	
	/** Returns the coordinate east of the snake's head.
	 *  @param coord		the given coordinate
	 *  @return east		 the coordinate east of the snake's head */
	public Coordinate getEast(Coordinate coord) {
		Coordinate head = snake.get(0).getValue();
		int newC = head.getCol() + 1;
		Coordinate newCoord = new Coordinate(head.getRow(), newC);
		return newCoord;
	}
	
	/** Returns the coordinate west of the snake's head.
	 *  @param coord		the given coordinate
	 *  @return west		 the coordinate west of the snake's head */
	public Coordinate getWest(Coordinate coord) {
		Coordinate head = snake.get(0).getValue();
		int newC = head.getCol() - 1;
		Coordinate newCoord = new Coordinate(head.getRow(), newC);
		return newCoord;
	}
	
	/** Checks if the snake dies. Snake dies if: 
	 * 	  - the location where its head is is off the board or is
	 * 		occupied by another part of the snake
	 * 	  - there are only 5 empty grid spaces left
	 * 	  - the snake's head is surrounded by the borders of the board
	 *		and / or other parts of the snake (the snake's head has
	 * 		nowhere to go)
	 *  @param move			user input
	 *  @param newHead		location where the new head will go
	 *  @return 			whether or not snake dies
	 */
	public boolean snakeWillDie(char move, Coordinate newHead){
		if (move == 'f' || move == 'r')
			return false;
			
		else if (move == 'q') {
			char confirmation = Prompt.getChar("Do you really want to " + 
				"quit? (y or n)");
			if (confirmation == 'y')
				return true;
		}
		
		else if (!isValid(newHead) || getNearbyValidSpaces() == 0 || 
			getTotalValidSpaces() <= 5)
				return true;
				
		return false;
	}
	
	/** Returns number of valid spaces in the NSEW direction of the 
	 * 	snake's head using isValid().
	 *  @return		number of valid spaces
	 */
	 public int getNearbyValidSpaces() {
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
		int row = coord.getRow();
		int col = coord.getCol();
		if (row < 0 || row >= board.getHeight() || col < 0 || col >= board.getWidth())
			return false;
		else {
			for (int i = 0; i < snake.size(); i++)
				if (snake.get(i).getValue().equals(coord))
					return false;
		}
		return true;
	 }
	 
	 /** Returns the total number of valid spaces on the board using isValid().
	  *  @return	number of valid spaces on board
	  */
	 public int getTotalValidSpaces() {
		 int numSpaces = 0;
		 for (int r = 0; r < board.getHeight(); r++) {
			 for (int c = 0; c < board.getWidth(); c++) {
				 if (board.getChar(r, c) != '*' && board.getChar(r, c) != '@')
					numSpaces++;
			 }
		 }
		 return numSpaces;
	 }
}
