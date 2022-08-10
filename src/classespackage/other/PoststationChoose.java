package classespackage.other;

/**
 * @author swzhao
 * @data 2022/8/10 19:46
 * @Discreption <> 邮局选址问题
 */
public class PoststationChoose {


    /**
     * 方法一：动态规划
     * @param arr 居民所在横坐标
     * @param num
     * @return
     */
    public static int minDistants1(int[] arr, int num){
        if (arr == null || arr.length == 0 || num < 1){
            return 0;
        }
        // w[i][j] 表示 arr[i...j]只放一个邮局的时候最短距离是多少，很显然应该选择中点作为邮局落点，求和的话可以使用上一个的答案来计算
        int[][] w = new int[arr.length][arr.length];
        // dp[i][j] 表示 i+1个邮局落在[0...j]之间最短距离是多少
        int[][] dp = new int[num][arr.length];
        w[0][0] = 0;
        for (int i = 1; i < arr.length; i++){
            // 只有自己的时候最短距离为0
            w[i][i] = 0;
            for (int j = i; j < arr.length; j++){
                // [i...j]中点在增加一个节点后还是中点，所以总距离只需要增加新节点-中点即可
                w[i][j] = w[i][j-1] + arr[j] - arr[(i+j)/2];
            }
        }
        for (int i = 1; i < num;i++){
            for (int j = i ; j < arr.length; j++){
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < j; k++){
                    // 前[0...k]个分配给i-1个邮局，后[k...j]个分配给一个
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][k] + w[k+1][j]);
                }
            }
        }
        return dp[num-1][arr.length-1];
    }

}
