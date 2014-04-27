package pe.edu.pucp.algorithms.sorting.DLinkedList;

import java.util.Iterator;

/**
 * Iterator for Doubly Linked List. Can be used as a normal iterator (methods
 * hasNext, next and remove) but also has the power to return back in the list.
 * 
 * @author Tomáš Apeltauer
 * 
 * @param <E>
 */
public class DLListIterator<E> implements Iterator<E> {

	private DLList<E> list;
	private DNode<E> pointer;

	public DLListIterator(DLList<E> list) {
		this.list = list;
		pointer = list.head;
	}

	/**
	 * Returns true if the iteration has more elements. (In other words, returns
	 * true if next() would return an element rather than throwing an
	 * exception.)
	 */
	@Override
	public boolean hasNext() {
		return (pointer.getNext() != list.tail);
	}

	/**
	 * Returns true if prev() would return an element rather than throwing an
	 * exception.
	 */
	public boolean hasPrev() {
		return (pointer.getPrev() != list.head);
	}

	/**
	 * Returns the next element in the iteration.
	 */
	@Override
	public E next() {
		if (!hasNext())
			return null;
		pointer = pointer.getNext();
		return pointer.getElement();
	}

	/**
	 * Returns the previous element in the iteration.
	 */
	public E prev() {
		if (!hasPrev())
			return null;
		pointer = pointer.getPrev();
		return pointer.getElement();
	}

	/**
	 * Removes from the underlying collection the last element returned by this
	 * iterator (optional operation). This method can be called only once per
	 * call to next(). The behavior of an iterator is unspecified if the
	 * underlying collection is modified while the iteration is in progress in
	 * any way other than by calling this method.
	 */
	@Override
	public void remove() {
		DNode<E> temp = pointer;
		pointer = pointer.getNext();
		list.remove(temp);
	}

}
