package leetcode;

/**
 * @author swzhao
 * @date 2023/12/3 11:02 上午
 * @Discreption <>只出现一次的数字
 */
public class SingleNumber {


    public int solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

}
