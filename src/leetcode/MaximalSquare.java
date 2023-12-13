package leetcode;

/**
 * @author swzhao
 * @date 2023/12/10 3:01 下午
 * @Discreption <> 最大正方形
 */
public class MaximalSquare {


    public static void main(String[] args) {
        solution(new char[][]{
                {'0','1','1','0','0','1','0','1','0','1'},
                {'0','0','1','0','1','0','1','0','1','0'},
                {'1','0','0','0','0','1','0','1','1','0'},
                {'0','1','1','1','1','1','1','0','1','0'},
                {'0','0','1','1','1','1','1','1','1','0'},
                {'1','1','0','1','0','1','1','1','1','0'},
                {'0','0','0','1','1','0','0','0','1','0'},
                {'1','1','0','1','1','0','0','1','1','1'},
                {'0','1','0','1','1','0','1','0','1','1'}
        });
//        solution(new char[][]{
//                {'1','1','1','1','0'},
//                {'1','1','1','1','0'},
//                {'1','1','1','1','1'},
//                {'1','1','1','1','1'},
//                {'0','0','1','1','1'}
//        });
    }


    public static int solution(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int res = 0;
        // right[i][j]代表点能够往右边探测的深度
        int[][] right = new int[row][col];

        // down[i][j] 代表点能够往下边探测的深度
        int[][] down = new int[row][col];
        if (matrix[row - 1][col - 1] != '0') {
            right[row-1][col-1] = 1;
            down[row - 1][col - 1] = 1;
            res = 1;
        }
        for (int i = row - 2; i >= 0; i--) {
            if (matrix[i][col - 1] == '0') {
                down[i][col-1] = 0;
                right[i][col - 1] = 0;
            }else {
                down[i][col-1] = down[i+1][col - 1] + 1;
                right[i][col - 1] = 1;
                res = 1;
            }
        }
        for (int j = col - 2; j >= 0; j--) {
            if (matrix[row - 1][j] == '0') {
                down[row - 1][j] = 0;
                right[row - 1][j] = 0;
            }else {
                down[row - 1][j] = 1;
                right[row - 1][j] = right[row - 1][j + 1] + 1;
                res = 1;
            }
        }
        for (int i = row - 2; i >= 0 ; i--) {
            for (int j = col - 2; j >= 0; j--) {
                // 无需计算0的位置
                if (matrix[i][j] != '0') {
                    // 计算出当前位置能够探到的最右和最下边
                    int r = right[i][j + 1];
                    int d = down[i+1][j];
                    // 更新当前位置
                    right[i][j] = r + 1;
                    down[i][j] = d + 1;
                    int min = Math.min(r, d) + 1;
                    // 不断尝试
                    while (min >= 0) {
                        boolean flag = true;
                        for (int k = i; k < i + min; k++) {
                            if (right[k][j] < min) {
                                flag = false;
                            }
                        }
                        if (flag) {
                            // 说明min可以
                            break;
                        }
                        min--;
                    }
                    res = Math.max(res, min * min);
                }
            }
        }
        return res;
    }

}
