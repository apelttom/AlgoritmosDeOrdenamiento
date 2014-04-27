package pe.edu.pucp.algorithms.sorting.DLinkedList.test;

import java.util.Iterator;

import pe.edu.pucp.algorithms.sorting.DLinkedList.DLList;
import pe.edu.pucp.algorithms.sorting.DLinkedList.DLListIterator;

/**
 * Primitive testing class for Doubly Linked List. It would be nice if somebody
 * would transform it into JUnit class.
 * 
 * @author Tomáš Apeltauer
 * 
 */
public class DLListTest {

	public static void main(String args[]) {
		DLList<Integer> list = new DLList<>();

		// Testing addFirst() method
		list.addFirst(-666);
		// [-666 ]
		System.out.println(list.toString());

		// Testing add(index, element) method
		for (int i = 0; i < 30; i++) {
			list.add(0, i);
		}
		// [...12 11 10 9 8 7 6 5 4 3 2 1 0 -666 ]
		System.out.println(list.toString());

		// Testing element() and size() methods
		System.out.println("First element: " + list.element());
		System.out.println("Size of DLList: " + list.size());

		// Testing aadLast() method
		for (int i = 0; i < 30; i++) {
			list.addLast(i);
		}
		System.out.println(list.toString());

		// Testing size() and get() methods
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " .. ");
		}

		// Testing clear() method
		list.clear();
		System.out.println("\n\nCleared list: " + list.toString() + "\n");

		// Testing addFirst() method
		list.addFirst(999);
		System.out.println(list.toString());

		// Testing isEmpty() method
		System.out.println("Is the list empty? " + list.isEmpty());

		// Testing isEmpty() method
		list.clear();
		System.out.println("Is the list empty now? " + list.isEmpty());

		// Testing element() method on empty list
		System.out.println("First element: " + list.element());

		// Testing get() and set() methods on empty list
		list.set(10, 777);
		System.out.println("Tenth element of empty list: " + list.get(10)
				+ "\n");

		// Filling with data
		for (int i = 107; i > 82; i--) {
			list.addLast(i);
		}

		// Testing specific iterator
		System.out.println("\nList printed by specific iterator:");
		DLListIterator<Integer> it = (DLListIterator<Integer>) list.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " .. ");
		}

		System.out.println("\n\nFrom back to front (doesnt print 83):");
		while (it.hasPrev()) {
			System.out.print(it.prev() + " .. ");
		}

		// Testing generic iterator
		System.out.println("\n\nList printed by generic iterator:");
		for (Integer integer : list) {
			System.out.print(integer + " .. ");
		}
		// Every even element is removed TEST
		Iterator<Integer> it2 = list.iterator();
		it2.next();
		while (it2.hasNext()) {
			int element = it2.next();
			if (element % 2 == 0) {
				it2.remove();
			}
		}
		System.out.println("\n\nOnly odd elements: " + list.toString());

		// Testing swap
		list.clear();
		for (int i = 0; i < 15; i++) {
			list.addLast(i);
		}

		// Swapping element on position 12 and element on position 4
		System.out
				.println("\nElement on position 12 was swapped for element on position 4:\n"
						+ list.toString());
		int temp = list.get(12);
		list.set(12, list.get(4));
		list.set(4, temp);
		System.out.println(list.toString());
	}

}
