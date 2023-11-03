package leetcode;


import java.util.*;
import java.util.stream.Collectors;

/**
 * @author swzhao
 * @date 2023/11/3 7:19 下午
 * @Discreption <> 字母异位词分组
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }


    public static List<List<String>> solution(String[] strs) {
//        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(s -> s.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString())).values());
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            List<String> orDefault = map.getOrDefault(key, new ArrayList<>());
            map.put(key ,orDefault);
            orDefault.add(s);
        }
        return new ArrayList<>(map.values());
//        List<String> strings = Arrays.asList(strs);
//
//        HashMap<Integer, List<String>> helper = new HashMap<>();
//
//        for (String s : strings) {
//            int cur = call(s);
//            if (!helper.containsKey(cur)){
//                helper.put(cur, new ArrayList<>());
//            }
//            List<String> strings1 = helper.get(cur);
//            strings1.add(s);
//        }
//        return new ArrayList<>(helper.values());
    }


//    public static int call(String s) {
//        int res = 0;
//        char[] chars = s.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            int count = chars[i] - 'a';
//            res = res | (1 << count);
//        }
//        return res;
//    }



}
