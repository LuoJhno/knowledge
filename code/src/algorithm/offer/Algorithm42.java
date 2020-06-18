package algorithm.offer;

public class Algorithm42 {
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int i : nums) {
            if (sum >= 0) {
                sum += i;
            } else {
                sum = i;
            }
            res = Math.max(sum, res);
        }
        return res;
    }
}