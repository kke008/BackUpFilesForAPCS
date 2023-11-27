import java.util.Scanner;

/**
 *	HTMLRender
 *	This program renders HTML code into a JFrame window.
 *	It requires your HTMLUtilities class and
 *	the SimpleHtmlRenderer and HtmlPrinter classes.
 * 
 *  To compile: javac -cp ".;SimpleHtmlRenderer.jar" *.jar
 *  To run: java -cp ".;SimpleHtmlRenderer.jar" HTMLRender example7.html
 *
 *	The tags supported:
 *		<html>, </html> - start/end of the HTML file
 *		<body>, </body> - start/end of the HTML code
 *		<p>, </p> - Start/end of a paragraph.
 *					Causes a newline before and a blank line after. Lines are restricted
 *					to 80 characters maximum.
 *		<hr>	- Creates a horizontal rule on the following line.
 *		<br>	- newline (break)
 *		<b>, </b> - Start/end of bold font print
 *		<i>, </i> - Start/end of italic font print
 *		<q>, </q> - Start/end of quotations
 *		<hX>, </hX> - Start/end of heading with size X = 1, 2, 3, 4, 5, 6
 *		<pre>, </pre> - Preformatted text
 *
 *	@author Mr.Greenstein and Karen Ke
 *  @ since November 16, 2023
 *	@version 1
 */
public class HTMLRender {
	
	// the array holding all the tokens of the HTML file
	private String [] tokens;
	private final int TOKENS_SIZE = 100000;	// size of array

	// SimpleHtmlRenderer fields
	private SimpleHtmlRenderer render;
	private HtmlPrinter browser;
	
	private String fileName;	// name of html file
	
	// different states of tokens
	private enum TokenState { BOLD, ITALIC, TEXT, OTHERTEXT, H1, H2, H3,
		H4, H5, H6, HRULE, BREAK, PRE, NONE };
	// the current state of the token to be printed
	private TokenState state;
		
	public HTMLRender() {
		// Initialize token array
		tokens = new String[TOKENS_SIZE];
		
		state = TokenState.NONE;
		
		// Initialize Simple Browser
		render = new SimpleHtmlRenderer();
		browser = render.getHtmlPrinter();
	}
	
	
	public static void main(String[] args) {
		HTMLRender hf = new HTMLRender();
		if (args.length >= 0)
			hf.fileName = args[0];
			
		else {
			System.out.println("ERROR: no file name given");
			System.exit(0);
		}
		
		hf.run();
	}
	
	public void run() {
		Scanner input = FileUtils.openToRead(fileName);
		HTMLUtilities hu = new HTMLUtilities();
		int a = 0;	// counter to add tokens to tokens array
		int maxLineLength = 0;		// for diff types of text
		int index = 0;		// counter to print tokens
		int lineLength = 0;	// num of chars in line to print
		
		// tokenizes file and adds to tokens array
		while (input.hasNext()) {
			String line = input.nextLine();
			String [] huTokens = hu.tokenizeHTMLString(line);
			for (int i = 0; i < huTokens.length; i++) {
				tokens[a] = huTokens[i];
				a++;
			}
		}
		input.close();
		
		while (index < tokens.length && tokens[index].length() != 0) {
			String token = tokens[index];
			
			// if token is </X> then the existing state is done 
			if (token.length() > 0 && token.charAt(1) == '/') {	////////////////////////// ERROR HERE!!!!!!
				state = TokenState.NONE;
				if (token.equalsIgnoreCase("</p>"))
					browser.println();
			}
			
			// if the token only prints 1 char, that char is printed directly
			else if (token.equalsIgnoreCase("<q>"))
				browser.print("\"");
				
			else if (token.equalsIgnoreCase("\n"))
				browser.println();
				
			else if (token.equalsIgnoreCase("<hr>"))
				browser.printHorizontalRule();
				
			else if (token.equalsIgnoreCase("<br>"))
				browser.printBreak();
			
			// if token sets rule for next tokens, the max line length and state are set
			else
				maxLineLength = getMLLAndState(token);
		
			// if the token will make the line go over the limit, it is
			// printed on a new line
			if (token.length() + lineLength > maxLineLength) {
				browser.println();
				lineLength = 0;
			}
				
			// printing the tokens to the browser
			if (state == TokenState.TEXT)
				browser.print(token);
				
			else if (state == TokenState.BOLD)
				browser.printBold(token);
			
			else if (state == TokenState.ITALIC)
				browser.printItalic(token);
			
			else if (state == TokenState.H1)
				browser.printHeading1(token);
			
			else if (state == TokenState.H2)
				browser.printHeading2(token);
			
			else if (state == TokenState.H3)
				browser.printHeading3(token);
			
			else if (state == TokenState.H4)
				browser.printHeading4(token);
			
			else if (state == TokenState.H5)
				browser.printHeading5(token);
			
			else if (state == TokenState.H6)
				browser.printHeading6(token);
				
			else if (state == TokenState.PRE)
				browser.printPreformattedText(token);
				
			// deals with space after tokens excluding punctuation
			if (index + 1 < tokens.length && (!tokens[index + 1].equals(".") ||
				!tokens[index + 1].equals("?") || !tokens[index + 1].equals("!")))
				browser.print(" ");
			
			index++;
			lineLength += token.length();
		}
	}
	
	public int getMLLAndState(String tokenIn) {
		int mll = 0;	// maxLineLength
		
		//changing state and mll based on whether or not certain tags are read
		if (tokenIn.equals("<p>") || tokenIn.charAt(0) != '<') {
			state = TokenState.TEXT;
			mll = 80;
		}
		else if (tokenIn.equals("<b>")) {
			state = TokenState.BOLD;
			mll = 80;
		}		
		else if (tokenIn.equals("<i>")) {
			state = TokenState.ITALIC;
			mll = 80;
		}		
		else if (tokenIn.equals("<h1>")) {
			state = TokenState.H1;
			mll = 40;
		}		
		else if (tokenIn.equals("<h2>")) {
			state = TokenState.H2;
			mll = 50;
		}			
		else if (tokenIn.equals("<h3>")) {
			state = TokenState.H3;
			mll = 60;
		}			
		else if (tokenIn.equals("<h4>")) {
			state = TokenState.H4;
			mll = 80;
		}			
		else if (tokenIn.equals("<h5>")) {
			state = TokenState.H5;
			mll = 100;
		}			
		else if (tokenIn.equals("<h6>")) {
			state = TokenState.H6;
			mll = 120;
		}		
		else if (tokenIn.equals("<pre>")) {
			state = TokenState.PRE;
			mll = tokenIn.length() + 1;
		}		
		return mll;
	}
}
