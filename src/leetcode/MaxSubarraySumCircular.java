package leetcode;

import java.util.*;

/**
 * @author swzhao
 * @date 2023/11/25 11:13 上午
 * @Discreption <>环形子数组的最大和
 */
public class MaxSubarraySumCircular {


    public static void main(String[] args) {
        System.out.println(solution(new int[]{6,9,-3}));
    }

    public static int solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] helper = new int[nums.length * 2];
        for (int i = 0; i < helper.length; i++) {
            helper[i] = nums[i%nums.length];
        }
        // 求窗口为length,和最大的res
        int res = nums[0];
        int sum = nums[0];
        Deque<int[]> deque = new LinkedList<>();
        // int[0] 代表数组下标，int[1] 代表当前数组下标的合
        deque.addLast(new int[]{0, sum});
        for (int i = 1; i < helper.length; i++) {
            // 先将大于窗口的提出,但是要留一个即将过期的在里面，需要被减掉
            while (!deque.isEmpty() && deque.peekFirst()[0] < i - nums.length) {
                deque.pollFirst();
            }
            // 更新当前的sum值
            sum += helper[i];
            res = Math.max(res, sum - deque.peekFirst()[1]);
            // 将当前队列中，合大于sum的提出
            while (!deque.isEmpty() && deque.peekLast()[1] >= sum) {
                deque.pollLast();
            }
            deque.addLast(new int[]{i, sum});
        }
        return res;
    }


    public static int solution2(int[] nums) {
        int n = nums.length;
        Deque<int[]> queue = new ArrayDeque<int[]>();
        int pre = nums[0], res = nums[0];
        queue.offerLast(new int[]{0, pre});
        for (int i = 1; i < 2 * n; i++) {
            while (!queue.isEmpty() && queue.peekFirst()[0] < i - n) {
                queue.pollFirst();
            }
            pre += nums[i % n];
            res = Math.max(res, pre - queue.peekFirst()[1]);
            while (!queue.isEmpty() && queue.peekLast()[1] >= pre) {
                queue.pollLast();
            }
            queue.offerLast(new int[]{i, pre});
        }
        return res;
    }




}
