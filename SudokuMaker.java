/**
 *	SudokuMaker - Creates a Sudoku puzzle using recursion and backtracking
 *
 *	@author Karen Ke
 *	@version 1
 *  @since January 16, 2024
 *
 */
public class SudokuMaker {

	private int[][] puzzle;
		
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
	 *  @param row		the row of the current value in the grid
	 *  @param column	the column of the current value in the grid
	 *  @param valWorks	whether or not the value works
	 */
	public boolean createPuzzle(int row, int column) {
		boolean valWorks = false;
		int val = Math.random()*9 + 1;
		
		while (valWorks == false////////////////////////////////////////////////////////////////////////////////////
			if (puzzle[r] == 
		}
		
		return valWorks;
	}
	
	public static void main(String[] args) {
		SodokuMaker sm = SodokuMaker.java();
		sm.createPuzzle();
		sm.printPuzzle();
	}
	
	public SodokuMaker() {
		for (int r = 0; r < puzzle.length; r++) {
			for (int c = 0; c < puzzle[0].length; c++)
				puzzle[r][c] = 0;
		}
	}
}
