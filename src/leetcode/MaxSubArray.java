package leetcode;

/**
 * @author swzhao
 * @data 2023/9/29 10:09
 * @Discreption <>最大子数组和
 */
public class MaxSubArray {

    public static void main(String[] args) {
        System.out.println(solution1(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }



    public static int solution1(int[] nums) {
        if (nums == null) {
            return 0;
        }
        // dp[i]表示以nums[i]结尾的和最大的子数组之和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i-1] < 0) {
                // 前面已经小于0了，就不加了,帮不了什么忙
                dp[i] = nums[i];
            }else{
                dp[i] = nums[i] + dp[i-1];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++ ){
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
