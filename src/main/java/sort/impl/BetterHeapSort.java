package sort.impl;

import heap.exception.OutOfHeapBoundException;
import heap.impl.CompletelyMaxHeap;

public class BetterHeapSort {
    CompletelyMaxHeap<Integer> heap;

    Integer[] arr;

    public BetterHeapSort(Integer[] arr) {
        heap = new CompletelyMaxHeap<>(arr);
        this.arr = arr;
    }

    public void sort() throws OutOfHeapBoundException {
        heap.heap();
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = heap.popItem();
        }
    }
}
