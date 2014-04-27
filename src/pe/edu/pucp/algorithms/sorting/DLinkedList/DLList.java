package pe.edu.pucp.algorithms.sorting.DLinkedList;

import java.util.Iterator;

/**
 * This is implementation of Doubly Linked List. It is using empty head element
 * and empty tail element as indicators of end and start. It has also
 * implemented an iterator which only can be used to remove elements from the
 * list.
 * 
 * Head(empty)<------>Element1<------>....<------>Tail(empty)
 * 
 * @author Tomáš Apeltauer
 * 
 * @param <E>
 *            Type of elements stored in the list
 */
public class DLList<E> implements Iterable<E> {
	DNode<E> head;
	DNode<E> tail;
	int size;

	/**
	 * Constructor initializing empty head and tail. Also size and current.
	 */
	public DLList() {
		initializeClean();
	}

	private void initializeClean() {
		head = new DNode<E>(null, null, null);
		tail = new DNode<E>(null, null, null);
		size = 0;
		head.setNext(tail);
		tail.setPrev(head);
	}

	/**
	 * Checks whether specified index is in range of elements in the list.
	 * 
	 * @param index
	 *            - index to be verified
	 * @return true if index is in range of elements. False otherwise.
	 */
	private boolean veryfiIndex(int index) {
		if (index >= 0 && index < size) {
			return true;
		}
		return false;
	}

	/**
	 * Returns pointer on the node of DLList on specified index.
	 * 
	 * @param index
	 *            - for creation of the pointer.
	 * @return pointer of type DNode<E>
	 */
	private DNode<E> pointToIndex(int index) {
		DNode<E> pointer = head;
		for (int counter = 0; counter <= index; counter++) {
			pointer = pointer.getNext();
		}
		return pointer;
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
	 * Inserts the specified element at the specified position in this list.
	 * Shifts the element currently at that position (if any) and any subsequent
	 * elements to the right.
	 * 
	 * @param index
	 *            where will be the new element inserted
	 * @param element
	 */
	public void add(int index, E element) {
		if (veryfiIndex(index)) {
			DNode<E> pointer = pointToIndex(index);
			addBefore(pointer, element);
		}
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
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		initializeClean();
	}

	/**
	 * Retrieves, but does not remove, the head (first element) of this list.
	 * 
	 * @return element from the current position
	 */
	public E element() {
		if (!isEmpty()) {
			return head.getNext().getElement();
		}
		return null;
	}

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index
	 *            - index of the element to return
	 * @return the element at the specified position in this list
	 */
	public E get(int index) {
		if (veryfiIndex(index)) {
			DNode<E> pointer = this.pointToIndex(index);
			return pointer.getElement();
		}
		return null;
	}

	/**
	 * Remove specified node and returns its element
	 * 
	 * @param removeNode
	 * @return
	 */
	E remove(DNode<E> removeNode) {
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
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 * 
	 * @param index
	 *            - index of the element to replace
	 * @param element
	 *            - element to be stored at the specified position
	 * @return the element previously at the specified position
	 */
	public E set(int index, E element) {
		if (veryfiIndex(index)) {
			DNode<E> pointer = this.pointToIndex(index);
			E oldElement = pointer.getElement();
			pointer.setElement(element);
			return oldElement;
		}
		return null;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return (this.size == 0);
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

	/**
	 * Returns a list-iterator of the elements in this list (in proper
	 * sequence), starting at the first position (head) in the list. Obeys the
	 * general contract of Iterator.
	 */
	@Override
	public Iterator<E> iterator() {
		return new DLListIterator<E>(this);
	}

}
