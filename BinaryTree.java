import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *	Binary Tree of Comparable values.
 *	The tree only has unique values. It does not add duplicate values.
 *	
 *	@author	Karen Ke
 *	@since	May 18, 2024
 */
 
public class BinaryTree {

	private TreeNode<State> root;		// the root of the tree
	
	private final int PRINT_SPACES = 3;	// print spaces between tree levels
										// used by printTree()
	
	/**	constructor for BinaryTree */
	public BinaryTree() { }
	
	/**	Field accessors and modifiers */
	
	/**	Add a node to the tree
	 *	@param value		the value to put into the tree
	 */
	public void add(State value) { 
		TreeNode<State> node = new TreeNode<State>(value);
		if (root == null)
			root = node;
		
		else {
			TreeNode<State> prevNode = root;
			TreeNode<State> nextNode = root;
			boolean left = true;
			while (nextNode != null) {
				prevNode = nextNode;
				if (value.compareTo(prevNode.getValue()) < 0) {
					nextNode = prevNode.getLeft();
					left = true;
				}
				else if (value.compareTo(prevNode.getValue()) > 0) {
					nextNode = prevNode.getRight();
					left = false;
				}
			}
			
			if (left)
				prevNode.setLeft(node);
			else
				prevNode.setRight(node);			
		}
	}
	
	/**	Add a node to the tree. Calls recursive add method
	 *	@param value		the value to put into the tree
	 */
/*	public void add(State value) {
		boolean added = false;
		if (root == null)
			root = new TreeNode<State>(value);
		else
			added = add(value, root);
	}
	*/
	
	/** Recursive add method. Compares value to the value of the given 
	 *  node and either adds it to the node (if the it's less and left is
	 *  empty or it's greater and right is empty) or recursively calls
	 *  add with the value and the left / right child node.
	 *  @param val		the value to add
	 *  @param node		the node value is being compared to
	 *  @return			whether or not the value was added
	 */
/*	public boolean add(State val, TreeNode<State> node) {
		TreeNode<State> valNode = new TreeNode<State>(val);
		
		if (val.compareTo(node.getValue()) < 0) {
			if (node.getLeft() == null) {
				node.setLeft(valNode);
				return true;
			}
			return add(val, node.getLeft());
		}
		
		if (node.getRight() == null) {
			node.setRight(valNode);
			return true;
		}
		return add(val, node.getRight());
	}
	*/
	
	/**
	 *	Print Binary Tree Inorder
	 */
	public void printInorder() {		// left, node, right
		List<TreeNode<State>> list = new ArrayList<TreeNode<State>>();
		TreeNode<State> node = root;
		
		while (list.size() > 0 || node != null) {
			while (node != null) {		// keep going left
				list.add(node);		// remember to visit
				node = node.getLeft();
			}

			do {
				node = list.get(list.size() - 1);	// revisiting
				list.remove(list.size() - 1);
				System.out.print(node.getValue() + "\n");	// if no right tree,
			} while (node.getRight() == null && list.size() > 0);	// print parent
			
			node = node.getRight();
		}
	}
	
	/**
	 *	Print Binary Tree Preorder
	 */
	public void printPreorder() { 		// node, left, right
		TreeNode<State> node = root;
		List<TreeNode<State>> list = new ArrayList<TreeNode<State>>();
		
		while (list.size() > 0 || node != null) {
			while (node != null) {
				System.out.print(node.getValue() + " ");
				list.add(node);
				node = node.getLeft();
			}
			
			do {
				node = list.get(list.size() - 1);
				list.remove(list.size() - 1);
			} while (node.getRight() == null && list.size() > 0);
			
			node = node.getRight();
		}
	}
	
	/**
	 *	Print Binary Tree Postorder
	 */
	public void printPostorder() { 		//left, right, node	
		List<TreeNode<State>> list = new ArrayList<TreeNode<State>>();
		TreeNode<State> node = root;
		TreeNode<State> lastNode = null;
		
		while (list.size() > 0 || node != null) {
			while (node != null) {
				list.add(node);
				node = node.getLeft();
			}
			
			node = list.get(list.size() - 1);
			if (node.getRight() == null || node.getRight().equals(lastNode)) {
				System.out.print(node.getValue() + " ");
				list.remove(list.size() - 1);
				lastNode = node;
			}
			
			if (node != lastNode)
				node = node.getRight();
			else
				node = null;
		}
	}
		
