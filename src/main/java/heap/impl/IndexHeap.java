package heap.impl;

import heap.exception.OutOfHeapBoundException;
import sort.util.SortHelper;

import java.util.Arrays;

public class IndexHeap {
    private int[] indexArr;
    private int[] heapArr;
    private int[] removedIndex;
    // 用来快速定位data中第几个值位于index arr数组的什么位置。
    private int[] reverseIndex;
    private int removedIndexArrIndex;
    private int count;

    public IndexHeap(int n) {
        this.heapArr = new int[n];
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

    public IndexHeap(int[] arr) {
        this.heapArr = arr;
        this.indexArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            indexArr[i] = i;
        }
        int count = arr.length;
        heapify();
    }

    public void addItem(int value) throws OutOfHeapBoundException {
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

    public int popUp() throws OutOfHeapBoundException {
        if (--count < 0) {
            throw new OutOfHeapBoundException(count++ + " heap arr is empty now");
        }
        int result = getValue(indexArr, 0);
        removedIndex[++removedIndexArrIndex] = indexArr[0];
        reverseIndex[indexArr[0]] = -1;
        SortHelper.swap(indexArr, 0, count);
        shiftDown(indexArr, 0, count - 1);
        return result;
    }

    public void change(int index, int value) throws OutOfHeapBoundException {
        if (reverseIndex[index] == -1) {
            throw new OutOfHeapBoundException("Item data[" + index + "] is not in the heap, it was popped or never initialed");
        }
        heapArr[index] = value;
        shiftUp(indexArr, reverseIndex[index]);
        shiftDown(indexArr, reverseIndex[index], count - 1);
    }

    public int[] sort() {
        int[] indexArrCopy = Arrays.copyOf(indexArr, indexArr.length);
        for (int i = indexArrCopy.length - 1; i > 0; i--) {
            SortHelper.swap(indexArrCopy, 0, i);
            shiftDown(indexArrCopy, 0, i - 1);
        }
        int[] result = new int[indexArrCopy.length];
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
        int tmp = getValue(indexArr, index);
        while ((index - 1) >= 0) {
            int fatherIndex = (index - 1) / 2;
            if (getValue(indexArr, fatherIndex) < tmp) {
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
        int tmp = getValue(indexArr, i);
        int tmpIndex = indexArr[i];
        int index = i;
        while (2 * index + 1 <= lastIndex) {
            int leftIndex = 2 * index + 1;
            int rightIndex = 2 * index + 2;
            if (rightIndex <= lastIndex ? getValue(indexArr, rightIndex) < tmp && getValue(indexArr, leftIndex) < tmp : getValue(indexArr, leftIndex) < tmp) {
                break;
            }
            if (rightIndex <= lastIndex) {
                indexArr[index] = getValue(indexArr, rightIndex) > getValue(indexArr, leftIndex) ? indexArr[index = rightIndex] : indexArr[index = leftIndex];
                reverseIndex[indexArr[index]] = index;
            } else {
                indexArr[index] = indexArr[index = leftIndex];
                reverseIndex[indexArr[index]] = index;
            }
        }
        indexArr[index] = tmpIndex;
        reverseIndex[indexArr[index]] = index;
    }

    private int getValue(int[] indexArr, int i) {
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
