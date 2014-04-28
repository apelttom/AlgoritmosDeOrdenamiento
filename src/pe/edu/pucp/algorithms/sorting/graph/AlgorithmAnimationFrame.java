package pe.edu.pucp.algorithms.sorting.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
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

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Frame that shows the sorting algorithm process.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 */
public class AlgorithmAnimationFrame extends JFrame implements ArrayChangeListener<CustomTimeSeriesDataItem>, ActionListener {

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
    private JPanel MainPanel, AnimationPanel;
    
    private JButton Bubble,Insertion, Merge, Quick, btMas;
    private JLabel lblAlgoritmosDeOrdenamiento;
    
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
    public AlgorithmAnimationFrame(String frameTitle, TimeSeriesDataItem[] dataToSort, int sleepTime) {
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

        JLabel lblVelocidad = new JLabel("Velocidad");
        lblVelocidad.setBounds(614, 309, 118, 32);
        MainPanel.add(lblVelocidad);
        

        
        JLabel lblMenos = new JLabel("menos");
        lblMenos.setBounds(716, 414, 46, 14);
        MainPanel.add(lblMenos);
        
        creaBotones();
        lblAlgoritmosDeOrdenamiento = new JLabel("Algoritmos de Ordenamiento Disponibles");
        lblAlgoritmosDeOrdenamiento.setBounds(587, 36, 317, 43);
        MainPanel.add(lblAlgoritmosDeOrdenamiento);
        
        AnimationPanel = createDemoPanel();
        AnimationPanel.setSize(new Dimension(555, 383));
        MainPanel.add(AnimationPanel);
    }
    
    private void creaBotones(){
        Bubble = new JButton("Bubble");
        Bubble.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        Bubble.setBounds(672, 114, 159, 25);
        MainPanel.add(Bubble);
        
        Insertion = new JButton("Insertion");
        Insertion.setBounds(672, 169, 159, 25);
        MainPanel.add(Insertion);
        
        Merge = new JButton("Merge");
        Merge.setBounds(672, 222, 159, 25);
        MainPanel.add(Merge);
        
        Quick = new JButton("Quick");
        Quick.setBounds(672, 273, 159, 25);
        MainPanel.add(Quick);
        
        btMas = new JButton("");
        btMas.setBounds(716, 352, 45, 43);
        //(new ImageIcon("src/Imagenes/Horse_1.gif")
        btMas.setIcon(new ImageIcon(AlgorithmAnimationFrame.class.getResource("/images/mas.png")));
        MainPanel.add(btMas);
    	
    }

    private JFreeChart createChart(IntervalXYDataset intervalxydataset) {
        JFreeChart jfreechart = ChartFactory.createXYBarChart(frameTitle, X_LABEL, true, Y_LABEL, intervalxydataset, PlotOrientation.VERTICAL, true, false, false);
        jfreechart.addSubtitle(new TextTitle(SUB_TITLE, new Font("Dialog", 2, 10)));
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

    private IntervalXYDataset startDataSet(TimeSeriesDataItem[] dataAsArray) {
        timeSeries = new TimeSeries(BAR_LABEL, X_LABEL, "Count");
        for (TimeSeriesDataItem dataItem : dataAsArray) {
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
        chartPanel.setBackground(Color.LIGHT_GRAY);
        chartPanel.setLocation(25, 77);
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