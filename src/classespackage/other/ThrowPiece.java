package classespackage.other;

import java.util.Map;

/**
 * @author swzhao
 * @data 2022/8/1 21:58
 * @Discreption <> 丢棋子问题
 * 问题：一共level层楼，棋子数量为pieceNum，每一层都可以扔棋子，棋子可能碎掉也可能不碎，现在需要找到碎与不碎的临界楼层，在最坏的情况下，扔最少的次数是多少
 */
public class ThrowPiece {

    /**
     * 方法一：
     * 递归暴力方法 O(n!)
     * @param levelNum
     * @param pieceNum
     */
    public static int solution1(int levelNum, int pieceNum){
        if (levelNum < 1 || pieceNum < 1){
            return 0;
        }
        return process1(levelNum, pieceNum);
    }

    private static int process1(int levelNum, int pieceNum) {
        if (levelNum == 1){
            // 只有一层
            return 1;
        }
        if (pieceNum == 1){
            // 只有一个棋子了，多少层就要多少次
            return levelNum;
        }
        int min = Integer.MAX_VALUE;
        // 否则需要遍历每一种情况，比如第一层碎还是没碎？碎了则问题就变成process1(1, pieceNum-1),没碎process(n-1, pieceNum-1)
        for (int i = 1; i < levelNum + 1 ; i++){
            // 当前计算从i扔一个棋子
            if (i == levelNum){
                break;
            }
            min = Math.min(min, Math.max(process1(i,pieceNum-1), process1(levelNum-i, pieceNum)));
        }
        return min+1;
    }

    /**
     * 二轮测试-方法1：暴力递归
     * @param levelNum
     * @param chessNum
     * @return
     */
    public static int solution1Cp1(int levelNum, int chessNum) {
        if (levelNum < 1 || chessNum < 1){
            // 棋子没了或者在0层的话直接返回
            return 0;
        }
        return process1Cp1(levelNum, chessNum);
    }

    /**
     * 二轮测试-方法1：递归主体
     * @param levelNum 代表还剩余的层数没有测试
     * @param chessNum 代表还有多少个棋子
     * @return
     */
    private static int process1Cp1(int levelNum, int chessNum) {
        if (levelNum < 1 || chessNum < 1) {
            return 0;
        }
        if (chessNum == 1) {
            // 剩余一颗棋子在最坏的情况下就要扔层数次
            return levelNum;
        }
        int min = Integer.MAX_VALUE;
        // 从当前状态的1层开始测试
        for (int i = 1; i < levelNum + 1;i ++) {
            min = Math.min(min, Math.max(process1Cp1(i-1, chessNum - 1), process1Cp1(levelNum - i, chessNum)))+1;
        }
        return min;
    }