	/**	Return a balanced version of this binary tree
	 *	@return		the balanced tree
	 */
	public BinaryTree makeBalancedTree() {
		BinaryTree balancedTree = new BinaryTree();
		List<TreeNode<State>> nodes = orderNodes();
		addToBalTree(balancedTree, nodes);
		return balancedTree;
	}
	
	/** Returns an array with the data values of the binary tree listed inorder.
	 *  Uses the printInorder process except adds to array instead of prints.
	 *  @return		the array list of values
	 */
	public List<TreeNode<State>> orderNodes() {
		List<TreeNode<State>> orderedNodes = new ArrayList<TreeNode<State>>();
		List<TreeNode<State>> list = new ArrayList<TreeNode<State>>();
		TreeNode<State> node = root;
		
		while (list.size() > 0 || node != null) {
			while (node != null) {
				list.add(node);
				node = node.getLeft();
			}
			
			do {
				node = list.get(list.size() - 1);
				list.remove(list.size() - 1);
				orderedNodes.add(node);
			} while (node.getRight() == null && list.size() > 0);
			
			node = node.getRight();
		}
		return orderedNodes;
	}
	
	/** Adds values of an ArrayList to the balenced tree using recursion. The 
	 *  middle value of the ArrayList is added and the method is recursively
	 *  called for the rest of the ArrayList on either side of the middle until
	 *  the ArrayList parameter only has one value left (base case).
	 *  @param tree		balenced tree to add values to
	 *  @param nodes	ArrayList of values
	 */
	public void addToBalTree (BinaryTree tree, List<TreeNode<State>> nodes) {
		if (nodes.size() == 1)
			tree.add(nodes.get(0).getValue());
			
		else {
			int mid = (nodes.size() - 1) / 2;
			tree.add(nodes.get(mid).getValue());
			
			List<TreeNode<State>> half1 = new ArrayList<TreeNode<State>>();
			if (mid > 0) {
				for (int i = 0; i < mid; i++)
					half1.add(nodes.get(i));
				addToBalTree(tree, half1);
			}
			
			if (mid < nodes.size() - 1) {
				List<TreeNode<State>> half2 = new ArrayList<TreeNode<State>>();
				for (int j = mid + 1; j < nodes.size(); j++)
					half2.add(nodes.get(j));
				addToBalTree(tree, half2);
			}
		}
	}
	
	/**
	 *	Remove value from Binary Tree
	 *	@param value		the value to remove from the tree
	 *	Precondition: value exists in the tree
	 */
	public void remove(State value) {
		root = remove(root, value);
	}
	/**
	 *	Remove value from Binary Tree
	 *	@param node			the root of the subtree
	 *	@param value		the value to remove from the subtree
	 *	@return				TreeNode that connects to parent
	 */
	public TreeNode<State> remove(TreeNode<State> node, State value) {
		TreeNode<State> parent = getValParent(node, value);
		TreeNode<State> valNode = null;
		boolean valIsLeft = false;
		
		if (parent == null) 
			valNode = node;
		else {
			if (parent.getLeft().getValue().equals(value)) {
				valNode = parent.getLeft();
				valIsLeft = true;
			}
			else
				valNode = parent.getRight();
		}
		
		// case 1:
		if (valNode.getLeft() == null && valNode.getRight() == null) {
			if (parent == null)
				return null;
			if (valIsLeft)
				parent.setLeft(null);
			else
				parent.setRight(null);
		}
		// case 2:
		else if (valNode.getRight() == null) {
			if (parent == null)
				return valNode.getLeft();
			if (valIsLeft)
				parent.setLeft(valNode.getLeft());
			else
				parent.setRight(valNode.getLeft());
		}
		// case 3:
		else {
			TreeNode<State> rightNode = valNode.getRight();
			if (rightNode.getLeft() == null) {
				rightNode.setLeft(valNode.getLeft());
				if (parent == null)
					return rightNode;
				if (valIsLeft)
					parent.setLeft(rightNode);
				else
					parent.setRight(rightNode);
			}
			
			else {
				TreeNode<State> nextSmallest = rightNode;
				while (nextSmallest.getLeft() != null)
					nextSmallest = nextSmallest.getLeft();
				State nextVal = nextSmallest.getValue();
				remove(nextVal);
				nextSmallest = new TreeNode<State>(nextVal);
				
				nextSmallest.setLeft(valNode.getLeft());
				nextSmallest.setRight(valNode.getRight());
				if (parent == null)
					return nextSmallest;
				if (valIsLeft)
					parent.setLeft(nextSmallest);
				else
					parent.setRight(nextSmallest);
			}
		}
		return node;
	}
	
