package sort.impl;

import sort.Sort;

public class BetterInsertion implements Sort {
    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int e = array[i];
            int j;
            for (j = i; j > 0 && array[j - 1] > e; j--) {
                array[j] = array[j - 1];
            }
            array[j] = e;
        }
    }

    public void sort(int[] array, int left, int right) {
        for (int i = left; i <= right; i++) {
            int e = array[i];
            int j;
            for (j = i; j > left && array[j - 1] > e; j--) {
                array[j] = array[j - 1];
            }
            array[j] = e;
        }
    }
}
