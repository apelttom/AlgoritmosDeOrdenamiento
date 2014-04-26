package pe.edu.pucp.algorithms.sorting.algs;

/**
 * Listener interface for the Observer Pattern implementation. Every class that
 * implements the interface will be notified when the elements in the array
 * change their position.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 * @param <T>
 *            Type of the elements of the array.
 */
@SuppressWarnings("unchecked")
public interface ArrayChangeListener<T extends Comparable> {

    public void arrayChanged(T[] dataArray);
}
