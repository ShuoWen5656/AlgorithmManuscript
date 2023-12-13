package classespackage.stringproblem;

import classespackage.CommonUtils;

/**
 * @author swzhao
 * @data 2022/5/17 20:03
 * @Discreption <> 0左边必有1的二进制字符串数量
 */
public class Left1Problem {

    /**
     * 给定长度num，求0左边必有1的字符串数量
     * 递归方式：
     * @param num
     * @return
     */
    public static int getNum1(int num){
        try {
            if(num < 1){
                return 0;
            }
            return process(1 ,num);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 二轮测试：获取0左边必有1的字符串总数
     * @param num
     * @return
     */
    public static int getNumCp1(int num) {
        if (num < 1) {
            return 0;
        }
        return processCp1(1, num);
    }

    private static int processCp1(int i, int num) {
        if (i == num - 1) {
            return 2;
        }
        if (i == num) {
            return 1;
        }
        return processCp1(i+1, num) + processCp1(i + 2, num);
    }


    private static int process(int i, int num) {
        if (i == num){
            return 1;
        }else if(i == num - 1){
            return 0;
        }else{
            return process(i+1, num) + process(i+2, num);
        }
    }

    /**
     * 第二种斐波那契数列
     * @param num
     * @return
     */
    private static int getNum2(int num){
        if(num < 1){
            return 0;
        }
        int[] dp = new int[num+1];
        dp[num] = 1;
        dp[num-1] = 2;
        for (int i = num-2; i > 0; i--){
            dp[i] = dp[i+1] + dp[i+2];
        }
        return dp[1];
    }


    /**
     * 时间复杂度最低的方法：O（logn）
     * 斐波那契书列矩阵乘法
     * @param num
     * @return
     */
    public static int getNum3Cp(int num){
        try {
            // 矩阵
            int[][] martix = {
                    {1, 1},
                    {1, 0}
            };
            int[][] martix2 = {
                    {2, 1}
            };
            int[][] res = martix;
            for (int i = 0; i < num-3; i++) {
                res = multiMartrix(res, martix);
            }
            res = multiMartrix(martix2, res);
            return res[0][0];
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 矩阵相乘
     * @param martix1
     * @param martix2
     * @return
     */
    private static int[][] multiMartrix(int[][] martix1, int[][] martix2) {
        if (martix1 == null || martix2 == null || martix1.length == 0 || martix2.length == 0) {
            return null;
        }
        int row = martix1.length;
        int col = martix2[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < col; j++) {
                int tmp = 0;
                for (int k = 0; k < martix1[0].length; k++) {
                    tmp += martix1[i][k] * martix2[k][j];
                }
                res[i][j] = tmp;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(getNum3Cp(3));
    }
}
