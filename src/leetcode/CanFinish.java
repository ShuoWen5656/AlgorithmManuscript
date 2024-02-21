package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/16 10:56 下午
 * @Discreption <> 课程表1
 * 判断有向图是否存在环路
 */
public class CanFinish {


    public static void main(String[] args) {
        solution(2, new int[][]{
                {0, 1}
        });
    }

    // 深度优先遍历的两个变量
    static List<List<Integer>> edge;
    // 每一个节点的状态：0未搜索 1：正在搜索 2：搜索完成
    static int[] status;
    // 是否成环：true成环 false不成环
    static boolean reverse;


    public static boolean solution(int numCourses, int[][] prerequisites) {
        // 初始化变量
        edge = new ArrayList<>();
        status = new int[numCourses];
        reverse = false;
        // 将课程表中的课程进行排序，初始化状态
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
            status[i] = 0;
        }

        // 遍历边，a要执行则必须先执行b，那么b -> a
        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0]; int b = prerequisites[i][1];
            edge.get(b).add(a);
        }

        // 遍历点
        for (int i = 0; i < numCourses; i++) {
            // 仅处理未开始的节点
            if (status[i] == 0) {
                dfs(i);
            }
            // 每次结束判断是否有环路
            if (reverse) {
                return false;
            }
        }
        return true;
    }

    /**
     * 从节点i开始进行深度遍历，遇到环直接退出
     * @param i
     */
    private static void dfs(int i) {
        // 有环或者结束的都直接结束
        if (status[i] == 1) {
            reverse = true;
            return;
        }else if (status[i] == 2){
            return;
        }
        // 标记正在处理
        status[i] = 1;
        List<Integer> integers = edge.get(i);
        // 指向的边都深度遍历一遍
        for (Integer integer : integers) {
            dfs(integer);
            if (reverse) {
                return;
            }
        }
        // 当前点结束
        status[i] = 2;
    }


}