	/** Returns the parent of the node in the given tree with the given value.
	 *  @param node		root of the given tree
	 * 	@param val		given value
	 * 	@return parent	parent node
	 */
	public TreeNode<State> getValParent(TreeNode<State> node, State val) {
		TreeNode<State> parent = null;
		
		if (node.getLeft() != null && node.getLeft().getValue().equals(val) || 
			node.getRight() != null && node.getRight().getValue().equals(val))
			parent = node;
			
		if (parent == null && node.getLeft() != null)
			parent = getValParent(node.getLeft(), val);
			
		if (parent == null && node.getRight() != null)
			parent = getValParent(node.getRight(), val);
		
		return parent;
	}

	//// METHODS FOR STATE TREE:
		
	/** Reads states2.txt using FileUtils and creates binary tree with data
	 *  Reads each line, removes excess spaces, and collects data by
	 *  setting values equal to the substring of the og string to the next
	 *  space, then setting the orignal string equal to the substring of
	 *  the original string from the index after the index of the last space
	 */
	public void loadData() {
		String fileName = "states2.txt";
		System.out.println("Loading file " + fileName + "\n");
		Scanner reader = FileUtils.openToRead(fileName);
		while (reader.hasNext()) {
			String line = reader.nextLine();
			line = removeExcessSpaces(line);
			
			String n = line.substring(0, line.indexOf(' '));
			line = line.substring(line.indexOf(' ') + 1);
			String a = line.substring(0, line.indexOf(' '));
			line = line.substring(line.indexOf(' ') + 1);
			int p = Integer.parseInt(line.substring(0, line.indexOf(' ')));
			line = line.substring(line.indexOf(' ') + 1);
			int ar = Integer.parseInt(line.substring(0, line.indexOf(' ')));
			line = line.substring(line.indexOf(' ') + 1);
			int r = Integer.parseInt(line.substring(0, line.indexOf(' ')));
			line = line.substring(line.indexOf(' ') + 1);
			String c = line.substring(0, line.indexOf(' '));
			line = line.substring(line.indexOf(' ') + 1);
			int m = Integer.parseInt(line.substring(0, line.indexOf(' ')));
			line = line.substring(line.indexOf(' ') + 1);
			int d = Integer.parseInt(line.substring(0, line.indexOf(' ')));
			line = line.substring(line.indexOf(' ') + 1);
			int y = Integer.parseInt(line);
			
			add(new State(n, a, p, ar, r, c, m , d, y));
		}
	}
	
	/** Removes all excess spaces in a string so that each space is only 
	 *  character long.
	 *  @param str			original string
	 *  @return newStr		str with excess spaces removed
	 */
	public String removeExcessSpaces(String str) {
		for (int i = 1; i < str.length(); i++) {
			if (str.substring(i - 1, i).equals(" ") && 
				str.substring(i, i + 1).equals(" ")) {
				str = str.substring(0, i - 1) + str.substring(i);
				i--;
			}
		}
		return str;
	}
	
	/** Inserts a new State object into the binary tree. States are compared
	 *  by their names
	 * 	@param next		State that will be inserted
	 */
	public void insert(State next) {	////////////////////////////////////////////////////////////
		add(next);	// ???
	}
	
	/** Prints the tree in ascending order by state name (inorder)
	 */
	public void printList() {
		printInorder();
		System.out.println();
	}
	
	/** Calls recursive print list method
	 */
/*	public void printList() {
		recursivePrint(root);
	}
	
	/** recurstive print list method
	 *  @param node		current node
	 */
/*	public void recursivePrint(TreeNode<State> node) {
		if (node.getLeft() != null)
			recursivePrint(node.getLeft());
		System.out.print(node.getValue() + "\n");
		if (node.getRight() != null) 
			recursivePrint(node.getRight());
	}
	*/
	
	/** Prompts the user for the state name and prints that state's
	 *  information
	 */
	public void testFind() {
		System.out.println("Testing search algorithm\n");
		String name = Prompt.getString("Enter state name to search for" + 
			" (Q to quit)");
		while (name.equalsIgnoreCase("q") == false) {
			name = changeCase(name);
			TreeNode<State> node = root;
			String nodeName = node.getValue().getName();
			while (node != null && nodeName.equalsIgnoreCase(name) == false) {
				if (name.compareTo(nodeName) < 0)
					node = node.getLeft();
				else if (name.compareTo(nodeName) > 0)
					node = node.getRight();
				if (node != null)
					nodeName = node.getValue().getName();
			}
			if (node != null)
				System.out.println("\n" + node.getValue().toString() + "\n");
			else
				System.out.println("Name = " + name + "  No such state name\n");
			
			name = Prompt.getString("Enter state name to search for" + 
				" (Q to quit)");
		}
	}
	
