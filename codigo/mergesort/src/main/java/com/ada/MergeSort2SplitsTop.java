package com.ada;

import java.util.Arrays;
import java.io.IOException;

public class MergeSort2SplitsTop {
    int[] b;
    int[] c;

    public MergeSort2SplitsTop(int[] b, int[] c) {
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

    public static void main(String[] args) {
        int[] myArray = { 1, 52, 5, 2, 1, 23 };
        int[] arrayCopy1 = copyArray(myArray);
        int[] arrayCopy2 = copyArray(myArray);
        // Sorting sortObj = Sorting();
        MergeSort2SplitsTop sortObj = new MergeSort2SplitsTop(arrayCopy1, arrayCopy2);
        sortObj.mergeSort(myArray);
        System.out.println(Arrays.toString(myArray));

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
        MergeSort2SplitsTop sortObj1 = new MergeSort2SplitsTop(arrayCopy1, arrayCopy2);
        sortObj1.mergeSort(myArray);
        return myArray;
    }

    public void mergeSort(int[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, lo, mid);
        mergeSort(a, mid + 1, hi);
        merge(a, lo, hi, mid);
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

    public void mergeSort(int[] myArray) {
        mergeSort(myArray, 0, myArray.length - 1);
    }

}