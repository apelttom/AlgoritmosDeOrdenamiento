package pe.edu.pucp.algorithms.sorting.algs.impl;

import pe.edu.pucp.algorithms.sorting.algs.BaseSorter;

/**
 * Implementation of the Odd-Even Transposition Sort Algorithm. Based on
 * http://home.westman.wave.ca/~rhenry/sort/src/OETransSortAlgorithm.java.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 * @param <T>
 *            Type of the array to be sorted
 */
public class OddEvenTranspositionSorter<T extends Comparable<T>> extends
        BaseSorter<T> {

    public OddEvenTranspositionSorter(Class<T> clazz, T[] data) {
        super(clazz, data);
    }

    @Override
    public void sortData() {
        for (int i = 0; i < getLength() / 2; i++) {
            for (int j = 0; j + 1 < getLength(); j += 2) {
                if (getDataAtIndex(j).compareTo(getDataAtIndex(j + 1)) > 0) {
                    exchange(j, j + 1);
                }
            }

            for (int j = 1; j + 1 < getLength(); j += 2) {
                if (getDataAtIndex(j).compareTo(getDataAtIndex(j + 1)) > 0) {
                    exchange(j, j + 1);
                }
            }
        }
    }
}
