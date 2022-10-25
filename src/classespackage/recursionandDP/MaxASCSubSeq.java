package classespackage.recursionandDP;

import classespackage.CommonUtils;

import java.util.Arrays;

/**
 * @author swzhao
 * @date 2022/4/21 9:43 上午
 * @Discreption <>最长递增子序列
 */
public class MaxASCSubSeq {

    /**
     * 方法一：
     * 动态规划 O（n^2）
     * 1、dp[i]代表以array[i]结尾的最长子序列长度
     * 2、dp[i]为i之前所有dp中array[j]比array[i]小的值中最大值+1
     * 3、找到dp中的最大值即为最长子序列长度
     * 4、如果要找到序列，则从最大长度的index开始往后找，比array[index]小的并且dp数量为array[index] - tem（tem递增）的
     * @param array
     * @return
     */
    public static int[] getMaxSeqLen(int[] array){
        try{
            int[] dp = new int[array.length];
            // 以array[0]结尾的最长子序列为自己
            dp[0] = 1;
            for (int i = 1; i < array.length; i++){
                int tem = 1;
                for (int j = i-1; j >= 0; j--){
                    if(array[j] < array[i] && dp[j] > tem){
                        // 以array[j]结尾的最长子序列长度大于1，并且array[j] > array[i]
                        tem = dp[j] + 1;
                    }
                }
                // 循环结束后tem应该是符合条件中的最大值
                dp[i] = tem;
            }
            // 到这里只是算出了array[i]结尾的所有最长递增子序列长度，需要计算出序列
            return getSub(dp, array);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 方法二：O（nlogn）
     * @param array
     * @return
     */
    public static int[] getMaxSeq2(int[] array){
        int[] dp = new int[array.length];
        // ends[i]表示 i+1长度为最小子数组结尾元素
        int[] ends = new int[array.length];
        dp[0] = 1;
        ends[0] = array[0];
        int right = 0;
        // 二分的初始变量
        int l = 0;
        int r = 0;
        for (int i = 0; i < array.length; i++){
            r = right;
            // 二分法查找位置
            while (l <= r){
                int m = (l+r)/2;
                if(array[i] > ends[m] ){
                    l = m + 1;
                }else{
                    r = m - 1;
                }
            }
            right = Math.max(right, r);
            ends[l] = array[i];
            dp[i] = l + 1;
        }
        return getSub(dp, array);
    }

    public static int[] getSub(int[] dp, int[] array){
        int index = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++){
            if(dp[i] > maxValue){
                index = i;
                maxValue = dp[i];
            }
        }
        int[] res = new int[maxValue];
        // 从index开始往回找
        int tem = 1;
        for (int i = index; i >= 0 ;i--){
            // 往前找到比当前小的并且最长子序列为当前-1， -2， -3的
            if(array[i] < array[index] && dp[i] == dp[index] - tem){
                // 倒着赋值
                res[maxValue--] = array[i];
                tem++;
            }
        }
        return res;
    }


    /**
     * 获取数组中最长递增序列长度的dp数组
     * @return
     */
    public static int[] getMaxLenFromArrCP(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        // dp[i] 代表 递增子序列以arr[i]结尾时的最长长度
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxLen = Integer.MIN_VALUE;
            for (int j = i; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    maxLen = Math.max(maxLen, dp[j]);
                }
            }
            dp[i] = maxLen == Integer.MIN_VALUE? 1 : maxLen + 1;
        }
        return dp;
    }

    /**
     * 获取最长子序列
     * 时间复杂度O(N^2)
     * @param arr
     * @return
     */
    public static int[] getSeqFromArrCP(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getMaxLenFromArrCP(arr);
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < dp.length; i++){
            if (dp[i] > max) {
                max = dp[i];
                maxIndex = i;
            }
        }
        int index = 0;
        int[] res1 = new int[arr.length];
        res1[index++] = arr[maxIndex];
        for (int i = maxIndex-1; i >= 0; i--) {
            if (arr[i] < arr[maxIndex] && dp[i] == dp[maxIndex] - 1){
                res1[index++] = arr[i];
                maxIndex = i;
            }
        }
        int[] res = new int[index];
        // 翻转数组
        while (index > 0) {
            res[res.length-index] = res1[index-1];
            index--;
        }
        return res;
    }
    /**
     * 获取数组中最长递增序列长度的dp数组
     * @return
     */
    public static int[] getMaxLenFromArrCPPlus(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int[] ends = new int[arr.length];
        int right = 0;
        // 代表到arr[0]为止，长度为right+1的所有子序列中，结尾最小的数为arr[0]
        ends[right] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            // 找ends最左边的大于等于arr[i]的数
            int index = findIndexFromEnds(ends, arr[i], right);
            if (index == -1) {
                // 没有找到，说明ends的有效区域需要增加
                ends[++right] = arr[i];
                dp[i] = right+1;
            }else {
                // 找到了，则arr[i]结尾的最长子数组长度也找到了，并且需要更新最小结尾
                dp[i] = index+1;
                ends[index] = arr[i];
            }
        }
        return dp;
    }

    /**
     * 找到ends左边第一个比value大的数
     * @param ends
     * @param value
     * @return
     */
    private static int findIndexFromEnds(int[] ends, int value, int right) {
        int left = 0;
        int r = right;
        while (left <= r) {
            int mid = (left+r)/2;
            if (ends[mid] > value) {
                r = mid-1;
            }else if (ends[mid] < value) {
                left = mid+1;
            }else {
                // 相等
                return mid;
            }
        }
        return left > right? -1 : left;
    }

    public static void main(String[] args) {
        System.out.println(getMaxLenFromArrCPPlus(new int[]{2,1,5,3,6,4,8,9,7}));
    }
}