	/** Calls recursive test find
	 */
/*	public void testFind() {
		System.out.println("Testing search algorithm\n");
		String name = Prompt.getString("Enter state name to search for" + 
			" (Q to quit)");
		while (name.equalsIgnoreCase("q") == false) {
			name = changeCase(name);
			findState(root, name);
			name = Prompt.getString("Enter state name to search for" + 
				" (Q to quit)");
		}
	}
	*/
	
	/** Recursive test find
	 *  @param node		current node
	 *  @param name		name of state to find
	 */
/*	public void findState(TreeNode<State> node, String name) {
		if (node == null)
			System.out.println("Name = " + name + "  No such state name\n");
		else if (node.getValue().getName().equals(name))
			System.out.println("\n" + node.getValue().toString() + "\n");
			
		else if (node.getValue().getName().compareTo(name) < 0)
			findState(node.getRight(), name);
		else if (node.getValue().getName().compareTo(name) > 0)
			findState(node.getLeft(), name);
	}
	*/
	
	/** Changes the case of a string so that the first letter is capital
	 *  and the rest are lowercase.
	 *  @param str		string to change
	 *  @return newStr	changed string
	 */
	public String changeCase(String str) {
		String newStr = "";
		newStr += getNewChar(str.substring(0, 1), 0);
		for (int i = 1; i < str.length(); i++)
			newStr += getNewChar(str.substring(i, i + 1), 1);
		return newStr;
	}
	
	/** Returns the uppercase or lowercase version of a given letter
	 * 	@param s			the letter
	 *  @param caseNum		0 if want uppercase, 1 if want lowercase
	 * 	@return				modified letter
	 */
	public String getNewChar(String s, int caseNum) {
		int start = 65 + 32 * caseNum;
		for (int i = start; i < start + 26; i++) {
			String newS = "" + (char)i;
			if (s.equalsIgnoreCase(newS))
				return newS;
		}
		return s;
	}
	
	/** @return		number of nodes in tree */
	public int size() {
		List<TreeNode<State>> states = orderNodes();
		return states.size();
	}
	
	/** calls recursive method to get size of tree
	 *  @return 	size of tree
	 */
/*	public int size() {
		return countNodes(root);
	}
	*/
	
	/** recursively counts number of nodes in tree
	 *  @param		node to count
	 *  @return		number of nodes in tree
	 */
/*	public int countNodes(TreeNode<State> node) {
		if (node == null)
			return 0;
		return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
	}
	*/
	
	/** clears tree of all nodes */
	public void clear() {
		root.setLeft(null);
		root.setRight(null);
		root = null;
	}
	
	/** Prompts the user for a level of the tree and prints the names of
	 * 	all states at that level
	 */
	public void printLevel() {
		System.out.println("Testing printLevel algorithm\n");
		int level = Prompt.getInt("Enter level value to print (-1 to quit)");
		while (level != -1) {
			List<TreeNode<State>> lastLevel = new ArrayList<TreeNode<State>>();
			lastLevel.add(root);
			int current = 0;
			while (current < level) {
				List<TreeNode<State>> newLevel = new ArrayList<TreeNode<State>>();
				for (int j = 0; j < lastLevel.size(); j++) {
					TreeNode<State> node = lastLevel.get(j);
					if (node.getLeft() != null)
						newLevel.add(node.getLeft());
					if (node.getRight() != null)
						newLevel.add(node.getRight());
				}
				lastLevel = newLevel;
				current++;
			}
			System.out.println("\nLevel\t" + level);
			for (int i = 0; i < lastLevel.size(); i++) {
				String name = lastLevel.get(i).getValue().getName();
				System.out.printf("%-12s", name);
				if (name.length() >= 12)
					System.out.print("\t");
			}
			System.out.println("\n");
			level = Prompt.getInt("Enter level value to print (-1 to quit)");
		}
		System.out.println("\n");
	}
	
