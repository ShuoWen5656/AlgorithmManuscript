package leetcode;

/**
 * @author swzhao
 * @date 2023/11/22 2:45 下午
 * @Discreption <> 单词搜索
 */
public class EexistWord {


    public static void main(String[] args) {
//        System.out.println(solution(new char[][]{
//                {'A','B','C','E'},
//                {'S','F','C','S'},
//                {'A','D','E','E'}
//        }, "ABCCED"));
        System.out.println(solution(new char[][]{
                {'A'}
        }, "A"));
    }



    public static boolean solution(char[][] board, String word) {
        char[] chars = word.toCharArray();
        boolean flag = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (flag) {
                    break;
                }
                flag = process(board, i, j, chars, 0);
            }
        }
        return flag;
    }

    private static boolean process(char[][] board, int row, int col, char[] chars, int index) {
        if (index == chars.length) {
            // 说明前面都是匹配的,找到了
            return true;
        }
        if (board[row][col] != chars[index]) {
            return false;
        }
        // 相等并且还没到头，则开始dfs
        boolean flag = false;
        char tmp = board[row][col];
        board[row][col] = 0;
        // 上
        if (row - 1 >= 0 && board[row - 1][col] != 0) {
            flag = process(board, row - 1, col, chars, index+1);
        }
        // 下
        if (!flag && row + 1 < board.length && board[row + 1][col] != 0) {
            flag = process(board, row + 1, col, chars, index+1);
        }
        // 左
        if (!flag && col - 1 >= 0 && board[row][col-1] != 0) {
            flag = process(board, row, col-1, chars, index+1);
        }
        // 右
        if (!flag && col + 1 < board[0].length && board[row][col+1] != 0) {
            flag = process(board, row, col+1, chars, index+1);
        }
        // 兼容无路可走的情况index已经到头
        if (!flag && index == chars.length-1) {
            flag = true;
        }
        board[row][col] = tmp;
        return flag;
    }


}
