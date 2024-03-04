import java.util.ArrayList;
import java.util.List;		// used by expression evaluator

/**
 *	Using postfix notation and stacks to calculate given arithmetic
 *  operations
 *
 *	@author	Karen Ke
 *	@since	February 27, 2024
 */
 ////////////////////////////////////////////////////////////////////////////////////////////
 /*
  *  - (28 * 28 - 4 / (5 + 3) * 6.5) + 3.4 gives indexOutOfBounds
  */
public class SimpleCalc {
	
	private ExprUtils utils;	// expression utilities
	
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack

	// constructor	
	public SimpleCalc() {
		utils = new ExprUtils();
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
	}
	
	public static void main(String[] args) {
		SimpleCalc sc = new SimpleCalc();
		sc.run();
	}
	
	public void run() {
		System.out.println("\nWelcome to SimpleCalc!!!");
		runCalc();
		System.out.println("\nThanks for using SimpleCalc! Goodbye.\n");
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
				
			else {
				List<String> tokens = utils.tokenizeExpression(expression);
				double answer = evaluateExpression(tokens);
				System.out.println(answer);
			}
			expression = prompt.getString("");
		}
	}
	
	/**	Print help */
	public void printHelp() {
		System.out.println("Help:");
		System.out.println("  h - this message\n  q - quit\n");
		System.out.println("Expressions can contain:");
		System.out.println("  integers or decimal numbers");
		System.out.println("  arithmetic operators +, -, *, /, %, ^");
		System.out.println("  parentheses '(' and ')'");
	}
	
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) {
		double value = 0;
		
		int a = 0;
		while (a < tokens.size()) {		// looking at all the tokens
			String token = tokens.get(a);
			// if tokens are operators
			if (token.equals("+") || token.equals("-") || token.equals("*") || 
				token.equals("/") || token.equals("%") || token.equals("^")) {
				
				if (operatorStack.isEmpty())	// if token is first operator
					operatorStack.push(token);
				
				else {
					String prevOp = operatorStack.peek();
					if (hasPrecedence(token, prevOp)) {	// if prevOp has precedence
						operation();
						operatorStack.push(token);
					}
					// if token has precedence, add it to the stack
					else
						operatorStack.push(token);
				}
			}
			
			// if token is "(", evaluate expression until ")"
			else if (token.equals("(")) {
				List<String> inParen = new ArrayList<String>();
				// add tokens in between () to list
				a++;
				while(tokens.get(a).equals(")") == false) {
					inParen.add(tokens.get(a));
					a++;
				}
				
				SimpleCalc newCalc = new SimpleCalc();
				double ans = newCalc.evaluateExpression(inParen);
				valueStack.push(ans);
			}
			
			// if token is a operand, add it to stack
			else
				valueStack.push(Double.parseDouble(token));
			a++;
		}
		
		// if there are operations left to do
		while (!operatorStack.isEmpty())
			operation();
	
		return valueStack.pop();
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
