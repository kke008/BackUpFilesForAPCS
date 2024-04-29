import java.util.NoSuchElementException;

/**
 *	SinglyLinkedList - creating singly linked lists by adding functionalities
 *  to the class to manipulate the list, using ListNode objects as nodes.
 *
 *	@author	Karen Ke
 *	@since	29 April, 2024
 */
public class SinglyLinkedList<E extends Comparable<E>>
{
	/* Fields */
	private ListNode<E> head, tail;		// head and tail pointers to list
	
	/* No-args Constructors */
	public SinglyLinkedList() {}
	
	/** Copy constructor */
	public SinglyLinkedList(SinglyLinkedList<E> oldList) {
		head = oldList.get(0);
		ListNode<E> node = head;
		while (node.getNext() != null || 
			node.compareTo(oldList.get(oldList.size() - 1)) == 0) {
			node.setNext(node.getNext());
			node = node.getNext();
			if (node.compareTo(oldList.get(oldList.size() - 1)) == 0)
				last = node;
		}
	}
	
	/**	Clears the list of elements */
	public void clear() {
		head = null;
		head.setNext(null);
		tail = null;
	}
	
	/**	Add the object to the end of the list
	 *	@param obj		the object to add
	 *	@return			true if successful; false otherwise
	 */
	public boolean add(E obj) {
		tail.setNext(obj);
		tail = obj;
		return true;
	}
	
	/**	Add the object at the specified index
	 *	@param index		the index to add the object
	 *	@param obj			the object to add
	 *	@return				true if successful; false otherwise
	 *	@throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj) {
		if (index < 0 || index > size())
			// throw exception
			
		else if (index == 0) {
			obj.setNext(head);
			head = obj;
			return true;
		}
		
		else if (index == size()) {
			last.setNext(obj);
			last = obj;
		}
		
		else {
			int i = 0;
			int indOfPrev = index - 1;
			ListNode<E> node = head;
		}
		
		return false;
	}
	
	/**	@return the number of elements in this list */
	public int size() {
		return 0;
	}
	
	/**	Return the ListNode at the specified index
	 *	@param index		the index of the ListNode
	 *	@return				the ListNode at the specified index
	 *	@throws NoSuchElementException if index does not exist
	 */
	public ListNode<E> get(int index) {
		return null;
	}
	
	/**	Replace the object at the specified index
	 *	@param index		the index of the object
	 *	@param obj			the object that will replace the original
	 *	@return				the object that was replaced
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E set(int index, E obj) {
		return null;
	}
	
	/**	Remove the element at the specified index
	 *	@param index		the index of the element
	 *	@return				the object in the element that was removed
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E remove(int index) {
		return null;
	}
	
	/**	@return	true if list is empty; false otherwise */
	public boolean isEmpty() {
		return false;
	}
	
	/**	Tests whether the list contains the given object
	 *	@param object		the object to test
	 *	@return				true if the object is in the list; false otherwise
	 */
	public boolean contains(E object) {
		return false;
	}
	
	/**	Return the first index matching the element
	 *	@param element		the element to match
	 *	@return				if found, the index of the element; otherwise returns -1
	 */
	public int indexOf(E element) {
		return -1;
	}
	
	/**	Prints the list of elements */
	public void printList()
	{
		ListNode<E> ptr = head;
		while (ptr != null)
		{
			System.out.print(ptr.getValue() + "; ");
			ptr = ptr.getNext();
		}
	}
	

}
