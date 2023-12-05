package leetcode;

/**
 * @author swzhao
 * @date 2023/12/5 10:16 下午
 * @Discreption <> 打家劫舍
 */
public class Rob {


    public static void main(String[] args) {
        solution(new int[]{2,7,9,3,1});
    }

    public static int solution(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }else if (nums.length == 1){
            return nums[0];
        }else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 至少3家
        // dp[i]偷到i+1家最多能偷多少
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1] - nums[i - 1], dp[i - 2]) + nums[i];
        }
        return Math.max(dp[nums.length - 1], dp[nums.length - 2]);
    }
}
