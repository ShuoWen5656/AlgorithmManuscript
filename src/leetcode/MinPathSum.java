package leetcode;

/**
 * @author swzhao
 * @date 2023/12/7 9:57 下午
 * @Discreption <> 最小路径和
 */
public class MinPathSum {


    public static void main(String[] args) {
        solution(new int[][]{{9,1,4,8}});
    }

    public static int solution(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int j = 1; j < grid[0].length; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[dp.length - 1][dp[0].length-1];
    }

}
