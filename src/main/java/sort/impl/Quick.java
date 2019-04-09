package sort.impl;

import sort.Sort;
import sort.util.SortHelper;

import java.util.Random;

import static sort.util.SortHelper.swap;

public class Quick implements Sort {
    Random random = new Random();
    BetterInsertion insertion = new BetterInsertion();

    @Override
    public void sort(int[] array) {
        __sort(array, 0, array.length - 1);
    }

    /**
     * [l,r]
     *
     * @param arr
     * @param l
     * @param r
     */
    private void __sort(int[] arr, int l, int r) {
        if (r - l <= 15) {
            insertion.sort(arr, l, r);
            return;
        }
        int p = findPosition(arr, l, r);
        __sort(arr, l, p - 1);
        __sort(arr, p + 1, r);
    }

    private int findPosition(int[] arr, int l, int r) {
        // nearly ordered array sort
        swap(arr, l, l + random.nextInt(r - l + 1));
        int v = arr[l];
        int ltIndex = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < v) {
                SortHelper.swap(arr, i, ++ltIndex);
            }
        }
        swap(arr, l, ltIndex);
        return ltIndex;
    }
}
