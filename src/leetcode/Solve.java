package leetcode;

/**
 * @author swzhao
 * @date 2023/11/15 8:09 下午
 * @Discreption <> 被围绕的区域
 */
public class Solve {


    public static void main(String[] args) {
        char[][] chars = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        solution(chars);
    }


    public static void solution(char[][] board) {
        // 围绕边缘转一圈，记录边缘O
        boolean[][] helper = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length;i++) {
            if (board[i][0] == 'O') {
                process(board, i, 0, helper);
            }
        }
        for (int j = 0; j < board[0].length;j++) {
            if (board[0][j] == 'O') {
                process(board, 0, j, helper);
            }
        }
        for (int i = 0; i < board.length;i++) {
            if (board[i][board[0].length-1] == 'O') {
                process(board, i, board[0].length - 1, helper);
            }
        }
        for (int j = 0; j < board[0].length;j++) {
            if (board[board.length-1][j] == 'O') {
                process(board, board.length-1, j, helper);
            }
        }
        // 记录完成后开始进行赋值
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !helper[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }


    /**
     * 从row扩张
     * @param grid
     * @param row
     * @param col
     * @param helper
     */
    public static void process(char[][] grid, int row, int col, boolean[][] helper) {
        if (helper[row][col]) {
            // 计算过得
            return;
        }
        helper[row][col] = true;

        // 上
        if (row - 1 >= 0) {
            if (grid[row-1][col] == 'O'){
                process(grid, row - 1, col, helper);
            }
        }
        // 下
        if (row + 1 < grid.length){
            if (grid[row + 1][col] == 'O'){
                process(grid, row + 1, col, helper);
            }
        }
        // 左
        if (col - 1 >= 0) {
            if (grid[row][col-1] == 'O'){
                process(grid, row, col-1, helper);
            }
        }
        // 右
        if (col + 1 < grid[0].length) {
            if (grid[row][col+1] == 'O'){
                process(grid, row, col+1, helper);
            }
        }
    }

}
