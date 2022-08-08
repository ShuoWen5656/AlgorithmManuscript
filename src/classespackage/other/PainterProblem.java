package classespackage.other;

/**
 * @author swzhao
 * @data 2022/8/7 9:46
 * @Discreption <> 画匠问题
 * 问题：一个数组描述一幅画所需要的时间，k个画匠画画，每一个画匠只能画连续的部分，最少需要多少时间才能完成画
 */
public class PainterProblem {


    /**
     * 经典动态规划
     * @param arr
     * @param k
     * @return
     */
    public static int mysolution1(int[] arr, int k){
        if (arr == null || arr.length == 0 || k < 1){
            return 0;
        }

        // dp[i][j]表示 j个画匠完成arr[0...i]所需要的最少时间
        int[][] dp = new int[arr.length][k];
        // 完成第一列
        dp[0][0] = arr[0];
        for (int i = 1; i < dp.length; i++){
            dp[i][0] = dp[i-1][0] + arr[i];
        }
        // 完成第一行
        for (int j = 1; j < dp[0].length; j++){
            dp[0][j] = arr[0];
        }
        // 完成剩下的,竖着来
        for (int j = 1; j < dp[0].length; j++){
            for (int i = 1; i < dp.length; i++){
                int min = Integer.MAX_VALUE;
                for (int l = i; l > 0; l--){
                    // l表示少一个画匠完成arr[0...l]
                    min = Math.min(min, Math.max(dp[l][j-1], dp[i][0] - dp[l][0]));
                }
                dp[i][j] = min;
            }
        }
        return dp[arr.length-1][k];
    }


    /**
     * 方法一：优化动态规划
     * 总时间 = Min(Max(sum(a[0,j], 1), sum(a[j, len], k-1)))
     * sum为arr在画匠为几的时候的完成最小值
     * @param arr
     * @param k
     * @return
     */
    public static int solution1(int[] arr, int k){
        if (arr == null || arr.length == 0 || k < 1){
            return 0;
        }
        // 累加和数组
        int[] sumArr = new int[arr.length];
        // 上一轮中的值，dp
        int[] map = new int[arr.length];
        sumArr[0] = 0;
        map[0] = 0;
        for (int i = 1; i < arr.length; i++){
            sumArr[i] = sumArr[i-1] + arr[i];
            map[i] = sumArr[i];
        }
        // 从只有一幅画开始
        for (int i = 1; i < arr.length; i++){
            // 画匠数不能比画数多
            for (int j = map.length-1; j > i-1; j--){
                int min = Integer.MAX_VALUE;
                for (int l = j ; l > 0; l--){
                    // 少一个画匠所用的时间、剩下的都让一个画匠去干所用的时间取最大值
                    min = Math.min(min, Math.max(map[l], sumArr[j] - sumArr[l]));
                }
                map[j] = min;
            }
        }
        return map[arr.length-1];
    }


    /**
     * 方法二：四边形不等式
     * @param arr
     * @param num
     * @return
     */
    public static int solution2(int[] arr, int num){
        if (arr == null || num < 1){
            return 0;
        }
        int[] sumArr = new int[arr.length];
        // 在i个画匠面前，j副画最少需要的时间，map[j]
        int[] map = new int[arr.length];
        // 四边形不等式专属变量，每一个元素为上一个的最优解的分割点k
        int[] cand = new int[arr.length];
        sumArr[0] = arr[0];
        map[0] = arr[0];
        for (int i = 1; i < arr.length ;i++){
            sumArr[i] = sumArr[i-1] + arr[i];
            map[i] = sumArr[i];
        }
        // i 是画匠数
        for (int i = 1; i < num; i++){
            // j是[0...j]副画需要画匠来画
            for (int j = arr.length-1; j > i-1; j++){
                int min = Integer.MAX_VALUE;
                int minPar = cand[j];
                int maxPar = j == arr.length-1 ? j:cand[j+1];
                for (int k = minPar; k <= maxPar; k++){
                    int cur = Math.max(map[k], sumArr[j] - sumArr[k]);
                    if (cur < min){
                        min = cur;
                        cand[j] = k;
                    }
                }
                map[j] = min;
            }
        }
        return map[arr.length-1];
    }





}
