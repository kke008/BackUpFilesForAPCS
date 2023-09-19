import java.util.Scanner;
/**
 *	Counts the frequency of letters in a file and produces a histogram.
 *
 *	@author	Karen Ke
 *	@since	September 14, 2023
 */
public class LetterCount {
	// Fields go here, all must be private
	private int[] count;	// stores the number of times a letter appears
	private int[] scaled;	// stores the number of x's in the bars for
							// each letter
	
	/* Constructor */							
	public LetterCount() {
		count = new int[26];
		scaled = new int[26];
	}
	
	/** Main routine */
	public static void main(String[] args) {
		LetterCount lc = new LetterCount();
		if (args.length != 1)
			System.out.println("Usage: java LetterCount <inputFile>");
		else
			lc.run(args);
	}
	
	/**	The core program of the class, it
	 *	- opens the input file
	 *	- reads the file and counts the letters
	 *	- closes the input file
	 *	- prints the histogram of the letter count
	 */
	public void run(String[] args) {
		String fileName = args[0];
		Scanner read = FileUtils.openToRead(fileName);
		String line = "";
		int charNum = -1;
		
		while (read.hasNext()) {
			line = read.nextLine();
			for (int i = 0; i < line.length(); i++) {
				String letter = line.substring(i, i + 1);
				for (int m = (int)('a'); 
				/*
				int numOfChar = Integer.parseInt(line.substring[i, i+1]);
				if (numOfChar >= ((int)('a') && numOfChar <= (int)('z') ) {
					charNum = numOfChar - (int)('a');
					count[charNum] += 1;
				}
				
				else if (numOfChar >= (int)('A') && numOfChar < 91) {
					charNum = numOfChar + 32;
					count[charNum] += 1;
				}
			}
			*/
		}
		read.close();
		scaleCounts();
		
		System.out.println("Histogram of number frequency in " + fileName);
		
		for (int l = 0; l < 26; l++) {
			String xBar = "";
			for (int m = 0; m < scaled[l]; m ++){
				xBar += "x";
			}
			
			String letter = "" + (char)(97 + l);
			System.out.printf("\n%1s:%7d %60s", letter, count[l], xBar);
		}
	}
	
	/** This method finds the frequency of the letter that appears most
	 * 	and uses it to find a scale for the other frequencies, then scales
	 * 	them. The results are stored in a new array.
	 */
	public void scaleCounts() {
		int indexOfMost = 0;
		
		for (int j = 1; j < 26; j++) {
			if (count[indexOfMost] < count[j])
				indexOfMost = j;
		}
		int scale = count[indexOfMost] / 60;
		
		for (int k = 0; k < 26; k++) {
			scaled[k] = count[k] / scale;
		}
	}
	
}
