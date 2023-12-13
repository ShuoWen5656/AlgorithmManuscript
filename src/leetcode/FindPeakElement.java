package leetcode;

/**
 * @author swzhao
 * @date 2023/11/26 2:30 下午
 * @Discreption <> 寻找峰值
 */
public class FindPeakElement {


    public static int solution(int[] nums) {
        int res = 0;
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                res = i;
                max = nums[i];
            }
        }
        return res;
    }
}
