package algorithm.offer;

public class Algorithm10_2 {

    /**
     * 空间复杂度 O(1) 时间复杂度O(N)
     * 
     * @param n
     * @return
     */
    public static int reactCover(int n) {

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
        System.out.println(reactCover(10));
    }
}