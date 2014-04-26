package pe.edu.pucp.algorithms.sorting.algs.impl;

import pe.edu.pucp.algorithms.sorting.algs.BaseSorter;

/**
 * Implementation of Bubble Sort algorithm. Based on the implementation
 * described in Robert Sedgewick's Algorithm book.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 * @param <T>
 *            Type of the array to be sorted
 */
public class BubbleSorter<T extends Comparable<T>> extends BaseSorter<T> {

    public BubbleSorter(Class<T> clazz, T[] data) {
        super(clazz, data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see pe.edu.pucp.algorithms.sorting.algs.BaseSorter#sortData()
     */
    @Override
    public void sortData() {
        for (int lastIndexToCompare = getMaxIndex(); lastIndexToCompare > 1; lastIndexToCompare--) {
            for (int currentIndex = 0; currentIndex < lastIndexToCompare; currentIndex++) {
                if (getDataAtIndex(currentIndex).compareTo(
                        getDataAtIndex(currentIndex + 1)) > 0) {
                    exchange(currentIndex, currentIndex + 1);
                }
            }

        }
    }

}
