package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/10/14 10:28 上午
 * @Discreption <> 翻转单词
 */
public class ReverseWords {

    public static void main(String[] args) {
        System.out.println(solution1("a good   example"));
    }

    /**
     * 官方题解
     * @param s
     * @return
     */
    public static String solution1(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        // 匹配一个或多个' '作为分割放在list中
        List<String> strings = Arrays.asList(s.split("\\s+"));
        // 直接翻转list
        Collections.reverse(strings);
        // 将list中的单词使用' '为分割拼接起来
        return String.join(" ", strings);
    }


    public static String solution(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        // 先去空格
        s = s.trim();
        char[] chars = s.toCharArray();
        // 翻转全部
        reverse(chars, 0, chars.length - 1);
        // 翻转部分
        int left = 0;
        int right = 0;

        while (left < chars.length){
            while (left < chars.length && !(left == 0 || (chars[left - 1] == ' ' && chars[left] != ' ') || (chars[left - 1] == 0 && chars[left] != ' '))) {
                chars[left] = 0;
                left++;
            }
            right = left;
            while (right < chars.length && !(right == chars.length-1 || (chars[right] != ' ' && chars[right + 1] == ' '))) {
                right++;
            }
            // 交换
            reverse(chars, left, right);
            // 新的坐标
            left = right + 2;
        }
        // 去中间空格
        StringBuffer stringBuffer = new StringBuffer();
        left = 0;
        while (left < chars.length) {
            if (chars[left] != 0) {
                stringBuffer.append(chars[left]);
            }
            left++;
        }
        return stringBuffer.toString();
    }





    /**
     * 翻转chars的start到end部分
     * @param chars
     * @param start
     * @param end
     */
    public static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char tem = chars[start];
            chars[start] = chars[end];
            chars[end] = tem;
            start ++;
            end--;
        }
    }





}
