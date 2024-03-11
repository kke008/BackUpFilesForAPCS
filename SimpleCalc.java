import java.util.ArrayList;
import java.util.List;		// used by expression evaluator

/**
 *	Using postfix notation and stacks to calculate given arithmetic
 *  operations
 *
 *	@author	Karen Ke
 *	@since	February 27, 2024
 */
public class SimpleCalc {
	
	private ExprUtils utils;	// expression utilities
	
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack
	private List<Identifier> identifiers;		// list of identifiers

	// constructor	
	public SimpleCalc() {
		utils = new ExprUtils();
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
		identifiers = new ArrayList<Identifier>();
	}
	
	public static void main(String[] args) {
		SimpleCalc sc = new SimpleCalc();
		sc.run();
	}
	
	public void run() {
		System.out.println("\nWelcome to SimpleCalc!!!");
		setMathIdentifiers();
		runCalc();
		System.out.println("\nThanks for using SimpleCalc! Goodbye.\n");
	}
	
	/** Sets the values of e and pi
	 */
	public void setMathIdentifiers() {
		Identifier e = new Identifier();
		e.setName("e", identifiers);
		e.setValue(Math.E);
		identifiers.add(e);
		
		Identifier pi = new Identifier();
		pi.setName("pi", identifiers);
		pi.setValue(Math.PI);
		identifiers.add(pi);
	}
	
	/**
	 *	Prompt the user for expressions, run the expression evaluator,
	 *	and display the answer.
	 */
	public void runCalc() {
		Prompt prompt = new Prompt();
		String expression = prompt.getString("\n");
		while (expression.equals("q") == false) {
			if (expression.length() == 0)
				System.out.println("Please enter an expression or enter" +
					" 'h' for help");
			else if (expression.equals("h"))
				printHelp();
			else if (expression.equals("l"))
				printListOfIdentifiers();
			else {
				boolean setToken = false;
				Identifier newId = new Identifier();
				if (expression.indexOf('=') != -1) {
					newId = makeId(expression.substring(0, expression.indexOf('=')));
					expression = expression.substring(expression.indexOf('=') + 1);
					setToken = true;
				}
				List<String> tokens = utils.tokenizeExpression(expression);
					double answer = evaluateExpression(tokens);
				
				System.out.print("  ");
				
				if (setToken) {
					newId.setValue(answer);
					System.out.print(newId.getName() + "\t = ");
				}
				System.out.println(answer);
			}
			expression = prompt.getString("");
		}
	}
	
	/** Returns identifier with given name. t If no such identifier exists, makes
	 *  one and adds it to the list.
	 *  @param name		name of identifier to make
	 *  @return			existing identifier with given name if it exists,
	 * 					new identifier with given name otherwise
	 */
	public Identifier makeId(String name) {
		name = name.trim();
		
		// checking if identifier already exists
		for (int r = 0; r < identifiers.size(); r++) {
			if (name.equals(identifiers.get(r).getName()))
				return identifiers.get(r);
		}
		
		Identifier newId = new Identifier();
		newId.setName(name, identifiers);
		identifiers.add(newId);
		return newId;
	}
	
	/**	Print help */
	public void printHelp() {
		System.out.println("Help:");
		System.out.println("  h - this message\n  i - displays list of current " +
			"identifiers\n  q - quit\n");
		System.out.println("Expressions can contain:");
		System.out.println("  integers or decimal numbers");
		System.out.println("  arithmetic operators +, -, *, /, %, ^");
		System.out.println("  parentheses '(' and ')'");
		System.out.println("  identifiers that only contain letters");
	}
	
