package algorithm.offer;

public class Algorithm11 {

    /**
     * 使用二分法查找
     * 
     * @param arr
     * @return
     */
    public static int minValueInRotateArr(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= arr[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }
        return arr[left];

    }

    public static void main(String[] arg) {
        int[] arr = { 7, 8, 9, 10, 1, 2, 3, 4 };
        System.out.println(minValueInRotateArr(arr));
    }
}