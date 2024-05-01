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
		for (int i = 0; i < oldList.size(); i++) {
			add(oldList.get(i).getValue());
		}
		head = get(0);
		tail = get(size() - 1);
		/*head = oldList.get(0);
		int i = 1;
		while (i < oldList.size()) {
			add(oldList.get(i).getValue());
			if (i == oldList.size() - 1)
				tail = oldList.get(i);
		}
		*/
	}
	
	/**	Clears the list of elements */
	public void clear() {
		if (!isEmpty()) {
			head.setNext(null);
			head = null;
			tail = null;
		}
	}
	
	/**	Add the object to the end of the list
	 *	@param obj		the object to add
	 *	@return			true if successful; false otherwise
	 */
	public boolean add(E obj) {
		ListNode<E> objNode = new ListNode<E>(obj);
		if (isEmpty()) {
			head = objNode;
			tail = objNode;
		}
		else {
			tail.setNext(objNode);
			tail = objNode;
		}
		return true;
	}
	
	/**	Add the object at the specified index
	 *	@param index		the index to add the object
	 *	@param obj			the object to add
	 *	@return				true if successful; false otherwise
	 *	@throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj) {
		if (index < 0 || index > size() || (isEmpty() && index != 0))
			throw new NoSuchElementException();
		
		ListNode<E> node = new ListNode<E>(obj);
		if (isEmpty()) {
			head = node;
			tail = node;
		}
		
		else if (index == 0) {
			node.setNext(head);
			head = node;
		}
		
		else if (index == size())
			add(obj);
			
		else {
			ListNode<E> prevNode = get(index - 1);
			ListNode<E> nextNode = get(index);
			prevNode.setNext(node);
			node.setNext(nextNode);
		}
		
		return true;
	}
	
	/**	@return the number of elements in this list */
	public int size() {
		if (isEmpty())
			return 0;
		
		int i = 0;
		ListNode<E> node = head;
		while (node != null) {
			node = node.getNext();
			i++;
		}
		
		return i;
	}
	
	/**	Return the ListNode at the specified index
	 *	@param index		the index of the ListNode
	 *	@return				the ListNode at the specified index
	 *	@throws NoSuchElementException if index does not exist
	 */
	public ListNode<E> get(int index) {
		if (index < 0 || index >= size() || isEmpty())
			throw new NoSuchElementException();
		
		ListNode<E> node = head;	
		for (int i = 1; i <= index; i++) {
			node = node.getNext();
		}
		return node;
	}
	
	/**	Replace the object at the specified index
	 *	@param index		the index of the object
	 *	@param obj			the object that will replace the original
	 *	@return				the object that was replaced
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E set(int index, E obj) {
		E oldObj = remove(index);
		add(index, obj);
		return oldObj;
	}
	
	/**	Remove the element at the specified index
	 *	@param index		the index of the element
	 *	@return				the object in the element that was removed
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E remove(int index) {
		if (isEmpty() || index < 0 || index > size())
			throw new NoSuchElementException();
			
		E oldVal = get(index).getValue();
		if (index == 0) {
			head.setValue(null);
			head = get(1);
		}
		
		else if (index == size() - 1) {
			get(size() - 1).setValue(null);
			get(index - 1).setNext(null);
			tail = get(index - 1);
		}
		
		else {
			ListNode<E> prevNode = get(index - 1);
			ListNode<E> nextNode = get(index + 1);
			ListNode<E> node = get(index);
			
			node.setValue(null);
			prevNode.setNext(nextNode);
		}
		return oldVal;
	}
	
	/**	@return	true if list is empty; false otherwise */
	public boolean isEmpty() {
		if (head == null)
			return true;
		return false;
	}
	
	/**	Tests whether the list contains the given object
	 *	@param object		the object to test
	 *	@return				true if the object is in the list; false otherwise
	 */
	public boolean contains(E object) {
		if (indexOf(object) >= 0)
			return true;
		return false;
	}
	
	/**	Return the first index matching the element
	 *	@param element		the element to match
	 *	@return				if found, the index of the element; otherwise returns -1
	 */
	public int indexOf(E element) {
		int i = 0;
		while (i < size()) {
			ListNode<E> node = get(i);
			if (node.getValue().equals(element))
				return i;
		}return -1;
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
