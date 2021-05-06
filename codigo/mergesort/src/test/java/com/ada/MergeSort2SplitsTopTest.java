package com.ada;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import org.junit.Test;

/**
 * Unit test for MergeSort2SplitsTop.
 */

public class MergeSort2SplitsTopTest {
    public static int[] copyArray(int[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }

    private static boolean isSorted(int[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (a[i] < a[i - 1])
                return false;
        return true;
    }

    private int[] createRandomSizeNArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            Random rnd = new Random();
            a[i] = rnd.nextInt();
        }
        return a;
    }

    private int[] createRandomSizeNArray(int n, long seed) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            Random rnd = new Random();
            rnd.setSeed(seed + (long) i);
            a[i] = rnd.nextInt();
        }
        return a;
    }

    public static int[] createArray1DRangeStep(int lo, int hi, int step) {
        int num = (hi - lo) / step;
        int[] a = new int[num];
        int val = lo;
        for (int i = 0; i < num; i++) {
            a[i] = val;
            val += step;
        }
        return a;
    }

    /**
     * Tests if the function is returning sorted arrays for given cases. The cases
     * comprises 5 tests:
     * <p>
     * Case 1 - Specific Array Case 2 - Specific Array- Worst Case sorted Array
     * n=3*x Case 3 - Specific Array- Worst Case sorted Array n=3*x+1 Case 4 -
     * Specific Array- Worst Case sorted Array n=3*x+2 Case 5 Random Arrays with N
     * values ranging from (99999 to 100010) being tested 1000 times.
     * 
     * @throws IOException
     */
    @Test
    public void testexecuteMergeSort() throws IOException {
        // Case 1 - Specific Array
        int[] myArray = { 1, 52, 5, 2, 1, 23 };
        myArray = MergeSort2SplitsTop.executeMergeSort(myArray);
        System.out.println("Sorted Case 1");
        System.out.println(Arrays.toString(myArray));
        assertTrue(isSorted(myArray));

        // Case 2 - Specific Array- Worst Case sorted Array n=3*x
        // Set Up
        int[] myArray2 = { 1, 2, 3, 4, 5, 6, 6, 6, 8 };
        myArray2 = MergeSort2SplitsTop.executeMergeSort(myArray2);
        System.out.println(Arrays.toString(myArray2));
        System.out.println("Sorted Case 2");
        assertTrue(isSorted(myArray2));

        // Case 3 - Specific Array- Worst Case sorted Array n=3*x+1
        // Set Up
        int[] myArray3 = { 1, 2, 3, 4, 5, 6, 6, 6, 8, 8 };
        myArray3 = MergeSort2SplitsTop.executeMergeSort(myArray3);
        System.out.println(Arrays.toString(myArray3));
        System.out.println("Sorted Case 3");
        assertTrue(isSorted(myArray3));

        // Set Up
        int[] myArray3_2 = { 1, 2, 3, 4, 5, 6, 6, 6, 8, 8, 9, 9, 9 };
        myArray3_2 = MergeSort2SplitsTop.executeMergeSort(myArray3_2);
        System.out.println(Arrays.toString(myArray3_2));
        System.out.println("Sorted Case 3");
        assertTrue(isSorted(myArray3_2));

        // Case 4 - Specific Array- Worst Case sorted Array n=3*x+2
        // Set Up
        int[] myArray4 = { 1, 2, 3, 4, 5, 6, 6, 6, 8, 8, 9 };
        myArray4 = MergeSort2SplitsTop.executeMergeSort(myArray4);
        System.out.println(Arrays.toString(myArray4));
        System.out.println("Sorted Case 4");
        assertTrue(isSorted(myArray4));

        // Set Up
        int[] myArray4_2 = { 1, 2, 3, 4, 5, 6, 6, 6, 8, 8, 9, 11, 11, 12 };
        myArray4_2 = MergeSort2SplitsTop.executeMergeSort(myArray4_2);
        System.out.println(Arrays.toString(myArray4_2));
        System.out.println("Sorted Case 4");
        assertTrue(isSorted(myArray4_2));

        // Case 5 Random Arrays
        System.out.println("Start case Random Arrays");

        // Set Up
        int numTests = 100;
        int[] nValues = createArray1DRangeStep(99999, 100010, 1);
        for (int t = 0; t < numTests; t++) {
            System.out.println("Start Random Test" + t);
            for (int i = 0; i < nValues.length; i++) {
                System.out.println("Start Random Case " + i);
                int[] array = createRandomSizeNArray(nValues[i]);
                array = MergeSort2SplitsTop.executeMergeSort(array);
                assertTrue(isSorted(array));
            }
        }
    }
}