package pe.edu.pucp.algorithms.sorting.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.time.Year;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;

import pe.edu.pucp.algorithms.sorting.algs.ArrayChangeListener;

/**
 * Frame that shows the sorting algorithm process.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 */
public class AlgorithmAnimationFrame extends ApplicationFrame implements
        ArrayChangeListener<CustomTimeSeriesDataItem> {

    private static final long serialVersionUID = -6451446062734426445L;

    private static final String SLEEP_TIME_LABEL = "Tiempo de pausa (milisegundos): ";
    private static final String ARRAY_SIZE_LABEL = "Número de elementos: ";
    private static final String SUB_TITLE = "PUCP - Maestría en Informática";
    private static final String X_LABEL = "Índice";
    private static final String Y_LABEL = "Tamaño";
    private static final String BAR_LABEL = "Elemento en el arreglo";

    private TimeSeries timeSeries;
    private String frameTitle;
    private int sleepTime;
    private TimeSeriesDataItem[] dataToSort;

    /**
     * Gets an animation frame instance.
     * 
     * @param frameTitle
     *            Title of the frame.
     * @param dataToSort
     *            Data to sort.
     * @param sleepTime
     *            Delay time for sorting.
     */
    public AlgorithmAnimationFrame(String frameTitle,
            TimeSeriesDataItem[] dataToSort, int sleepTime) {
        super(frameTitle);
        this.frameTitle = frameTitle;
        this.sleepTime = sleepTime;
        this.dataToSort = dataToSort;
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    private JFreeChart createChart(IntervalXYDataset intervalxydataset) {
        JFreeChart jfreechart = ChartFactory.createXYBarChart(frameTitle,
                X_LABEL, true, Y_LABEL, intervalxydataset,
                PlotOrientation.VERTICAL, true, false, false);
        jfreechart.addSubtitle(new TextTitle(SUB_TITLE, new Font("Dialog", 2,
                10)));
        jfreechart.setBackgroundPaint(Color.white);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        XYBarRenderer xybarrenderer = (XYBarRenderer) xyplot.getRenderer();
        StandardXYToolTipGenerator standardxytooltipgenerator = new StandardXYToolTipGenerator(
                "{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat(
                        "0"));
        xybarrenderer.setBaseToolTipGenerator(standardxytooltipgenerator);
        xybarrenderer.setMargin(0.10000000000000001D);
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setRangeGridlinePaint(Color.white);
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        dateaxis.setLowerMargin(0.01D);
        dateaxis.setUpperMargin(0.01D);
        return jfreechart;
    }

    private IntervalXYDataset startDataSet(TimeSeriesDataItem[] dataAsArray) {
        timeSeries = new TimeSeries(BAR_LABEL, X_LABEL, "Count");
        for (TimeSeriesDataItem dataItem : dataAsArray) {
            timeSeries.add(dataItem);
        }
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(
                timeSeries);
        return timeseriescollection;
    }

    /**
     * Change the data set of the Frame, that redraws the entire frame.
     * 
     * @param dataAsArray
     *            New data to show.
     */
    public void updateDataSet(CustomTimeSeriesDataItem[] dataAsArray) {
        timeSeries.clear();
        int currentItem = 1;
        for (CustomTimeSeriesDataItem dataItem : dataAsArray) {
            timeSeries.addOrUpdate(new Year(currentItem), dataItem.getValue());
            currentItem++;
        }
    }

    public JPanel createDemoPanel() {
        IntervalXYDataset dataSet = startDataSet(dataToSort);
        JFreeChart chart = createChart(dataSet);
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pe.edu.pucp.algorithms.sorting.algs.ArrayChangeListener#arrayChanged(T[])
     */
    @Override
    public void arrayChanged(final CustomTimeSeriesDataItem[] dataArray) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                updateDataSet(dataArray);
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.Component#toString()
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(ARRAY_SIZE_LABEL + dataToSort.length + "\n");
        buffer.append(SLEEP_TIME_LABEL + sleepTime);
        return buffer.toString();
    }

}