package classespackage.recursionandDP;

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

}
