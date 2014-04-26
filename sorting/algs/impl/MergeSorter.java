package pe.edu.pucp.algorithms.sorting.algs.impl;

import java.lang.reflect.Array;

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

    public MergeSorter(Class<T> clazz, T[] data) {
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

        int leftArrayMarker = lowerIndex;
        int rightArrayMarker = midIndex + 1;

        int auxArraySize = higherIndex + 1;
        T[] auxiliarArray = (T[]) Array.newInstance(clazz, auxArraySize);
        for (int i = lowerIndex; i <= higherIndex; i++) {
            auxiliarArray[i] = getDataAtIndex(i);
        }
        for (int j = lowerIndex; j <= higherIndex; j++) {
            T itemFromLeftArray = null;
            if (leftArrayMarker < auxArraySize) {
                itemFromLeftArray = auxiliarArray[leftArrayMarker];
            }
            T itemFromRightArray = null;
            if (rightArrayMarker < auxArraySize) {
                itemFromRightArray = auxiliarArray[rightArrayMarker];
            }
            if (leftArrayMarker > midIndex) {
                setDataAtIndex(j, itemFromRightArray);
                rightArrayMarker++;
            } else if (rightArrayMarker > higherIndex) {
                setDataAtIndex(j, itemFromLeftArray);
                leftArrayMarker++;
            } else if (itemFromRightArray != null
                    && itemFromRightArray.compareTo(itemFromLeftArray) < 0) {
                setDataAtIndex(j, itemFromRightArray);
                rightArrayMarker++;
            } else {
                setDataAtIndex(j, itemFromLeftArray);
                leftArrayMarker++;
            }
        }
    }
}
