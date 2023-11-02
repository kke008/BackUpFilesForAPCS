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
		boolean isPunctuation = false;
		boolean isTokenDone = false;
		
		while (strIndex < str.length()) {
			char c = str.charAt(strIndex);
			char nextC = ' ';
			if (strIndex < str.length() - 1) {
				nextC = str.charAt(strIndex + 1);
			
			if (c == '<')
				isTag = true;
				
			else if (!isTag) {
				if (c < 'A' || c > 'Z' && c < 'a' || c > 'z')
					isWord = true;
				
				else if (findIfPunctuation(c)) {
					if (!(c == '-' && nextC != ' '))
						isPunctuation = true;
				}
			}
				
			if (isTag) {				// tokenizes tags
				tempString += c;
				if (c == '>') {
					isTokenDone = true;
				}
			}
			
			else if (isWord) {			// tokenizes words
				tempString += c;
				if ((nextC < 'A' || nextC > 'Z' && nextC < 'a' || 
						nextC > 'z') && nextC != '-')
					isTokenDone = true;
			}
			
			else if (isPunctuation) {
				tempString = c;
				isTokenDone = true;
			}
			
			strIndex++;
			
			// when token is done, it's added to result and everything is reset
			if (isTokenDone) {
				isTag = false;
				isWord = false;
				isTokenDone = false;
				result[index] = tempString;
				tempString = "";
				index++;
			}
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
	 *  Sees if the character passed in is one of the possible punctuation.
	 * 	@param charIn			the character to check
	 * 	@return isPunctuation	true if char is punctuation, otherwise false
	 */
	public boolean findIfPunctuation(char charIn) {
		boolean isPuctuation = false;
		char[] punctuation = {'.', ',', ';', ':', '(', ')', '?', '!', 
				'=', '&', '~', '+', '-'};
		for (int i = 0; i < punctuation.length; i++) {
			if (charIn == punctuation[i])
				isPunctuation = true;
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
