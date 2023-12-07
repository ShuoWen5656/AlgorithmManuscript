package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author swzhao
 * @date 2023/12/6 8:18 下午
 * @Discreption <> 单词拆分
 */
public class WordBreak {

    public static void main(String[] args) {
        solution("leetcode", Arrays.asList("leet", "code"));
    }

    public static boolean solution(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];
        // dp[i] 代表s[0...i]是否能够被wordDict组成
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            // 从0开始一段一段的截取
            // 一直找到一个j...i由一个单词管，剩下的交给其他单词时能行的通的方案
            for (int j = 0; j <= i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }



}
