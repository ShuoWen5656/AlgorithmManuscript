package leetcode;

/**
 * @author swzhao
 * @date 2023/10/10 9:29 下午
 * @Discreption <>最后一个单词的长度
 */
public class LengthOfLastWord {

    public static void main(String[] args) {

        System.out.println(solution("hello world"));

    }



    public static int solution(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        char[] chars = s.toCharArray();
        for (int i = 0 ; i < chars.length; i++) {
            if (i != 0 && chars[i-1] == ' ' && chars[i] != ' ') {
                left = i;
            }
            if (chars[i] != ' ') {
                right = i;
            }
        }

        return right - left + 1;
    }

}
