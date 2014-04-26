package pe.edu.pucp.algorithms.sorting.graph;

import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeriesDataItem;

/**
 * Custom data item for the algorithm frame, that compares items using value
 * instead of time.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 */
public class CustomTimeSeriesDataItem extends TimeSeriesDataItem {

    private static final long serialVersionUID = -3099867917159200883L;

    public CustomTimeSeriesDataItem(RegularTimePeriod period, double value) {
        super(period, value);
    }

    @Override
    public int compareTo(Object o1) {
        int result = 1;

        if (o1 instanceof CustomTimeSeriesDataItem) {
            CustomTimeSeriesDataItem datapair = (CustomTimeSeriesDataItem) o1;
            if (getValue().longValue() > datapair.getValue().longValue()) {
                result = 1;
            } else if (getValue().longValue() < datapair.getValue().longValue()) {
                result = -1;
            } else {
                result = 0;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Value: " + getValue();
    }
}
