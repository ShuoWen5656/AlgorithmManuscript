package leetcode;

import java.util.HashMap;

/**
 * @author swzhao
 * @date 2023/11/2 9:21 下午
 * @Discreption <> 同构字符串
 */
public class IsIsomorphic {

    public static void main(String[] args) {
        System.out.println(solution( "add", "egg"));
    }


    public static boolean solution(String s, String t) {
        HashMap<Character, Character> s2t = new HashMap<>();
        HashMap<Character, Character> t2s = new HashMap<>();

        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();

        for (int i = 0; i < charsS.length; i++) {
            char s1 = charsS[i];
            char t1 = charsT[i];
            if ((s2t.containsKey(s1) && s2t.get(s1) != t1) || (t2s.containsKey(t1) && t2s.get(t1) != s1)) {
                return false;
            }
            // 放入
            s2t.put(s1, t1);
            t2s.put(t1, s1);
        }
        return true;
    }

}
