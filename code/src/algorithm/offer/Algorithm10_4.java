package algorithm.offer;

public class Algorithm10_4 {

    public static int jumpFloor2(int n) {
        int[] resultArr = new int[n];
        resultArr[0] = 1;
        resultArr[1] = 2;
        for (int i = 2; i < n; i++) {
            for (int j = 0; j < i; j++) {
                resultArr[i] += resultArr[j];
            }
        }
        return resultArr[n - 1];

    }

    public static void main(String[] arg) {
        System.out.println(jumpFloor2(10));
    }
}