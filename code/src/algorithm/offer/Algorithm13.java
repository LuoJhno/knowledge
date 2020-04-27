package algorithm.offer;

public class Algorithm13 {

    public static int integerBreak(int n) {
        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int timeOf3 = n / 3;
        if (n % 3 == 1) {
            timeOf3--;
        }
        int timeOf2 = (n - timeOf3 * 3) / 2;
        return (int) (Math.pow(3, timeOf3) * Math.pow(2, timeOf2));
    }

    public static int integerBreakOfDynamicProgram(int n) {
        if (n <= 2) {
            return 1;
        }
        int[] arr = new int[n+1];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i - j; j++) {
                arr[i] = Math.max(arr[i], arr[j] * arr[i - j]);
            }
        }
        return arr[n];
    }

    public static void main(String[] arg) {
        System.out.println(integerBreak(10));
        System.out.println(integerBreakOfDynamicProgram(10));
    }
}