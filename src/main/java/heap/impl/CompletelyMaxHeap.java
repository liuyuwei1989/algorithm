package heap.impl;

import heap.Heap;
import heap.exception.OutOfHeapBoundException;

import static heap.util.Util.swap;

public class CompletelyMaxHeap<T extends Comparable> extends Heap<T> {

    @Override
    public void addItem(T item) throws OutOfHeapBoundException {

        if (item == null)
            throw new NullPointerException("You can't add null into heap");
        if (count == maxNum) {
            throw new OutOfHeapBoundException("Heap is full, can't add more item");
        } else {
            data[++count] = item;
            shiftUp(count);
        }
    }

    @SuppressWarnings("unchecked")
    private void shiftUp(int index) {
        int fatherIndex = (index) / 2;
        while (fatherIndex != 0 && ((T) data[fatherIndex]).compareTo(data[index]) < 0) {
            swap(data, fatherIndex, index);
            index = fatherIndex;
            fatherIndex = index / 2;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T popItem() throws OutOfHeapBoundException {
        if (isEmpty()) {
            throw new OutOfHeapBoundException("Heap is empty");
        }
        T item = (T) data[1];
        data[1] = data[count--];
        shiftDown(1);
        return item;
    }

    private void shiftDown(int index) {
        T tmpItem = (T) data[index];
        while (2 * index <= count) {
            int tmp = index;
            int k2 = 2 * index;
            int k2_1 = 2 * index + 1;
            if (2 * index + 1 <= count) {
                index = ((T) data[k2]).compareTo(data[k2_1]) < 0 ? k2_1 : k2;
            } else {
                index = k2;
            }
            if (tmpItem.compareTo(data[index]) < 0)
                data[tmp] = data[index];
            else break;
        }
        data[index] = tmpItem;
    }

    public CompletelyMaxHeap(int n) {
        super(n);
    }

    public CompletelyMaxHeap(T[] arr) {
        super(arr);
    }

    public void heap() {
        int last = count / 2;
        for (int i = last; i >= 1; i--) {
            shiftDown(i);
        }
    }

}
