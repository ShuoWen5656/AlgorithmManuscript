package leetcode;

/**
 * @author swzhao
 * @date 2023/11/14 10:03 下午
 * @Discreption <>岛屿数量
 */
public class NumIslands {

    public static void main(String[] args) {
        solution(new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        });
    }

    public static int solution(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == '1') {
                    count++;
                    process(grid, i, j);
                }
            }
        }
        return count;
    }

    /**
     * 从row扩张
     * @param grid
     * @param row
     * @param col
     */
    public static void process(char[][] grid, int row, int col) {
        if (grid[row][col] == '0') {
            return;
        }
        grid[row][col] = '0';

        // 上
        if (row - 1 >= 0) {
            if (grid[row-1][col] != '0'){
                process(grid, row - 1, col);
            }
        }
        // 下
        if (row + 1 < grid.length){
            if (grid[row + 1][col] != '0'){
                process(grid, row + 1, col);
            }
        }
        // 左
        if (col - 1 >= 0) {
            if (grid[row][col-1] != '0'){
                process(grid, row, col-1);
            }
        }
        // 右
        if (col + 1 < grid[0].length) {
            if (grid[row][col+1] != '0'){
                process(grid, row, col+1);
            }
        }
    }

}
