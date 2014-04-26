package pe.edu.pucp.algorithms.sorting.algs.impl;

/**
 * Implementation of Bottom-Up Merge Sort algorithm. Based on the implementation
 * described in Robert Sedgewick's Algorithm book.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 * @param <T>
 *            Type of the array to be sorted
 */
public class BottomUpMergeSorter<T extends Comparable<T>> extends
        MergeSorter<T> {

    public BottomUpMergeSorter(Class<T> clazz, T[] data) {
        super(clazz, data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see pe.edu.pucp.algorithms.sorting.algs.BaseSorter#sortData()
     */
    @Override
    public void sortData() {
        for (int subArraySize = 1; subArraySize < getLength(); subArraySize = subArraySize
                + subArraySize) {
            for (int subArrayIndex = 0; subArrayIndex < getLength()
                    - subArraySize; subArrayIndex += subArraySize
                    + subArraySize) {
                int lowerIndex = subArrayIndex;
                int midIndex = subArrayIndex + subArraySize - 1;
                int higherIndex = Math.min(subArrayIndex + subArraySize
                        + subArraySize - 1, getMaxIndex());
                merge(lowerIndex, midIndex, higherIndex);
            }
        }
    }

}
