package sort.impl;

import sort.Sort;

import java.util.Random;

import static sort.util.SortHelper.swap;

public class TripleQuick implements Sort {
    private Random random = new Random();
    private BetterInsertion insertion = new BetterInsertion();

    @Override
    public void sort(int[] array) {
        __tripleQuick(array, 0, array.length - 1);
    }

    private void __tripleQuick(int[] arr, int l, int r) {
        if (r - l <= 15) {
            insertion.sort(arr, l, r);
            return;
        }
        int[] range = findPositionForV(arr, l, r);
        __tripleQuick(arr, l, range[0] - 1);
        __tripleQuick(arr, range[1] + 1, r);
    }

    private int[] findPositionForV(int[] arr, int l, int r) {
        int[] range = new int[2];
        swap(arr, l, l + random.nextInt(r - l + 1));
        int v = arr[l];
        int lt = l;
        int gt = r + 1;
        int i = l + 1;
        while (true) {
            if (i >= gt) break;
            if (arr[i] < v) {
                swap(arr, i, lt + 1);
                lt++;
                i++;
            } else if (arr[i] == v) {
                i++;
            } else if (arr[i] > v) {
                swap(arr, i, gt - 1);
                gt--;
            }
        }
        swap(arr, l, lt);
        range[0] = lt;
        range[1] = gt - 1;
        return range;
    }
}
