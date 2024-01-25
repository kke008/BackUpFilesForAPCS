/**
 *	SudokuMaker - Creates a Sudoku puzzle using recursion and backtracking
 *
 *	@author Karen Ke
 *	@version 1
 *  @since January 16, 2024
 *
 */
 
 
 /*
  * CURRENTLY:
  *  - how to make it recurse back properly?
  */
  
public class SudokuMaker {

	private int[][] puzzle;	// the puzzle
	
	public SudokuMaker() {
		puzzle = new int[9][9];
		for (int r = 0; r < puzzle.length; r++) {
			for (int c = 0; c < puzzle[0].length; c++)
				puzzle[r][c] = 0;
		}
	}
	
	public static void main(String[] args) {
		SudokuMaker sm = new SudokuMaker();
		sm.createPuzzle(0,0);
	}
	
	/**
	 * creates the Sodoku puzzle as a 2D array (puzzle) with int values
	 * from 1-9, starting at puzzle[0][0] and working row by row. 
	 * - If the value at the current location is not duplicated, createPuzzle
	 *   is called for the next grid location.
	 * - If the value at the current location is duplicated, other random 
	 *   values from 1-9 are tested
	 *    - if all 9 values fail, createPuzzle is called for the previous
	 * 		grid location and a different random value is assigned
	 * 
	 *  @param r		the row of the current value in the grid
	 *  @param c	the column of the current value in the grid
	 *  @param valWorks	whether or not the value works
	 */
	public boolean createPuzzle(int r, int c) {
		boolean nextWorked = true;
		int val = getVal(r, c);
		if (val == -1) {		// need to go to prev grid square
			return false;
			/*if (c == 0 && r - 1 >= 0)		// need to go to prev row
				createPuzzle(r - 1, puzzle[r - 1].length - 1);
			
			else if (r >= 0 && c - 1 >= 0)
				createPuzzle(r, c - 1);
				*/
		}
		
		else {
			puzzle[r][c] = val;
			
			if (c + 1 == puzzle[r].length && r + 1 <= puzzle.length - 1)
				nextWorked = createPuzzle(r + 1, 0);
				
			//else if (c + 1 < puzzle[r].length)
			else
				nextWorked = createPuzzle(r, c + 1);
		}
		
		if (!nextWorked)
			createPuzzle(r, c);
		
		//if (r >= puzzle.length - 1 && c >= puzzle[r].length - 1)	// at last square
			
			System.out.println(r + "\t" + c);
			printPuzzle();
			System.out.println("\n\n");	
			
		return true;
	}
	
	/**
	 *  creates a randomized list of integers from 1-9 then checks each
	 *  integer until one works 
	 * 
	 *  @param row		row of position that value is for
	 * 	@param col		column of position that value is for
	 * 	@return 		if val works, returns val
	 * 					if val doesn't work, returns -1
	 */
	public int getVal(int row, int col) {
		int[] vals = new int[9];	// list of integers 1-9 in random order
		for (int i = 1; i < 10; i++) {
			int ind = 0;
			while (vals[ind] != 0) {
				ind = (int)(Math.random() * 9);
			}
			vals[ind] = i;
		}
		
		int x = 0;	// number of vals checked
		int val;
		do {
			val = vals[x];
			x++;
		}while (x < 9 && !valWorks(val, row, col));
		
		if (x >= 9)
			return -1;
			
		else
			return val;	
	}
	
	/**
	 *  Checks if a given value works at the given grid location, determined
	 *  by whether or not there are duplicates in the row, column, and
	 * 	the 9 x 9 square it is in
	 * 
	 *   @param val		value being checked
	 *   @param row		row in grid with value
	 * 	 @param col		column in grid with value
	 * 	 @return		whether or not value works
	 */
	public boolean valWorks(int val, int row, int col) {

		for (int i = 0; i < col; i++) {		// checks row for duplicates
			if (val == puzzle[row][i])
				return false;
		}
		
		for (int j = 0; j < row; j++) {		// checks column for duplicates
			if (val == puzzle[j][col])
				return false;
		}
		
		int startRow = row - row % 3;
		int startCol = col - col % 3;
		
		for (int k = 0; k < 3; k++) {		// checks 9 x 9 for duplicates
			for (int l = 0; l < 3; l++) {
				if (val == puzzle[startRow + k][startCol + l])
					return false;
			}
		}
		
		return true;
	}
	
	/**
	 *	printPuzzle - prints the Sudoku puzzle with borders
	 *	If the value is 0, then print an empty space; otherwise, print the number.
	 */
	public void printPuzzle() {
		System.out.print("  +-----------+-----------+-----------+\n");
		String value = "";
		for (int row = 0; row < puzzle.length; row++) {
			for (int col = 0; col < puzzle[0].length; col++) {
				// if number is 0, print a blank
				if (puzzle[row][col] == 0) value = " ";
				else value = "" + puzzle[row][col];
				if (col % 3 == 0)
					System.out.print("  |  " + value);
				else
					System.out.print("  " + value);
			}
			if ((row + 1) % 3 == 0)
				System.out.print("  |\n  +-----------+-----------+-----------+\n");
			else
				System.out.print("  |\n");
		}
	}
}
