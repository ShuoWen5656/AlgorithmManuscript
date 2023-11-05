package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/5 10:51 上午
 * @Discreption <> 插入区间
 */
public class InsertRange {


    public static int[][] solution(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> list = new ArrayList<>(Arrays.asList(intervals));
        list.add(newInterval);
        return solution2(list.toArray(new int[list.size()][2]));
    }

    public static int[][] solution2(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        // 排序:按照区间开始排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            if (res.size() == 0 || start > res.get(res.size() - 1)[1]) {
                res.add(intervals[i]);
            }else {
                // 合并
                int[] lastRange = res.get(res.size() - 1);
                lastRange[1] = Math.max(lastRange[1], end);
            }
        }
        return res.toArray(new int[res.size()][2]);
    }
}
