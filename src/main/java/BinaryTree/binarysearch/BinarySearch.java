package BinaryTree.binarysearch;

import sort.impl.TripleQuick;

public class BinarySearch {
    private int arr[];

    public BinarySearch(int[] arr) {
        new TripleQuick().sort(arr);
        this.arr = arr;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + ": " + arr[i] + " ");
        }
        System.out.println("\n");
    }

    public int search(int value) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == value)
                return mid;
            if (arr[mid] > value) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
}
