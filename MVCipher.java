import java.util.Scanner;
import java.io.PrintWriter;

/**
 *	MVCipher - Encrypts and decrypts a file using a word that the user
 * 			   provides.
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	Karen Ke
 *	@since	September 21, 2023
 */
public class MVCipher {
	private String key;
	// fields go here
		
	/** Constructor */
	public MVCipher() { }
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Method header goes here
	 */
	public void run() {
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */
		   
		do {
			boolean goodKey = false;
			key = Prompt.getString("Please input a word to use as key " +
				"(letters only)");
			if (key.length() >= 3)
				goodKey = true;
				
			for (int i = 0; i < key.length(); i++) {
				int letter = (int)(key.charAt(i));
				if (letter >
			}
			
		} while (goodKey = false);
		
		
		/* Prompt for encrypt or decrypt */
			
			
		/* Prompt for an input file name */
		
		
		/* Prompt for an output file name */
		
		
		/* Read input file, encrypt or decrypt, and print to output file */
		
		
		/* Don't forget to close your output file */
	}
	
	// other methods go here
	/** Checks whether the char passed in is an uppercase letter, a 
	 * 	lowercase letter, or not a letter.
	 * 	@param charIn	the char that will be checked
	 * 	@return value	a String representing the value of the letter
	 */
	public int checkChar(char charIn) {
		int letterNum = (int)(charIn);
		String value = -1;
		if (letterNum >= (int)('a') && letterNum <= (int)('z'))
			value = "lower";
			
		else if (letterNum >= (int)('A') && letterNum <= (int)('Z'))
			value = "upper";
			
		else
			value = "neither";
			
		return value;
	}
	
	/** Reads each line of a file based on the user's input, then calls 
	 *  changeLine to encrypt or decrypt that line.
	 *  Writes the changed line to another file, also specified by the 
	 * 	user.
	 * 	@param inFile	name of file to encrypt or decrypt
	 * 	@param outFile	name of file to put encrypted or decrypted text
	 * 					into
	 */
	public void changeFile(String inFile, String outFile) {
		Scanner readIn = FileUtils.openToRead(inFile);
		
		
		PrintWriter writeOut = FileUtils.openToWrite(outFile);
	}
	
	
	/** Encrypts or decrypts lines of a file using the key.
	 * @param fileLine	line of file to encrypt or decrypt
	 * @param change	int from user to decide whether to encrypt or decrypt
	 * @return newLine	encrypted or decrypted line
	 */
	public String changeLine(String fileLine, int change) {
		String newLine = "";
		
		return newLine;
	}
	
}
