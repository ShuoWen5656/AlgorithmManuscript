package leetcode;

import dataConstruct.Query;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author swzhao
 * @date 2023/11/17 11:13 下午
 * @Discreption <> 蛇梯棋
 */
public class SnakesAndLadders {


    public static void main(String[] args) {
        solution(new int[][]{
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,15,-1,-1,-1,-1}
        });
    }


    public static int solution(int[][] board) {
        int len = board.length;
        // 辅助队列，元素int[0]为第几个元素, int[1]为当前步数
        Deque<int[]> queue = new LinkedList<>();
        queue.addLast(new int[]{1,0});
        // 辅助矩阵表示位置是否已经被遍历过,因为从1开始所以多一个格子
        boolean[] hasVis = new boolean[len * len + 1];
        while (!queue.isEmpty()) {
            // 弹出当前点
            int[] cur = queue.pollFirst();
            int a = cur[0];
            int b = cur[1];
            // 最多走6个格子
            for (int i = 1; i <= 6; i++) {
                int next = a + i;
                if (next > len * len) {
                    // 是否越界
                    break;
                }
                // 计算出来next的行列值
                int[] rowAndCol = getRowAndCol(next, len);
                if (board[rowAndCol[0]][rowAndCol[1]] > 0) {
                    // 说明是蛇梯,next直接跳转到蛇梯的位置
                    next = board[rowAndCol[0]][rowAndCol[1]];
                }
                if (next == len * len) {
                    // 到终点了步数+1即可
                    return b + 1;
                }
                if (!hasVis[next]) {
                    hasVis[next] = true;
                    // 更新当前点
                    queue.addLast(new int[]{next, b+1});
                }
            }
        }
        // 一直没到终点
        return -1;
    }

    private static int[] getRowAndCol(int next, int len) {
        int row = (next-1) / len;
        int col = (next-1) % len;
        return new int[]{len - 1 - row, (row & 1) == 0 ? col : len - 1 - col};
    }


}
