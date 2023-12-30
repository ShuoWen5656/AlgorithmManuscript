package leetcode;

/**
 * @author swzhao
 * @date 2023/10/20 8:59 下午
 * @Discreption <> 判断子序列
 */
public class IsSubsequence {


    public static void main(String[] args) {
        System.out.println(solution2("abc", "ahbgdc"));
    }

    public static boolean solution(String s, String t) {
        if (s == null || t == null || s.length() > t.length()) {
            return false;
        }else if (s.length() == 0) {
            return true;
        }
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();
        // 双游标
        int sIndex = 0;
        int tIndex = 0;

        while (sIndex < s.length()) {
            if (tIndex >= charsT.length) {
                // 说明已经匹配完了都s都没结束,那就是不匹配
                return false;
            }
            if (charsS[sIndex] == charsT[tIndex]) {
                sIndex++;
                tIndex++;
            }else {
                tIndex++;
            }
        }
        return true;
    }

    /**
     * 进阶解法，动态规划，能够判断s的任何一个子串是否在t中
     * @param s
     * @param t
     * @return
     */
    public static boolean solution2(String s, String t) {
        char[] charS = s.toCharArray();
        int sLen = s.length();
        char[] chatT = t.toCharArray();
        int tLen = t.length();

        // dp[i][j] 代表 从t[i]开始出发，字母j第一次出现的地方
        int[][] dp = new int[t.length() + 1][26];
        // 初始化dp,从tlen开始出发的字母第一次出现的地方都应该是tlen（不存在的意思）
        for (int i = 0; i < 26; i++) {
            dp[tLen][i] = tLen;
        }
        // 计算dp
        for (int i = dp.length-2; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                // 如果当前char是j，则直接为i位置，否则继承前一个位置
                dp[i][j] = chatT[i] - 'a' == j ? i : dp[i+1][j];
            }
        }
        int index = 0;
        // 计算答案
        for (int i = 0; i < sLen; i++) {
            // 从index开始判断char第一次出现的地方
            if (dp[index][charS[i] - 'a'] == tLen) {
                // 说明t中从index开始不存在这个字符
                return false;
            }else {
                // 跳到这个字符第一次出现的地方，然后判断下一个字符
                index = dp[index][charS[i] - 'a'] + 1;
            }
        }
        return true;
    }


}
