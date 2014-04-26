package pe.edu.pucp.algorithms.sorting.algs.impl;

/**
 * Implementation of Top-Down Merge Sort algorithm. Based on the implementation
 * described in Robert Sedgewick's Algorithm book.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 * @param <T>
 *            Type of the array to be sorted
 */
public class TopDownMergeSorter<T extends Comparable<T>> extends MergeSorter<T> {

    public TopDownMergeSorter(Class<T> clazz, T[] data) {
        super(clazz, data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see pe.edu.pucp.algorithms.sorting.algs.BaseSorter#sortData()
     */
    @Override
    public void sortData() {
        sortData(0, getLength() - 1);
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
        if (higherIndex <= lowerIndex) {
            return;
        }
        int midIndex = lowerIndex + (higherIndex - lowerIndex) / 2;
        sortData(lowerIndex, midIndex);
        sortData(midIndex + 1, higherIndex);
        merge(lowerIndex, midIndex, higherIndex);
    }

}
