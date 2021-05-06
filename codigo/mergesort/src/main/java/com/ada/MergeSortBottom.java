package com.ada;

import java.io.IOException;

public class MergeSortBottom {
    int[] b;
    int[] c;

    public MergeSortBottom(int[] b, int[] c) {
        this.b = b;
        this.c = c;
    }

    public static int[] copyArray(int[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }

    private static boolean isSorted(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (a[i] < a[i - 1])
                return false;
        return true;
    }

    private static boolean isSorted(int[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    /**
     * Execute the MergeSort 3 splits. Creates the necessary copies and encapsulate
     * the MergeSort3SplitsTop object and executes the sort.
     * 
     * @param myArray
     * @throws IOException
     */
    public static int[] executeMergeSort(int[] myArray) throws IOException {
        if (isSorted(myArray)) {
            return myArray;
        }

        int[] arrayCopy1 = copyArray(myArray);
        int[] arrayCopy2 = copyArray(myArray);
        MergeSortBottom sortObj1 = new MergeSortBottom(arrayCopy1, arrayCopy2);
        sortObj1.mergeSort(myArray);
        return myArray;
    }

    public void merge(int[] a, int lo, int hi, int mid) {
        for (int k = lo; k <= mid; k++) {
            b[k - lo] = a[k];
        }
        for (int k = mid + 1; k <= hi; k++) {
            c[k - mid - 1] = a[k];
        }
        b[mid - lo + 1] = c[hi - mid] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        for (int k = lo; k <= hi; k++) {
            if (c[j] < b[i]) {
                a[k] = c[j++];
            } else {
                a[k] = b[i++];
            }
        }
    }

    public void mergeSort(int[] a) {
        int n = a.length;
        for (int s = 1; s < n; s += s) {// Aumento o tamanho do subarray
            for (int lo = 0; lo < n - s; lo += 2 * s) {// Dobro o tamanho do meu subarray.
                merge(a, lo, Math.min(lo + 2 * s - 1, n - 1), lo + s - 1);// Igual ao método TD2
            }
        }
    }

}