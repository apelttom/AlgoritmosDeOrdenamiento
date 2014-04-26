package pe.edu.pucp.algorithms.sorting.algs.impl;

import pe.edu.pucp.algorithms.sorting.algs.BaseSorter;

/**
 * Implementation of Insertion Sort algorithm. Based on the implementation
 * described in Robert Sedgewick's Algorithm book.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 * @param <T>
 *            Type of the array to be sorted
 */
public class InsertionSorter<T extends Comparable<T>> extends BaseSorter<T> {

    public InsertionSorter(Class<T> clazz, T[] data) {
        super(clazz, data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see pe.edu.pucp.algorithms.sorting.algs.BaseSorter#sortData()
     */
    @Override
    public void sortData() {
        for (int currentIndex = 0; currentIndex < getLength(); currentIndex++) {
            T currentItem = getDataAtIndex(currentIndex);
            int auxIndex = currentIndex;
            while (auxIndex > 0
                    && getDataAtIndex(auxIndex - 1).compareTo(currentItem) >= 0) {
                setDataAtIndex(auxIndex, getDataAtIndex(auxIndex - 1));
                --auxIndex;
            }
            setDataAtIndex(auxIndex, currentItem);
        }
    }

}
