package leetcode;

/**
 * @author swzhao
 * @date 2023/10/20 8:59 下午
 * @Discreption <> 判断子序列
 */
public class IsSubsequence {


    public static void main(String[] args) {
        System.out.println(solution("abc", "ahbgdc"));
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

}
