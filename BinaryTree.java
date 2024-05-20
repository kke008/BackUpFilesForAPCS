/**
 *	Binary Tree of Comparable values.
 *	The tree only has unique values. It does not add duplicate values.
 *	
 *	@author	
 *	@since	
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
		boolean added = false;
		if (root == null)
			root = new TreeNode<E>(value);
		else
			added = add(value, root);
	}
	
	/** Recursive add method. Compares value to the value of the given 
	 *  node and either adds it to the node (if the it's less and left is
	 *  empty or it's greater and right is empty) or recursively calls
	 *  add with the value and the left / right child node.
	 *  @param val		the value to add
	 *  @param node		the node value is being compared to
	 *  @return			whether or not the value was added
	 */
	public boolean add(E val, TreeNode<E> node) {
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
	
	
	/**
	 *	Print Binary Tree Inorder
	 */
	public void printInorder() { }
	
	/**
	 *	Print Binary Tree Preorder
	 */
	public void printPreorder() { }
	
	/**
	 *	Print Binary Tree Postorder
	 */
	public void printPostorder() { }
		
	/**	Return a balanced version of this binary tree
	 *	@return		the balanced tree
	 */
	public BinaryTree<E> makeBalancedTree() {
		BinaryTree<E> balancedTree = new BinaryTree<E>();

		return balancedTree;
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
		return null;
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
