package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/19 2:56 下午
 * @Discreption <> 电话号码的字母组合
 */
public class LetterCombinations {


    public static void main(String[] args) {
        solution("23");
    }

    public static String[] phone = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};


    public static List<String> solution(String digits) {
        if (digits == null || digits.equals("")) {
            return new ArrayList<>();
        }
        HashSet<String> res = new HashSet<>();
        char[] chars = digits.toCharArray();
        process(chars, 0, res, "");
        return new ArrayList<>(res);
    }


    private static void process(char[] chars, int start, HashSet<String> res, String pre) {
        if (start > chars.length - 1) {
            // 已经越界结束
            res.add(pre);
            return;
        }

        char curChar = chars[start];
        String values = phone[curChar - '0' - 2];
        // 可能的值
        char[] cloudValue = values.toCharArray();

        // 每一个可能性都递归一次
        for (int i = 0; i < cloudValue.length ;i++) {
            char curValue = cloudValue[i];
            process(chars, start+1, res, pre + new String(new char[]{curValue}));
        }
    }

}
