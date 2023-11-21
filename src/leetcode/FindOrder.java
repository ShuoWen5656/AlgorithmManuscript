package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/16 9:53 下午
 * @Discreption <> 课程表2
 */
public class FindOrder {

    public static void main(String[] args) {
        solution(2, new int[][]{
                {1, 0}
        });
    }
    // 深度优先算法必须的两个变量
    // 1、保存边的数组
    static List<List<Integer>> edges;
    // 2、每一个节点的状态，0：未搜索 1：正在搜索 2：搜索完成
    static int[] status;
    // 接下来根据业务的不同进行存储
    // 由于深度遍历结果需要倒序存储
    static int index;
    static int[] res;
    // 是否成环
    static boolean  reverse = false;
    public static int[] solution(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        status = new int[numCourses];
        index = numCourses-1;
        res = new int[numCourses];
        reverse = false;
        // 初始化
        for (int i = 0; i < numCourses; i++) {
            status[i] = 0;
            edges.add(new ArrayList<>());
        }
        // 遍历所有边
        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0], b = prerequisites[i][1];
            // b->a
            edges.get(b).add(a);
        }
        // 开始搜索
        for (int i = 0; i < numCourses; i++) {
            // 首先status不会为1，为2直接过
            if (status[i] == 0) {
                dfs(i);
            }
            if (reverse) {
                return new int[0];
            }
        }
        return res;
    }

    private static void dfs(int i) {
        // 状态不是0的都返回
        if (status[i] == 1) {
            // 成环
            reverse = true;
            return;
        }else if (status[i] == 2){
            return;
        }
        status[i] = 1;
        // 获取指向当前节点的节点
        List<Integer> integers = edges.get(i);
        for (int j = 0; j < integers.size(); j++) {
            dfs(integers.get(i));
            if (reverse) {
                return;
            }
        }
        // 放入
        res[index--] = i;
        status[i] = 2;
    }

}
