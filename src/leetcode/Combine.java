package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/20 9:54 下午
 * @Discreption <>组合
 */
public class Combine {

    public static void main(String[] args) {
        solution(4, 2);
    }


    public static List<List<Integer>> solution(int n, int k) {
        if (n < k) {
            return null;
        }
        HashSet<List<Integer>> res = new HashSet<>();

        process(1, 0, new ArrayList<>(),res, n, k);
        return new ArrayList<>(res);
    }

    private static void process(int start, int level, List<Integer> pre, HashSet<List<Integer>> res, int n, int k) {
        if (level == k) {
            // 已经到达指定字符数
            res.add(pre);
            return;
        }
        // 距离k层还差几个level
        int len = k - level - 1;
        for (int i = start; i <= n - len; i++) {
            pre.add(i);
            List<Integer> curPre = new ArrayList<>(pre);
            process(i+1, level + 1, curPre, res, n , k);
            // 结束后删掉
            pre.remove(pre.size() - 1);
        }
    }

}
