package heap.Main;

import heap.impl.CompletelyMaxHeap;
import heap.exception.OutOfHeapBoundException;
import heap.impl.IndexHeap;
import sort.util.SortHelper;


import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws OutOfHeapBoundException {
        /*CompletelyMaxHeap<Integer> heap = new CompletelyMaxHeap<>(50);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            heap.addItem(random.nextInt(100));
        }
        heap.treePrint();
        System.out.println(heap.popItem());
        heap.treePrint();

        heap.removeAll();

        int[] arr = SortHelper.generateArray(20, 1, 100);
        for (int i : arr) {
            heap.addItem(i);
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = heap.popItem();
        }

        System.out.println(Arrays.toString(arr));*/

        /*int[] arr1 = SortHelper.generateArray(10, 1, 100);
        Integer[] arrCopy = new Integer[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arrCopy[i] = arr1[i];
        }

        CompletelyMaxHeap<Integer> heap1 = new CompletelyMaxHeap<>(arrCopy);
        heap1.heap();
        for (int i = arrCopy.length - 1; i >= 0; i--) {
            arrCopy[i] = heap1.popItem();
        }

        System.out.println(Arrays.toString(arrCopy));

        IndexHeap heap = new IndexHeap(arr1);
        System.out.println(heap);

        System.out.println(Arrays.toString(heap.sort()));*/

        Random random = new Random();

        IndexHeap heap2 = new IndexHeap(100);
        for (int i = 0; i < 10; i++) {
            heap2.addItem(random.nextInt(100));
        }
        System.out.println(heap2);
        System.out.println(heap2.popUp());
        System.out.println(heap2);
        System.out.println(heap2.popUp());
        System.out.println(heap2);
        heap2.addItem(random.nextInt(100));
        System.out.println(heap2);
        heap2.change(0, 99);
        System.out.println(heap2);
    }
}
