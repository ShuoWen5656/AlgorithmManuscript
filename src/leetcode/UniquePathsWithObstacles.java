package leetcode;

/**
 * @author swzhao
 * @date 2023/12/8 8:27 下午
 * @Discreption <> 不同路径 II
 */
public class UniquePathsWithObstacles {

    public static void main(String[] args) {
        solution(new int[][]
                {
                        {0,1},
                        {0,0}
                });
    }

    public static int solution(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1) {
            return 0;
        }
        // dp[i][j] 表示到达ij 可能的种数，不可达为0
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        dp[0][0] = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i-1][0] == 0 || obstacleGrid[i][0] == 1 ? 0 : 1;
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = dp[0][j-1] == 0 || obstacleGrid[0][j] == 1 ? 0 : 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    // 障碍物
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

}