	/** Calls recursive print level
	 */
/*	public void printLevel() {
		System.out.println("Testing printLevel algorithm\n");
		int level = Prompt.getInt("Enter level value to print (-1 to quit)");
		while (level != -1) {
			System.out.println("\nLevel\t" + level);
			getLevel(root, 0, level);
			System.out.println("\n");
			level = Prompt.getInt("Enter level value to print (-1 to quit)");
		}
		System.out.println("\n");
	}
	*/
	
	/** If level of the current node is the one the user wants, adds the node name
	 *  to the list. Otherwise, calls itself for any nodes on the left or right 
	 * 	until it reaches the level the user is looking for.
	 *  @param node			current node
	 *  @param current		level of the current node
	 *  @param goal			level user wants
	 */
/*	public void getLevel(TreeNode<State> node, int current, int goal) {
		if (current == goal)
			System.out.print(node.getValue().getName() + "\t");
			
		else if (current < goal) {
			current++;
			if (node.getLeft() != null)
				getLevel(node.getLeft(), current, goal);
			if (node.getRight() != null)
				getLevel(node.getRight(), current, goal);
		}
	}
	*/
	
	/** Prints the depth (highest level number) of the tree to print. Level
	 * 	of root is 0. If tree has no nodes, prints "is empty"
	 */
	public void testDepth() {
		if (size() == 0)
			System.out.println("is empty");
		else {
			int lvl = getDepth(root, 0);
			System.out.println("Depth of the tree = " + lvl + "\n");
		}
	}
	
	/** Returns the depth of the tree by getting the max level of the branches.
	 *  If the current node is a leaf, returns the level of the node. If the node
	 *  only has one child, returns the max level of that child's branch. Otherwise
	 *  returns the greater value between the max level of either child's branch.
	 *  @param node		current node
	 *  @param level	level of current node
	 * 	@return 		max level of branch / tree
	 */
	public int getDepth(TreeNode<State> node, int level) {
		int leftLvl = level;
		int rightLvl = level;
		if (node.getLeft() != null)
			leftLvl = getDepth(node.getLeft(), level + 1);
		if (node.getRight() != null)
			rightLvl = getDepth(node.getRight(), level + 1);
		if (leftLvl >= rightLvl)
			return leftLvl;
		return rightLvl;
	}
	
	/** Prompts the user for a state name and deletes that state's node, if the
	 *  state exists. Finds node by changing the user's input using changeCase().
	 */
	public void testDelete() {
		System.out.println("Testing delete algorithm\n");
		String name = Prompt.getString("Enter state name to delete (Q to quit)");
		while (name.equalsIgnoreCase("q") == false) {
			name = changeCase(name);
			TreeNode<State> node = root;
			String nodeName = node.getValue().getName();
			while (node != null && nodeName.equals(name) == false) {
				if (name.compareTo(nodeName) < 0)
					node = node.getLeft();
				else if (name.compareTo(nodeName) > 0)
					node = node.getRight();
				if (node != null)
					nodeName = node.getValue().getName();
			}
			if (node == null)
				System.out.println("Name = " + name + "  No such state name\n");
			else {
				remove(node.getValue());
				System.out.println("\nDeleted " + name + "\n");
			}
			name = Prompt.getString("Enter state name to delete (Q to quit)");
		}
	}
	
	
	/*******************************************************************************/	
	/********************************* Utilities ***********************************/	
	/*******************************************************************************/	
	/**
	 *	Print binary tree
	 *	@param root		root node of binary tree
	 *
	 *	Prints in vertical order, top of output is right-side of tree,
	 *			bottom is left side of tree,
	 *			left side of output is root, right side is deepest leaf
	 *	Example Integer tree:
	 *			  11
	 *			/	 \
	 *		  /		   \
	 *		5			20
	 *				  /	  \
	 *				14	   32
	 *
	 *	would be output as:
	 *
	 *				 32
	 *			20
	 *				 14
	 *		11
	 *			5
	 ***********************************************************************/
	public void printTree() {
		printLevel(root, 0);
	}
	
	/**
	 *	Recursive node printing method
	 *	Prints reverse order: right subtree, node, left subtree
	 *	Prints the node spaced to the right by level number
	 *	@param node		root of subtree
	 *	@param level	level down from root (root level = 0)
	 */
	private void printLevel(TreeNode<State> node, int level) {
		if (node == null) return;
		// print right subtree
		printLevel(node.getRight(), level + 1);
		// print node: print spaces for level, then print value in node
		for (int a = 0; a < PRINT_SPACES * level; a++) System.out.print(" ");
		System.out.println(node.getValue());
		// print left subtree
		printLevel(node.getLeft(), level + 1);
	}	
}
