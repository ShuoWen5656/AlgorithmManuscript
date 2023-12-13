package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/10/14 10:04 上午
 * @Discreption <> 最长公共前缀
 */
public class LongestCommonPrefix {


    public static void main(String[] args) {
        System.out.println(solution(new String[]{"flower","flow","flight"}));
    }


    public static String solution(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        StringBuffer res = new StringBuffer();
        List<String> strings = new ArrayList<>();
        // 最短的字符串
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            minLen = Math.min(minLen, strs[i].length());
            strings.add(strs[i]);
        }
        // 开始循环
        for (int i = 0; i < minLen; i++) {
            // 获取第一个字符串的第i个位置char
            char c = strings.get(0).charAt(i);
            for (String s : strings) {
                if (s.charAt(i) != c) {
                    // 只要有一个不相等的直接返回
                    return res.toString();
                }
            }
            // 到这里说明当前字符都相等可以接入
            res.append(c);
        }
        return res.toString();
    }




}
