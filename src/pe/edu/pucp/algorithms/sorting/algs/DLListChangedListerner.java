package pe.edu.pucp.algorithms.sorting.algs;

import pe.edu.pucp.algorithms.sorting.DLinkedList.DLList;

/**
 * Listener interface for the Observer Pattern implementation. Every class that
 * implements the interface will be notified when the elements in the Doubly
 * Linked List change their position.
 * 
 * @author Tomáš Apeltauer
 * 
 */
@SuppressWarnings("rawtypes")
public interface DLListChangedListerner<T extends Comparable> {

	public void listChanged(DLList<T> data);		
}
