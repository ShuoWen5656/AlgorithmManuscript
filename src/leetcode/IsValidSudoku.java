package leetcode;

/**
 * @author swzhao
 * @date 2023/10/31 9:41 下午
 * @Discreption <>有效的数独
 */
public class IsValidSudoku {


    public static void main(String[] args) {
        System.out.println(solution(new char[][]{
                {'.','8','7','6','5','4','3','2','1'},
                {'2','.','.','.','.','.','.','.','.'},
                {'3','.','.','.','.','.','.','.','.'},
                {'4','.','.','.','.','.','.','.','.'},
                {'5','.','.','.','.','.','.','.','.'},
                {'6','.','.','.','.','.','.','.','.'},
                {'7','.','.','.','.','.','.','.','.'},
                {'8','.','.','.','.','.','.','.','.'},
                {'9','.','.','.','.','.','.','.','.'}
        }));
    }


    public static boolean solution(char[][] board) {

        // row[i][j] 代表第i行数字j是否已经出现过
        boolean[][] row = new boolean[9][9];
        // col[i][k] 代表第i列数字j是否已经出现过
        boolean[][] col = new boolean[9][9];
        // 9宫格 box[i][j][k]代表第[i][j]个矩阵中数字k是否已经出现过
        boolean[][][] box = new boolean[3][3][9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char cur = board[i][j];
                if (cur < '0' || cur > '9') {
                    continue;
                }
                // 计算出当前值的integer类型
                int curValue = cur - '0' - 1;
                // 判断当前值是否在当前行出现过
                if (row[i][curValue]) {
                    // 出现过
                    return false;
                }
                if (col[j][curValue]) {
                    return false;
                }
                if (box[i/3][j/3][curValue]) {
                    return false;
                }
                // 说明没有出现过，更新矩阵
                row[i][curValue] = true;
                col[j][curValue] = true;
                box[i/3][j/3][curValue] = true;
            }
        }
        return true;
    }


}
