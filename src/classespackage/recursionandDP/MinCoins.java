package classespackage.recursionandDP;

/**
 * @author swzhao
 * @date 2022/4/19 3:45 下午
 * @Discreption <>换钱的最少货币数
 */
public class MinCoins {

    /**
     * 货币张数无限
     * array.length * (aim+1)的矩阵用来存储硬币
     * @return
     */
    public static int minCoins1(int[] array, int aim){
        try{
            if(array == null || array.length == 0 || aim < 0){
                return -1;
            }
            int max = Integer.MAX_VALUE;
            // 创建dp矩阵
            int[][] dp = new int[array.length][aim+1];
            // 初始化行
            for (int i = 1; i < aim + 1; i++){
                // 先给初始值为最大值，也就是当前值i，无法使用array[0]拼凑而成
                dp[0][i] = max;
                // 只用array[0]拼凑值i，如果i-array[0]没有办法用若干array[0]拼凑，那么i也无法拼凑
                // 但是如果i-array[0]可以拼凑，那么i一定可以拼凑，再多一张就行
                if(i - array[0] >= 0 && dp[0][i-array[0]] != max){
                    dp[0][i] = dp[0][i-array[0]] + 1;
                }
            }
            // 因为java会初始化int数组为0，所以无需初始化列，c++需要初始化
            // 计算内容
            for (int i = 1; i < array.length; i++){
                for (int j = 1; j < aim + 1; j++){
                    int left = max;
                    // 这里的判断就是：如果一定有array[i]参与，那么可能是1个或者多个然后最终凑成j
                    // 如果是多个，那么此时减掉一个array[i]后一定也是能够有正确结果的，结果就是当前的结果-1即可
                    // 如果是0个，那么此时减掉1个array[i]可能会小于0，那么答案应该再dp[i-1][j]（即没有当前数的凑j的结果）获取。
                    if(j - array[i] >= 0 && dp[i][j-array[i]] != max){
                        left = dp[i][j-array[i]];
                    }
                    dp[i][j] = Math.min(left, dp[i-1][j]);
                }
            }
            return dp[array.length-1][aim] == max ? -1: dp[array.length-1][aim];
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 方法二：空间压缩
     * @param array
     * @param aim
     * @return
     */
    public static int minCoins2(int[] array, int aim){
        if(array == null || array.length == 0 || aim < 0){
            return -1;
        }
        // 空间压缩法
        int[] dp = new int[aim + 1];
        int max = Integer.MAX_VALUE;
        // 初始化
        for (int j = 1; j < aim + 1; j++){
            dp[j] = max;
            // dp[0] = 0
            if(j - array[0] >= 0 && dp[j-array[0]] != max){
                dp[j] = dp[j-array[0]] + 1;
            }
        }
        // 滚动计算其他列
        for (int i = 1; i < array.length; i++){
            for (int j = 1; j < aim + 1; j++){
                int tem = max;
                if(j - array[i] >= 0 && dp[j-array[i]] != max){
                    tem = dp[j-array[i]] + 1;
                }
                dp[j] = Math.min(dp[j], tem);
            }
        }
        return dp[aim] == max? -1 : dp[aim];
    }

    /**
     * 当每一种货币只能使用一张时
     * @param array
     * @param aim
     * @return
     */
    public static int minCoins3(int[] array, int aim){
        try {
            if(array == null || array.length == 0 || aim < 0){
                return -1;
            }
            int max = Integer.MAX_VALUE;
            int[][] dp = new int[array.length][aim+1];
            for (int i = 1; i <= aim; i++){
                dp[0][i] = max;
            }
            // 这里不同：初始化只能一张array[0]，其余的都不能拼凑了
            if(array[0] <= aim){
                dp[0][array[0]] = 1;
            }
            for (int i = 1; i < array.length; i++){
                for (int j = 1; j <= aim; j++){
                    int tem = max;
                    // 这里需要比较的是不用当前货币拼凑出来的刚好缺少array[i]的种树
                    // 每一种货币只能用一次，所以dp[i][j-array[i]]没有意义
                    if(j-array[i] >= 0 && dp[i-1][j-array[i]] != max){
                        tem = dp[i-1][j-array[i]] + 1;
                    }
                    dp[i][j] = Math.min(tem, dp[i-1][j]);
                }
            }
            return dp[array.length-1][aim];
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 方法二： 压缩空间
     * 因为要用到前面的数据，所以这里倒着赋值
     * @param array
     * @param aim
     * @return
     */
    public static int minCoins4(int[] array, int aim){
        try {
            if(array == null || array.length == 0 || aim < 0){
                return -1;
            }
            int max = Integer.MAX_VALUE;
            int[] dp = new int[aim+1];
            // 初始化dp
            for (int j = 1; j <= aim; j++){
                dp[j] = max;
            }
            if(aim >= array[0]){
                dp[array[0]] = 1;
            }
            for (int i = 1; i < array.length; i++){
                // 这里需要用到前面i-1的数据，所以到倒着来
                 for (int j = aim; j > 0; j--){
                     int tem = max;
                     if(j-array[i] >= 0 && dp[j-array[i]] != max){
                         tem = dp[j-array[i]] + 1;
                     }
                     dp[j] = Math.min(dp[j], tem);
                 }
            }
            return dp[aim];
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

}