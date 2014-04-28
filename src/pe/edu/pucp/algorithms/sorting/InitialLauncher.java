package pe.edu.pucp.algorithms.sorting;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.jfree.data.time.Year;

import pe.edu.pucp.algorithms.sorting.algs.BaseSorter;
import pe.edu.pucp.algorithms.sorting.algs.SorterFactory;
import pe.edu.pucp.algorithms.sorting.algs.SortingAlgorithm;
import pe.edu.pucp.algorithms.sorting.graph.AlgorithmAnimationFrame;
import pe.edu.pucp.algorithms.sorting.graph.CustomTimeSeriesDataItem;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("serial")
public class InitialLauncher extends JFrame implements ActionListener {

	private JPanel MainContainer;
	private JTextField txtElements;
	private JButton btnContinuar;
	private JMenuItem New, Exit;
	

    private static final String INVALID_INPUT = "ERROR: El valor ingresado es inválido.\n";
    private static final String SELECT_SORTING_ALGORITHM = "Seleccione el algoritmo de ordenamiento (del 0 al 5):";
    private static final String EXECUTION_TIME_MESSAGE = "Tiempo de ejecución del algoritmo (segundos): ";
    private static final String SELECT_NUMBER_OF_ITEMS = "Seleccione el número de elementos a ordenar:";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitialLauncher frame = new InitialLauncher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InitialLauncher() {
		super("Sorting Algorithms");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 377);
		MainContainer = new JPanel();
		MainContainer.setBackground(Color.LIGHT_GRAY);
		MainContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MainContainer);
		MainContainer.setLayout(null);
		
		createMenuBar();
		
		txtElements = new JTextField();
		txtElements.setBounds(42, 188, 181, 25);
		MainContainer.add(txtElements);
		txtElements.setColumns(10);
		
		JLabel tittle = new JLabel("Bienvenido a Sorting Algoritms");
		tittle.setFont(new Font("Arial", Font.BOLD, 15));
		tittle.setBounds(110, 36, 228, 63);
		MainContainer.add(tittle);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(271, 272, 165, 48);
		btnContinuar.addActionListener(this);
		MainContainer.add(btnContinuar);
		
		JLabel lblDigiteElNmero = new JLabel("Digite el n\u00FAmero de elementos que se desean ordenar");
		lblDigiteElNmero.setBounds(42, 137, 321, 33);
		MainContainer.add(lblDigiteElNmero);
		runAnimation();
	}
	
	private void createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 590, 25);
		MainContainer.add(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		New = new JMenuItem("Nuevo");
		mnArchivo.add(New);
		
		Exit = new JMenuItem("Salir");
		mnArchivo.add(Exit);
		
	}
	 @SuppressWarnings("unchecked")
    private void runAnimation(){
        SortingAlgorithm selectedAlgoritm = getSelectedAlgorithm();  // Algorithm Input
        int sleepTime = 10; // Thread Sleep
        CustomTimeSeriesDataItem[] dataToSort = getDataToSort(); // This fill the data 
        
        // creates the JPanel that animates the sorting algorithm
        AlgorithmAnimationFrame algorithmAnimationFrame = new AlgorithmAnimationFrame(selectedAlgoritm.name(), dataToSort); 
        algorithmAnimationFrame.setVisible(true);
        BaseSorter<CustomTimeSeriesDataItem> sorter = SorterFactory.getSorter(CustomTimeSeriesDataItem.class, dataToSort, selectedAlgoritm);
        sorter.setArrayChangeListener(algorithmAnimationFrame);

        long startTime = System.nanoTime();

        String message = sorter.sortAndShowResults();
        System.out.println(message);
        
        long endTime = System.nanoTime();
        double durationInSeconds = TimeUnit.SECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        System.out.println(EXECUTION_TIME_MESSAGE + durationInSeconds);
    }
	
    private static SortingAlgorithm getSelectedAlgorithm() {
        SortingAlgorithm selectedAlgoritm = null;
        while (selectedAlgoritm == null) {
            System.out.println(SELECT_SORTING_ALGORITHM);
            
            // muestra los algoritmos posibles a ejecutar
            for (SortingAlgorithm algorithm : SortingAlgorithm.values()) {
                String menuItem = algorithm.ordinal() + ": " + algorithm.name();
                System.out.println(menuItem);
            }
            
            
            try {
                Scanner keyBoardInput = new Scanner(System.in);
                int algorithmOrdinal = keyBoardInput.nextInt();
                System.out.println();
                
                // Asigna en esta variable el algoritmo a ejecutar
                selectedAlgoritm = SortingAlgorithm.values()[algorithmOrdinal];
                
            } catch (Exception e) {
                System.out.println(INVALID_INPUT);
            }
        }
        return selectedAlgoritm;
    }

    private static CustomTimeSeriesDataItem[] getDataToSort() {
    	// this method fill the entire array of numbers
        int numberOfItems = 50;
        /*10
        while (numberOfItems == 0) {
            try {
                System.out.println(SELECT_NUMBER_OF_ITEMS);
                Scanner keyBoardInput = new Scanner(System.in);
                numberOfItems = keyBoardInput.nextInt();
                System.out.println();
            } catch (Exception e) {
                System.out.println(INVALID_INPUT);
            }
        }
        */
        List<CustomTimeSeriesDataItem> dataToSort = new ArrayList<CustomTimeSeriesDataItem>();
        Random random = new Random();
        for (int i = 1; i <= numberOfItems; i++) {
            dataToSort.add(new CustomTimeSeriesDataItem(new Year(i),
                    new Integer(random.nextInt(100) + 1)));
        }
        return dataToSort.toArray(new CustomTimeSeriesDataItem[dataToSort.size()]);
    }
    


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnContinuar){
			//System.out.println("next frame");
			//System.out.println(txtElements.getText());
			dispose();
			runAnimation();
			
		}
		
	}
}
