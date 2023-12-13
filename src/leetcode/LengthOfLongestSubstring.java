package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @date 2023/10/25 8:32 下午
 * @Discreption <> 无重复字符的最长子串
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(solution("bbbbb"));
    }



    public static int solution(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int cur = 0;
        int pre = 0;
        int res = Integer.MIN_VALUE;

        char[] chars = s.toCharArray();

        Map<Character, Integer> cIndexMap = new HashMap<>();

        while (cur < chars.length) {
            if (!cIndexMap.containsKey(chars[cur])) {
                cIndexMap.put(chars[cur], cur);
                res = Math.max(res, cur - pre + 1);
                cur++;
                continue;
            }else {
                // 出现过
                Integer index = cIndexMap.get(chars[cur]);
                // index之前的都删除
                while (pre <= index) {
                    cIndexMap.remove(chars[pre]);
                    pre++;
                }
            }
        }
        return res;
    }


}
