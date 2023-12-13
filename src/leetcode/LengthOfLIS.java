package leetcode;

/**
 * @author swzhao
 * @date 2023/12/6 9:55 下午
 * @Discreption <> 最长递增子序列
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        solution(new int[]{7,7,7,7,7,7,7});
    }


    public static int solution(int[] nums) {
        // dp[i]表示以i结尾的数组递增子序列的长度
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < dp.length;i ++) {
            int max = 0;
            for (int j = i-1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++ ) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
