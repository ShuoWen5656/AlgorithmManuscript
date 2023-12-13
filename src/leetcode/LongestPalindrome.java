package leetcode;

/**
 * @author swzhao
 * @date 2023/12/8 8:51 下午
 * @Discreption <> 最长回文子串
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.printf(solution("babad"));
    }


    public static String solution(String s) {
        if (s.isEmpty()) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        // dp[i][j] 代表从i。。。j是否时回文字符串
        boolean[][] dp = new boolean[chars.length][chars.length];
        String res = "";
        // 斜对角都是true
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
        }
        // 从右往左，从下往上
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = dp.length-1; j >= i; j--) {
                // 决定边界
                if (j - i <= 1) {
                    dp[i][j] = chars[i] == chars[j];
                }else {
                    dp[i][j] = dp[i+1][j-1] && chars[i] == chars[j];
                }
                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j+1);
                }
            }
        }
        return res;
    }


}
