package algorithm.offer;

public class Algorithm39 {
    public int moreThanHalfNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int count = 1;
        int major = arr[0];
        for (int i = 1; i < arr.length; i++) {
            count = arr[i] == major ? count + 1 : count - 1;
            if (count > arr.length / 2) {
                return major;
            }
            if (count == 0) {
                major = arr[i];
                count = 1;
            }
        }
        int cnt = 0;
        for (int num : arr) {
            if (major == num) {
                cnt++;
            }
        }
        return cnt > arr.length / 2 ? major : 0;

    }
}