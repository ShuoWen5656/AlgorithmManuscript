package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/5 9:18 上午
 * @Discreption <> 合并区间
 */
public class MergeArrays {

    public static void main(String[] args) {

        solution2(new int[][]{
                {1,4},
                {5,6}
        });
    }

    /**
     * 有些缺陷，不重叠但是连续的话也会被合并进去
     * @param intervals
     * @return
     */
    public static int[][] solution(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][2];
        }
        List<int[]> res = new ArrayList<>();

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < intervals.length; i++) {
            max = Math.max(max, intervals[i][1]);
            min = Math.min(min, intervals[i][0]);
        }

        // 新建区间
        int[] helper = new int[max - min + 1];
        for (int i = 0; i < intervals.length; i++) {
            // 将helper中的部分位置1
            int start = intervals[i][0];
            int end = intervals[i][1];
            while (start <= end) {
                helper[start - min] = 1;
                start++;
            }
        }

        // 开始分割helper
        int cur = 0;
        int pre = 0;


        while (cur < helper.length){
            // 找到下一个为1的地方
            while (cur < helper.length) {
                if (helper[cur] == 1) {
                    break;
                }
                cur++;
                pre++;
            }
            // 找到这个区间
            while (cur < helper.length && helper[cur] == 1) {
                cur++;
            }
            // 区间加入结果
            res.add(new int[]{pre + min, cur + min - 1});
            pre = cur;
        }
        return res.toArray(new int[res.size()][]);
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
