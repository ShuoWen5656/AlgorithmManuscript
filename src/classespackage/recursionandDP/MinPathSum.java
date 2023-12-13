package classespackage.recursionandDP;

import classespackage.stackAndQueue.catDogQueue.Pet;

/**
 * @author swzhao
 * @date 2022/4/18 9:29 上午
 * @Discreption <>矩阵的最小路径和
 */
public class MinPathSum {

    /**
     * 给二维数组MxN
     * 求最短路径
     * 1、左边缘和右边缘最短路径应该都是直接相加
     * 2、中间的应该是上边缘的和与下边缘的最短和
     * 时间：O（MxN）
     * @param m
     * @return
     */
    public static int mimPathSum1(int[][] m){
        try {
            if(m == null || m.length == 0 || m[0] == null || m[0].length == 0){
                return 0;
            }
            // 获取行列值
            int row = m.length;
            int col = m[0].length;
            // 将结果存储在dp中
            int[][] dp = new int[row][col];
            dp[0][0] = m[0][0];
            //初始化行
            for (int i = 1; i < col; i++){
                dp[0][i] = dp[0][i - 1] + m[0][i];
            }
            // 初始化列
            for (int j = 1; j < row; j++){
                dp[j][0] = dp[j-1][0] + m[j][0];
            }
            // 计算行内
            for (int i = 1; i < col; i++){
                for (int j = 1; j < row; j++){
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + m[i][j];
                }
            }
            // 每一个地方的结果这时候都计算出来了
            return dp[row-1][col-1];
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 方法2，压缩空间法
     * 1、只适用于计算最优解的值，不能计算最优解得路径
     * @param m
     * @return
     */
    public static int mimPathSum2(int[][] m){
        if(m == null || m.length == 0 || m[0] == null || m[0].length == 0){
            return 0;
        }
        int less = Math.max(m.length, m[0].length);
        int more = Math.min(m.length, m[0].length);
        boolean isRowMax = m.length > m[0].length;
        // 辅助结果，滚动数组
        int[] array = new int[less];
        array[0] = m[0][0];
        // 将第一行的结果放入array中
        for (int i = 1; i < less; i++){
            array[i] = array[i - 1] + (isRowMax? m[i][0] : m[0][i]);
        }
        // 滚动填充
        for (int i = 1; i < more; i++){
            // 先初始化array[0]
            array[0] = array[0] + (isRowMax? m[0][i] : m[i][0]);
            for (int j = 1; j < less; j++){
                array[j] = Math.min(array[j-1], array[j]) + (isRowMax? m[j][i] : m[i][j]);
            }
        }
        return array[array.length - 1];
    }


    /**
     * 递归方法
     * @param arr
     * @return
     */
    public static int minPath(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
             return 0;
        }
        return process(arr, arr.length - 1, arr[0].length - 1);
    }

    /**
     * 递归主体
     * 返回到达arr[i][j] 的最小距离
     * @param arr
     * @param i 横坐标
     * @param j 纵坐标
     */
    private static int process(int[][] arr, int i, int j) {
        if (i == 0 && j == 0) {
            return arr[i][j];
        }else if (i == 0) {
            return arr[i][j] + process(arr, i, j-1);
        }else if (j == 0) {
            return arr[i][j] + process(arr, i-1 , j);
        }else {
            // 找到前面或者上面较小的那个
            return arr[i][j] + Math.min(process(arr, i-1, j), process(arr, i, j-1));
        }
    }


    /**
     * 动态规划最小路径
     * @param arr
     * @return
     */
    public static int minPathForDP(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return 0;
        }
        // 动态规划矩阵dp[i][j]代表到arr[i][j]的最短路径和
        int[][] dp = new int[arr.length][arr[0].length];
        dp[0][0] = arr[0][0];
        for (int i = 1; i < arr.length; i++) {
            dp[0][i] = dp[0][i-1] + arr[0][i];
        }
        for (int j = 1; j < arr[0].length; j++) {
            dp[j][0] = dp[j-1][0] + arr[j][0];
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                dp[i][j] = arr[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[arr.length-1][arr.length-1];
    }

    /**
     * 动态规划最小路径
     * 空间降低一个维度
     * @param arr
     * @return
     */
    public static int minPathForDPReduceMem(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        dp[0] = arr[0][0];
        for (int i = 1; i < arr.length; i++) {
            dp[i] = dp[i-1] + arr[0][i];
        }
        for (int i = 1; i < arr.length; i++) {
            dp[0] += arr[i][0];
            for (int j = 1; j < arr.length; j++) {
                dp[j] = arr[i][j] + Math.min(dp[j-1], dp[j]);
            }
        }
        return dp[arr.length-1];
    }


    public static void main(String[] args) {
        int[][] arr = {
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}
        };
        System.out.println(minPathForDPReduceMem(arr));
    }



}
