package classespackage.recursionandDP;

/**
 * @author swzhao
 * @date 2022/4/20 9:22 上午
 * @Discreption <>换钱的方法数
 */
public class CoinsChange {

    /**
     * 方法一：递归方法
     * 暴力递归O(aim^n)
     * @param array
     * @param num
     * @return
     */
    public static int coins1(int[] array, int num){
        if(array == null || array.length == 0 || num < 0){
            return 0;
        }
        return process1(array, 0, num);
    }


    /**
     * 递归含义：
     * 从index开始往后的几个货币种类来完成aim拼凑，前面的已经用过
     * @param array
     * @param index
     * @param aim
     * @return
     */
    public static int process1(int[] array, int index, int aim){
        int res = 0;
        if(index == array.length){
            // 递归到越界后，判断是否前面的已经完成aim目标，也就是这波递归aim为0
            // 如果是0，则这条递归有效，返回一种方法，如果不是0，则返回0，这波递归无法拼凑aim
            return res = aim == 0? 1: 0;
        }else{
            // 如果还没有递归到最后一个index，则循环当前index数值
            for (int i = 0; array[index] * i <= aim; i++){
                // 1、2、3个array[index]时剩下的拼凑出aim的种数
                res += process1(array, index+1, aim - array[index] * i);
            }
        }
        return res;
    }

    /**
     * 方法二：
     * 初步优化：记忆搜索 O(Nxaim^2)
     * 重复计算：当2张5元和0张10元与0张5元，1张10元等价时，都需要计算process（array, 2, aim）,参数完全一样，但是需要计算两次
     * 添加一个全局变量map存储每一次的计算结果，a表示计算过，-1表示计算过但返回值为0，0表示没有计算过（因为二维数组初始化时为0）
     * @param array
     * @param num
     * @return
     */
    public static int coins2(int[] array, int num){
        if(array == null || array.length == 0 || num < 0){
            return 0;
        }
        int[][] map = new int[array.length+1][num+1];
        return process2(array, 0, num, map);
    }

    public static int process2(int[] array, int index, int aim, int[][] map){
        int res = 0;
        if(index == array.length){
            return res = aim == 0? 1 : 0;
        }else{
            for (int i = 0; array[index] * i <= aim; i++){
                if(map[index+1][aim-array[index] * i] != 0
                        && map[index+1][aim-array[index] * i] != -1){
                    res += map[index+1][aim-array[index] * i];
                }else if(map[index+1][aim-array[index] * i] == 0){
                    // 没有计算
                    res += process2(array, index+1, aim-array[index]*i, map);
                }
            }
        }
        // 每一层结束之后将map更新
        map[index][aim] = res == 0? -1 : res;
        return res;
    }


    /**
     * 方法三：
     * 经典动态规划O(Nxaim^2)，最坏情况下，需要枚举aim次结果相加
     * 比如当前面值为1，则相加时，需要将前面的都相加一遍，后面每一个都要相加一遍所以aim^2
     * @param array
     * @param aim
     * @return
     */
    public static int coins3(int[] array, int aim){
        if(array == null || array.length == 0 || aim < 0){
            return 0;
        }
        // 初始化数组
        int[][] dp = new int[array.length][aim+1];
        initDp(dp, array, aim);
        // 计算内容,按照行来
        for (int i = 1; i < array.length; i++){
            for (int j = 1; j < aim+1; j++){
                // 初始化为不用array[i]时的种数
                int res = 0;
                // 一种以上时,用一张，剩下的不用，所以应该是i-1
                for (int k = 0; j - array[i] * k >0; k++){
                    res += dp[i-1][j-array[i]];
                }
                dp[i][j] = res;
            }
        }
        return dp[array.length - 1][aim];
    }


    /**
     * 方法4：
     * 优化动态规划：O（N*aim）
     * @param array
     * @param aim
     * @return
     */
    public static int coins4(int[] array, int aim){
        if(array == null || array.length == 0 || aim < 0){
            return 0;
        }
        // 初始化数组
        int[][] dp = new int[array.length][aim+1];
        initDp(dp, array, aim);
        for (int i = 1; i < array.length; i++){
            for (int j = 1; j < aim + 1; j++){
                int res = dp[i-1][j];
                if(j - array[i] > 0){
                    // dp[i][j-array[i]] 其实就是除了dp[i-1][j]的累加和
                    res += dp[i][j-array[i]];
                }
                dp[i][j] = res;
            }
        }
        return dp[array.length - 1][aim];
    }


