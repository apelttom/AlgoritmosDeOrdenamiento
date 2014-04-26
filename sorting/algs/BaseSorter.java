package pe.edu.pucp.algorithms.sorting.algs;

/**
 * Base class for Sorting Algorithms, with several helper methods that every
 * algorithm of the program needs.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 * @param <T>
 *            Type of the array to be sorted
 */
@SuppressWarnings("unchecked")
public abstract class BaseSorter<T extends Comparable> {

    private static final String FAIL_MESSAGE = "ERROR: La ejecución del algoritmo no ordenó el set de datos.";
    private static final String SUCCESS_MESSAGE = "El set de Datos se encuentra ordenado.";
    private static final String HORIZONTAL_LINE = "==============================\n";
    private static final String EXECUTION_FINISHED = "Ejecución finalizada\n";

    protected Class<T> clazz;
    private T[] data;
    private ArrayChangeListener<T> arrayChangeListener;

    /**
     * Gets a Sorter instance.
     * 
     * @param clazz
     *            Class of the elements to sort.
     * @param data
     *            Data that the algorithm must sort.
     */
    public BaseSorter(Class<T> clazz, T[] data) {
        this.data = data;
        this.clazz = clazz;
    }

    /**
     * Operation that applies the algorithm and sorts the data.
     */
    public abstract void sortData();

    /**
     * Applies the sorting algorithm to the data and shows a message.
     * 
     * @return Message that informs if sorting was successfull or not.
     */
    public String sortAndShowResults() {
        sortData();
        StringBuffer buffer = new StringBuffer();
        buffer.append(EXECUTION_FINISHED);
        buffer.append(HORIZONTAL_LINE);
        if (!isSorted()) {
            buffer.append(FAIL_MESSAGE);
        } else {
            buffer.append(SUCCESS_MESSAGE);
        }
        return buffer.toString();

    }

    /**
     * Changes the position of two items of the array to sort.
     * 
     * @param index
     *            Index of one item.
     * @param anotherIndex
     *            Index of another item.
     */
    protected void exchange(int index, int anotherIndex) {
        T itemAtIndex = data[index];
        setDataAtIndex(index, data[anotherIndex]);
        setDataAtIndex(anotherIndex, itemAtIndex);
    }

    /**
     * Evaluates if the sorting was successfull or not.
     * 
     * @return True if the sorting succeed and false if not.
     */
    protected boolean isSorted() {
        for (int i = 1; i < data.length; i++) {
            if (data[i].compareTo(data[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns number of items to sort.
     * 
     * @return Number of items to sort.
     */
    protected int getLength() {
        return data.length;
    }

    /**
     * Returns a data element at the desired position.
     * 
     * @param index
     *            Index of the element.
     * @return The element.
     */
    protected T getDataAtIndex(int index) {
        return data[index];
    }

    /**
     * Sets a data item at a particular index, and notifies listeners of the
     * change.
     * 
     * @param index
     *            Index where to put the item.
     * @param item
     *            The item to move.
     */
    protected void setDataAtIndex(int index, T item) {
        data[index] = item;
        arrayChangeListener.arrayChanged(data);
    }

    /**
     * Returns the maximum index of the array.
     * 
     * @return Max index.
     */
    protected int getMaxIndex() {
        return data.length - 1;
    }

    /**
     * Sets the change listener for the sorter algorithms.
     * 
     * @param arrayChangeListener
     *            Listener instance.
     */
    public void setArrayChangeListener(ArrayChangeListener arrayChangeListener) {
        this.arrayChangeListener = arrayChangeListener;
    }

}
