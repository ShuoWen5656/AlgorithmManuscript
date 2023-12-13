package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/16 10:56 下午
 * @Discreption <> 课程表1
 */
public class CanFinish {


    public static void main(String[] args) {
        solution(2, new int[][]{
                {0, 1}
        });
    }

    // 深度优先遍历的两个变量
    static List<List<Integer>> edge;
    // 每一个节点的状态
    static int[] status;
    // 是否成环
    static boolean reverse;


    public static boolean solution(int numCourses, int[][] prerequisites) {
        // 初始化变量
        edge = new ArrayList<>();
        status = new int[numCourses];
        reverse = false;
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
            status[i] = 0;
        }

        // 遍历边
        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0]; int b = prerequisites[i][1];
            edge.get(b).add(a);
        }

        // 遍历点
        for (int i = 0; i < numCourses; i++) {
            if (status[i] == 0) {
                dfs(i);
            }
            if (reverse) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(int i) {
        if (status[i] == 1) {
            reverse = true;
            return;
        }else if (status[i] == 2){
            return;
        }
        status[i] = 1;
        List<Integer> integers = edge.get(i);
        for (Integer integer : integers) {
            dfs(integer);
            if (reverse) {
                return;
            }
        }
        status[i] = 2;
    }


}
