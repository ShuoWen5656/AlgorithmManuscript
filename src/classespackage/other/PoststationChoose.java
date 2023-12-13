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


    /**
     * 方法二:
     * 四边形不等式降低一介
     * @param arr
     * @param num
     * @return
     */
    public static int minDistants2(int[] arr, int num){
        if (arr == null || arr.length == 0 || num < 1){
            return 0;
        }
        int[][] w = new int[arr.length][arr.length];
        int[][] dp = new int[num][arr.length];
        // 上一局的最优解,
        // todo 其中的横坐标是否有意义呢？？？？
        int[][] cand = new int[num][arr.length];
        // 0~0的居民放一个邮局最短距离就是0
        w[0][0] = 0;
        // 初始化w
        for (int i = 1; i < arr.length; i++){
            w[i][i] = 0;
            for (int j = i; j < arr.length; j++){
                w[i][j] = w[i][j-1] + arr[j] - arr[(i+j)/2];
            }
        }
        for (int i = 0; i < arr.length; i++){
            dp[0][i] = w[0][i];
            cand[0][i] = 0;
        }
        for (int i = 1; i < num; i++){
            for (int j = arr.length-1; j > i; j--){
                dp[i][j] = Integer.MAX_VALUE;
                // 上下界计算
                // todo 当前倒着赋值的话，当前j应该是i-1那一轮的结果
                int min = cand[i-1][j];
                // todo 当前倒着赋值，j+1应该是当前轮的赋值结果，所以i应该是没有意义的，cand完全可以使用一维数组代替
                int max = j == arr.length-1? j : cand[i][j+1];
                for (int k = min; k < max; k++){
                    int cur = dp[i-1][k] + w[k+1][j];
                    if (cur < dp[i][j]){
                        cand[i][j] = k;
                        dp[i][j] = cur;
                    }
                }
            }
        }
        return dp[num-1][arr.length-1];
    }

    /**
     * 二轮测试：动态规划解法
     * @return
     */
    public static int solution1Cp1(int[] arr, int num) {
        if (arr == null || num == 0) {
            return -1;
        }
        // wArr[i][j] 表示 arr[i...j]中只放一个邮局时需要的最短距离数
        int[][] wArr = getWArr(arr);
        // dp[i][j]代表i个邮局解决 arr[0..j]的居民问题
        int[][] dp = new int[num + 1][arr.length];
        // 初始化dp
        dp[1][0] = 0;
        for (int j = 1; j < dp[0].length; j++) {
            dp[1][j] = wArr[0][j];
        }
        // 动态规划主体
        for (int i = 2; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j <= i) {
                    // 每一个居民都能分到邮局，直接没有距离
                    dp[i][j] = 0;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                // 第一个邮局负责arr[k...j]，剩下的负责arr[0..k-1]
                for (int k = j; k >= 1; k--) {
                    // 第一个邮局解决的最短距离
                    int first = wArr[k][j];
                    int second = dp[i-1][k-1];
                    min = Math.min(min, first+second);
                }
                dp[i][j] = min;
            }
        }
        return dp[num][arr.length-1];
    }

    /**
     * wArr[i][j] 表示 arr[i...j]中只放一个邮局时需要的最短距离数
     * @param arr
     * @return
     */
    private static int[][] getWArr(int[] arr) {
        int[][] wArr = new int[arr.length][arr.length];
        // 只需要填充上半部分
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                // 这里需要解释一下，中点因为两边的元素数目相同（偶数特殊，但也相同），滑动时保持两边数相同时能够保证总合不变
                wArr[i][j] = wArr[i][j-1] + arr[j] - arr[(i+j)/2];
            }
        }
        return wArr;
    }

    /**
     * 二轮测试：四边形不等式
     * @return
     */
    public static int solution2Cp2(int[] arr, int num) {
        if (arr == null || num == 0) {
            return -1;
        }
        // wArr[i][j] 表示 arr[i...j]中只放一个邮局时需要的最短距离数
        int[][] wArr = getWArr(arr);
        // dp[i][j]代表i个邮局解决 arr[0..j]的居民问题的最短距离
        int[][] dp = new int[num + 1][arr.length];
        // map[i][j]代表i个邮局解决 arr[0..j]的居民问题最优解时的k值
        int[][] map = new int[num + 1][arr.length];
        // 初始化dp
        dp[1][0] = 0;
        for (int j = 1; j < dp[0].length; j++) {
            dp[1][j] = wArr[0][j];
        }
        // 动态规划主体
        for (int i = 2; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j <= i) {
                    // 每一个居民都能分到邮局，直接没有距离
                    dp[i][j] = 0;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                // 左边至少给一个居民让给剩下的邮局，因为邮局至少有两个
                int l = map[i-1][j] == 0 ? 1 : map[i-1][j];
                int r = j == dp[0].length-1 ? dp[0].length-1 : map[i][j+1];
                // 第一个邮局负责arr[k...j]，剩下的负责arr[0..k-1]
                for (int k = r; k >= l; k--) {
                    // 第一个邮局解决的最短距离
                    int first = wArr[k][j];
                    int second = dp[i-1][k-1];
                    if (first + second < min) {
                        min = first+second;
                        map[i][j] = k;
                    }
                }
                dp[i][j] = min;
            }
        }
        return dp[num][arr.length-1];
    }




    public static void main(String[] args) {
        System.out.println(solution2Cp2(new int[]{1,2,3,4,5,1000}, 2));
    }




}
