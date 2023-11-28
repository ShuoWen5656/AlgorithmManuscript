package leetcode;

import java.util.Arrays;

/**
 * @author swzhao
 * @date 2023/11/28 10:06 下午
 * @Discreption <>寻找两个正序数组的中位数
 */
public class FindMedianSortedArrays {


    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,2}, new int[]{3,4}));
    }


    public static double solution(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (((len1 + len2) & 1) == 1) {
            // 和是奇数
            return getKthNum(nums1, nums2, (len1 + len2)/2 + 1);
        }else {
            // 和是偶数
            return (getKthNum(nums1, nums2, (len1+len2)/2) + getKthNum(nums1, nums2, (len1 + len2)/2 + 1))/2.0;
        }

    }

    private static int getKthNum(int[] nums1, int[] nums2, int k) {
        int index1 = 0, index2 = 0;
        while (true) {
            // 边界
            if (index1 == nums1.length) {
                // 剩下的k都是num2的
                return nums2[index2 + k - 1];
            }
            if (index2 == nums2.length) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                // 只查询剩下的第一个数，则选择较小的
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int mid = k/2;
            int newIndex1 = Math.min(nums1.length,index1 + mid) - 1;
            int newIndex2 = Math.min(nums2.length, index2 + mid) - 1;
            if (nums1[newIndex1] <= nums2[newIndex2]) {
                // 淘汰newindex1前面的元素
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            }else {
                // 淘汰newindex2前面的元素
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }


}
