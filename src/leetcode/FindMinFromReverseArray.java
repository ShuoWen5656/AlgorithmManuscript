package leetcode;

/**
 * @author swzhao
 * @date 2023/11/27 11:01 下午
 * @Discreption <> 寻找旋转排序数组中的最小值
 */
public class FindMinFromReverseArray {


    public static int solution(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < nums[nums.length-1]) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return nums[left];
    }


}
