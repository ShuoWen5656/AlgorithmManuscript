package leetcode;

/**
 * @author swzhao
 * @date 2023/10/24 8:39 下午
 * @Discreption <>长度最小的子数组
 */
public class MinSubArrayLen {

    public static void main(String[] args) {
        System.out.println(solution(7, new int[]{2,3,1,2,4,3}));

    }

    public static int solution(int target, int[] nums) {
        if (nums == null || nums.length == 0 || target < 0) {
            return 0;
        }
        // 滑动窗口当前指针
        int cur = 0;
        // 上一个指针
        int pre = 0;
        // 当前和
        int sum = nums[0];

        int res = Integer.MAX_VALUE;
        while (cur < nums.length-1) {
            if (sum >= target && pre <= sum) {
                // 计算最小值
                res = Math.min(cur - pre + 1, res);
                // 滑动窗口调整
                sum -= nums[pre];
                pre++;
            }else {
                // 小了
                cur++;
                sum += nums[cur];
            }
        }
        // 收尾
        while (sum >= target && pre <= sum) {
            res = Math.min(cur - pre + 1, res);
            sum -= nums[pre];
            pre++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

}
