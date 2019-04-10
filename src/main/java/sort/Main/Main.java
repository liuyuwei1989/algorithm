package sort.Main;

import sort.Sort;
import sort.impl.*;
import sort.util.SortHelper;

import static java.util.Arrays.copyOf;

import static sort.util.SortHelper.*;

public class Main {

    public static void main(String[] args) {
        int[] arr = generateArray(100, 1, 5);
        /*sort(new MergeSortBU(), arr);*/
        sort(new MergeSort(), arr);
        /*sort(new Quick(), arr);*/
        sort(new DoubleQuick(), arr);
        sort(new TripleQuick(), arr);
        /*sort(new BetterInsertion(), arr);
        sort(new Insertion(), arr);
        sort(new Selection(), arr);*/
        /*int[] nealyOrderedArray = generateNearlyOrderedArray(100000000, 1, 200000000);
        sort(new MergeSort(), nealyOrderedArray);
        sort(new Quick(), nealyOrderedArray);*/
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
}
