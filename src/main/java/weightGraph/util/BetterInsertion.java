package weightGraph.util;

import weightGraph.Sort;

public class BetterInsertion<T extends Comparable<T>> implements Sort<T> {
    @Override
    public void sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T e = array[i];
            int j;
            for (j = i; j > 0 && array[j - 1].compareTo(e) > 0; j--) {
                array[j] = array[j - 1];
            }
            array[j] = e;
        }
    }

    public void sort(T[] array, int left, int right) {
        for (int i = left; i <= right; i++) {
            T e = array[i];
            int j;
            for (j = i; j > left && array[j - 1].compareTo(e) > 0; j--) {
                array[j] = array[j - 1];
            }
            array[j] = e;
        }
    }
}
