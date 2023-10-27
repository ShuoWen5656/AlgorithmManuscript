package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/10/27 8:55 下午
 * @Discreption <> 串联所有单词的子串
 */
public class FindSubstring {

    public static void main(String[] args) {
        System.out.println(solution("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}));
    }

    public static List<Integer> solution(String s, String[] words) {
        if (words == null || s == null || s.length() == 0 || words.length == 0) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        // 单词总长度
        int len = words.length;
        // 每一个词汇的长度
        int wLen = words[0].length();
        // 每次校验的个数
        int everyLen = len * wLen;
        if (s.length() < everyLen) {
            // s的长度不能小于校验的长度
            return res;
        }
        // words中的每一个单词 - 出现的次数
        HashMap<String, Integer> wordCountMap = new HashMap<>();
        prepareWordCountMap(wordCountMap, words);
        // 开头不会大于s.length() - everyLen
        for (int i = 0; i <= s.length() - everyLen; i++) {
            // 校验以i开头的单词是否能够是结果
            int start = i;
            int end = start + everyLen;
            while (start < end) {
                // 取
                String cur = getStringFromS(s, start, wLen);
                Integer count = wordCountMap.get(cur);
                if (count == null || count <= 0) {
                    // 不存在或者是已经用完了的，出去，不加入结果
                    break;
                }else {
                    // 说明有戏,减少一个数量
                    wordCountMap.put(cur, count-1);
                }
                start += wLen;
            }
            // 如果有戏说明start>=end
            if (start >= end) {
                res.add(i);
            }
            // 每次结束后将word重新清理一下
            prepareWordCountMap(wordCountMap, words);
        }
        return res;
    }

    private static String getStringFromS(String s, int start, int wLen) {
        return s.substring(start, start + wLen);
    }

    private static void prepareWordCountMap(HashMap<String, Integer> wordCountMap, String[] words) {
        wordCountMap.clear();
        for (int i = 0; i < words.length; i++) {
            if (!wordCountMap.containsKey(words[i])) {
                wordCountMap.put(words[i], 0);
            }
            // 自增数量
            wordCountMap.put(words[i], wordCountMap.get(words[i]) + 1);
        }
    }


}
