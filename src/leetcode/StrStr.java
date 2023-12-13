package leetcode;

/**
 * @author swzhao
 * @date 2023/10/14 1:39 下午
 * @Discreption <> 找出字符串中第一个匹配项的下标
 */
public class StrStr {

    public static void main(String[] args) {
        System.out.println(solution("leetcode", "leet"));
    }



    public static int solution(String haystack, String needle) {
//        if (haystack == null || needle == null || needle.length() > haystack.length()) {
//            return -1;
//        }
        return haystack.indexOf(needle);
     }


}
