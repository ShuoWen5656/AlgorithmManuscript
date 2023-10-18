package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/10/18 8:43 下午
 * @Discreption <> 文本左右对其
 */
public class FullJustify {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"}, 20));
    }


    public static List<String> solution(String[] words, int maxWidth) {
        if (words == null || words.length == 0) {
            return null;
        }
        List<String> res = new ArrayList<>();
        // 两个游标
        int left = 0;
        int right = 0;

        // 用来判断是否能够加入接下来的单词
        int sum = 0;
        // 用来记录单词的总长度
        int sum2 = 0;
        while (right < words.length) {
            int curLen = words[right].length();
            // 校验
            if (curLen > maxWidth) {
                return null;
            }
            // 找到第一个大于wid的单词
            if (right != left) {
                // 非起点需要至少加一个空格
                sum += 1;
            }
            if (sum + curLen <= maxWidth) {
                // 说明可以放下
                sum2 += curLen;
                sum += curLen;
                right++;
                continue;
            }
            // 到这里说明无法放下了,将left到right的单词放入列表中
            StringBuilder stringBuilder = new StringBuilder();
            // 计算中间可能需要的空格数
            int i1 = right - left - 1 == 0 ? 0 : (maxWidth - sum2)/(right - left - 1);
            int i2 = right - left - 1 == 0 ? 0 : (maxWidth - sum2)%(right - left - 1);
            int index = left;
            while (index < right){
                if (index == left) {
                    // 第一个不加
                    stringBuilder.append(words[index]);
                    index++;
                    continue;
                }
//                if (index == left+1) {
//                    // 说明是第二个
//                    int num = 0;
//                    while (num < i1+i2) {
//                        stringBuilder.append(" ");
//                        num++;
//                    }
//                    stringBuilder.append(words[index]);
//                    index++;
//                    continue;
//                }
                // 剩余的全部加i1个空格
                int num = 0;
                while (num < i1) {
                    stringBuilder.append(" ");
                    num++;
                }
                // 后面开始+i1个，如果i2没有减少到0则i1+1个
                if (i2 > 0) {
                    stringBuilder.append(" ");
                    i2--;
                }
                stringBuilder.append(words[index]);
                index++;
            }
            // 一个单词的时候需要补齐一下
            if (stringBuilder.length() < maxWidth) {
                int num = stringBuilder.length();
                while (num < maxWidth) {
                    stringBuilder.append(" ");
                    num++;
                }
            }
            res.add(stringBuilder.toString());
            // 复位
            left = right;
            sum = 0;
            sum2 = 0;
        }

        // right超过长度后，最后一行需要单独处理
        StringBuffer temBuffer = new StringBuffer();
        int index = left;
        while (index < right) {
            if (index == left) {
                temBuffer.append(words[index++]);
                continue;
            }
            temBuffer.append(" ");
            temBuffer.append(words[index++]);
        }
        // 补齐
        if (temBuffer.length() < maxWidth) {
            int num = temBuffer.length();
            while (num < maxWidth) {
                temBuffer.append(" ");
                num++;
            }
        }
        res.add(temBuffer.toString());
        return res;
    }
}
