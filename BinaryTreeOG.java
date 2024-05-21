import java.util.ArrayList;
import java.util.List;

/**
 *	Binary Tree of Comparable values.
 *	The tree only has unique values. It does not add duplicate values.
 *	
 *	@author	Karen Ke
 *	@since	May 18, 2024
 */
 
public class BinaryTree<E extends Comparable<E>> {

	private TreeNode<E> root;		// the root of the tree
	
	private final int PRINT_SPACES = 3;	// print spaces between tree levels
										// used by printTree()
	
	/**	constructor for BinaryTree */
	public BinaryTree() { }
	
	/**	Field accessors and modifiers */
	
	/**	Add a node to the tree
	 *	@param value		the value to put into the tree
	 */
	public void add(E value) { 
		TreeNode<E> node = new TreeNode<E>(value);
		if (root == null)
			root = node;
		
		else {
			TreeNode<E> prevNode = root;
			TreeNode<E> nextNode = root;
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
/*	public void add(E value) {
		boolean added = false;
		if (root == null)
			root = new TreeNode<E>(value);
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
/*	public boolean add(E val, TreeNode<E> node) {
		TreeNode<E> valNode = new TreeNode<E>(val);
		
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
		List<TreeNode<E>> list = new ArrayList<TreeNode<E>>();
		TreeNode<E> node = root;
		
		while (list.size() > 0 || node != null) {
			while (node != null) {		// keep going left
				list.add(node);		// remember to visit
				node = node.getLeft();
			}

			do {
				node = list.get(list.size() - 1);	// revisiting
				list.remove(list.size() - 1);
				System.out.print(node.getValue() + " ");	// if no right tree,
			} while (node.getRight() == null && list.size() > 0);	// print parent
			
			node = node.getRight();
		}
	}
	
	/**
	 *	Print Binary Tree Preorder
	 */
	public void printPreorder() { 		// node, left, right
		TreeNode<E> node = root;
		List<TreeNode<E>> list = new ArrayList<TreeNode<E>>();
		
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
		List<TreeNode<E>> list = new ArrayList<TreeNode<E>>();
		TreeNode<E> node = root;
		TreeNode<E> lastNode = null;
		
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
	public BinaryTree<E> makeBalancedTree() {
		BinaryTree<E> balancedTree = new BinaryTree<E>();
		List<TreeNode<E>> nodes = orderNodes();
		addToBalTree(balancedTree, nodes);
		return balancedTree;
	}
	
	/** Returns an array with the data values of the binary tree listed inorder.
	 *  Uses the printInorder process except adds to array instead of prints.
	 *  @return		the array list of values
	 */
	public List<TreeNode<E>> orderNodes() {
		List<TreeNode<E>> orderedNodes = new ArrayList<TreeNode<E>>();
		List<TreeNode<E>> list = new ArrayList<TreeNode<E>>();
		TreeNode<E> node = root;
		
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
	public void addToBalTree (BinaryTree<E> tree, List<TreeNode<E>> nodes) {
		if (nodes.size() == 1)
			tree.add(nodes.get(0).getValue());
			
		else {
			int mid = (nodes.size() - 1) / 2;
			tree.add(nodes.get(mid).getValue());
			
			List<TreeNode<E>> half1 = new ArrayList<TreeNode<E>>();
			if (mid > 0) {
				for (int i = 0; i < mid; i++)
					half1.add(nodes.get(i));
				addToBalTree(tree, half1);
			}
			
			if (mid < nodes.size() - 1) {
				List<TreeNode<E>> half2 = new ArrayList<TreeNode<E>>();
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
	public void remove(E value) {
		root = remove(root, value);
	}
	/**
	 *	Remove value from Binary Tree
	 *	@param node			the root of the subtree
	 *	@param value		the value to remove from the subtree
	 *	@return				TreeNode that connects to parent
	 */
	public TreeNode<E> remove(TreeNode<E> node, E value) {
		TreeNode<E> parent = getValParent(node, value);
		TreeNode<E> valNode = null;
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
			TreeNode<E> rightNode = valNode.getRight();
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
				TreeNode<E> nextSmallest = rightNode;
				while (nextSmallest.getLeft() != null)
					nextSmallest = nextSmallest.getLeft();
				E nextVal = nextSmallest.getValue();
				remove(nextVal);
				nextSmallest = new TreeNode<E>(nextVal);
				
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
	public TreeNode<E> getValParent(TreeNode<E> node, E val) {
		TreeNode<E> parent = null;
		
		if (node.getLeft() != null && node.getLeft().getValue().equals(val) || 
			node.getRight() != null && node.getRight().getValue().equals(val))
			parent = node;
			
		if (parent == null && node.getLeft() != null)
			parent = getValParent(node.getLeft(), val);
			
		if (parent == null && node.getRight() != null)
			parent = getValParent(node.getRight(), val);
		
		return parent;
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
	private void printLevel(TreeNode<E> node, int level) {
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
