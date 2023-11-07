/**
 *	Utilities for handling HTML
 *
 *	@author	Karen Ke
 *	@since	October 31, 2023
 */
public class HTMLUtilities {
	
	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str				the HTML string
	 *	@return	fixedResult		the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		String[] result = new String[10000];
		
		int index = 0;	// index in result that next token will be added at
		int strIndex = 0;	// index of character being checked in str
		String tempString = "";
		
		boolean isTag = false;
		boolean isWord = false;
		boolean isNumber = false;
		boolean isPunctuation = false;
		boolean isTokenDone = false;
		
		while (strIndex < str.length()) {
			char c = str.charAt(strIndex);
			//System.out.println(c + ":\t" + isTag + "\t" + isWord + "\t" + isPunctuation);	/////////////////////////////////////////////
			char nextC = ' ';
			String forEInNums = "";
			if (strIndex < str.length() - 1) {
				nextC = str.charAt(strIndex + 1);
			}
			
			if (c == '<') 
				isTag = true;
			
			else if (!isTag) {
				if (Character.isLetter(c) && !isNumber)
					isWord = true;
				
				else if (Character.isDigit(c) || c == '-' && Character.isDigit(nextC))
					isNumber = true;
			}
				
			if (isTag) {				// tokenizes tags
				tempString += c;
				if (c == '>') {
					isTag = false;
					isTokenDone = true;
				}
			}
			
			else if (isWord) {				// tokenizes words
				tempString += c;
				if (!(Character.isLetter(nextC) || (c == '-' && 
										!findIfPunctuation(str, strIndex)))) {
					isWord = false;
					isTokenDone = true;
				}
			}
			
			else if (isNumber) {	//////////////////////////////////////////////////////////////////////
				tempString += c;
				char nextNextC = ' ';
				if (strIndex + 2 < str.length())
					nextNextC = str.charAt(strIndex + 2);
					
				if ((c == 'e' && nextC != '-' && !(Character.isDigit(nextNextC))) ||
					!(Character.isDigit(nextC) && !findIfPunctuation(str, strIndex + 1))){
					isNumber = false;
					isTokenDone = true;
				}
			}
			
			if(isTokenDone) {
				result[index] = tempString;
				tempString = "";
				index++;
				isTokenDone = false;
			}
			
			strIndex++;
		}
		
		// return the correctly sized array
		int actualLength = 0;
		for (int i = 0; i < result.length; i++) {
			if (result[i] != null)
				actualLength++;
		}
		
		String[] fixedResult = new String[actualLength];
		for (int j = 0; j < actualLength; j++) {
			fixedResult[j] = result[j];
		}
		return fixedResult;
	}
	
	/**
	 *  Sees if the character passed in is one of the possible punctuation, and
	 *  not part of a word or a number.
	 * 	@param strIn			the string with the character to check
	 *  @param indexIn			the index of the character to check in strIn
	 * 	@return isPunctuation	true if char is punctuation, otherwise false
	 */
	public boolean findIfPunctuation(String strIn, int indexIn) {
		boolean isPunctuation = false;
		char[] punctuation = {'.', ',', ';', ':', '(', ')', '?', '!', 
				'=', '&', '~', '+', '-'};
		char charIn = strIn.charAt(indexIn);
		char prevChar = ' ';
		char nextChar = ' ';
		if (indexIn - 1 >= 0)
			prevChar = strIn.charAt(indexIn - 1);
		if (indexIn + 1 < strIn.length())
			nextChar = strIn.charAt(indexIn + 1);
			
		for (int i = 0; i < punctuation.length; i++) {	
			if (strIn.charAt(indexIn) == punctuation[i]) {
				if (!(charIn == '-' && ((Character.isLetter(prevChar) && 
					Character.isLetter(nextChar)) || Character.isDigit(nextChar))))
					isPunctuation = true;
				else if (!(charIn == '.' && Character.isDigit(prevChar) &&
											Character.isDigit(nextChar)))
					isPunctuation = true;
			}
		}
		return isPunctuation;
	}
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if (tokens == null) return;
		for (int a = 0; a < tokens.length; a++) {
			if (a % 5 == 0) System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}
}

}
