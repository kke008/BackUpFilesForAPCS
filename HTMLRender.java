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
	private enum TokenState { BOLD, ITALIC, TEXT, H1, H2, H3,
		H4, H5, H6, HRULE, BREAK, PRE };
	// the current state of the token to be printed
	private TokenState state;
		
	public HTMLRender() {
		// Initialize token array
		tokens = new String[TOKENS_SIZE];
		
		state = TokenState.TEXT;	// default state is normal text
		
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
		
		while (index < tokens.length && tokens[index] != null) {
			String token = tokens[index];
			
			// if token is </X> then the existing state is done 
			if (token.length() > 1 && token.charAt(1) == '/')
				maxLineLength = changeMLLAndState("end");
			
			// if the token only prints 1 constant char, that char is printed directly
			if ((token.equalsIgnoreCase("<p>") && index - 1 >= 0 && 
				!tokens[index - 1].equalsIgnoreCase("</p>")) || token.equalsIgnoreCase("</p>")) {
				browser.println();
				browser.println();
				lineLength = 0;
			}
			
			else if (token.equalsIgnoreCase("<q>") || token.equalsIgnoreCase("</q>"))
				browser.print("\"");
				
			else if (token.equalsIgnoreCase("\n")) {
				browser.println();
				lineLength = 0;
			}
				
			else if (token.equalsIgnoreCase("<hr>")) {
				browser.printHorizontalRule();
				lineLength = 0;
			}
				
			else if (token.equalsIgnoreCase("<br>")) {
				browser.printBreak();
				lineLength = 0;
			}
			
			// if token sets rule for next tokens, the max line length and state are set
			else if (token.charAt(0) == '<')
				maxLineLength = changeMLLAndState(token);
		
			// if the token will make the line go over the limit, it is
			// printed on a new line
			if (token.length() + lineLength > maxLineLength) {
				browser.println();
				lineLength = 0;
			}
				
			// only deals with printing if the token is not a tag
			if (token.charAt(0) != '<') {
								
				// deals with space between tokens but accounts for punctuation
				if (index > 0 && !(token.length() == 1 && !Character.isLetter(token.charAt(0)) &&
					!Character.isDigit(token.charAt(0)))) {
					browser.print(" ");
					lineLength++;
				}
					
				lineLength += token.length();
				
				// printing the tokens to the browser
				if (state == TokenState.TEXT)
					browser.print(token);
					
				else if (state == TokenState.BOLD)
					browser.printBold(token);
				
				else if (state == TokenState.ITALIC)
					browser.printItalic(token);
					
				else if (state == TokenState.PRE)
					browser.printPreformattedText(token);
					
				// headings handled separately
				else {
					if (index + 1 < tokens.length && 
						tokens[index + 1].length() > 2 &&
						tokens[index + 1].length().charAt(1) == "/") {
							
					}/////////////////////////////////////////////////////////////////////////////////////////
						
					if (state == TokenState.H1)
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
				}
			}
			index++;
		}
	}
	
	/** If the token being worked on is a tag, the max line length and the token
	 * state (field var; changed directly) may be changed accordingly.
	 * @param tokenIn		the token (tag) being worked with
	 * @return mll			the max line length determined by the tag
	 */
	public int changeMLLAndState(String tokenIn) {
		int mll = 80;	// maxLineLength; default is 80 characters
		
		// if the String passed in represents a tag that ends a state, the state
		// is set to default (NONE) and the max line length remains its default		
		// otherwise: changing state and mll based on whether or not certain tags are read
		if (tokenIn.equalsIgnoreCase("<p>") || tokenIn.charAt(0) != '<' || 
			tokenIn.equals("end")) {
			state = TokenState.TEXT;
			mll = 80;
		}
		else if (tokenIn.equalsIgnoreCase("<b>")) {
			state = TokenState.BOLD;
			mll = 80;
		}		
		else if (tokenIn.equalsIgnoreCase("<i>")) {
			state = TokenState.ITALIC;
			mll = 80;
		}		
		else if (tokenIn.equalsIgnoreCase("<h1>")) {
			state = TokenState.H1;
			mll = 40;
		}		
		else if (tokenIn.equalsIgnoreCase("<h2>")) {
			state = TokenState.H2;
			mll = 50;
		}			
		else if (tokenIn.equalsIgnoreCase("<h3>")) {
			state = TokenState.H3;
			mll = 60;
		}			
		else if (tokenIn.equalsIgnoreCase("<h4>")) {
			state = TokenState.H4;
			mll = 80;
		}			
		else if (tokenIn.equalsIgnoreCase("<h5>")) {
			state = TokenState.H5;
			mll = 100;
		}			
		else if (tokenIn.equalsIgnoreCase("<h6>")) {
			state = TokenState.H6;
			mll = 120;
		}		
		else if (tokenIn.equalsIgnoreCase("<pre>")) {
			state = TokenState.PRE;
			mll = tokenIn.length() + 1;
		}		
		return mll;
	}
}
