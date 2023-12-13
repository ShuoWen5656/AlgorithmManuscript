package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/4 10:41 上午
 * @Discreption <> 汇总区间
 */
public class SummaryRanges {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{0,1,2,4,5,7}));
    }


    public static List<String> solution(int[] nums) {
        List<String> res = new ArrayList<>();

        if (nums.length == 0) {
            return res;
        }
        int pre = 0;
        int cur = 0;

        while (cur < nums.length) {
            if (nums[cur] - nums[pre] == cur - pre) {
                cur++;
                continue;
            }else {
                // 说明断层了
                res.add(nums[pre] == nums[cur-1] ? String.valueOf(nums[pre]) : String.format("%s->%s", nums[pre], nums[cur-1]));
                pre = cur;
            }
        }
        // 收尾
        res.add(nums[pre] == nums[cur-1] ? String.valueOf(nums[pre]) : String.format("%s->%s", nums[pre], nums[cur-1]));
        return res;
    }

}
