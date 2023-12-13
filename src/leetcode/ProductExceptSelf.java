package leetcode;

/**
 * @author swzhao
 * @data 2023/10/3 11:59
 * @Discreption <>  除自身以外数组的乘积
 */
public class ProductExceptSelf {


    public static void main(String[] args) {
        System.out.println(solution1(new int[]{1,2,3,4}));
    }


    public static int[] solution1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] res = new int[nums.length];
        // 统计0的个数
        int count0 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count0++;
            }
        }
        // 不能超过2个
        if (count0 >= 2) {
            return res;
        }
        // left[i]代表i左边的（包含i）的所有数字的乘积
        int[] left = new int[nums.length];
        // right[i]代表i左边的（包含i）的所有数字的乘积
        int[] right = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            left[i] = i == 0 ? nums[i] : left[i-1] * nums[i];
            right[nums.length - 1 - i] = i == 0 ? nums[nums.length-1]
                    : nums[nums.length - 1 - i] * right[nums.length - i];
        }
        // 最终开始计算res
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                res[i] = right[1];
            }else if (i == nums.length-1) {
                res[i] = left[nums.length-2];
            }else {
                res[i] = left[i-1] * right[i+1];
            }
        }
        return res;
    }

}
