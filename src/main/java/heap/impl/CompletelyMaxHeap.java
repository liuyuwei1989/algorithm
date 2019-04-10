package heap.impl;

import heap.Heap;
import heap.exception.OutOfHeapBoundException;

import static heap.util.Util.swap;

public class CompletelyMaxHeap<T extends Comparable> extends Heap<T> {

    @Override
    @SuppressWarnings("unchecked")
    public void addItem(T item) throws OutOfHeapBoundException {

        if (item == null)
            throw new NullPointerException("You can't add null into heap");
        if (count == maxNum) {
            throw new OutOfHeapBoundException("Heap is full, can't add more item");
        } else {
            data[++count] = item;
            int fatherIndex = (count) / 2;
            int index = count;
            while (fatherIndex != 0 && ((T) data[fatherIndex]).compareTo(data[index]) < 0) {
                swap(data, fatherIndex, index);
                index = fatherIndex;
                fatherIndex = index / 2;
            }
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
        T tmpItem = (T) data[1];
        int index = 1;
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
        return item;
    }

    public CompletelyMaxHeap(int n) {
        super(n);
    }

}
