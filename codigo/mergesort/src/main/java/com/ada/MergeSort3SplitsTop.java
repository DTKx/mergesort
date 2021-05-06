package com.ada;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Sorts an array of int.
 */
public class MergeSort3SplitsTop {
    int[] b;
    int[] c;
    int[] d;

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
        int[] arrayCopy3 = copyArray(myArray);
        MergeSort3SplitsTop sortObj1 = new MergeSort3SplitsTop(arrayCopy1, arrayCopy2, arrayCopy3);
        sortObj1.mergeSort3Splits(myArray);
        return myArray;
    }

    public MergeSort3SplitsTop(int[] b, int[] c, int[] d) {
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public void printArrayFromTo(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }

    /**
     * Sorts an array for a Merge Sort with 3 splits. It splits the array into 3
     * parts, please note depending on the number of values in array, it might be
     * splitted into 2 parts in the last leave. It then calls the merge, depending
     * on the number of splits.
     * 
     * @param a  Array to be sorted.
     * @param lo Lowest index of current array.
     * @param hi Highest index of current array.
     */
    public void sort3Splits(int[] a, int lo, int hi) {
        // System.out.println("sort3Splits in");
        // printArrayFromTo(a, lo, hi);

        int numVal = hi - lo;
        if (numVal <= 0) {
            return;
        }
        if (numVal == 1) {// Caso 1) 2 valores remanescentes, separa em 2 partes
            int div2 = (hi - lo) / 2;
            int split1 = lo + div2;
            merge2Splits(a, lo, hi, split1);
        } else {// Caso 2) Tamanho Subarray>=3
            int div3 = (hi - lo) / 3;
            int split1 = lo + div3;
            int split2 = split1 + 1 + div3;
            sort3Splits(a, lo, split1);
            sort3Splits(a, split1 + 1, split2);
            sort3Splits(a, split2 + 1, hi);
            merge3Splits(a, lo, hi, split1, split2);
        }
    }

    /**
     * Merges sorting an array with 2 splits.
     * 
     * @param a   Array to be merged sorted.
     * @param lo  Lowest index of current array.
     * @param hi  Highest index of current array.
     * @param mid Splitting point.
     */
    public void merge2Splits(int[] a, int lo, int hi, int mid) {
        // System.out.println("merge2Splits in");
        // printArrayFromTo(a, lo, hi);

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
        // System.out.println("merge2Splits out");
        // printArrayFromTo(a, lo, hi);

    }

    /**
     * Merges an array in sorted position receiving 3 subarrays (2 splitting
     * points).
     * 
     * @param a      Array
     * @param lo     Lowest index of current array.
     * @param hi     Highest index of current array.
     * @param split1 First Splitting point.
     * @param split2 Second Splitting point.
     */
    public void merge3Splits(int[] a, int lo, int hi, int split1, int split2) {
        // System.out.println("merge3Splits in");
        // printArrayFromTo(a, lo, hi);

        for (int k = lo; k <= split1; k++) {
            b[k - lo] = a[k];
        }
        for (int k = split1 + 1; k <= split2; k++) {
            c[k - split1 - 1] = a[k];
        }
        for (int k = split2 + 1; k <= hi; k++) {
            d[k - split2 - 1] = a[k];
        }
        b[split1 - lo + 1] = Integer.MAX_VALUE;
        c[split2 - split1] = Integer.MAX_VALUE;
        d[hi - split2] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        int l = 0;
        for (int k = lo; k <= hi; k++) {// Merge
            // Verifica em qual parte está localizado o menor valor
            if ((d[l] < b[i]) & (d[l] < c[j])) {
                a[k] = d[l++];// Adiciona o menor valor e aumenta o indice
            } else if ((c[j] <= b[i]) & (c[j] <= d[l])) {
                a[k] = c[j++];
            } else {// Adiciona o valor do primeiro array
                a[k] = b[i++];
            }
        }
        // System.out.println("merge3Splits out");
        // printArrayFromTo(a, lo, hi);
    }

    /**
     * Sorts an array a using a Merge Sort with 3 splits.
     * 
     * @param myArray Array to be sorted.
     */
    public void mergeSort3Splits(int[] myArray) {
        sort3Splits(myArray, 0, myArray.length - 1);
    }

    public void mergeSort2Splits(int[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;// 0+(5-2)/2=2. Note teto[n/2]+chao[n/2]=N
        mergeSort2Splits(a, lo, mid);// Chão
        mergeSort2Splits(a, mid + 1, hi);// Teto
        // Inicia o Merge
        for (int k = lo; k <= mid; k++) {
            b[k - lo] = a[k];
        }
        for (int k = mid + 1; k <= hi; k++) {
            c[k - mid - 1] = a[k];
        }
        b[mid - lo + 1] = c[hi - mid] = Integer.MAX_VALUE;// Sentinelas marca o finald o array
        int i = 0;
        int j = 0;
        for (int k = lo; k <= hi; k++) {// Merge
            if (c[j] < b[i]) {
                a[k] = c[j++];
            } else {
                a[k] = b[i++];
            }
        }
    }
}