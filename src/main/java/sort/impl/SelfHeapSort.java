package sort.impl;

import sort.Sort;
import sort.util.SortHelper;

public class SelfHeapSort implements Sort {
    @Override
    public void sort(int[] array) {
        heap(array);
        selfSort(array);
    }

    private void selfSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            SortHelper.swap(arr, 0, i);
            shiftDown(arr, 0, i - 1);
        }
    }

    private void heap(int[] arr) {
        int lastNonLeafNode = (arr.length - 2) / 2;
        for (int i = lastNonLeafNode; i >= 0; i--) {
            shiftDown(arr, i, arr.length - 1);
        }
    }

    private void shiftDown(int[] arr, int index, int lastIndex) {
        int tmp = arr[index];
        while (2 * index + 1 <= lastIndex) {
            int leftIndex = 2 * index + 1;
            int rightIndex = 2 * index + 2;
            if (rightIndex <= lastIndex ? arr[leftIndex] < tmp && arr[rightIndex] < tmp : arr[leftIndex] < tmp) {
                break;
            }
            if (rightIndex <= lastIndex) {
                arr[index] = arr[leftIndex] > arr[rightIndex] ? arr[index = leftIndex] : arr[index = rightIndex];
            } else {
                arr[index] = arr[index = leftIndex];
            }
        }
        arr[index] = tmp;
    }
}
