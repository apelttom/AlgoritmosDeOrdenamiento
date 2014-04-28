package pe.edu.pucp.algorithms.sorting.algs;

import pe.edu.pucp.algorithms.sorting.DLinkedList.DLList;
import pe.edu.pucp.algorithms.sorting.algs.impl.BottomUpMergeSorter;
import pe.edu.pucp.algorithms.sorting.algs.impl.BubbleSorter;
import pe.edu.pucp.algorithms.sorting.algs.impl.CombSorter;
import pe.edu.pucp.algorithms.sorting.algs.impl.InsertionSorter;
import pe.edu.pucp.algorithms.sorting.algs.impl.OddEvenTranspositionSorter;
import pe.edu.pucp.algorithms.sorting.algs.impl.QuickSorter;
import pe.edu.pucp.algorithms.sorting.algs.impl.SeveralUniqueSorter;
import pe.edu.pucp.algorithms.sorting.algs.impl.TopDownMergeSorter;

/**
 * Factory for algorithm implementations.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 */
public class SorterFactory {

    /**
     * Gets an instance of a Sorting algorithm.
     * 
     * @param <T>
     *            Class of the items to sort.
     * @param clazz
     *            Class of the items to sort.
     * @param data
     *            Data array to sort.
     * @param algorithm
     *            The algorithm to use.
     * @return
     */
    public static <T extends Comparable<T>> BaseSorter<T> getSorter(
            Class<T> clazz, DLList<T> data, SortingAlgorithm algorithm) {
        BaseSorter<T> sorter = null;

        switch (algorithm) {
        case INSERTION:
            sorter = new InsertionSorter<T>(clazz, data);
            break;
        case BUBBLE:
            sorter = new BubbleSorter<T>(clazz, data);
            break;
        case QUICK:
            sorter = new QuickSorter<T>(clazz, data);
            break;
        case TOPDOWN_MERGE:
            sorter = new TopDownMergeSorter<T>(clazz, data);
            break;
        }
        return sorter;
    }
}
