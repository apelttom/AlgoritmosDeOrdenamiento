package pe.edu.pucp.algorithms.sorting.DLinkedList;

/**
 * This is implementation of Doubly Linked List. It is using empty head element
 * and empty tail element. It has current pointer on a current element in the
 * list
 * 
 * Head(empty)<------>Element1<------>....<------>Tail(empty)
 * 
 * WARNING: It still needs testing if all methods are working correctly.
 * 
 * @author Tomáš Apeltauer
 * 
 * @param <E>
 *            Type of elements stored in the list
 */
public class DLList<E> {
	DNode<E> head;
	DNode<E> tail;
	DNode<E> current;
	int size;

	/**
	 * Constructor initializing empty head and tail. Also size and current.
	 */
	public DLList() {
		head = new DNode<E>(null, null, null);
		tail = new DNode<E>(null, null, null);
		size = 0;
		head.setNext(tail);
		tail.setPrev(head);
		current = head;
	}

	private DNode<E> getNext(DNode<E> node) throws IllegalStateException {
		if (node == tail)
			throw new IllegalStateException("DLList overflow");
		return node.getNext();
	}

	private DNode<E> getPrev(DNode<E> node) throws IllegalStateException {
		if (node == head)
			throw new IllegalStateException("DLList underflow.");
		return node.getPrev();
	}

	/**
	 * Adds new DNode with parameter element BEFORE reference node
	 * 
	 * @param refNode
	 *            new element will be inserted before this node
	 * @param element
	 */
	private void addBefore(DNode<E> refNode, E element) {
		DNode<E> prevNode = this.getPrev(refNode);

		DNode<E> newNode = new DNode<E>(element, prevNode, refNode);
		refNode.setPrev(newNode);
		prevNode.setNext(newNode);
		size++;
	}

	/**
	 * Adds new DNode with parameter element AFTER reference node
	 * 
	 * @param refNode
	 *            new element will be inserted before this node
	 * @param element
	 */
	private void addAfter(DNode<E> refNode, E element) {
		DNode<E> nextNode = this.getNext(refNode);

		DNode<E> newNode = new DNode<E>(element, refNode, nextNode);
		refNode.setNext(newNode);
		nextNode.setPrev(newNode);
		size++;
	}

	/**
	 * Inserts the specified element at the beginning of this list.
	 * 
	 * @param element
	 */
	public void addFirst(E element) {
		this.addAfter(head, element);
	}

	/**
	 * Appends the specified element to the end of this list.
	 * 
	 * @param element
	 */
	public void addLast(E element) {
		this.addBefore(tail, element);
	}

	/**
	 * Inserts the specified element at the specified position in this list.
	 * Shifts the element currently at that position (if any) and any subsequent
	 * elements to the right.
	 * 
	 * @param index
	 *            where will be the new element inserted
	 * @param element
	 */
	public void add(int index, E element) {
		goToPos(index);
		addBefore(current.getNext(), element);
	}

	/**
	 * Remove specified node and returns its element
	 * 
	 * @param removeNode
	 * @return
	 */
	private E remove(DNode<E> removeNode) {
		DNode<E> prevNode = removeNode.getPrev();
		DNode<E> nextNode = removeNode.getNext();

		removeNode.setPrev(null);
		removeNode.setNext(null);

		prevNode.setNext(nextNode);
		nextNode.setPrev(prevNode);
		size--;

		return removeNode.getElement();
	}

	/**
	 * Remove element on specified index. Throws exception if the list is empty
	 * or if the position is not in the range of elements in the list.
	 * 
	 * @param position
	 * @return removed element
	 */
	public E remove(int position) {
		if (this.isEmpty()) {
			throw new IllegalStateException("DLList is empty. Cannot remove.");
		}
		goToPos(position);
		return this.remove(current.getNext());
	}

	/**
	 * Return element on the current position. Throws exception if the list is
	 * empty.
	 * 
	 * @return element from the current position
	 */
	public E getElement() {
		if (isEmpty()) {
			throw new IllegalStateException(
					"DLList is empty. There are no elements.");
		} else {
			return current.getNext().getElement();
		}
	}

	/**
	 * @return current position in the list
	 */
	public int getPos() {
		DNode<E> probe = head;
		int counter = 0;
		while (probe != current) {
			probe = probe.getNext();
			counter++;
		}
		return counter;
	}

	/**
	 * Moves pointer on current item to the specified position. Throw error if
	 * the position is not in the range of the elements in the list.
	 * 
	 * @param newPos
	 *            where the pointer will be moved
	 */
	public void goToPos(int newPos) {
		if (this.veryfiPosition(newPos)) {
			this.goToStart();
			while (this.getPos() != newPos) {
				this.next();
			}
		} else {
			throw new IndexOutOfBoundsException("Illegal position in DLList.");
		}
	}

	/**
	 * Moves the pointer to the start of the list.
	 */
	public void goToStart() {
		current = head;
	}

	/**
	 * Moves the pointer to the end of the list.
	 */
	public void goToEnd() {
		current = tail;
	}

	/**
	 * Moves the pointer to the NEXT element if there is any. Throw exception if
	 * current pointer is already on the end.
	 */
	public void next() throws IllegalStateException {
		this.getNext(current);
	}

	/**
	 * Moves the pointer to the PREVIOUS element if there is any. Throw
	 * exception if current pointer is already on the beginning.
	 */
	public void prev() throws IllegalStateException {
		this.getPrev(current);
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return (this.size == 0);
	}

	/**
	 * Checks whether specified index is in range of elements in the list.
	 * 
	 * @param position
	 * @return
	 */
	private boolean veryfiPosition(int position) {
		if (position >= 0 && position < size) {
			return true;
		}
		return false;
	}

	/**
	 * Returns all elements in the String.
	 */
	@Override
	public String toString() {
		String s = "[";

		DNode<E> probe = head.getNext();
		while (probe != tail) {
			s += probe.toString() + " ";
			probe = probe.getNext();
		}
		s += "]";
		return s;
	}
}
