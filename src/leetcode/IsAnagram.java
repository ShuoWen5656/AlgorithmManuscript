package leetcode;


import java.util.Arrays;

/**
 * @author swzhao
 * @date 2023/11/3 7:08 下午
 * @Discreption <> 有效的字母异位词
 */
public class IsAnagram {


    public static void main(String[] args) {
        System.out.println(solution("abc", "cba"));
    }


    public static boolean solution(String s, String t) {
        char[] chars = s.toCharArray();
        char[] chars1 = t.toCharArray();
        Arrays.sort(chars);
        Arrays.sort(chars1);
        return String.valueOf(chars).equals(String.valueOf(chars1));
    }

}
