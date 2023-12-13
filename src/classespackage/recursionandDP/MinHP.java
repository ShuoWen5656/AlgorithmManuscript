package classespackage.recursionandDP;

import java.util.Map;

/**
 * @author swzhao
 * @date 2022/4/27 10:30 上午
 * @Discreption <>龙与地下城游戏
 * 给定二维矩阵，计算骑士从左上到右下角至少需要多少初始血量
 */
public class MinHP {

    /**
     * 从地图左上角到右下角需要多少的初始血量
     * dp[i][j]为骑士从ij出发到右下角至少需要的血量
     * dp[i][j] + map[i][j] = dp[i+1][j] 或 dp[i][j+1]
     * @param map
     * @return
     */
    public static int minHP1(int[][] map){
        try{
            if(map == null || map.length == 0 || map[0].length == 0){
                return 0;
            }
            int row = map.length;
            int col = map[0].length;
            int[][] dp = new int[row][col];
            // 至少需要的血量向上不限制，但是如果一直回血的话，至少1即可，血量初始不能为负值
            dp[row-1][col-1] = Math.max(1, 1-map[row-1][col-1]);
            // 初始化行列
            for (int i = row-2; i >= 0; i--){
                dp[i][row-1] = Math.max(1, dp[i+1][col-1] - map[i][col-1]);
            }
            for (int j = col-2; j >= 0; j--){
                dp[row-1][j] = Math.max(1, dp[row-1][j+1] - map[row-1][j]);
            }
            for (int i = row-2; i >= 0; i--){
                for (int j = col-2; j >= 0; j++){
                    // 向下走和向右走的至少血量的最小值就是答案
                    dp[i][j] = Math.min(Math.max(1, dp[i][j+1] - map[i][j]), Math.max(1, dp[i+1][j] - map[i][j]));
                }
            }
            return dp[0][0];
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 方法二：
     * 空间压缩,这里不按照最小的行列计算，直接按照行即可
     * @param map
     * @return
     */
    public static int minHP(int[][] map){
        try {
            if(map == null || map.length == 0 || map[0].length == 0){
                return 0;
            }
            int row = map.length;
            int col = map[0].length;
            int[] dp = new int[col];
            dp[col - 1] = Math.max(1, 1-map[row-1][col-1]);
            for (int i = col-2; i >= 0; i--){
                dp[i] = Math.max(1, dp[i+1] - map[row-1][i]);
            }
            for (int i = row - 2; i >= 0; i--){
                for (int j = col-2; j >= 0; j--){
                    dp[j] = Math.min(Math.max(1, dp[j+1] - map[i][j]), Math.max(1, dp[j] - map[i][j]));
                }
            }
            return dp[0];
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 二轮：经典动态规划
     * @param map
     * @return
     */
    public static int minHpCP(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int row = map.length;
        int col = map[0].length;
        int[][] dp = new int[row][col];
        dp[row-1][col-1] = 1 - map[row-1][col-1] > 0 ? 1 - map[row-1][col-1] : 1;
        for (int i = row-2; i >= 0; i--) {
            dp[i][col-1] = dp[i+1][col-1] - map[i][col-1] > 0 ? dp[i+1][col-1] - map[i][col-1] : 1;
        }
        for (int j = col-2; j >= 0; j--) {
            dp[row-1][j] = dp[row-1][j+1] - map[row-1][j] > 0 ? dp[row-1][j+1] - map[row-1][j] : 1;
        }
        for (int i = row-2; i >= 0; i--) {
            for (int j = col-2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i+1][j], dp[i][j+1]) - map[i][j] > 0 ? Math.min(dp[i+1][j], dp[i][j+1]) - map[i][j] : 1;
            }
        }
        return dp[0][0];
    }

    /**
     * 二轮：动态规划降低空间复杂度版本
     * @param map
     * @return
     */
    public static int minHPCPReduceMem(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int row = map.length;
        int col = map[0].length;
        int[] dp = new int[col];
        dp[col-1] = 1 - map[row-1][col-1] > 0 ? 1 - map[row-1][col-1] : 1;
        for (int j = col-2; j >= 0; j --) {
            dp[j] = dp[j+1] - map[row-1][j] > 0 ? dp[j+1] - map[row-1][j] : 1;
        }
        for (int i = row-2; i >= 0; i--) {
            // 每一行先初始化第一个
            dp[col-1] = dp[col-1] - map[i][col-1] > 0 ? dp[col-1] - map[i][col-1] : 1;
            for (int j = col-2; j >= 0 ; j--) {
                dp[j] = Math.min(dp[j], dp[j+1]) - map[i][j] > 0 ? Math.min(dp[j], dp[j+1]) - map[i][j] :1;
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        System.out.println(minHPCPReduceMem(new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {0, 30, -5}
        }));
    }


}