	/** Prints list of current identifiers */
	public void printListOfIdentifiers() {
		System.out.println("Identifiers:");
		for (int v = 0; v < identifiers.size(); v++) {
			Identifier id = identifiers.get(v);
			System.out.printf("\t%-16s=%12f\n", id.getName(), id.getValue()); 
		}
	}
	
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) {
		int a = 0;
		
		while (a < tokens.size()) {		// looking at all the tokens
			String token = tokens.get(a);
			
			// if token is identifier, find value and replace token with it
			if (isIdentifier(token)) {
				boolean found =  false;
				int i = 0;
				while(i < identifiers.size() && !found) {
					if (token.equals(identifiers.get(i).getName())) {
						found = true;
						tokens.set(a, "" + identifiers.get(i).getValue());
					}
					i++;
				}
				if (!found)
					tokens.set(a, "0");
				a--;
			}		
			
			// if token is operator, compare with previous operator
			else if (token.equals("+") || token.equals("-") || token.equals("*") || 
				token.equals("/") || token.equals("%") || token.equals("^") ||
				token.equals("(") ||token.equals(")")) {
				
				boolean addedToken = false;
				if (!operatorStack.isEmpty() && token.equals("(") == false) {
					String prevOp = operatorStack.peek();
					
					// while token does not have precedence and stacks
					// have more than one value left
					boolean noLoop = false;
					while (hasPrecedence(token, prevOp) && !noLoop) {
						operation();
						if (!operatorStack.isEmpty()) {
							prevOp = operatorStack.peek();
							if (token.equals(")") && prevOp.equals("(")) {
								operatorStack.pop();
								noLoop = true;
							}
						}
						
						if (valueStack.isEmpty() || operatorStack.isEmpty())
							noLoop = true;
						
						else {
							double topVal = valueStack.pop();
							String topOp = operatorStack.pop();
							if (valueStack.isEmpty() || operatorStack.isEmpty())
								noLoop = true;
							valueStack.push(topVal);
							operatorStack.push(topOp);
						}						
					}
				}
				if (!token.equals(")"))
					operatorStack.push(token);
			}
			
			// if token is a operand, add it to stack
			else
				valueStack.push(Double.parseDouble(token));

			a++;
		}
		
		while (!operatorStack.isEmpty())	// if there are operations left to do
			operation();
		
		return valueStack.pop();
	}
	
	/**
	 *  Checks if the token is valid (is an operator, a number, or contains
	 *  only letters)
	 *  @param token		the token to check, as a string
	 *  @return 			true if token is valid, false otherwise
	 * 
	 */
	public boolean isValid(String token) {
		int firstASCII = (int)(token.charAt(0));
		if (firstASCII >= 48 && firstASCII <= 57) {
			for (int i = 1; i < token.length(); i++) {
				if (!(token.charAt(i) >= 48 && token.charAt(i) <= 57))
					return false;
			}
		}
		
		else if (firstASCII >= 65 && firstASCII <= 90 || firstASCII >= 97 &&
				 firstASCII <= 122) {
			for (int i = 1; i < token.length(); i++) {
				if (!(firstASCII >= 65 && firstASCII <= 90 || 
						firstASCII >= 97 && firstASCII <= 122))
					return false;
			}
		}
		
		else if (!(token.equals("+") || token.equals("-") || token.equals("*") || 
				token.equals("/") || token.equals("%") || token.equals("^") ||
				token.equals("(") ||token.equals(")")))
			return false;
			
		return true;
	}
	
	/**
	 * 	Checks if token is identifier. Token is identifier if it the first
	 *  character is a letter (assuming that the token is valid)
	 *  @param token	token being checked, as a string
	 *  @return			true if token is an identifier, false otherwise
	 */
	public boolean isIdentifier(String token) {
		int ascii = (int)(token.charAt(0));
		// if characters aren't letters, not an identifier
		if (!(ascii >= 65 && ascii <= 90 || ascii >= 97 && ascii <= 122))
			return false;
		
		return true;
	}
	
	/**
	 *  Performs the operation given by the top operator on operatorStack and
	 *  the top two values on valueStack. Pops these values from their stacks
	 *  and pushes the result onto valueStack.
	 */
	public void operation() {
		String op = operatorStack.pop();	// poping prevOp
		double val2 = valueStack.pop();
		double val1 = valueStack.pop();
		if (op.equals("^"))
			valueStack.push(Math.pow(val1, val2));
		else if (op.equals("+"))
			valueStack.push(val1 + val2);
		else if (op.equals("-"))
			valueStack.push(val1 - val2);
		else if (op.equals("/"))
			valueStack.push(val1 / val2);
		else if (op.equals("*"))
			valueStack.push(val1 * val2);
		else if (op.equals("%"))
			valueStack.push(val1 % val2);
	}
	
	/**
	 *	Precedence of operators
	 *	@param op1	operator 1
	 *	@param op2	operator 2
	 *	@return		true if op2 has higher or same precedence as op1; false otherwise
	 *	Algorithm:
	 *		if op1 is exponent, then false
	 *		if op2 is either left or right parenthesis, then false
	 *		if op1 is multiplication or division or modulus and 
	 *				op2 is addition or subtraction, then false
	 *		otherwise true
	 */
	private boolean hasPrecedence(String op1, String op2) {
		if (op1.equals("^")) return false;
		if (op2.equals("(") || op2.equals(")")) return false;
		if ((op1.equals("*") || op1.equals("/") || op1.equals("%")) 
				&& (op2.equals("+") || op2.equals("-")))
			return false;
		return true;
	}
}

/** 
 *  Assinging names and values to identifiers to be used in SimpleCalc expressions.
 *  Values are either set in SimpleCalc or assumed to be 0.
 * 
 *  @author Karen Ke
 *  @since March 8, 2024
 */
class Identifier {
	private String name;			// name of identifier
	private double value;			// value of identifier
	private List<Identifier> list;	// list of existing identifiers
	
	public Identifier() {
		name = "";
		value = 0;
		list = new ArrayList<Identifier>();
	}	
	
	/** Sets proposed name and the list of existing identifiers, then calls  
	 *  nameWorks() to check if the name is valid. If not, it prompts the user 
	 *  for a new name. If identifier (name) already exists, changes the value
	 *  of the identifier
	 *  @param nameIn		the proposed name
	 *  @param listIn		the list of existing identifiers
	 */
	public void setName(String nameIn, List<Identifier> listIn) { 
		name = nameIn; 
		list = listIn;
		while (!nameWorks(name))
			name = Prompt.getString("Please enter a new name");
	}
	
	/** Checks if the proposed name works:
	 *  	1. name must not be q or h or l
	 * 		2. name must contain only letters
	 *  @param name		the proposed name
	 *  @return			false if name violates one of the conditions, true otherwise
	 */
	public boolean nameWorks(String name) {
		if (name.equals("q") || name.equals("h") || name.equals("l")) {
			System.out.println("\nIdentifier names can't be 'q' or 'h' or 'l'.");
			return false;
		}	
		for (int i = 0; i < name.length(); i++) {
			int charASCII = (int)(name.charAt(i));
			if (charASCII < 65 || (charASCII > 90 && charASCII < 97) || charASCII > 122) {
				System.out.print("\nIdentifier names can only contain letters " +
					"and can't be the same as that of an existing identifier.");
				return false;
			}
		}
		return true;
	}
	
	/** Sets value of identifier
	 * 	@param valueIn		value to set
	 */ 
	public void setValue(double valueIn) { value = valueIn; }
	
	/** @return name	name of identifier */
	public String getName() { return name; }
	
	/** @return value	value of identifier */
	public double getValue() { return value; }
}
