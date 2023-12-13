package leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author swzhao
 * @date 2023/11/4 9:22 上午
 * @Discreption <> 最长连续序列
 */
public class LongestConsecutive {

    public static void main(String[] args) {
        solution(new int[]{1,3,2});
    }


    /**
     * 自己的题解
     * @param nums
     * @return
     */
    public static int solution(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 以key为边界的连续序列长度
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                continue;
            }
            map.put(nums[i], 1);
            if (map.containsKey(nums[i] + 1)) {
                // 看看后面有没有
                res = Math.max(res, merge(map, nums[i], nums[i]+1));
            }
            if (map.containsKey(nums[i] - 1)) {
                // 看看前面有没有
                res = Math.max(res, merge(map, nums[i] - 1, nums[i]));
            }
        }
        return res;
    }


    private static int merge(HashMap<Integer, Integer> map, int pre, int next) {
        Integer left = map.get(pre);
        Integer right = map.get(next);

        int len = left + right;

        map.put(pre - left + 1, len);
        map.put(pre + right, len);

        return len;
    }


    /**
     * 官方题解
     * @param nums
     * @return
     */
    public static int solution2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i] - 1)) {
                // 说明前面没有值，这个就是区间开头
                int max = 0;
                int offset = 0;
                while (set.contains(nums[i] + offset)) {
                    offset++;
                    max++;
                }
                res = Math.max(max, res);
            }
        }
        return res;
    }

}
