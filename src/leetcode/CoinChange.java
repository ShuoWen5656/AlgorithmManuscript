package leetcode;

/**
 * @author swzhao
 * @date 2023/12/6 8:59 下午
 * @Discreption <> 零钱兑换
 */
public class CoinChange {


    public static void main(String[] args) {
        solution(new int[]{1,2}, 2);
    }


    public static int solution(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        // dp[i] 代表组成i的最少硬币数量
        int[][] dp = new int[coins.length][amount+1];
        // 初始化行列
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = j % coins[0] == 0 ? j/coins[0] : -1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int count = 0;
                int max = Integer.MAX_VALUE;
                while (j - count * coins[i] >= 0) {
                    if (dp[i-1][j - count * coins[i]] != -1) {
                        // 说明能够组成,则找到最小值
                        max = Math.min(max, dp[i-1][j - count * coins[i]] + count);
                    }
                    count++;
                }
                dp[i][j] = max == Integer.MAX_VALUE ? -1 : max;
            }
        }
        return dp[coins.length-1][amount];
    }


}
