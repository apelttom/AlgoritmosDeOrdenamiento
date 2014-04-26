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
    private static final String HORIZONTAL_LINE = "==============================";
    private static final String TITLE = "Algoritmo de Ordenamiento";
    private static final String APPLICATION_TITLE = "Demo: Algoritmos de Ordenamiento";
    private static final String SELECT_SORTING_ALGORITHM = "Seleccione el algoritmo de ordenamiento (del 0 al 5):";
    private static final String EXECUTION_TIME_MESSAGE = "Tiempo de ejecución del algoritmo (segundos): ";
    private static final String SELECT_SLEEP_TIME = "Seleccione el tiempo de pausa para cada paso del algoritmo (en milisegundos):";
    private static final String SELECT_NUMBER_OF_ITEMS = "Seleccione el número de elementos a ordenar:";
    private static final String STARTING_EXECUTION = "Ejecución en proceso:";
    private static final String ALGORITHM_LABEL = "Algoritmo: ";

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        SortingAlgorithm selectedAlgoritm = getSelectedAlgorithm();
        int sleepTime = getSleepTime();
        CustomTimeSeriesDataItem[] dataToSort = getDataToSort();

        AlgorithmAnimationFrame algorithmAnimationFrame = new AlgorithmAnimationFrame(
                TITLE + ": " + selectedAlgoritm.name(), dataToSort, sleepTime);
        algorithmAnimationFrame.pack();
        RefineryUtilities.centerFrameOnScreen(algorithmAnimationFrame);
        algorithmAnimationFrame.setVisible(true);
        BaseSorter<CustomTimeSeriesDataItem> sorter = SorterFactory.getSorter(
                CustomTimeSeriesDataItem.class, dataToSort, selectedAlgoritm);
        sorter.setArrayChangeListener(algorithmAnimationFrame);

        long startTime = System.nanoTime();
        System.out.println(STARTING_EXECUTION);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(ALGORITHM_LABEL + selectedAlgoritm.name());
        System.out.println(algorithmAnimationFrame);
        System.out.println();

        String message = sorter.sortAndShowResults();
        System.out.println(message);
        long endTime = System.nanoTime();
        double durationInSeconds = TimeUnit.SECONDS.convert(
                endTime - startTime, TimeUnit.NANOSECONDS);
        System.out.println(EXECUTION_TIME_MESSAGE + durationInSeconds);
    }

    private static int getSleepTime() {
        int sleepTime = 0;
        while (sleepTime == 0) {
            try {
                System.out.println(SELECT_SLEEP_TIME);
                Scanner keyBoardInput = new Scanner(System.in);
                sleepTime = keyBoardInput.nextInt();
            } catch (Exception e) {
                System.out.println(INVALID_INPUT);
            }
        }
        return sleepTime;
    }

    private static SortingAlgorithm getSelectedAlgorithm() {
        SortingAlgorithm selectedAlgoritm = null;
        System.out.println(APPLICATION_TITLE);
        System.out.println(HORIZONTAL_LINE);
        while (selectedAlgoritm == null) {
            System.out.println(SELECT_SORTING_ALGORITHM);
            for (SortingAlgorithm algorithm : SortingAlgorithm.values()) {
                String menuItem = algorithm.ordinal() + ": " + algorithm.name();
                System.out.println(menuItem);
            }
            try {
                Scanner keyBoardInput = new Scanner(System.in);
                int algorithmOrdinal = keyBoardInput.nextInt();
                System.out.println();
                selectedAlgoritm = SortingAlgorithm.values()[algorithmOrdinal];
            } catch (Exception e) {
                System.out.println(INVALID_INPUT);
            }
        }
        return selectedAlgoritm;
    }

    private static CustomTimeSeriesDataItem[] getDataToSort() {
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
