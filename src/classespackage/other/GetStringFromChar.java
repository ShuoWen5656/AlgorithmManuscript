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


    /**
     * 二轮测试：给定num，获取String的值
     * @param num
     * @return
     */
    public static String num2StringCp1(int num, char[] chars) {
        if (num < 0 || chars == null || chars.length == 0) {
            return null;
        }
        int len = chars.length;
        // 先判断需要多少位存储
        int n = 0;
        while((int)(num / Math.pow(len, n))!= 0) {
            n++;
        }
        char[] res = new char[n];
        // 将所有位全部置为A
        for (int i = 0; i < res.length; i++) {
            res[i] = 'A';
            num -= Math.pow(len, i);
        }
        // 此时num剩下的需要进行进制转换,从最高位开始判断
        for (int i = 0; i < res.length; i++) {
            int r = (int) (num/Math.pow(len, res.length-1 - i));
            res[i] += r;
            num -= r * Math.pow(len, res.length-1 - i);
        }
        return String.valueOf(res);
    }


    /**
     * 二轮测试：通过字符串获取num数字
     * @param str
     * @param chars
     * @return
     */
    public static int string2Num(String str, char[] chars) {
        if (str == null || chars == null
                || str.length() == 0 || chars.length == 0) {
            return 0;
        }
        int len = chars.length;
        char[] chars1 = str.toCharArray();
        int res = 0;
        for (int i = 0; i < chars1.length; i++) {
            int cur = chars1[i] - 'A' + 1;
            res += cur * Math.pow(len, chars1.length - 1 - i);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(string2Num("BABC", new char[]{'A', 'B', 'C'}));
    }
}
