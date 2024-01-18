/**
 *	SudokuMaker - Creates a Sudoku puzzle using recursion and backtracking
 *
 *	@author Karen Ke
 *	@version 1
 *  @since January 16, 2024
 *
 */
public class SudokuMaker {

	private int[][] puzzle;	// the puzzle
	
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
	 *  @param r		the row of the current value in the grid
	 *  @param c	the column of the current value in the grid
	 *  @param valWorks	whether or not the value works
	 */
	public boolean createPuzzle(int r, int c) {
		int[] nums = new int[9];
		for (int i = 0; i < 9; i++) {
			int num = (int)(Math.random()*9 + 1);
			boolean works = true;
			int j = 0;
			while (j < i && works) {
				if (num == nums[j])
					works = false;
			}
			
			if (!works) {
				num = (int)(Math.random()*9 + 1);
				i--;
			}
		}
		
		boolean valWorks = true;
		int k;
		for (k = 0; k < 9; k++) {	// checking all possible values of val
			int val = nums[k];
			for (int l = 0; l < puzzle[r].length(); l++) {	// checking row
				if (val == puzzle[r][l])
					valWorks = false;
			}
			
			for (int m = 0; m < puzzle.length; m++) {	// checking column
				if (val == puzzle[m][c])
					valWorks = false;
			}
		}
		
		if (k == 9) {
			// if valWorks and not last row or not last column
			if (valWorks && (r < puzzle.length - 1 || c < puzzle[r].length - 1) {
				if (c >= puzzle[r].length - 1)	// if last column:
					createPuzzle(r + 1, 0);
				else 	// if last row
					createPuzzle(r, c + 1);
			}
			
			else/////////////////////////////////////////////////////////////////////////////////////////////////
		}
	}
	
	public static void main(String[] args) {
		SudokuMaker sm = new SudokuMaker();
		sm.createPuzzle(0,0);
		sm.printPuzzle();
	}
	
	public SudokuMaker() {
		for (int r = 0; r < puzzle.length; r++) {
			for (int c = 0; c < puzzle[0].length; c++)
				puzzle[r][c] = 0;
		}
	}
}
