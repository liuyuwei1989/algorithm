package sort.Main;

import heap.exception.OutOfHeapBoundException;
import sort.Sort;
import sort.impl.*;
import sort.util.SortHelper;

import java.util.ArrayList;

import static java.util.Arrays.copyOf;

import static sort.util.SortHelper.*;

public class Main {

    public static void main(String[] args) throws OutOfHeapBoundException {
        int[] arr = generateArray(1000000, 1, 20000000);
        Integer[] arrs = convert(arr);
        sort(arr, arrs);
        int[] arr_1 = generateArray(1000000, 1, 20);
        Integer[] arrs_1 = convert(arr);
        sort(arr_1, arrs_1);
        int[] nealyOrderedArray = generateNearlyOrderedArray(1000000, 1, 200000000);
        Integer[] nealyOrderedArrays = convert(nealyOrderedArray);
        sort(nealyOrderedArray, nealyOrderedArrays);

    }

    public static void sort(int[] arr, Integer[] arrs) throws OutOfHeapBoundException {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        sort(new MergeSort(), arr);
        sort(new TripleQuick(), arr);
        sort(new HeapSort(arr.length), arr);
        sort(new BetterHeapSort(arrs), arrs);
        sort(new SelfHeapSort(), arr);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }

    public static void sort(Sort sort, int[] integers) {
        SortHelper helper = new SortHelper();
        int[] arr = copyOf(integers, integers.length);
        String name = sort.getClass().getName();
        helper.start(name);
        sort.sort(arr);
        helper.end(name);
        assert assertArray(arr) : sort.getClass().getName() + " arr is error\n";
    }

    public static void sort(BetterHeapSort sort, Integer[] integers) throws OutOfHeapBoundException {
        SortHelper helper = new SortHelper();
        String name = sort.getClass().getName();
        helper.start(name);
        sort.sort();
        helper.end(name);
        assert assertArray(integers) : sort.getClass().getName() + " arr is error\n";
    }
}