    /**
     * 方法五：
     * 动态规划+空间压缩
     * @param array
     * @param aim
     * @return
     */
    public static int coins5(int[] array, int aim){
        if(array == null || array.length == 0 ||  aim < 0){
            return 0;
        }
        int[] dp = new int[aim+1];
        // 初始化行
        for (int k = 0,i = 0; i * array[0] <= aim; i++, k = i * array[0]){
            array[k] = 1;
        }
        for (int i = 1; i < array.length ; i++){
            for (int j = 1; j < aim + 1; j++){
                dp[j] = dp[j] + j-array[i] > 0? dp[j - array[i]] : 0;
            }
        }
        return dp[aim];
    }

    /**
     * 初始化行列公共方法
     * 10010001
     * 10000000
     * 10000000
     * 1...
     * 1
     * @param dp
     * @param array
     * @param aim
     */
    public static void initDp(int[][] dp, int[] array,int aim){
        // 初始化行
        for (int i = 1; i*array[0] <= aim; i++){
            dp[0][array[0] * i] = 1;
        }
        // 初始化列
        for (int i = 0; i<array.length; i++){
            // 拼凑0的种数永远都是1个
            dp[i][0] = 1;
        }
    }


    /**
     * 二轮 - 递归方法
     */
    public static int coinsCP1(int[] arr, int aim) {
        if (arr == null || arr.length == 0
                || aim < 0){
            return 0;
        }
        return processCoinsCP1(arr, 0, aim);
    }

    private static int processCoinsCP1(int[] arr, int start, int aim) {
        if (aim == 0) {
            // 剩下的组成0的方法数为1
            return 1;
        }
        if (start == arr.length) {
            // 越界
            return 0;
        }
        int res = 0;
        for (int i = 0; i <= aim/arr[start]; i++) {
            res += processCoinsCP1(arr, start + 1, aim - (i * arr[start]));
        }
        return res;
    }

    /**
     * 经典动态规划
     * @param arr
     * @param aim
     * @return
     */
    public static int coinsCP2(int[] arr, int aim) {
        if (arr == null || arr.length == 0
                || aim < 0){
            return 0;
        }
        int[][] dp = new int[arr.length][aim+1];
        // 初始化行
        for (int i = 0; i <= aim; i++) {
            dp[0][i] = i%arr[0] == 0? 1 : 0;
        }
        // 初始化列
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                int res = 0;
                for (int k = 0; k <= j/arr[i]; k++) {
                    res += dp[i-1][j- k * arr[i]];
                }
                dp[i][j] = res;
            }
        }
        return dp[arr.length - 1][aim];
    }

    /**
     * 节省空间版本
     * @param arr
     * @param aim
     * @return
     */
    public static int coinsCP2ReduceMem(int[] arr, int aim) {
        if (arr == null || arr.length == 0
                || aim < 0){
            return 0;
        }
        int[] dp = new int[aim+1];
        // 初始化行
        for (int i = 0; i <= aim; i++) {
            dp[i] = i%arr[0] == 0? 1 : 0;
        }
        for (int i = 1; i < arr.length; i++) {
            // 缓存
            int[] dpCp = new int[aim+1];
            // 0初始化为1，后面不会被计算到
            dpCp[0] = 1;
            for (int j = 1; j <= aim; j++) {
                int res = 0;
                for (int k = 0; k <= j/arr[i]; k++) {
                    res += dp[j- k * arr[i]];
                }
                dpCp[j] = res;
            }
            dp = dpCp;
        }
        return dp[aim];
    }

    /**
     * 动态规划最优解 aim*N复杂度
     * @param arr
     * @param aim
     * @return
     */
    public static int coinsCP3(int[] arr, int aim) {
        if (arr == null || arr.length == 0
                || aim < 0){
            return 0;
        }
        int[][] dp = new int[arr.length][aim+1];
        // 初始化行
        for (int i = 0; i <= aim; i++) {
            dp[0][i] = i%arr[0] == 0? 1 : 0;
        }
        // 初始化列
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i-1][j] + (j-arr[i] >= 0? dp[i][j-arr[i]] : 0);
            }
        }
        return dp[arr.length - 1][aim];
    }

    /**
     * 动态规划最优解 aim*N复杂度,空间复杂度O(aim)
     * @param arr
     * @param aim
     * @return
     */
    public static int coinsCP3ReduceMem(int[] arr, int aim) {
        if (arr == null || arr.length == 0
                || aim < 0){
            return 0;
        }
        int[] dp = new int[aim+1];
        // 初始化行
        for (int i = 0; i <= aim; i++) {
            dp[i] = i%arr[0] == 0? 1 : 0;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[j] = dp[j] + (j-arr[i] >= 0 ? dp[j-arr[i]] : 0);
            }
        }
        return dp[aim];
    }




    public static void main(String[] args) {
        System.out.println(coinsCP3ReduceMem(new int[]{5,10,25,1}, 15));
    }


}
