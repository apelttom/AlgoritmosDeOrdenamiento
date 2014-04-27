package pe.edu.pucp.algorithms.sorting.DLinkedList;
/**
 * 
 * @author Tomáš Apeltauer
 *
 * @param <E>
 */
final class DNode<E> {

	private E element;
	private DNode<E> prev;
	private DNode<E> next;

	DNode(E element) {
		this(element, null, null);
	}

	DNode(E element, DNode<E> previous, DNode<E> next) {
		this.element = element;
		this.prev = previous;
		this.next = next;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public DNode<E> getNext() {
		return next;
	}

	public void setNext(DNode<E> next) {
		this.next = next;
	}

	public DNode<E> getPrev() {
		return prev;
	}

	public void setPrev(DNode<E> prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		if (element == null) {
			return null;
		} else {
			return element.toString();
		}
	}
}