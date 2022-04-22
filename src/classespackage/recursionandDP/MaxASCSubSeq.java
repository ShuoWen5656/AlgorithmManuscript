package classespackage.recursionandDP;

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



}
