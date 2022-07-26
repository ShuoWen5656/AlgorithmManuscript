package classespackage.other;

import java.util.Arrays;

/**
 * @author swzhao
 * @data 2022/7/26 21:26
 * @Discreption <> 在两个排序数组中找到第k小的数
 */
public class FindKthNum {

    /**
     * 主方法
     * 原理：
     * 判断第k小的数前后各需要压多少个值按照范围依次排除即可
     * @param arr1
     * @param arr2
     * @param k
     * @return
     */
    public int findKthNum(int[] arr1, int[] arr2, int k){
        if (arr1 == null || arr2 == null){
            return 0;
        }
        // 情况1
        if (k < 0 || k > arr1.length + arr2.length){
            throw new RuntimeException("非法数组");
        }
        int[] shortArr = arr1.length < arr2.length? arr1 : arr2;
        int[] longArr = arr2.length > arr1.length ? arr2 : arr1;
        int s = shortArr.length;
        int l = longArr.length;
        // 情况2
        if (k <= s){
            return GetUpMedian.getUpMedian(Arrays.copyOfRange(shortArr,0,k), Arrays.copyOfRange(longArr, 0, k));
        }
        // 情况3
        if (k > l){
            if (shortArr[k-l-1] > longArr[l-1]){
                return shortArr[k-l-1];
            }
            if (longArr[k-s-1] > shortArr[s-1]){
                return longArr[k-s-1];
            }
            return GetUpMedian.getUpMedian(Arrays.copyOfRange(shortArr, k-l, s), Arrays.copyOfRange(longArr, k-s, l));
        }
        // 情况4
        if (longArr[k-s-1] > shortArr[s-1]){
            return longArr[k-s-1];
        }
        return GetUpMedian.getUpMedian(Arrays.copyOfRange(longArr, k-s, l), shortArr);






    }


}
