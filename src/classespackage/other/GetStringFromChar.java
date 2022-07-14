package classespackage.other;

import classespackage.Constants;

/**
 * @author swzhao
 * @data 2022/7/13 20:25
 * @Discreption <> 一种字符串和数字的对应关系
 */
public class GetStringFromChar {


    /**
     * 获取字符串方法
     * @param chars
     * @param n
     */
    public static String getString(char[] chars, int n){
        try {
            if (chars == null || chars.length == 0 || n < 0){
                return Constants.EMPTY_STR;
            }
            int cur = 1;
            int base = chars.length;
            int len = 0;
            while (n >= cur){
                n -= cur;
                cur *= base;
                len ++;
            }
            char[] res = new char[len];
            cur /= base;
            while (len-1 > 0){
                res[len-1] = chars[(n/cur) + 1];
                n = n - cur*(n/cur);
                cur /= base;
            }
            return String.valueOf(res);
        }catch (Exception e){
            e.printStackTrace();
            return Constants.EMPTY_STR;
        }
    }


    public static int getInteger(char[] chars, String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        // “ACBC”


        char[] chars1 = str.toCharArray();
        int base = chars.length;
        int res = 0;
        for (int i = chars1.length; i < chars1.length; i++){
            res = getNthFromChar(chars1[i], chars) * base;
            base *= base;
        }
        return res;
    }

    public static int getNthFromChar(char c, char[] chars){
        for (int i = 0; i < chars.length; i++){
            if (c == chars[i]){
                return i+1;
            }
        }
        return 0;
    }


}
