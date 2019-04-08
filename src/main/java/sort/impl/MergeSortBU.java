package sort.impl;

import java.util.Arrays;

import static java.lang.Math.min;

public class MergeSortBU extends MergeSort {
    @Override
    public void sort(int[] array) {
        for (int size = 1; size < array.length; size += size) {
            for (int i = 0; i + size < array.length; i += 2 * size) {
                merge(array, i, i + size - 1, i + size, min(i + 2 * size - 1, array.length - 1));

            }
        }
    }

    private void merge(int[] array, int ls, int le, int rs, int rn) {
        if (array[rs] >= array[le])
            return;
        int[] leftArr = Arrays.copyOfRange(array, ls, le + 1);
        int[] rightArr = Arrays.copyOfRange(array, rs, rn + 1);
        __merge(array, ls, rn, leftArr, rightArr);
    }
}
