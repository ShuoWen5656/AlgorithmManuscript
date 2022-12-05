package classespackage.stringproblem;

import classespackage.Constants;

/**
 * @author swzhao
 * @data 2022/5/7 19:57
 * @Discreption <> 替换字符串中连续出现的指定字符串
 */
public class ReplaceFrom2To {


    public static String replace(String str, String from, String to){
        try {
            if (str  == null || str.length() == 0){
                return null;
            }
            int m = 0;
            char[] fromChar = from.toCharArray();
            char[] strChar = str.toCharArray();
            for (int i = 0; i < strChar.length; i++){
                if(strChar[i] != fromChar[m]){
                    // 不匹配的话从头开始匹配
                    m = 0;
                    continue;
                }else if (m == from.length()-1){
                    // 当前字符串匹配并且还是最后一个字符串，将str的i-m到i个字符替换成‘0’
                    for (int j = m-i; j < m; j ++){
                        strChar[j] = 0;
                    }
                    // 匹配完成继续下一轮匹配
                    m = 0;
                }else {
                    // 继续下一个字符的匹配
                    m ++;
                }
            }
            // 最后将‘0’的字符替换成to
            String res = Constants.EMPTY_STR;
            for (int i = 0; i < strChar.length; i++){
                if(strChar[i] != 0){
                    res.concat(String.valueOf(strChar[i]));
                }
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Constants.EMPTY_STR;
        }
    }


    /**
     * 二轮测试：替换
     * @param str
     * @param from
     * @param to
     * @return
     */
    public static String replaceCp1(String str, String from, String to) {
        if (str == null || from == null || to == null
                || str.length() == 0 || from.length() == 0 || to.length() == 0){
            return null;
        }
        char[] chars = str.toCharArray();
        char[] charsFrom = from.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == charsFrom[0]) {
                int index = 0;
                while (index < charsFrom.length && index < chars.length) {
                    if (charsFrom[index] != chars[i+index]) {
                        break;
                    }
                    index++;
                }
                if (index == charsFrom.length) {
                    // 说明全部相等.匹配成功
                    int tmp = 0;
                    while (tmp < index) {
                        chars[i + tmp] = 0;
                        tmp++;
                    }
                }
                // 结束之后，i调整为index
                i = index;
            }
        }
        // chars中的0全部替换成to，连续的算一个
        int start = -1;
        int index = 0;
        char[] res = new char[chars.length];
        for (int j = 0 ; j < chars.length; j++) {
            if (chars[j] == 0) {
                if (start == -1) {
                    start = j;
                    res[index++] = 0;
                }
            }else {
                res[index++] = chars[j];
                start = -1;
            }
        }

        return String.valueOf(res, 0, index).replace("\u0000", to);
    }


    public static void main(String[] args) {
        System.out.println(replaceCp1("123abcabc", "abc", "x"));

    }
}
