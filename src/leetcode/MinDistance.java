package leetcode;

/**
 * @author swzhao
 * @date 2023/12/9 8:16 下午
 * @Discreption <>编辑距离
 */
public class MinDistance {




    public static int solution(String word1, String word2) {
        int row = word1.length();
        int col = word2.length();
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        // dp[i] 表示从word1[0...i] 到word2[0...j] 最少需要多少步骤
        int[][] dp = new int[row + 1][col + 1];
        dp[0][0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i-1][0] + 1;
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = dp[0][j-1] + 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (chars1[i-1] == chars2[j-1]) {
                    // 相等则直接继承
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    // 不相等则必须存在增一次替换
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                // 剩下两种都应该试一下,增删的情况
                dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + 1);
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + 1);
            }
        }
        return dp[row][col];
    }

}