    /**
     * 方法2：动态规划方法
     * @param levelNum
     * @param pieceNum
     * @return
     */
    public static int dpSolution1(int levelNum, int pieceNum){
        if (levelNum < 1 || pieceNum < 1){
            return 0;
        }
        if (pieceNum == 1){
            return levelNum;
        }
        int[][] dp = new int[levelNum + 1][pieceNum + 1];
        // 一颗棋子的都是i
        for (int i = 1; i < dp.length + 1; i++){
            dp[i][1] = i;
        }
        for (int i = 1; i < dp.length+1; i++){
            for (int j = 1; j < dp[0].length+1; i++){
                int min = Integer.MAX_VALUE;
                // 每一层都扔棋子
                for (int k = 1 ; k < i; k++){
                    min = Math.min(min, Math.max(dp[i-k][j], dp[k-1][j-1]));
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[levelNum][pieceNum];
    }


    public static int dpSolutionCp2(int levelNum, int chessNum) {
        if (levelNum < 1 || chessNum < 1) {
            return 0;
        }
        if (chessNum == 1) {
            // 剩余一颗棋子在最坏的情况下就要扔层数次
            return levelNum;
        }
        // dp[i][j] 还剩下i个棋子，j层最坏情况下的最少次数
        int[][] dp = new int[chessNum][levelNum+1];
        for (int i = 0; i < chessNum; i ++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < levelNum+1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < chessNum; i++) {
            for (int j = 1; j <levelNum+1; j ++) {
                int min = Integer.MAX_VALUE;
                for (int k = 1; k <= j; k++) {
                    min = Math.min(min, Math.max(dp[i-1][k-1], dp[i][j-k]));
                }
                dp[i][j] = min+1;
            }
        }
        return dp[chessNum-1][levelNum];
    }

    /**
     * 方法三：压缩动态规划
     * @param levelNum
     * @param pieceNum
     * @return
     */
    public static int dpSolution2(int levelNum, int pieceNum){
        if (levelNum < 1 || pieceNum < 1){
            return 0;
        }
        if (pieceNum == 1){
            return levelNum;
        }
        // 压缩版本dp
        int[] dp = new int[levelNum + 1];
        // 上次计算的结果
        int[] preDp = new int[levelNum + 1];
        // 只有一个棋子的时候
        for (int i = 1; i <= levelNum; i++){
            preDp[i] = i;
        }
        // 从第一层开始
        for (int i = 1; i <= levelNum; i++){
            // 从2个棋子开始
            for (int j = 2; j <= pieceNum; j ++){
                int min = Integer.MAX_VALUE;
                for (int k = 1; k < i+1; k++){
                    min = Math.min(min, Math.max(preDp[k-1], dp[i-k]));
                }
                dp[i] = min+1;
            }
            preDp = dp;
        }
        return dp[levelNum];
    }


    /**
     * 方法4：四边形不等式
     * 3个棋子扔100层楼，最优解第一个棋子在37层扔，那么2个棋子扔100层楼的时候，第一个棋子扔37层以下下
     * 2个棋子扔10层楼，最优解第一个棋子在4层，那么2个棋子，扔100层或者往上，最优解第一个棋子一定在4层以上开始扔
     * @param levelNum
     * @param pieceNum
     * @return
     */
    public static int solution4(int levelNum, int pieceNum){
        if (levelNum < 1 || pieceNum < 1){
            return 0;
        }
        if (pieceNum == 1){
            return levelNum;
        }
        int[][] dp = new int[levelNum + 1][pieceNum + 1];
        int[] cands = new int[pieceNum + 1];
        // 一颗棋子
        for (int i = 1; i < dp.length; i++){
            dp[i][1] = i;
        }
        // 一层楼
        for (int i = 1; i < dp[1].length; i++){
            dp[1][i] = 1;
            cands[i] = 1;
        }
        for (int i = 2; i < levelNum + 1; i++){
            for (int j = pieceNum; j > 0; j--){
                int min = Integer.MAX_VALUE;
                int minK = cands[j];
                int maxK = j == pieceNum ? i/2+1 : cands[j+1];
                for (int k = minK; k <maxK; k++){
                    int cur = Math.max(dp[k-1][j-1], dp[i-k][j]);
                    if (cur <= min){
                        min = cur;
                        cands[j] = k;
                    }
                }
                dp[i][j] = min+1;
            }
        }
        return dp[levelNum][pieceNum];
    }


    /**
     * 二轮测试-方法4：四边形不等式
     * @return
     */
    public static int solutionCp4(int levelNum, int chessNum) {
        if (levelNum < 1 || chessNum < 1){
            return 0;
        }
        if (chessNum == 1){
            return levelNum;
        }
        // dp[i][j] 还剩下i个棋子，j层最坏情况下的最少次数
        int[][] dp = new int[levelNum+1][chessNum+1];
        int[] cands = new int[chessNum + 1];
        for (int i = 1; i < levelNum + 1; i++) {
            dp[i][1] = i;
        }
        for (int j = 1; j < chessNum+1; j++) {
            dp[1][j] = 1;
            // 最优解都是1
            cands[j] = 1;
        }
        for (int i = 2; i < levelNum + 1; i++) {
            for (int j = chessNum; j >= 2; j--) {
                int min = Integer.MAX_VALUE;
                int down = cands[j];
                // todo 为什么？
                int up = j == chessNum? i/2 + 1 : cands[j+1];
                for (int k = down; k <= up; k++) {
                    int cur = Math.max(dp[k-1][j-1], dp[i-k][j]);
                    if (cur < min) {
                        min = cur;
                        cands[j] = k;
                    }
                }
                dp[i][j] = min+1;
            }
        }
        return dp[levelNum][chessNum];
    }

    /**
     * 最优解
     * 原理：二分法寻找是最少步骤的最优解，如果棋子够就直接用二分法扔即可
     * 2、计算k个棋子在扔n次后能够搞定最多多少楼层
     * 3、直接找到第一个大于levelNum的单元即可
     * @param levelNum
     * @param pieceNum
     * @return
     */
    public static int bestSolution(int levelNum, int pieceNum){
        if (levelNum < 1 || pieceNum < 1){
            return 0;
        }
        int bsTimes = log2N(levelNum);
        // 二分法如果能够解决就用二分法
        if (pieceNum > bsTimes){
            return bsTimes;
        }
        // 棋子数量
        int[] dp = new int[pieceNum+1];
        // 这里不需要初始化dp，因为当前dp更新后的结果就是下一个dp,向后更新，无需依赖前面
        int res = 0;
        while (true){
            res++;
            // 前一个的上面一个
            int pre = 0;
            /**
             * pre   x         pre x
             * dp[i] x   ->    tem dp[i]新
             */
            for (int i = 0; i < pieceNum; i ++){
                int tmp = dp[i];
                dp[i] = dp[i] + pre + 1;
                pre = tmp;
                if (dp[i] > levelNum){
                    return res;
                }
            }
        }
    }


    /**
     * 二轮测试-最优解
     * 换个角度：m个棋子扔k次最多搞定的楼层，第一次超过目标值的次数就是最优解
     * @param levelNum
     * @param chessNum
     * @return
     */
    public static int bestSolutionCp5(int levelNum, int chessNum) {
        if (levelNum < 1 || chessNum < 1){
            return 0;
        }
        int bsTimes = log2N(levelNum);
        // 二分法如果能够解决就用二分法
        if (chessNum > bsTimes){
            return bsTimes;
        }
        int[] dp = new int[chessNum+1];
        // 上一轮，相当于[i-1,j-1]
        int count = 1;
        while (true) {
            int pre = 0;
            for (int i = 1; i < dp.length; i++) {
                int tmp = dp[i];
                dp[i] = dp[i] + pre + 1;
                pre = tmp;
                if (dp[i] >= levelNum) {
                    return count;
                }
            }
            count++;
        }
    }


    /**
     * 找到2的几次方边界
     * 因为楼层用二分的方式一定能够找到，这里求出二分的方式需要几个棋子，如果给的棋子大于这个数值，那就直接用这个数值
     * @param num
     * @return
     */
    public static int log2N(int num){
        int res = -1;
        while (num != 0){
            num >>>= 1;
            res++;
        }
        return res;
    }


    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(bestSolutionCp5(105, 2));
    }



}
