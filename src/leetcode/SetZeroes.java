package leetcode;

/**
 * @author swzhao
 * @date 2023/11/1 9:19 下午
 * @Discreption <> 矩阵置零
 */
public class SetZeroes {

    public static void main(String[] args) {
        solution(new int[][]{
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}
        });
    }


    public static void solution(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] col = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    // 记录当前行列
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i = 0; i < row.length; i++) {
            if (row[i]) {
                for (int j = 0; j < col.length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 0; j < col.length; j++) {
            if (col[j]) {
                for (int i = 0; i < row.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

    }



}
