package classespackage.recursionandDP;

import java.math.BigInteger;

/**
 * @author swzhao
 * @date 2022/4/17 8:55 上午
 * @Discreption <>斐波那契系列问题的递归和动态规划
 */
public class Fibonacci {


    /**
     * 主方法
     * @param num
     * @return
     */
    public static int solute(int num){
        return 0;
    }


    /**
     * 台阶问题:递归方式
     * 计算有重合，O（2^n）
     * @param num
     * @return
     */
    public static int fibobacci(int num){
        if(num == 1){
            return 1;
        }else if(num == 2){
            return 2;
        }
        return fibobacci(num - 1) + fibobacci(num - 2);
    }


    /**
     * 台阶问题：动态规划方法，复杂度降低到o（n）
     * @param num
     * @return
     */
    public static int fibobacciDP(int num){
        // 结果集合，这里的结果集合长度多一个为了对应答案的位置res[num]
        int[] res = new int[num + 1];
        res[1] = 1;
        res[2] = 2;
        // 循环将每一个答案都计算出来,从3开始计算
        for (int i = 3; i < num + 1; i++){
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[num];
    }

    /**
     * 台阶问题：优化算法：O（logn）
     * 问题可以使用矩阵状态代替
     *
     * (f(n), f(n-1)) = (f(n-1), f(n-2)) x |1, 1|
     *                                     |1, 0|
     * 矩阵是计算出来的，通过已知的f1，f2， f3， f4
     *
     * @param num
     * @return
     */
    public static int fibobacci3(int num){
        if(num == 1){
            return 1;
        }
        if(num == 2){
            return 2;
        }
        int[][] base = {{1, 1}, {1, 0}};
        // 计算次方
        int[][] res = multiPower(base, num - 2);
        // 返回乘积
        return res[0][0] + res[1][0];
    }

    /**
     * 计算base矩阵的p次方
     * 1、算法精髓，将p变量转化成二进制，右移，最低位为1时说明需要累乘，每一次结束后都要累乘一次表示当前位的累乘矩阵
     * @param base
     * @param p
     * @return
     */
    public static int[][] multiPower(int[][] base, int p){
        // 造一个单位矩阵（对角1矩阵）
        int[][] res = new int[base.length][base[0].length];
        for (int i = 0; i < res.length; i ++){
            res[i][i] = 1;
        }
        int[][] tmp = base;
        for (; p != 0; p >>= 1){
            if((p & 1) != 0){
                // 说明是奇数，二进制末尾为1，要累乘
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    /**
     * 矩阵相乘
     * @param m
     * @param n
     * @return
     */
    public static int[][] muliMatrix(int[][] m, int[][] n){
        // 乘积结果：m的行数为res的行数，n的列数为结果的列数
        int[][] res = new int[m.length][n[0].length];
        for (int i = 0; i < m.length ; i++){
            for (int j = 0; j < n[0].length; j++){
                for (int k = 0; k < m.length; k ++){
                    res[i][j] = m[i][k] + n[k][j];
                }
            }
        }
        return res;
    }



    /**
     * 第一年一头母牛
     * 1、每一年母牛可以生一个
     * 2、小牛三年后成熟可以生小牛
     * 3、问n年后多少牛
     * 三年前的总牛数应该就是三年后的牛数的增量，因为三年前的牛在三年后都会成熟，并且期间新增的牛不会成熟
     * 所以三年后C(n) = C(n-1) + C(n - 3), C(n-3)就是三年后新增的牛数量
     * @param n
     * @return
     */
    public static int cowProblem1(int n){
        switch (n){
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
        }
        return cowProblem1(n - 1) + cowProblem1(n - 3);
    }

    /**
     * 动态规划解法
     * @param n
     * @return
     */
    public static int cowProblem2(int n){
        int[] res = new int[n + 1];
        res[1] = 1;
        res[2] = 2;
        res[3] = 3;
        for (int i = 4; i < n + 1; i++){
            res[i] = res[i-1] + res[i-3];
        }
        return res[n];
    }

    /**
     * 矩阵解法：三阶递推数列
     *
     * @param n
     * @return
     */
    public static int cowProblem3(int n){
        int[][] base = {{1, 1, 0}, {0, 0, 1}, {0, 1, 0}};
        switch (n){
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
        }
        int[][] res = multiPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }


    /**
     * 阶梯问题：递归解法
     * O(2^n)
     * @param num
     */
    public static int process1(int num){
        if (num == 1) {
            return 1;
        }else if (num == 2){
            return 2;
        }else if (num == 0){
            return 0;
        }
        else {
            return process1(num-1) + process1(num - 2);
        }
    }


    /**
     * 阶梯问题：dp解法
     * 比递归快非常多
     * O(n)
     * @param num
     */
    public static int dp1(int num){
        int[] dp = new int[num+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= num; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[num];
    }


    /**
     * logn解法
     * 状态矩阵相乘
     * @param num
     * @return
     */
    public static int superFunc1(int num) {
        // 构造一个初始矩阵[func(2), func(1)]
        int[][] arr = {{2, 1}};
        arr =  multiMartrix(arr, powMartrixPro(new int[][]{
                        {1,1},
                        {1,0}
                },
                num - 2));
        return arr[0][0];
    }

    /**
     * 母牛问题
     * 求第years年时有多少牛
     * @param years
     * @return
     */
    public static int cowPro(int years) {
        if (years <= 3){
            return years;
        }
        int[][] arr = {{3,2,1}};
        arr = multiMartrix(arr, powMartrixPro(new int[][]{
                {1,1,0},
                {0,0,1},
                {1,0,0}
        }, years-3));
        return arr[0][0];
    }



    /**
     * arr的n次方
     * @param arr
     * @param n
     * @return
     */
    public static int[][] powMartrix(int[][] arr, int n) {
        if (arr == null || arr.length == 0 || arr[0].length == 0 ) {
            return arr;
        }
        int[][] res = new int[arr.length][arr[0].length];
        // 单位举证
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        for (int i = 0; i < n; i++) {
            res = multiMartrix(res, arr);
        }
        return res;
    }

    /**
     * arr的n次方
     * 优化版本
     * @param arr
     * @param n
     * @return
     */
    public static int[][] powMartrixPro(int[][] arr, int n) {
        if (arr == null || arr.length == 0 || arr[0].length == 0 ) {
            return arr;
        }
        int[][] res = new int[arr.length][arr[0].length];
        // 单位举证
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tem = arr;
        for (; n > 0; n >>= 1) {
            if ((n & 1) != 0){
                // 这波res需要乘
                res = multiMartrix(res, tem);
            }
            // 准备下一波的乘数，虽然可能不乘
            tem = multiMartrix(tem, tem);
        }
        return res;
    }

    /**
     * 矩阵相乘
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[][] multiMartrix(int[][] arr1, int[][] arr2) {
        if (arr1 == null || arr1.length == 0 || arr1[0].length == 0
                || arr2 == null || arr2.length == 0 || arr2[0].length == 0) {
            return null;
        }
        // 相乘结果：行 = arr1的行 列 = arr2的列数
        int[][] res = new int[arr1.length][arr2[0].length];
        // 一行一行来
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++){
                int sum = 0;
                for (int k = 0; k < arr1[0].length; k++) {
                    // 这里的k是arr1的列，arr2 的行
                    sum += arr1[i][k]*arr2[k][j];
                }
                res[i][j] = sum;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(cowPro(7));
    }

}
