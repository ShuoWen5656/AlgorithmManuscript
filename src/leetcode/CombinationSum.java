package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author swzhao
 * @data 2023/11/22 1:27
 * @Discreption <> 组合总和
 */
public class CombinationSum {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2,3,5}, 8));
    }


    public static List<List<Integer>> solution(int[] candidates, int target) {
        HashSet<List<Integer>> res = new HashSet<>();
        // 先排序
        Arrays.sort(candidates);
        process(0, 0,target, res, candidates, new ArrayList<>());
        return new ArrayList<>(res);
    }

    private static void process(int start, int level, int target, HashSet<List<Integer>> res, int[] candidates, List<Integer> pre) {
        if (target == 0) {
            res.add(pre);
            return;
        }
        if (target < 0) {
            // 层数到了
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 减掉
            target -= candidates[i];
            pre.add(candidates[i]);
            List<Integer> curPre = new ArrayList<>(pre);
            process(i, level + 1, target, res, candidates, curPre);
            // 检查完删除
            pre.remove(pre.size() - 1);
            target += candidates[i];
        }
    }
}
