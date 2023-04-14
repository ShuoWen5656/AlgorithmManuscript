package classespackage.other;

import java.util.*;

/**
 * @author swzhao
 * @data 2022/7/11 21:23
 * @Discreption <>:正数数组的最小不可组成和
 * 问题：
 * 1、数组中任意几个数相加得到的数中最大的数和最小的数之间形成一个区间[min...max]
 * 2、从min开始到max，找到第一个数使得不管怎么组合都不能够达到这个和的这个数就是最小不可组成和
 * 进阶：如果已知正数数组arr中肯定有1这个数，是否能更快的得到最小不可组成和
 */
public class UnformedSum {

    /**
     * 方法1：
     * 暴力递归，得出所有可能性然后遍历
     * @param array
     * @return
     */
    public static int unformedSum1(int[] array){
        if (array == null || array.length == 0){
            return 0;
        }
        // 存储所有可能性的set
        Set<Integer> set = new HashSet<>();
        // 递归获取所有可能性
        process(array, 0, array.length-1, set);
        // 算出最小值
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++){
            if (array[i] < min){
                min = array[i];
            }
        }
        // 从小到大遍历，如果区间里的值都能够得到，则为max+1
        for (min = min+1; min <= Integer.MAX_VALUE; min++){
            if (!set.contains(min)){
                return min;
            }
        }
        return 0;
    }

    /**
     * 递归获取所有的可能性
     * @param array
     * @param i
     * @param sum
     * @param set
     * @return
     */
    private static void process(int[] array, int i, int sum, Set<Integer> set) {
        if (i == array.length){
            set.add(sum);
            return;
        }
        // 这里意思是 从 i+1开始计算所有的可能性
        process(array, i+1, sum, set);
        // 从i+1开始计算所有可能性，并且结果中都从sum开始
        process(array, i+1, sum+array[i], set);
    }


    /**
     * 二轮测试：暴力递归算出所有可能性遍历即可
     * @param arr
     * @return
     */
    public static int unformedSumCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 保存所有集合的和
        HashSet<Integer> sumSet = new HashSet<>();
        processCp1(arr, 0 , 0,sumSet);
        int min = Collections.min(sumSet);
        for (int i = min; ; i++) {
            if (!sumSet.contains(i)) {
                return i;
            }
        }
    }

    /**
     * 递归循环填充sumSet
     * @param arr
     * @param start 从 arr[start]开始计算
     * @param sum 前面的和
     * @param sumSet
     */
    private static void processCp1(int[] arr, int start, int sum, HashSet<Integer> sumSet) {
        if (start == arr.length) {
            return;
        }
        sumSet.add(sum + arr[start]);
        //当前位置有两种可能性，一种有start位置的和，一种无start位置的和
        processCp1(arr, start + 1, sum + arr[start], sumSet);
        processCp1(arr, start + 1, sum, sumSet);
    }

    public static void main(String[] args) {
        unformSumCp3(new int[]{1,2,4});
    }


    /**
     * 方法二：通过动态规划的方式计算
     * @param arr
     */
    public static int unformeSumCp2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 计算所有和
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        // dp[i]代表是否存在sum为i的集合
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < arr.length; i++) {
            for (int j = sum; j > 0; j--) {
                 if (j - arr[i] >= 0) {
                     dp[j] = dp[j - arr[i]] || dp[j];
                 }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            if (!dp[i]) {
                return i;
            }
        }
        return sum+1;
    }



    /**
     * 方法二：
     * 动态规划
     * @param array
     * @return
     */
    public static int unformeSum2(int[] array){
        if (array == null || array.length == 0){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < array.length; i++){
            min = Math.min(min, array[i]);
            sum += array[i];
        }
        // dp[j] 表示存在array的子集相加得到
        boolean[] dp = new boolean[sum + 1];
        for (int i = 0; i < array.length;i ++){
            for (int j = sum; j >= array[i]; j--){
                dp[j] = dp[j-array[i]] ? true : dp[j];
            }
        }
        for (int i = 0; i < dp.length; i++){
            if (!dp[i]){
                return i;
            }
        }
        return sum+1;
    }


    /**
     * 进阶问题：如果数组中存在一个1，如何能够最快的求得答案
     * @param arr
     * @return
     */
    public static int unformSumCp3(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int range = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > range+1) {
                return range+1;
            }else {
                range += arr[i];
            }
        }
        return range+1;
    }


    /**
     * 进阶问题
     * @param array
     * @return
     */
    public static int unformSumPlus3(int[] array){
        if (array == null || array.length == 0){
            return 0;
        }
        Arrays.sort(array);
        int range = 0;
        for (int i = 0; i < array.length; i++){
            if (array[i] > range+1){
                return range+1;
            }else {
                range += array[i];
            }
        }
        return range+1;
    }

}
