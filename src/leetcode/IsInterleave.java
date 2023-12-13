package leetcode;

/**
 * @author swzhao
 * @date 2023/12/8 8:45 下午
 * @Discreption <> 交错字符串
 */
public class IsInterleave {


    public static void main(String[] args) {
        solution("aabcc", "dbbca", "aadbbcbcac");
    }

    public static boolean solution(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        char[] chars3 = s3.toCharArray();
        // dp[i][j]代表取chars1[0...i] 和 chars2[0...j] 是否能够组成char3[0...i+j+1]
        boolean[][] dp = new boolean[chars1.length+1][chars2.length+1];
        dp[0][0] = true;
        // 初始化
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = chars1[i-1] == chars3[i-1] && dp[i-1][0];
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = chars2[j-1] == chars3[j-1] && dp[0][j-1];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // 角标需要-1
                // 如果上面和左边都是true
                if (dp[i-1][j] && dp[i][j-1]) {
                    // 去掉哪边都可以，说明只要有一个相等的就可以
                    dp[i][j] = chars3[i+j-1] == chars1[i-1] || chars3[i+j-1] == chars2[j-1];
                }else if (dp[i-1][j]) {
                    // 找到一个true的，说明接下来的一个数字必须要从另一边取
                    dp[i][j] = chars3[i+j-1] == chars1[i-1];
                }else if (dp[i][j-1]){
                    dp[i][j] = chars3[i+j-1] == chars2[j-1];
                }else {
                    // 两个都是false的时候说明不可能组成了
                    dp[i][j] = false;
                }
            }
        }
        return dp[dp.length-1][dp[0].length - 1];
    }
}
