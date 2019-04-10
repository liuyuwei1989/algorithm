package sort.impl;

import heap.exception.OutOfHeapBoundException;
import heap.impl.CompletelyMaxHeap;
import sort.Sort;

public class HeapSort implements Sort {
    CompletelyMaxHeap<Integer> heap ;

    public HeapSort(int n) {
        heap = new CompletelyMaxHeap<>(n + 1);
    }

    @Override
    public void sort(int[] array) {
        try {
            for (int i : array) {
                    heap.addItem(i);
                }
            for (int i = array.length -1 ; i >=0 ; i --){
                array[i] = heap.popItem();
            }
        } catch (OutOfHeapBoundException e) {
            e.printStackTrace();
        }
    }
}
