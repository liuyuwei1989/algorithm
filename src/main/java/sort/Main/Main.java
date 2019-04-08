package sort.Main;

import sort.Sort;
import sort.impl.*;
import sort.util.SortHelper;

import static java.util.Arrays.copyOf;

import static sort.util.SortHelper.*;

public class Main {

    public static void main(String[] args) {
        int[] arr = generateArray(1000000, 1, 200000000);
        sort(new MergeSortBU(), arr);
        sort(new MergeSort(), arr);
        /*sort(new BetterInsertion(), arr);
        sort(new Insertion(), arr);
        sort(new Selection(), arr);*/
    }

    public static void sort(Sort sort, int[] integers) {
        SortHelper helper = new SortHelper();
        int[] arr = copyOf(integers, integers.length);
        String name = sort.getClass().getName();
        helper.start(name);
        sort.sort(arr);
        helper.end(name);
        assert assertArray(arr) : sort.getClass().getName() + " arr is error\n" + printArray(arr);
    }
}