package leetcode;

import java.util.HashMap;

/**
 * @author swzhao
 * @date 2023/11/2 10:37 下午
 * @Discreption <> 单词规律
 */
public class WordPattern {


    public static void main(String[] args) {

    }

    public static boolean solution(String pattern, String s) {

        char[] chars = pattern.toCharArray();

        String[] s1 = s.split("\\s+");

        if (chars.length != s1.length) {
            return false;
        }

        HashMap<Character, String> c2s = new HashMap<>();
        HashMap<String, Character> s2c = new HashMap<>();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            String s2 = s1[i];
            if ((c2s.containsKey(c) && !c2s.get(c).equals(s2))
                    || (s2c.containsKey(s2) && s2c.get(s2) != c)) {
                return false;
            }

            c2s.put(c, s2);
            s2c.put(s2, c);
        }
        return true;

    }

}
