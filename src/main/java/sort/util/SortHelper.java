package sort.util;

import sort.impl.Quick;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class SortHelper {

    Timer timer = new Timer();

    public static int[] swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        return array;
    }

    public static int[] generateArray(int i, int left, int right) {
        int[] integers = new int[i];
        Random random = new Random();
        for (int s = 0; s < i; s++) {
            integers[s] = left + random.nextInt(right - left);
        }
        return integers;
    }

    public static int[] generateNearlyOrderedArray(int i, int left, int right) {
        Quick quick = new Quick();
        int[] integers = new int[i];
        Random random = new Random();
        for (int s = 0; s < i; s++) {
            integers[s] = left + random.nextInt(right - left);
        }
        quick.sort(integers);
        for (int j = 2; j >= 0; j--) {
            int a = random.nextInt(i);
            int b = random.nextInt(i);
            swap(integers, a, b);
        }
        return integers;
    }

    public static String printArray(int[] arr) {
        String str = Arrays.toString(arr);
        System.out.println(str);
        return str;
    }

    public static boolean assertArray(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public void start(String stepName) {
        timer.put(stepName, System.currentTimeMillis());
    }

    public void end(String stepName) {
        Long time = System.currentTimeMillis() - timer.get(stepName);
        System.out.println(stepName + " time : " + time / 1000.0 + "s");
    }

    public SortHelper() {

    }

    private static class Timer extends ConcurrentHashMap<String, Long> {
    }
}
