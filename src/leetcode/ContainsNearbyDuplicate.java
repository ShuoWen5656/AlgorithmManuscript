package leetcode;

import java.util.HashMap;

/**
 * @author swzhao
 * @date 2023/11/4 9:18 上午
 * @Discreption <> 存在重复元素
 */
public class ContainsNearbyDuplicate {

    public static void main(String[] args) {
    }


    public static boolean solution(int[] nums, int k) {
        // 数字 - 该数字最近一次出现的索引
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                // 出现过
                Integer preIndex = map.get(nums[i]);
                if (i - preIndex <= k) {
                    return true;
                }
            }
            // 记录当前元素最近一次出现的地方
            map.put(nums[i], i);
        }
        return false;
    }

}
