package com.ada;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Compilation: javac Sorting.java Execution: java Sorting Dependencies: Data
 * files:
 * 
 * Executes a comparison of sorting algorithms. Generates t tests of N arrays
 * composed of a random permutation with size s. Compares the time performance
 * of Merge Sort Top-Down 3 splits, Merge Sort Top-Down 2 splits and Merge Sort
 * Bottom up.
 * 
 */

public class Sorting {
    private static boolean isSorted(int[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (a[i] < a[i - 1])
                return false;
        return true;
    }

    private static int[] createRandomSizeNArray(int n, long seed) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            Random rnd = new Random();
            rnd.setSeed(seed + (long) i);
            a[i] = rnd.nextInt();
        }
        return a;
    }

    private static int[] createRandomSizeNArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            Random rnd = new Random();
            a[i] = rnd.nextInt();
        }
        return a;
    }

    private static void swapElements1DArray(int[] a, int i, int r) {
        int temp = a[i];
        a[i] = a[r];
        a[r] = temp;
    }

    private static void shuffleKnuthFisher(int[] a) {
        int r;
        for (int i = 1; i < a.length; i++) {
            r = (int) (Math.random() * (i + 1));
            swapElements1DArray(a, i, r);
            // System.out.println(Arrays.toString(a));
        }
    }

    public static int[] createRandomPermutation(int maxRange) {
        int[] a = new int[maxRange];
        for (int i = 0; i < maxRange; i++) {
            a[i] = i;
        }
        shuffleKnuthFisher(a);
        assert (!isSorted(a));
        return a;
    }

    /**
     * Export a 2D array to a new file. Example of usage:
     * <p>
     * Path path4 = Paths.get(System.getProperty("user.dir"), "data",
     * "myOutputStreamWriterBuffered2D.csv");
     * export2DArrayOutputStreamWriterBuffered(arrStr, path4, ",");
     * 
     * @param a
     * @param path
     * @param separator
     * @throws IOException
     */
    public static void export2DArrayOutputStreamWriterBuffered(String[][] a, Path path, String separator)
            throws IOException {
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(new File(path.toString())), "UTF-8"))) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    builder.append(a[i][j]);
                    if (j < a[0].length - 1)// except for last element in row
                        builder.append(separator);
                }
                builder.append("\n");
            }
            writer.write(builder.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static void main(String[] args) throws IOException {
        int numElements = 100000;
        int numArrays = 10;
        int numAlgo = 3;// Number of algorithms
        boolean isPilotTest = false;// Runs pilot test
        if (isPilotTest) {
            int numPilot = 100;// Amostragem testes piloto
            Path path = Paths.get("src", "main", "resources", "com", "ada", "raw", "timePilot.csv");
            executeExportNTestsCompareAlgorithms(numElements, numArrays, numAlgo, numPilot, path);
        } else {
            int numTests = 500;// Amostragem testes reais
            Path path = Paths.get("src", "main", "resources", "com", "ada", "raw", "timeTest.csv");
            executeExportNTestsCompareAlgorithms(numElements, numArrays, numAlgo, numTests, path);
        }
    }

    private static void executeExportNTestsCompareAlgorithms(int numElements, int numArrays, int numAlgo, int numTests,
            Path path) throws IOException {
        String[][] times = new String[numTests][numAlgo];
        for (int t = 0; t < numTests; t++) {
            int timetd3 = 0;
            int timetd2 = 0;
            int timetbu = 0;
            for (int n = 0; n < numArrays; n++) {
                int[] array = createRandomPermutation(numElements);
                int[] arraytd2 = copyArray(array);
                int[] arraybu = copyArray(array);
                long t0 = System.currentTimeMillis();
                array = MergeSort3SplitsTop.executeMergeSort(array);
                long t1 = System.currentTimeMillis();
                arraytd2 = MergeSort2SplitsTop.executeMergeSort(arraytd2);
                long t2 = System.currentTimeMillis();
                arraybu = MergeSortBottom.executeMergeSort(arraybu);
                long t3 = System.currentTimeMillis();
                timetd3 += (int) (t1 - t0);
                timetd2 += (int) (t2 - t1);
                timetbu += (int) (t3 - t2);
            }
            times[t][0] = String.valueOf(timetd3);
            times[t][1] = String.valueOf(timetd2);
            times[t][2] = String.valueOf(timetbu);
        }
        export2DArrayOutputStreamWriterBuffered(times, path, ",");
    }

    public static int[] copyArray(int[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }

}