package leetcode;

import java.util.Stack;

/**
 * @author swzhao
 * @date 2023/10/7 9:18 下午
 * @Discreption <> 罗马数字转整数
 */
public class RomanToInt {


    public static void main(String[] args) {
        System.out.println(solution1("MCMXCIV"));
    }



    public static int solution(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int res = 0;
        // 每一次循环必须处理一个数字
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 'I' && chars[i] != 'X' && chars[i] != 'C') {
                // 直接加
                res += Roman2Int.getByR(chars[i]);
            }else {
                // 三种特殊的字母
                if (chars[i] == 'X') {
                    if (i+1 < chars.length && (chars[i+1] == 'L' || chars[i+1] == 'C')) {
                        res += Roman2Int.getByR(chars[i+1]) - Roman2Int.getByR(chars[i]);
                        i++;
                    }else {
                        res += Roman2Int.getByR(chars[i]);
                    }
                }else if (chars[i] == 'C'){
                    if (i+1 < chars.length && (chars[i+1] == 'D' || chars[i+1] == 'M')) {
                        res += Roman2Int.getByR(chars[i+1]) - Roman2Int.getByR(chars[i]);
                        i++;
                    }else {
                        res += Roman2Int.getByR(chars[i]);
                    }
                }else {
                    // I
                    if (i+1 < chars.length && (chars[i+1] == 'V' || chars[i+1] == 'X')) {
                        res += Roman2Int.getByR(chars[i+1]) - Roman2Int.getByR(chars[i]);
                        i++;
                    }else {
                        res += Roman2Int.getByR(chars[i]);
                    }
                }
            }
        }
        return res;
    }

    enum Roman2Int {
        I('I', 1),
        V('V', 5),
        X('X', 10),
        L('L', 50),
        C('C', 100),
        D('D', 500),
        M('M', 1000);

        /**
         * 罗马字母
         */
        private char r;
        /**
         * 数字
         */
        private Integer i;

        Roman2Int(char r, Integer i) {
            this.r = r;
            this.i = i;
        }

        public static int getByR(char r1) {
            for (Roman2Int roman2Int : Roman2Int.values()) {
                if (roman2Int.r == r1) {
                    return roman2Int.i;
                }
            }
            return 0;
        }
    }



    public static int solution1(String s) {
        char[] chars = s.toCharArray();
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            if(i == chars.length - 1 || Roman2Int.getByR(chars[i]) >= Roman2Int.getByR(chars[i+1])) {
                // 比下一位大或者相等直接加
                res += Roman2Int.getByR(chars[i]);
            }else {
                // 比下一位小,说明要减掉
                res += Roman2Int.getByR(chars[i+1]) - Roman2Int.getByR(chars[i]);
                i++;
            }
        }
        return res;
    }
}
