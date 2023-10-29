package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @date 2023/10/27 10:54 下午
 * @Discreption <> 最小覆盖子串
 */
public class MinWindow {


    public static void main(String[] args) {
        System.out.println(solution("ADOBECODEBANC", "ABC"));
    }


    public static String solution(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return null;
        }
//        计算欠的数量和每一个字母的数量
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        // 总欠量
        int total = tChars.length;
        // 欠量明细
        Map<Character, Integer> characterCountMap = new HashMap<>();
        for (int i = 0; i < tChars.length; i++) {
            if (!characterCountMap.containsKey(tChars[i])) {
                characterCountMap.put(tChars[i], 0);
            }
            characterCountMap.put(tChars[i], characterCountMap.get(tChars[i]) + 1);
        }
        // 最终结果
        int res = Integer.MAX_VALUE;
        String resStr = "";
        int cur = 0;
        int pre = 0;
        while (cur < sChars.length) {
            Integer integer = characterCountMap.get(sChars[cur]);
            // 更新total和map
            if (integer != null) {
                // 能还个
                if (integer > 0) {
                    // 有效还
                    total--;
                }
                // 更新map
                characterCountMap.put(sChars[cur], integer - 1);
            }

            if (total <= 0) {
                // 已经还清，计算最小值
                if (cur - pre + 1 < res) {
                    resStr = s.substring(pre, cur+1);
                    res = cur - pre + 1;
                }
                // 变到欠款状态
                while (total <= 0) {
                    // 去掉pre
                    Integer integer1 = characterCountMap.get(sChars[pre]);
                    // 更新total和map
                    if (integer1 != null) {
                        if (integer1 + 1 > 0) {
                            // 又欠款了
                            total++;
                        }
                        // 更新map
                        characterCountMap.put(sChars[pre], integer1 + 1);
                    }
                    pre++;
                    if (total <= 0) {
                        if (cur - pre + 1 < res) {
                            resStr = s.substring(pre, cur+1);
                            res = cur - pre + 1;
                        }
                    }
                }
            }
            cur++;
        }
        return resStr;
    }
}
