package sort.impl;

import static sort.util.SortHelper.swap;

public class DoubleQuick extends Quick {
    @Override
    protected int findPosition(int[] arr, int l, int r) {
        swap(arr, l, l + random.nextInt(r - l + 1));
        int v = arr[l];
        int left = l + 1;
        int right = r;
        while (true) {
            while (left <= r && arr[left] < v) left++;
            while (right >= l + 1 && arr[right] > v) right--;
            if (left > right)
                break;
            swap(arr, left, right);
            left ++;
            right --;
        }
        swap(arr, right, l);
        return right;
    }
}
