package heap.util;

public class Util {
    private Util() {

    }

    public static void swap(Object[] arr, int i, int j) {
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
