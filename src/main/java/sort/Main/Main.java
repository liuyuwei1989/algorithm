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
        /*sort(new MergeSortBU(), arr);*/
        sort(new MergeSort(), arr);
        /*sort(new Quick(), arr);*/
        sort(new TripleQuick(), arr);
        sort(new HeapSort(arr.length), arr);
        sort(new BetterHeapSort(arrs), arrs);
        /*sort(new BetterInsertion(), arr);
        sort(new Insertion(), arr);
        sort(new Selection(), arr);*/
        int[] nealyOrderedArray = generateNearlyOrderedArray(1000000, 1, 200000000);
        Integer[] nealyOrderedArrays = convert(nealyOrderedArray);
        sort(new MergeSort(), nealyOrderedArray);
        sort(new TripleQuick(), nealyOrderedArray);
        sort(new HeapSort(nealyOrderedArray.length), nealyOrderedArray);
        sort(new BetterHeapSort(nealyOrderedArrays), nealyOrderedArrays);
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
