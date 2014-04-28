package pe.edu.pucp.algorithms.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.jfree.data.time.Year;
import org.jfree.ui.RefineryUtilities;

import pe.edu.pucp.algorithms.sorting.algs.BaseSorter;
import pe.edu.pucp.algorithms.sorting.algs.SorterFactory;
import pe.edu.pucp.algorithms.sorting.algs.SortingAlgorithm;
import pe.edu.pucp.algorithms.sorting.graph.AlgorithmAnimationFrame;
import pe.edu.pucp.algorithms.sorting.graph.CustomTimeSeriesDataItem;

/**
 * Launcher class for the Algorithm Demo Program. It's a console application
 * with the responsability of instantiating the Algorithm frame.
 * 
 * @author Carlos Gavidia (cgavidia@acm.org)
 * 
 */
public class SortingAlgorithmDemoLauncher {

    private static final String INVALID_INPUT = "ERROR: El valor ingresado es inválido.\n";
    private static final String SELECT_SORTING_ALGORITHM = "Seleccione el algoritmo de ordenamiento (del 0 al 5):";
    private static final String EXECUTION_TIME_MESSAGE = "Tiempo de ejecución del algoritmo (segundos): ";
    private static final String SELECT_NUMBER_OF_ITEMS = "Seleccione el número de elementos a ordenar:";

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        SortingAlgorithm selectedAlgoritm = getSelectedAlgorithm();  // Algorithm Input
        int sleepTime = 10; // Thread Sleep
        CustomTimeSeriesDataItem[] dataToSort = getDataToSort(); // This fill the data 
        
        // creates the JPanel that animates the sorting algorithm
        AlgorithmAnimationFrame algorithmAnimationFrame = new AlgorithmAnimationFrame(selectedAlgoritm.name(), dataToSort, sleepTime); 
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
        int numberOfItems = 0;
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
        List<CustomTimeSeriesDataItem> dataToSort = new ArrayList<CustomTimeSeriesDataItem>();
        Random random = new Random();
        for (int i = 1; i <= numberOfItems; i++) {
            dataToSort.add(new CustomTimeSeriesDataItem(new Year(i),
                    new Integer(random.nextInt(100) + 1)));
        }
        return dataToSort.toArray(new CustomTimeSeriesDataItem[dataToSort
                .size()]);
    }
}
