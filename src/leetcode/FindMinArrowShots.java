package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/5 11:03 上午
 * @Discreption <> 用最少数量的箭引爆气球
 */
public class FindMinArrowShots {


    public static void main(String[] args) {
        System.out.println(solution(new int[][]{
                {-2147483646,-2147483645},{2147483646,2147483647}
        }));
    }

    public static int solution(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] > o2[0]) {
                    return 1;
                }else if (o1[0] == o2[0]) {
                    return 0;
                }else {
                    return -1;
                }
            }
        });
        int res = 0;
        List<int[]> helper = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            int start = points[i][0];
            int end = points[i][1];
            if (helper.size() == 0 || start > helper.get(helper.size() - 1)[1]) {
                helper.add(points[i]);
                res++;
            }else {
                // 重叠了不需要多一只剪
                int[] lastRange = helper.get(helper.size() - 1);
                lastRange[0] = Math.max(lastRange[0], start);
                lastRange[1] = Math.min(lastRange[1], end);
            }
        }

        return res;
    }

}
