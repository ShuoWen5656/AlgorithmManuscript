package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/10/31 10:53 下午
 * @Discreption <> 螺旋矩阵
 */
public class SpiralOrder {


    public static void main(String[] args) {
        System.out.println(solution(new int[][]{
                {7},
                {9},
                {6}
        }));
    }

    public static List<Integer> solution(int[][] matrix) {
        List<Integer> list = new ArrayList<>();

        int row = matrix.length;
        int col = matrix[0].length;

        int min = Math.min(row, col);

        // 圈数
        int count = (min & 1) == 1 ? min/2 + 1 : min / 2;

        for (int i = 0 ; i < count; i++) {
            getInteger(i, matrix, list);
        }
        return list;
    }


    /**
     * 注意点：
     * 1、每次的起点不可重合
     * 2、横向一条线多个判断
     * 3、纵向一条线多个判断
     * @param i
     * @param matrix
     * @param list
     */
    private static void getInteger(int i, int[][] matrix, List<Integer> list) {
        // 起点
        int startRow = i;
        int startCol = i;

        // 横向
        while (startCol < matrix[0].length - i) {
            list.add(matrix[startRow][startCol]);
            startCol++;
        }
        startCol--;
        startRow++;
        // 竖向
        while (startRow < matrix.length - i) {
            list.add(matrix[startRow][startCol]);
            startRow++;
        }
        startRow--;
        startCol--;
        // 反向横
        while (startCol >= i && startRow != i) {
            list.add(matrix[startRow][startCol]);
            startCol--;
        }
        startCol++;
        startRow--;
        while (startRow > i && startCol != matrix[0].length-1 - i) {
            list.add(matrix[startRow][startCol]);
            startRow--;
        }
    }
}
