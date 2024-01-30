import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *	SudokuSolver - Solves an incomplete Sudoku puzzle using recursion and backtracking
 *
 *	@author	Karen Kw
 *	@since	January 26, 2024
 *
 */
public class SudokuSolver {

	private int[][] puzzle;		// the Sudoku puzzle
	
	private String PUZZLE_FILE = "puzzle1.txt";	// default puzzle file
	
	/* Constructor */
	public SudokuSolver() {
		puzzle = new int[9][9];
		// fill puzzle with zeros
		for (int row = 0; row < puzzle.length; row++)
			for (int col = 0; col < puzzle[0].length; col++)
				puzzle[row][col] = 0;
	}
	
	public static void main(String[] args) {
		SudokuSolver sm = new SudokuSolver();
		sm.run(args);
	}
	
	public void run(String[] args) {
		// get the name of the puzzle file
		String puzzleFile = PUZZLE_FILE;
		if (args.length > 0) puzzleFile = args[0];
		
		System.out.println("\nSudoku Puzzle Solver");
		// load the puzzle
		System.out.println("Loading puzzle file " + puzzleFile);
		loadPuzzle(puzzleFile);
		printPuzzle();
		// solve the puzzle starting in (0,0) spot (upper left)
		solvePuzzle(0, 0);
		printPuzzle();
	}
	
	/**	Load the puzzle from a file
	 *	@param filename		name of puzzle file
	 */
	public void loadPuzzle(String filename) {
		Scanner infile = FileUtils.openToRead(filename);
		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
				puzzle[row][col] = infile.nextInt();
		infile.close();
	}
	
	/**	Solve the Sudoku puzzle using brute-force method.
	 * Assumes current grid location is empty (unless current location is
	 * (0,0), then checks if it is empty) and tries to assign value. Then
	 * gets coordinates of next empty grid location and recursively calls
	 * methods for that location. 
	 * 
	 * @param row	row of current grid location
	 * @param col	column of current grid location
	 * @return		whether or not any values at this location work
	 */
	public boolean solvePuzzle(int row, int col) {
		int[] vals = new int[9];	// list of integers 1-9 in random order
		for (int i = 1; i < 10; i++) {
			int ind = 0;
			while (vals[ind] != 0) {
				ind = (int)(Math.random() * 9);
			}
			vals[ind] = i;
		}
		
		int a = 0;
		while (a < 9) {
			int val = vals[a];
			if (!valWorks(val, row, col))
				a++;
				
			else {
				puzzle[row][col] = val;
				int[] coords = nextEmptySpace(row, col);
				System.out.println(coords[0] + "\t" + coords[1]);	////////////////////////////////////
				boolean works = solvePuzzle(coords[0], coords[1]);		// index is 9 -> out of bounds
				if (works) {
					a = 9;
					return true;
				}
				
				a++;
			}
		}
		puzzle[row][col] = 0;
		return false;
	}
	
	/**
	 *  checks if a given value works at a given grid position. Value works
	 *  if it is not repeated in the row, column, or its respective 3 x 3 grid.
	 * 
	 *  @param val		value being tested
	 *  @param row		row of position that value is for
	 * 	@param col		column of position that value is for
	 * 	@return 		if val works, returns val
	 * 					if val doesn't work, returns -1
	 */
	public int getVals(int val, int row, int col) {
		int[] vals = new int[9];	// list of integers 1-9 in random order
		for (int i = 1; i < 10; i++) {
			int ind = 0;
			while (vals[ind] != 0) {
				ind = (int)(Math.random() * 9);
			}
			vals[ind] = i;
		}
		
		int x = 0;	// number of vals checked
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
	 * Looks for the first empty space in the puzzle
	 * 
	 * @param row		row of current grid coordinate
	 * @param col		column of current grid coordinate
	 * @return coords	array with coordinates of next empty spot
	 */
	public int[] nextEmptySpace(int row, int col) {		////////////////// ERROR HERE WITH BOUNDS
		int[] coords = 
		
		
		/*int[] coords = new int[2];
		while (puzzle[row][col] != 0 && (row < puzzle.length || 
											col < puzzle[row].length)) {
			if (col < puzzle[row].length)
				col++;
				
			else {
				row++;
				col = 0;
			}
		}
		
		if (row == puzzle.length - 1 && col == puzzle[row].length - 1) {
			coords[0] = -1;
			coords[1] = -1;
			return coords;
		}
					
		coords[0] = row;
		coords[1] = col;
		return coords;
		*/
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
