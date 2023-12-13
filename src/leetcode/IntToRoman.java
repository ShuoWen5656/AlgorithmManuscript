package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @data 2023/10/8 20:48
 * @Discreption <> 整数转罗马数字
 */
public class IntToRoman {


    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman(int num) {
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution(10));
    }


    public static String solution(int num) {
        if (num < 0) {
            return "";
        }
        List<Character> res = new ArrayList<>();
        // 一套体系的初始值
        int i1 = 10000;
        int i2 = 5000;
        int i3 = 1000;

        // 循环三套体系即可结束
        for (int i = 0; i < 3; i++) {
            if (num == 0) {
                Character[] characters = res.toArray(new Character[res.size()]);

                char[] chars = new char[characters.length];
                for (int k = 0; k < characters.length; k++) {
                    chars[k] = characters[k];
                }

                return new String(chars);
            }
            i1 = i1/10;
            i2 = i2/10;
            i3 = i3/10;

            int n1 = num / i1;

            for (int j = 0; j < n1; j++) {
                res.add(Roman2Int2.getByI(i1).r);
            }

            num = num % i1;
            // 开始本轮计算
            if (num >= i1 - i3) {
                // 900、90、9
                res.add(Roman2Int2.getByI(i3).r);
                res.add(Roman2Int2.getByI(i1).r);
                num -= i1 - i3;
                continue;
            }

            if (num >= i2) {
                res.add(Roman2Int2.getByI(i2).r);
                num -= i2;
            }

            if (num >= i2 - i3) {
                res.add(Roman2Int2.getByI(i3).r);
                res.add(Roman2Int2.getByI(i2).r);
                num -= i2 - i3;
            }
        }
        // 多一轮
        int n1 = num / i3;

        for (int j = 0; j < n1; j++) {
            res.add('I');
        }



        Character[] characters = res.toArray(new Character[res.size()]);

        char[] chars = new char[characters.length];
        for (int i = 0; i < characters.length; i++) {
            chars[i] = characters[i];
        }

        return new String(chars);
    }

    //private static int dealM(int num, Roman2Int2 m, List<Character> res) {
    //    num = num% m.i;
    //    Roman2Int2 other = Roman2Int2.getByI(m.i/10);
    //
    //
    //}


    enum Roman2Int2 {
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

        Roman2Int2(char r, Integer i) {
            this.r = r;
            this.i = i;
        }

        public static int getByR(char r1) {
            for (Roman2Int2 roman2Int2 : Roman2Int2.values()) {
                if (roman2Int2.r == r1) {
                    return roman2Int2.i;
                }
            }
            return 0;
        }

        public static Roman2Int2 getByI(int i) {
            for (Roman2Int2 roman2Int2 : Roman2Int2.values()) {
                if (roman2Int2.i == i) {
                    return roman2Int2;
                }
            }
            return null;
        }

    }


}
