package algorithm.offer;

public class Algorithm10_1 {
    /**
     * 递归求解
     * @param n
     * @return
     */
    public static int febonacci(int n) {
        if (n <= 2) {
            return n;
        }
        return febonacci(n - 1) + febonacci(n - 2);
    }

    /**
     * 空间复杂度 O(N) 时间复杂度O(N)
     * 
     * @param n
     * @return
     */
    public static int febonacciWithDynamicProgram1(int n) {
        int[] resultArr = new int[n];
        resultArr[0] = 1;
        resultArr[1] = 2;
        for (int i = 2; i < n; i++) {
            resultArr[i] = resultArr[i - 1] + resultArr[i - 2];
        }
        return resultArr[n - 1];
    }

    /**
     * 空间复杂度 O(1) 时间复杂度O(N)
     * 
     * @param n
     * @return
     */
    public static int febonacciWithDynamicProgram2(int n) {

        int pre1 = 1;
        int pre2 = 2;
        int result = 0;
        for (int i = 2; i < n; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }

    public static void main(String[] arg) {
        System.out.println(febonacci(10));
        System.out.println(febonacciWithDynamicProgram1(10));
        System.out.println(febonacciWithDynamicProgram2(10));
    }
}