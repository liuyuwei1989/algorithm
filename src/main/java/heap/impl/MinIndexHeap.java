package heap.impl;

import heap.exception.OutOfHeapBoundException;
import sort.util.SortHelper;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MinIndexHeap<T extends Comparable<T>> {
    private int[] indexArr;
    private T[] heapArr;
    private int[] removedIndex;
    // 用来快速定位data中第几个值位于index arr数组的什么位置。
    private int[] reverseIndex;
    private int removedIndexArrIndex;

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private int count;
    private Class<T> tClass;

    public MinIndexHeap(Class<T> tClass, int n) {
        this.tClass = tClass;
        this.heapArr = (T[]) Array.newInstance(tClass, n);
        this.indexArr = new int[n];
        this.removedIndex = new int[n];
        this.reverseIndex = new int[n];
        Arrays.fill(reverseIndex, -1);
        removedIndexArrIndex = -1;
        for (int i = 0; i < n; i++) {
            indexArr[i] = i;
        }
        int count = 0;
    }

    public MinIndexHeap(T[] arr) {
        this.heapArr = arr;
        this.indexArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            indexArr[i] = i;
        }
        int count = arr.length;
        heapify();
    }

    public void addItem(T value) throws OutOfHeapBoundException {
        if (++count > indexArr.length) {
            throw new OutOfHeapBoundException(count-- + " out of heap bound " + indexArr.length);
        } else {
            int lastIndexArrIndex = count - 1;
            int heapArrIndex = removedIndexArrIndex != -1 ? removedIndex[removedIndexArrIndex--] : lastIndexArrIndex;
            heapArr[heapArrIndex] = value;
            indexArr[lastIndexArrIndex] = heapArrIndex;
            reverseIndex[indexArr[lastIndexArrIndex]] = lastIndexArrIndex;
            shiftUp(indexArr, lastIndexArrIndex);
        }
    }

    public T popUp() throws OutOfHeapBoundException {
        if (isEmpty()) {
            throw new OutOfHeapBoundException(count++ + " heap arr is empty now");
        }
        --count;
        T result = getValue(indexArr, 0);
        removedIndex[++removedIndexArrIndex] = indexArr[0];
        reverseIndex[indexArr[0]] = -1;
        SortHelper.swap(indexArr, 0, count);
        shiftDown(indexArr, 0, count - 1);
        return result;
    }

    public int popMinIndex() throws OutOfHeapBoundException {
        if (isEmpty()) {
            throw new OutOfHeapBoundException(count++ + " heap arr is empty now");
        }
        --count;
        int index = indexArr[0];
        removedIndex[++removedIndexArrIndex] = indexArr[0];
        reverseIndex[indexArr[0]] = -1;
        SortHelper.swap(indexArr, 0, count);
        shiftDown(indexArr, 0, count - 1);
        return index;
    }

    public void change(int index, T value) throws OutOfHeapBoundException {
        if (reverseIndex[index] == -1) {
            throw new OutOfHeapBoundException("Item data[" + index + "] is not in the heap, it was popped or never initialed");
        }
        heapArr[index] = value;
        shiftUp(indexArr, reverseIndex[index]);
        shiftDown(indexArr, reverseIndex[index], count - 1);
    }

    public void insertValue(int index, T value) throws OutOfHeapBoundException {
        if (++count > indexArr.length) {
            throw new OutOfHeapBoundException(count-- + " out of heap bound " + indexArr.length);
        } else {
            int lastIndexArrIndex = count - 1;
            heapArr[index] = value;
            indexArr[lastIndexArrIndex] = index;
            reverseIndex[indexArr[lastIndexArrIndex]] = lastIndexArrIndex;
            shiftUp(indexArr, lastIndexArrIndex);
        }
    }

    public T[] sort() {
        int[] indexArrCopy = Arrays.copyOf(indexArr, indexArr.length);
        for (int i = indexArrCopy.length - 1; i > 0; i--) {
            SortHelper.swap(indexArrCopy, 0, i);
            shiftDown(indexArrCopy, 0, i - 1);
        }
        T[] result = (T[]) Array.newInstance(tClass, indexArrCopy.length);
        for (int i = 0; i < indexArrCopy.length; i++) {
            result[i] = getValue(indexArrCopy, i);
        }
        return result;
    }

    private void heapify() {
        int lastNonLeaf = (indexArr.length - 2) / 2;
        for (int i = lastNonLeaf; i >= 0; i--) {
            shiftDown(this.indexArr, i, indexArr.length - 1);
        }
    }

    private void shiftUp(int[] indexArr, int index) {
        int tmpIndex = indexArr[index];
        T tmp = getValue(indexArr, index);
        while ((index - 1) >= 0) {
            int fatherIndex = (index - 1) / 2;
            if (getValue(indexArr, fatherIndex).compareTo(tmp) > 0) {
                indexArr[index] = indexArr[index = fatherIndex];
                reverseIndex[indexArr[index]] = index;
            } else {
                break;
            }
        }
        indexArr[index] = tmpIndex;
        reverseIndex[indexArr[index]] = index;
    }

    private void shiftDown(int[] indexArr, int i, int lastIndex) {
        T tmp = getValue(indexArr, i);
        int tmpIndex = indexArr[i];
        int index = i;
        while (2 * index + 1 <= lastIndex) {
            int leftIndex = 2 * index + 1;
            int rightIndex = 2 * index + 2;
            if (rightIndex <= lastIndex ? getValue(indexArr, rightIndex).compareTo(tmp) > 0 && getValue(indexArr, leftIndex).compareTo(tmp) > 0 : getValue(indexArr, leftIndex).compareTo(tmp) > 0) {
                break;
            }
            if (rightIndex <= lastIndex) {
                indexArr[index] = getValue(indexArr, rightIndex).compareTo(getValue(indexArr, leftIndex)) < 0 ? indexArr[index = rightIndex] : indexArr[index = leftIndex];
                reverseIndex[indexArr[index]] = index;
            } else {
                indexArr[index] = indexArr[index = leftIndex];
                reverseIndex[indexArr[index]] = index;
            }
        }
        indexArr[index] = tmpIndex;
        reverseIndex[indexArr[index]] = index;
    }

    private T getValue(int[] indexArr, int i) {
        return heapArr[indexArr[i]];
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < count; i++) {
            str += " " + getValue(indexArr, i) + " ";
        }
        str += "\n";
        for (int i = 0; i < count; i++) {
            str += " " + indexArr[i] + " ";
        }
        return str;
    }
}
