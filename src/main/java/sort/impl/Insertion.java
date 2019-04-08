package sort.impl;

import sort.Sort;

import static sort.util.SortHelper.swap;

public class Insertion implements Sort {
    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
                swap(array, j - 1, j);
            }
        }
    }
}
