package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/20 11:36 下午
 * @Discreption <> 全排列
 */
public class Permute {


    public static void main(String[] args) {
        solution(new int[]{1,2,3});
    }


    public static List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, false);
        }
        int length = nums.length;
       process(0, length, new ArrayList<>(),map, res);
       return new ArrayList<>(res);
    }


    private static void process(int level, int length, List<Integer> pre,HashMap<Integer, Boolean> map, List<List<Integer>> res) {
        if (length == level){
            // 说明长度够了
            res.add(pre);
            return;
        }
        // 遍历所有key,找为false的
        for (Integer key : map.keySet()) {
            if (map.get(key)) {
                continue;
            }
            map.put(key, true);
            // 加入
            pre.add(key);
            ArrayList<Integer> curPre = new ArrayList<>(pre);
            process(level + 1, length, curPre, map, res);
            pre.remove(pre.size() - 1);
            // 还原回去
            map.put(key, false);
        }
    }

}
