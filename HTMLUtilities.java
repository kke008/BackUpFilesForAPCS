/**
 *	Utilities for handling HTML
 *
 *	@author	Karen Ke
 *	@since	October 31, 2023
 */
public class HTMLUtilities {
	
	public static void main (String[] args) {	////////////////////////////////////
		HTMLUtilities h = new HTMLUtilities();
		String[] hh = h.tokenizeHTMLString("<abc>cd-e");
		for (int z = 0; z < hh.length; z++) {
			System.out.println(hh[z]);
		}
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
		String tempString = "";
		
		boolean isTag = false;
		boolean isWord = false;
		
		for (int a = 0; a < str.length(); a++) {
			char c = str.charAt(a);
			if (!isWhitespace(c))
				tempString += c;
						
			if (c == '<')
				isTag = true;
				
			else if (!isTag && isLetter(c))
				isWord = true;
				
			if (isTag) {		// tokenize tags
				if (c == '>') {
					result[index] = tempString;
					index++;
					tempString = "";
					isTag = false;
				}
			}
			
			//else if (isWord){		// tokenize words
			//}
			
			if (a == str.length - 1) {
				tempString += c;
				result[index] = tempString;
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
