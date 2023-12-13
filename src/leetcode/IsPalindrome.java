package leetcode;

/**
 * @author swzhao
 * @date 2023/10/19 8:34 下午
 * @Discreption <> 验证回文串
 */
public class IsPalindrome {

    public static void main(String[] args) {
        System.out.println(solution("0P"));
    }

    public static boolean solution(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        char[] chars = s.toCharArray();

        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            // 首先过滤掉其他字符
            if (!check(chars[left])) {
                left++;
                continue;
            }
            if (!check(chars[right])) {
                right--;
                continue;
            }
            // 将大写字母转小写
            if (chars[left] >= 'A' && chars[left] <= 'Z') {
                chars[left] = (char) ('a' + (chars[left] - 'A'));
            }
            if (chars[right] >= 'A' && chars[right] <= 'Z') {
                chars[right] = (char) ('a' + (chars[right] - 'A'));
            }
            // 开始比较
            if (chars[right] != chars[left]) {
                // 不相等直接g
                return false;
            }
            right--;
            left++;
        }
        return true;
    }


    public static boolean check(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
            return true;
        }
        return false;
    }


}
