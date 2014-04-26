package pe.edu.pucp.algorithms.sorting.algs.impl;

import pe.edu.pucp.algorithms.sorting.algs.BaseSorter;

/**
 * Implementation of Insertion Sort algorithm. Based on
 * http://www.cs.ubc.ca/~harrison/Java/CombSort11Algorithm.java.html.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 * @param <T>
 *            Type of the array to be sorted
 */
public class CombSorter<T extends Comparable<T>> extends BaseSorter<T> {

    private final float SHRINK_FACTOR = (float) 1.3;

    public CombSorter(Class<T> clazz, T[] data) {
        super(clazz, data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see pe.edu.pucp.algorithms.sorting.algs.BaseSorter#sortData()
     */
    @Override
    public void sortData() {
        boolean flipped = false;
        int gap = getLength();
        do {
            gap = (int) (gap / SHRINK_FACTOR);
            if (gap == 0) {
                gap = 1;
            } else if (gap == 9 || gap == 10) {
                gap = 11;
            }
            flipped = false;
            int top = getLength() - gap;
            for (int i = 0; i < top; i++) {
                int j = i + gap;
                T elementAtI = getDataAtIndex(i);
                T elementAtJ = getDataAtIndex(j);
                if (elementAtI.compareTo(elementAtJ) > 0) {
                    exchange(i, j);
                    flipped = true;
                }
            }
        } while (flipped || (gap > 1));
    }

}
