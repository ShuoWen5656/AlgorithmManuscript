package leetcode;

/**
 * @author swzhao
 * @date 2023/12/5 10:06 下午
 * @Discreption <> 爬楼梯
 */
public class ClimbStairs {



    public int solution(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n];
        // 代表上i+1阶楼梯有多少种
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }


}
