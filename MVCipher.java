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
	private String key;	// the word used to encrypt / decrypt
	private int cryptNum;	// int that represents whether to encrypt or
				// decrypt
	private final int LENGTH_OF_ALPHABET = 26;	// 26 letters in alphabet
	// fields go here
		
	/** Constructor */
	public MVCipher() {
		key = "";
	}
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/** Uses the Prompt class to get the key, a variable representing 
	 * 	encryption or decryption, input file name, and output file name.
	 * 	Then reads and processes the input file, and prints it to the
	 * 	output file.
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
				String check = checkChar(key.charAt(i));
				if(check.equals("neither"))
					goodKey = false;
			}
			if (goodKey = false)
				System.out.println("ERROR: key must be all letters and " +
					"at least 3 letters long");	
		} while (goodKey = false);
		
		/* Prompt for encrypt or decrypt */
		cryptNum = Prompt.getInt("Encrypt or decrypt?", 1, 2);
			
		/* Prompt for an input file name */
		String inFileName = Prompt.getString("Name of file to encrypt");
		
		/* Prompt for an output file name */
		String outFileName = Prompt.getString("Name of output file");
		
		/* Read input file, encrypt or decrypt, and print to output file.
		 * Closes output file */
		Scanner readIn = FileUtils.openToRead(inFileName);
		while (readIn.hasNext()) {
			String ogLine = readIn.nextLine();
			String changedLine = getNewLine(ogLine, cryptNum)
			PrintWriter writeOut = FileUtils.openToWrite(outFileName);
			writeOut.append(changedLine + "\n");
		}
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
	
	/** Encrypts or decrypts lines of a file one char at a time. Keeps track
 	 *  of which letter of the key that each char corresponds with using the
   	 *  letter's index.
	 * @param fileLine	line of file to encrypt or decrypt
	 * @param change	int from user to decide whether to encrypt or decrypt
	 * @return newLine	encrypted or decrypted line
	 */
	public String getNewLine(String fileLine, int change) {
		String newLine = "";
		int keyIndex = 0;
		for (int j = 0; j < fileLine.length(); j++) {
			String charVal = checkChar(fileLine.charAt(j));
			if (charVal.equals("upper"))
				newLine += changeUpper(fileLine.charAt(j), keyIndex);
			else if (charVal.equals("lower"))
				newLine += changeLower(fileLine.charAt(j), keyIndex);
			else {
				newLine += fileLine.charAt(j);
				keyIndex --;
			}
			keyIndex ++;
		}
		return newLine;
	}

	/** Encrypts or decrypts uppercase (letter) chars using the index of the letter
 	 *  in the key that corresponds with the char and the cryptNum. To encrypt, the
   	 *  char is shifted up. To decrypt, it is shifted down.
     	 * @param ogChar	the original char that needs to be shifted
       	 * @param keyIndexIn	the index of the corresponding letter of the key
	 * @return newChar	the shifted char
	 */
	public char changeUpper(char ogChar, int keyIndexIn) {
		int keyInt = (int)(key.charAt(keyIndex));	// ASCII of key letter
		int shift = (keyInt - (int)('A') - 1) % LENGTH_OF_ALPHABET;
		// how much to shift the letter by
		if (cryptNum == 1)
			char newChar = (char)(ogChar + shift);
		else
			char newChar = (char)(ogChar - shift);
		return newChar;
	}

	/** Encrypts or decrypts lowercase (letter) chars using the index of the letter
 	 *  in the key that corresponds with the char and the cryptNum. To encrypt, the
   	 *  char is shifted up. To decrypt, it is shifted down.
     	 * @param ogChar	the original char that needs to be shifted
       	 * @param keyIndexIn	the index of the corresponding letter of the key
	 * @return newChar	the shifted char
	 */
	public char changeUpper(char ogChar, int keyIndexIn) {
		int keyInt = (int)(key.charAt(keyIndex));	// ASCII of key letter
		int shift = (keyInt - (int)('a') - 1) % LENGTH_OF_ALPHABET;
		// how much to shift the letter by
		
		if (cryptNum == 1)
			char newChar = (char)(ogChar + shift);
		else
			char newChar = (char)(ogChar - shift);
		return newChar;
	}
}
