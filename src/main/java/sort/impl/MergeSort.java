package sort.impl;

import sort.Sort;

import java.util.Arrays;

public class MergeSort implements Sort {

    protected BetterInsertion insertion = new BetterInsertion();

    @Override
    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private void merge(int[] array, int left, int mid, int right) {
        int[] leftArray = Arrays.copyOfRange(array, left, mid + 1);
        int[] rightArray = Arrays.copyOfRange(array, mid + 1, right + 1);
        __merge(array, left, right, leftArray, rightArray);
    }

    /**
     * [left,right]
     *
     * @param array
     * @param left
     * @param right
     */
    private void sort(int[] array, int left, int right) {
        if (right - left <= 15) {
            this.insertion.sort(array, left, right);
            return;
        }
        int mid = (right + left) / 2;
        sort(array, left, mid);
        sort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    protected void __merge(int[] array, int ls, int rn, int[] leftArr, int[] rightArr) {
        int leftIndex = 0;
        int rightIndex = 0;
        int arrayIndex = ls;
        for (; arrayIndex <= rn; arrayIndex++) {
            if (leftIndex >= leftArr.length)
                array[arrayIndex] = rightArr[rightIndex++];
            else if (rightIndex >= rightArr.length)
                array[arrayIndex] = leftArr[leftIndex++];
            else
                array[arrayIndex] = leftArr[leftIndex] > rightArr[rightIndex] ? rightArr[rightIndex++] : leftArr[leftIndex++];
        }
    }
}
