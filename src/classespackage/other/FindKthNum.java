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


    /**
     * 二轮测试：两个排序数组中找到第k小的数
     * @return
     */
    public static int findKMinFromSortArrCp1(int[] arr1, int[] arr2, int k) {

        if (k < 1
                || arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0
                || (arr1.length + arr2.length < k)) {
            throw new RuntimeException("非法入参");
        }
        int[][] compareRes = compareArr(arr1, arr2);
        int[] shortArr = compareRes[0];
        int[] longArr = compareRes[1];
        if (k < shortArr.length) {
            return getUpMidFromSameLenArr(shortArr, 0, k-1, longArr, 0, k-1);
        }else if (k > longArr.length) {
            // 后面需要有几个元素
            int sub = (shortArr.length + longArr.length) - k;
            int start1 = shortArr.length - 1 - sub;
            int start2 = longArr.length - 1 - sub;
            if (longArr[start2] >= shortArr[shortArr.length - 1]) {
                return longArr[start2];
            }
            if (longArr[shortArr.length - 1] <= shortArr[start1]) {
                return longArr[shortArr.length-1];
            }
            return getUpMidFromSameLenArr(shortArr, start1+1, shortArr.length-1, longArr, start2+1, longArr.length-1);
        }else {
            int sub = (shortArr.length + longArr.length) - k;
            // 后面必须凑够20个
            int start2 = k - shortArr.length - 1;
            // 排除一个值否则长度不相等
            if (shortArr[shortArr.length - 1] <= longArr[start2]) {
                return longArr[start2];
            }else {
                start2++;
            }
            int end2 = longArr.length - (sub - shortArr.length) - 1;
            return getUpMidFromSameLenArr(shortArr, 0, shortArr.length-1, longArr, start2, end2);
        }
    }

    /**
     * 从两个数组中相同长度的子数组部分中求上中位数
     * @param arr1
     * @param start1
     * @param end1
     * @param arr2
     * @param start2
     * @param end2
     * @return
     */
    private static int getUpMidFromSameLenArr(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2) {
        int mid1 = 0;
        int mid2 = 0;
        while (start1 < end1) {
            // 获取中间值
            mid1 = (start1 + end1)/2;
            mid2 = (start2 + end2)/2;
            if (arr1[mid1] == arr1[mid2]) {
                return arr1[mid1];
            }else {
                // 计算长度偶数或者奇数，偶数0，奇数1
                int offset = ((end1 - start1 + 1) & 1) ^ 1;
                if (arr1[mid1] > arr2[mid2]) {
                    // 奇数的话不需要调整数组大小直接割
                    end1 = mid1;
                    start2 = mid2 + offset;
                }else {
                    start1 = mid1 + offset;
                    end2 = mid2;
                }
            }
        }
        return Math.min(arr1[start1], arr2[start2]);
    }

    /**
     * 比较出来两个数组的长短
     * @param arr1
     * @param arr2
     * @return compareRes[0] 为短数组
     */
    private static int[][] compareArr(int[] arr1, int[] arr2) {
        return arr1.length > arr2.length ? new int[][]{arr2, arr1} : new int[][]{arr1, arr2};
    }


    public static void main(String[] args) {
        System.out.println(findKMinFromSortArrCp1(new int[]{1,2,3}, new int[]{3,4,5,6}, 6));
    }


}
