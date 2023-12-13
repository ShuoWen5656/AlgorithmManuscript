package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @date 2023/10/30 11:27 下午
 * @Discreption <> 赎金信
 */
public class CanConstruct {

    public static void main(String[] args) {
        System.out.println(solution("aa", "ab"));
    }



    public static boolean solution(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();

        char[] chars = magazine.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!map.containsKey(chars[i])) {
                map.put(chars[i], 0);
            }
            map.put(chars[i], map.get(chars[i]) + 1);
        }

        char[] chars1 = ransomNote.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            if (!map.containsKey(chars1[i]) || map.get(chars1[i]) - 1 < 0) {
                return false;
            }
            map.put(chars1[i], map.get(chars1[i]) - 1);
        }
        return true;
    }


}
