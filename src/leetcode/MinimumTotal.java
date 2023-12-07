package leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/12/7 9:31 下午
 * @Discreption <>三角形最小路径和
 */
public class MinimumTotal {


    public static void main(String[] args) {
        solution(Arrays.asList(Arrays.asList(2), Arrays.asList(3,4), Arrays.asList(6,5,7), Arrays.asList(4,1,8,3)));
    }



    public static int solution(List<List<Integer>> triangle) {
        int row = triangle.size();
        int col = triangle.get(triangle.size() - 1).size();
        int[][] dp = new int[row][col];
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    // 最左边的特殊处理
                    dp[i][j] = i-1 >= 0 ? dp[i-1][j] + triangle.get(i).get(j) : triangle.get(i).get(j);
                }else if (j == i) {
                    // 最右边的特殊处理
                        dp[i][j] = dp[i-1][j-1] + + triangle.get(i).get(j);
                }else {
                    // 中间的
                    dp[i][j] = Math.min(dp[i-1][j-1] , dp[i-1][j] ) + triangle.get(i).get(j);
                }
                if (i == dp.length -1) {
                    // 最后一行需要比较一下最小值
                    res = Math.min(res, dp[i][j]);
                }
            }
        }
        return res;
    }



}
