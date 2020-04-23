package algorithm.offer;

import java.util.HashSet;
import java.util.Set;

public class Algorithm3 {

    public static int duplicate1(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            if (set.add(i)) {
                return i;
            }
        }
        return Integer.parseInt(null);
    }

    public static int duplicate2(int[] arr) {
        if (null == arr || arr.length == 0) {
            return Integer.parseInt(null);
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i) {
                if (arr[arr[i]] == arr[i]) {
                    return arr[i];
                }
                //交换
                swap(arr, arr[i], i);
            }
        }
        return Integer.parseInt(null);
    }

    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 0, 2, 5};
        System.out.println(duplicate2(arr));
    }
}
