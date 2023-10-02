package leetcode;

/**
 * @author swzhao
 * @data 2023/10/2 13:27
 * @Discreption <>
 */
public class CanJump {


    public static void main(String[] args) {
        int[] ints = {2,3,1,1,4};
        System.out.println(solution1(ints));
    }



    public static boolean solution2(int[] nums) {
        return process1(nums, 0, nums.length-1);
    }

    private static boolean process1(int[] nums, int start, int end) {
        if (start == end) {
            // 终点了
            return true;
        }
        int num = nums[start];
        if (num == 0) {
            // 跳不了
            return false;
        }
        for (int i = 1; i <= num; i++) {
            boolean b = process1(nums, start + i, end);
            if (b) {
                return true;
            }
        }
        // 都到不了
        return false;
    }

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public static boolean solution3(int[] nums) {
        if (nums == null) {
            return false;
        }
        if (nums.length == 0 || nums.length == 1) {
            return true;
        }
        // dp[i]代表从i出发是否能够到终点
        boolean[] dp = new boolean[nums.length];
        // 最后一个元素坑定能到
        dp[dp.length - 1] = true;
        for (int i = dp.length - 2; i >= 0; i--) {
            int num = nums[i];
            if (num == 0) {
                dp[i] = false;
            }else {
                for (int j = 1; j <= num; j++) {
                    dp[i] = dp[i] || dp[i + j];
                }
            }
        }
        return dp[0];
    }


    /**
     * 跳跃需要的最小步数
     * @param nums
     * @return
     */
    public static int solution1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            // 不用跳跃
            return 0;
        }
        // 记录跳跃的最小步数,最起码调一下
        int jump = 0;
        // 当前位置
        int cur = 0;
        // 下一个位置
        int next = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > cur) {
                // 需要跳跃
                jump++;
                cur = next;
            }
            next = Math.max(next, i + nums[i]);
        }
        return jump;
    }



}
