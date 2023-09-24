package leetcode;

import java.lang.reflect.Array;

/**
 * @author swzhao
 * @date 2023/9/24 1:12 下午
 * @Discreption <> 合并两个有序数组
 */
public class MergeNums {


    public static void main(String[] args) {
        int[] nums1 = new int[]{2,0};
        int[] nums2 = new int[]{1};
        merge(nums1,1, nums2, 1);
    }

    /**
     * 合并两个有序数组
     * @param nums1
     * @return
     */
    public static int[] merge(int[] nums1, int m, int[] nums2, int n){
        // 两个游标
        int index1 = 0, index2 = 0;
        int resLen = m+n;
        int[] res = new int[resLen];
        if (m == 0 && n == 0) {
            return new int[0];
        }else if (n == 0){
            return nums1;
        }else if (m == 0) {
            res = nums2;
            for (int i = 0; i < nums1.length; i++) {
                nums1[i] = res[i];
            }
        }

        int index = 0;
        while (index1 < m && index2 < n) {
            if (nums1[index1] >= nums2[index2]) {
                res[index++] = nums2[index2++];
            }else {
                res[index++] = nums1[index1++];
            }
        }
        if (index1 >= m) {
            while (index2 < n){
                res[index++] = nums2[index2++];
            }
        }else {
            while (index1 < m) {
                res[index++] = nums1[index1++];
            }
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = res[i];
        }
        return res;
    }


}
