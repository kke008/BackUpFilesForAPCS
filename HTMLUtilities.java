/**
 *	Utilities for handling HTML
 *
 *	@author	Karen Ke
 *	@since	October 31, 2023
 */
public class HTMLUtilities {
	// NONE = not nested in a block, COMMENT = inside a comment block
	// PREFORMAT = inside a pre-format block
	private enum TokenState { NONE, COMMENT, PREFORMAT };
	// the current tokenizer state
	private TokenState state;
	
	public static void main (String[] args) {////////////////////////////////////////////////////////////////
		HTMLUtilities h = new HTMLUtilities();
		String s = "<!-- comment -->";
		String[] t = h.tokenizeHTMLString(s);
		h.printTokens(t);
	}

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
		
		while (strIndex < str.length()) {	// checks every char of str
			char c = str.charAt(strIndex);
			char nextC = ' ';
			if (strIndex < str.length() - 1)
				nextC = str.charAt(strIndex + 1);
			
			if (c == '<') 
				isTag = true;
			
			else if (!isTag) {
				if (!isNumber && Character.isLetter(c))
					isWord = true;
				
				else if (Character.isDigit(c) || c == '-' && 
					(Character.isDigit(nextC) || nextC == '.'))
					isNumber = true;
					
				else if (findIfPunctuation(c) && !isWord && !isNumber)
					isPunctuation = true;
			}
			
			if (state == TokenState.COMMENT) {	// RECOGNIZES COMMENT BUT TOKENIZES ANYWAY !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				tempString += c;
				int tsLength = tempString.length();
				if (tsLength - 2 > 0) {
					if (c == '>' && tempString.charAt(tsLength - 2) == '-' &&
						tempString.charAt(tsLength - 1) == '-') {
						state = TokenState.NONE;
						tempString = "";
						System.out.println();
					}
				}
			}
				
			else if (isTag) {
				if (tempString.equals("<!") && c == '-' && nextC == '-') {	// checks if comment
					isTag = false;
					state = TokenState.COMMENT;
					strIndex--;
				}
				
				else if (isWord || isNumber) {
					isWord = false;
					isNumber = false;
					isTokenDone = true;
					strIndex--;
				}
				
				else {						// tokenizes tags
					tempString += c;
					if (c == '>') {
						isTag = false;
						isTokenDone = true;
					}
				}
			}
			
			else if (isWord) {				// tokenizes words
				if (Character.isLetter(c) || c == '-' &&
					Character.isLetter(nextC))
					tempString += c;
					
				else {
					isWord = false;
					isTokenDone = true;
					strIndex--;
				}
			}
			
			else if (isNumber) {			// tokenizes numbers
				char nextNextC = ' ';
				if (strIndex + 2 < str.length())
					nextNextC = str.charAt(strIndex + 2);
					
				if (Character.isDigit(c) || Character.isDigit(nextC) ||
					(c == 'e' && nextC == '-') || (c == '-' && nextC == '.'))
					tempString += c;
				
				else if (isWord || isNumber) {
					isWord = false;
					isNumber = false;
					isTokenDone = true;
					strIndex--;
				}
				
				else {						// tokenizes tags
					tempString += c;
					if (c == '>') {
						isTag = false;
						isTokenDone = true;
					}
				}
			}
			
			else if (isWord) {				// tokenizes words
				if (Character.isLetter(c) || c == '-' &&
					Character.isLetter(nextC))
					tempString += c;
					
				else {
					isWord = false;
					isTokenDone = true;
					strIndex--;
				}
			}
			
			else if (isNumber) {			// tokenizes numbers
				char nextNextC = ' ';
				if (strIndex + 2 < str.length())
					nextNextC = str.charAt(strIndex + 2);
					
				if (Character.isDigit(c) || Character.isDigit(nextC) ||
					(c == 'e' && nextC == '-') || (c == '-' && nextC == '.'))
					tempString += c;
					
				
					
				else {
					isNumber = false;
					isTokenDone = true;
					strIndex--;
				}
			}
			
			else if (isPunctuation) {		// tokenizes punctuation
				tempString += c;
				isPunctuation = false;
				isTokenDone = true;
			}
			
			
			if(state != TokenState.COMMENT && (isTokenDone || strIndex == str.length() - 1)) {
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
	 * 	@param charIn			the character to check
	 * 	@return isPunctuation	true if char is punctuation, otherwise false
	 */
	public boolean findIfPunctuation(char charIn) {
		boolean isPunctuation = false;
		char[] punctuation = {'.', ',', ';', ':', '(', ')', '?', '!', 
				'=', '&', '~', '+', '-'};
		for (int i = 0; i < punctuation.length; i++) {	
			if (charIn == punctuation[i]) {
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
