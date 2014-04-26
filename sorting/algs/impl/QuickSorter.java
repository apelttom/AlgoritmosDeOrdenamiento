package pe.edu.pucp.algorithms.sorting.algs.impl;

import pe.edu.pucp.algorithms.sorting.algs.BaseSorter;

/**
 * Implementation of Quick Sort algorithm. Based on the implementation described
 * in Robert Sedgewick's Algorithm book.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 * @param <T>
 *            Type of the array to be sorted
 */
public class QuickSorter<T extends Comparable<T>> extends BaseSorter<T> {

    public QuickSorter(Class<T> clazz, T[] data) {
        super(clazz, data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see pe.edu.pucp.algorithms.sorting.algs.BaseSorter#sortData()
     */
    @Override
    public void sortData() {
        sortData(0, getMaxIndex());
    }

    /**
     * Sorting method that allows recursion.
     * 
     * @param lowerIndex
     *            Lower index.
     * @param higherIndex
     *            Higher index.
     */
    private void sortData(int lowerIndex, int higherIndex) {
        if (higherIndex < lowerIndex) {
            return;
        }
        int pivotPosition = partition(lowerIndex, higherIndex);
        sortData(lowerIndex, pivotPosition - 1);
        sortData(pivotPosition + 1, higherIndex);
    }

    /**
     * Partiion method implementation for QuickSort. It assures the statement
     * that every item on the left of the pivot is less than it and every item
     * to the right is bigger.
     * 
     * @param lowerIndex
     * @param higherIndex
     * @return
     */
    private int partition(int lowerIndex, int higherIndex) {
        int leftScanner = lowerIndex;
        int rightScanner = higherIndex + 1;
        T pivotItem = getDataAtIndex(lowerIndex);
        while (true) {
            while (leftScanner < (getMaxIndex() - 1)
                    && getDataAtIndex(++leftScanner).compareTo(pivotItem) < 0) {
                if (leftScanner == higherIndex) {
                    break;
                }
            }
            while (rightScanner > 0
                    && pivotItem.compareTo(getDataAtIndex(--rightScanner)) < 0) {
                if (rightScanner == lowerIndex) {
                    break;
                }
            }

            if (leftScanner >= rightScanner) {
                break;
            }
            exchange(leftScanner, rightScanner);
        }
        exchange(lowerIndex, rightScanner);
        return rightScanner;
    }

}
