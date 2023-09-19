import java.util.Scanner;
/**
 *	Counts the frequency of letters in a file and produces a histogram.
 *
 *	@author	Karen Ke
 *	@since	September 14, 2023
 */
public class LetterCount {
	private int[] count;
	private int[] scaled;
	// Fields go here, all must be private
	
	/* Constructor */							
	public LetterCount() {
		count = new int[26];
		scaled = new int[26];
	}
	
	/* Main routine */
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
				int num = Integer.parseInt(line(i));
				if (num > 96 && num < 123) {
					charNum = num - 97;
					count[charNum] += 1;
				}
				
				else if (num > 64 && num < 91) {
					charNum = num + 32;
					count[charNum] += 1;
				}
			}
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
	
	public void scaleCounts() {
		int scale = 1;
		int indexOfMost = 0;
		
		for (int j = 1; j < 26; j++) {
			if (count[indexOfMost] < count[j])
				indexOfMost = j;
		}
		scale = count[indexOfMost] / 60;
		
		for (int k = 0; k < 26; k++) {
			scaled[k] = count[k] / scale;
		}
	}
	
}
