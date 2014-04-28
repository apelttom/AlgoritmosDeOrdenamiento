package pe.edu.pucp.algorithms.sorting.algs;

import java.util.Iterator;

import pe.edu.pucp.algorithms.sorting.DLinkedList.DLList;

/**
 * Base class for Sorting Algorithms, with several helper methods that every
 * algorithm of the program needs.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * @author Tomáš Apeltauer
 * 
 * @param <T>
 *            Type of the array to be sorted
 */
@SuppressWarnings("unchecked")
public abstract class BaseSorter<T extends Comparable> {

	private static final String FAIL_MESSAGE = "ERROR: La ejecuci�n del algoritmo no orden� el set de datos.";
	private static final String SUCCESS_MESSAGE = "El set de Datos se encuentra ordenado.";
	private static final String HORIZONTAL_LINE = "==============================\n";
	private static final String EXECUTION_FINISHED = "Ejecuci�n finalizada\n";

	protected Class<T> clazz;
	private DLList<T> data;
	private DLListChangedListerner<T> listChangeListener;

	/**
	 * Gets a Sorter instance.
	 * 
	 * @param clazz
	 *            Class of the elements to sort.
	 * @param data
	 *            Data that the algorithm must sort.
	 */
	public BaseSorter(Class<T> clazz, DLList<T> data) {
		this.data = data;
		this.clazz = clazz;
	}

	/**
	 * Operation that applies the algorithm and sorts the data.
	 */
	public abstract void sortData();

	/**
	 * Applies the sorting algorithm to the data and shows a message.
	 * 
	 * @return Message that informs if sorting was successfull or not.
	 */
	public String sortAndShowResults() {
		sortData();
		StringBuffer buffer = new StringBuffer();
		buffer.append(EXECUTION_FINISHED);
		buffer.append(HORIZONTAL_LINE);
		if (!isSorted()) {
			buffer.append(FAIL_MESSAGE);
		} else {
			buffer.append(SUCCESS_MESSAGE);
		}
		return buffer.toString();

	}

	/**
	 * Swap the two elements of the DLList to sort.
	 * 
	 * @param index
	 *            Index of one item.
	 * @param anotherIndex
	 *            Index of another item.
	 */
	protected void exchange(int index, int anotherIndex) {
		T temp = data.get(index);
		this.setDataAtIndex(index, data.get(anotherIndex));;
		this.setDataAtIndex(anotherIndex, temp);

		// T itemAtIndex = data[index];
		// setDataAtIndex(index, data[anotherIndex]);
		// setDataAtIndex(anotherIndex, itemAtIndex);
	}

	/**
	 * Evaluates if the sorting was successfully or not.
	 * 
	 * @return True if the sorting succeed and false if not.
	 */
	protected boolean isSorted() {
		if (data.isEmpty())
			return true;
		Iterator<T> it = data.iterator();
		T smallerElem = it.next();
		while (it.hasNext()) {
			T biggerElem = it.next();
			if (smallerElem.compareTo(biggerElem) > 0) {
				return false;
			}
			smallerElem = biggerElem;
		}
		return true;

		// for (int i = 1; i < data.length; i++) {
		// if (data[i].compareTo(data[i - 1]) < 0) {
		// return false;
		// }
		// }
		// return true;
	}

	/**
	 * Returns number of items.
	 * 
	 * @return Number of items.
	 */
	protected int getLength() {
		return data.size();
	}

	/**
	 * Returns a data element at the desired position.
	 * 
	 * @param index
	 *            Index of the element.
	 * @return The element.
	 */
	protected T getDataAtIndex(int index) {
		return data.get(index);
	}

	/**
	 * Sets a data item at a particular index, and notifies listeners of the
	 * change.
	 * 
	 * @param index
	 *            Index where to put the item.
	 * @param item
	 *            The item to move.
	 */
	protected void setDataAtIndex(int index, T item) {
		data.set(index, item);
//		data[index] = item;
		
		listChangeListener.listChanged(data);
	}

	/**
	 * Returns the maximum index of the array.
	 * 
	 * @return Max index.
	 */
	protected int getMaxIndex() {
		return (data.size()-1);
	}

	/**
	 * Sets the change listener for the sorter algorithms.
	 * 
	 * @param listChangeListener
	 *            Listener instance.
	 */
	public void setDLListChangeListener(DLListChangedListerner<T> listChangeListener) {
		this.listChangeListener = listChangeListener;
	}

}
