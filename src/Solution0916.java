/**
 * @author swzhao
 * @data 2023/9/16 15:13
 * @Discreption <>
 */
public class Solution0916 {


    public static void main(String[] args) {

        System.out.println(maxDictionaryOrder2("aabcbccacbbcbaaba"));

    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param s string字符串
     * @return string字符串
     */
    public static String maxDictionaryOrder2(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        // dp[i]表示以当前char[i]为底的序列最大值串
        String[] dp = new String[s.length()];
        String res = s;
        char[] chars = s.toCharArray();
        dp[0] = String.valueOf(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            for (int j = 0; j < i; j++) {
                String com = dp[j];
                String cur = String.valueOf(chars[i]);
                if (cur.compareTo(com) > 0) {
                    // 直接赋值
                    dp[i] = cur;
                }else if (com.contains(cur)) {
                    // 相等的情况需要特殊处理一下
                    int index = com.indexOf(cur);
                    char[] chars1 = com.toCharArray();
                    char curChar = cur.toCharArray()[0];
                    // 找到第一个不相等的
                    for (;index < chars1.length; index++){
                        if (chars1[index] != curChar) {
                            break;
                        }
                    }
                    dp[i] = com.substring(0, index) + cur;
                } else {
                    dp[i] = com + cur;
                }
                // 查看是不是最大值
                res = dp[i].compareTo(res) > 0 ? dp[i] : res;
            }
        }
        return res;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param s string字符串
     * @return string字符串
     */
    public static String maxDictionaryOrder(String s) {
        String[] res = new String[1];
        res[0] = s;
        // write code here
        process(s, res);
        return res[0];
    }

    /**
     * 求s字符串中最大的序列存入res
     * @param str
     * @param res
     */
    private static void process(String str, String[] res) {
        // 先判断自己
        if (str.compareTo(res[0]) > 0) {
            res[0] = str;
        }
        // 循环递归去掉一个字符后，剩余的字符计算出最大的序列即可
        for (int i = 0; i < str.length(); i++) {
            String sub = str.substring(0, i) + str.substring(i + 1, str.length());
            process(sub, res);
        }
    }


}
