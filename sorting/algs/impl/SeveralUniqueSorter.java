package pe.edu.pucp.algorithms.sorting.algs.impl;

import pe.edu.pucp.algorithms.sorting.algs.BaseSorter;

/**
 * Implementation of the Several Unique Sort Algorithm. Based on the
 * implementation found in:
 * http://home.westman.wave.ca/~rhenry/sort/src/SeveralUniqueSortAlgorithm.java
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 * @param <T>
 *            Type of the array to be sorted
 * 
 */
public class SeveralUniqueSorter<T extends Comparable<T>> extends BaseSorter<T> {

    public SeveralUniqueSorter(Class<T> clazz, T[] data) {
        super(clazz, data);
    }

    @Override
    public void sortData() {
        int swapIndex = 0;
        int currentPosition = -1;

        int currentScanFinalIndex = getMaxIndex();
        do {
            currentPosition = -1;
            T highestValueItem = null;

            while (currentPosition < currentScanFinalIndex) {
                currentPosition++;
                T currentItem = getDataAtIndex(currentPosition);
                if (highestValueItem != null
                        && currentItem.compareTo(highestValueItem) < 0) {
                    setDataAtIndex(swapIndex, currentItem);
                    swapIndex++;
                    setDataAtIndex(currentPosition, highestValueItem);
                } else if (highestValueItem == null
                        || currentItem.compareTo(highestValueItem) > 0) {
                    swapIndex = currentPosition;
                    highestValueItem = getDataAtIndex(currentPosition);
                }
            }
            currentScanFinalIndex = swapIndex - 1;
        } while (currentPosition >= swapIndex);
    }
}
