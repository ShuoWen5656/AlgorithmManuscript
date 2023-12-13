package leetcode;

/**
 * @author swzhao
 * @date 2023/11/2 10:19 下午
 * @Discreption <>生命游戏
 */
public class GameOfLife {


    public static void main(String[] args) {

    }


    public static void solution(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        // 第i行第j列的元素周边1的个数
        int[][] helper = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i - 1 >= 0 && j - 1 >= 0 && board[i-1][j-1] == 1) {
                    helper[i][j] ++;
                }
                if (i - 1 >= 0 && board[i-1][j] == 1) {
                    helper[i][j] ++;
                }
                if (i - 1 >= 0 && j + 1 < col && board[i-1][j+1] == 1) {
                    helper[i][j] ++;
                }
                if (j + 1 < col && board[i][j+1] == 1) {
                    helper[i][j] ++;
                }
                if (i + 1 < row  && j + 1 < col && board[i+1][j+1] == 1) {
                    helper[i][j] ++;
                }
                if (i + 1 < row && board[i+1][j] == 1) {
                    helper[i][j] ++;
                }
                if (i + 1 < row && j - 1 >= 0 && board[i+1][j-1] == 1) {
                    helper[i][j] ++;
                }
                if (j - 1 >= 0 && board[i][j-1] == 1) {
                    helper[i][j] ++;
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (helper[i][j] < 2 || helper[i][j] > 3) {
                    board[i][j] = 0;
                }
                if (board[i][j] == 0 && helper[i][j] == 3) {
                    board[i][j] = 1;
                }
            }
        }

    }
}
