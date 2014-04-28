package pe.edu.pucp.algorithms.sorting.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.time.Year;
import org.jfree.data.xy.IntervalXYDataset;

import pe.edu.pucp.algorithms.sorting.DLinkedList.DLList;
import pe.edu.pucp.algorithms.sorting.algs.DLListChangedListerner;

/**
 * Frame that shows the sorting algorithm process.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * @author Tomáš Apeltauer
 * 
 */
@SuppressWarnings("serial")
public class AlgorithmAnimationFrame extends JFrame implements DLListChangedListerner<CustomTimeSeriesDataItem>, ActionListener {

    private static final String X_LABEL = "�ndice";
    private static final String Y_LABEL = "Tama�o";
    private static final String BAR_LABEL = "Elemento en el arreglo";

    private TimeSeries timeSeries;
    private String frameTitle;
    private int sleepTime;
    private DLList<TimeSeriesDataItem> dataToSort;
    private JPanel MainPanel, AnimationPanel;
    
    private JButton Bubble,Insertion, Merge, Quick, btMas;
    private JLabel lblAlgoritmosDeOrdenamiento;
    private JMenuItem mntmSalir;
    
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
    public AlgorithmAnimationFrame(String frameTitle, DLList<TimeSeriesDataItem> dataToSort, int sleepTime) {
        super(frameTitle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBounds(50, 50, 920, 500);
        this.frameTitle = frameTitle;
        this.sleepTime = sleepTime;
        this.dataToSort = dataToSort;
        setResizable(false);
        setVisible(true);
        MainPanel = new JPanel();
        setContentPane(MainPanel);
        MainPanel.setBackground(Color.LIGHT_GRAY);
        MainPanel.setBounds(0, 0, 1000, 600);
        MainPanel.setLayout(null);

 
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1000, 25);
        MainPanel.add(menuBar);
        
        JMenu mnArchivo = new JMenu("Archivo");
        menuBar.add(mnArchivo);
        
        mntmSalir = new JMenuItem("Salir");
        mnArchivo.add(mntmSalir);

        JLabel lblVelocidad = new JLabel("Velocidad");
        lblVelocidad.setBounds(614, 309, 118, 32);
        MainPanel.add(lblVelocidad);
        
        JLabel lblMenos = new JLabel("menos");
        lblMenos.setBounds(716, 414, 46, 14);
        MainPanel.add(lblMenos);
        
        creaBotones();
        lblAlgoritmosDeOrdenamiento = new JLabel("Algoritmos de Ordenamiento Disponibles");
        lblAlgoritmosDeOrdenamiento.setBounds(614, 36, 317, 43);
        MainPanel.add(lblAlgoritmosDeOrdenamiento);
        
        AnimationPanel = createDemoPanel();
        AnimationPanel.setSize(new Dimension(555, 383));
        MainPanel.add(AnimationPanel);
    }
    
    private void creaBotones(){
        Bubble = new JButton("Bubble");
        Bubble.setBounds(672, 114, 159, 25);
        Bubble.addActionListener(this);
        MainPanel.add(Bubble);
        
        Insertion = new JButton("Insertion");
        Insertion.setBounds(672, 169, 159, 25);
        Insertion.addActionListener(this);
        MainPanel.add(Insertion);
        
        Merge = new JButton("Merge");
        Merge.setBounds(672, 222, 159, 25);
        Merge.addActionListener(this);
        MainPanel.add(Merge);
        
        Quick = new JButton("Quick");
        Quick.setBounds(672, 273, 159, 25);
        Quick.addActionListener(this);
        MainPanel.add(Quick);
        
        btMas = new JButton("");
        btMas.setBounds(716, 352, 45, 43);
        btMas.setIcon(new ImageIcon(AlgorithmAnimationFrame.class.getResource("/images/mas.png")));
        MainPanel.add(btMas);
    	
    }

    private JFreeChart createChart(IntervalXYDataset intervalxydataset) {
        JFreeChart jfreechart = ChartFactory.createXYBarChart(frameTitle, X_LABEL, true, Y_LABEL, intervalxydataset, PlotOrientation.VERTICAL, true, false, false);
        jfreechart.setBackgroundPaint(Color.white);
        
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        XYBarRenderer xybarrenderer = (XYBarRenderer) xyplot.getRenderer();
        StandardXYToolTipGenerator standardxytooltipgenerator = new StandardXYToolTipGenerator("{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0"));
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

    private IntervalXYDataset startDataSet(DLList<TimeSeriesDataItem> dataAsList) {
        timeSeries = new TimeSeries(BAR_LABEL, X_LABEL, "Count");
        for (TimeSeriesDataItem dataItem : dataAsList) {
            timeSeries.add(dataItem);
        }
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeSeries);
        return timeseriescollection;
    }

    /**
     * Change the data set of the Frame, that redraws the entire frame.
     * 
     * @param dataAsArray
     *            New data to show.
     */
    public void updateDataSet(DLList<CustomTimeSeriesDataItem> data) {
        timeSeries.clear();
        int currentItem = 1;
        for (CustomTimeSeriesDataItem dataItem : data) {
        	timeSeries.addOrUpdate(new Year(currentItem), dataItem.getValue());
            currentItem++;
		}
//        for (CustomTimeSeriesDataItem dataItem : dataAsArray) {
//            timeSeries.addOrUpdate(new Year(currentItem), dataItem.getValue());
//            currentItem++;
//        }
    }

    public JPanel createDemoPanel() {
        IntervalXYDataset dataSet = startDataSet(dataToSort);
        JFreeChart chart = createChart(dataSet);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Color.LIGHT_GRAY);
        chartPanel.setLocation(22, 64);
        return chartPanel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pe.edu.pucp.algorithms.sorting.algs.ArrayChangeListener#arrayChanged(T[])
     */
    @Override
    public void listChanged(final DLList<CustomTimeSeriesDataItem> data) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                updateDataSet(data);
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
    	return "";
    	/*
        StringBuffer buffer = new StringBuffer();
        buffer.append(ARRAY_SIZE_LABEL + dataToSort.length + "\n");
        buffer.append(SLEEP_TIME_LABEL + sleepTime);
        return buffer.toString();
        */
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}