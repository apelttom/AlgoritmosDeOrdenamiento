package pe.edu.pucp.algorithms.sorting.algs.impl;

import java.util.Iterator;

import pe.edu.pucp.algorithms.sorting.DLinkedList.DLList;
import pe.edu.pucp.algorithms.sorting.algs.BaseSorter;

/**
 * Base Class for Merge Sort algorithms.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 * @param <T>
 *            Type of the array to be sorted
 */
public abstract class MergeSorter<T extends Comparable<T>> extends
		BaseSorter<T> {

	public MergeSorter(Class<T> clazz, DLList<T> data) {
		super(clazz, data);

	}

	/**
	 * Based on the implementation described in Robert Sedgewick's Algorithm
	 * book.Given two sorted sub-arrays of the data array, it merges the two of
	 * them into one sorted array. The arrays are delimited by 3 indexes.
	 * 
	 * @param lowerIndex
	 *            Lower index of the data array.
	 * @param midIndex
	 *            Mid index of the data array.
	 * @param higherIndex
	 *            Higher index of the data array.
	 */
	@SuppressWarnings("unchecked")
	protected void merge(int lowerIndex, int midIndex, int higherIndex) {
		int leftCollectionMarker = lowerIndex;
		int rightCollectionMarker = midIndex + 1;

		DLList<T> leftList = new DLList<T>();
		DLList<T> rightList = new DLList<T>();

		// Fill left collection with elements
		for (int i = lowerIndex; i <= midIndex; i++) {
			leftList.addLast(getDataAtIndex(i));
		}
		// Fill right collection with elements
		for (int i = midIndex + 1; i <= higherIndex; i++) {
			rightList.addLast(getDataAtIndex(i));
		}
		// Get iterators of left and right collection
		Iterator<T> leftCollectionIt = leftList.iterator();
		Iterator<T> rightCollectionIt = rightList.iterator();

		// Store elements from collections
		T itemFromLeftCollection = null;
		T itemFromRightCollection = null;
		
		for (int j = lowerIndex; j <= higherIndex; j++) {
			// If the last element was used, take new one from collection 
			if (leftCollectionIt.hasNext() && itemFromLeftCollection==null) {
				itemFromLeftCollection = leftCollectionIt.next();
			}
			if (rightCollectionIt.hasNext() && itemFromRightCollection==null) {
				itemFromRightCollection = rightCollectionIt.next();
			}
			
			// Compare elements and pick the smaller one 
			if (leftCollectionMarker > midIndex) {
				setDataAtIndex(j, itemFromRightCollection);
				rightCollectionMarker++;
				itemFromRightCollection = null;
			} else if (rightCollectionMarker > higherIndex) {
				setDataAtIndex(j, itemFromLeftCollection);
				leftCollectionMarker++;
				itemFromLeftCollection = null;
			} else if (itemFromRightCollection != null
					&& itemFromRightCollection
							.compareTo(itemFromLeftCollection) < 0) {
				setDataAtIndex(j, itemFromRightCollection);
				rightCollectionMarker++;
				itemFromRightCollection = null;
			} else {
				setDataAtIndex(j, itemFromLeftCollection);
				leftCollectionMarker++;
				itemFromLeftCollection = null;
			}
		}
	}
}
