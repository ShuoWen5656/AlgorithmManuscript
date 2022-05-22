package classespackage.stringproblem;

/**
 * @author swzhao
 * @data 2022/5/22 12:25
 * @Discreption <>字符串匹配问题
 * str1中没有“.”和“*”字符，exp中有“.”和“*”字符，其中.代表任何字符，*代表一个或多个，计算str1是否匹配exp
 */
public class MatchStrMethod {


    /**
     * 主方法：
     * @param str
     * @param exp
     * @return
     */
    public static boolean isMatch(String str, String exp){
       if(str == null || str.length() == 0){
           return false;
       }
       if (!isValid(str, exp)){
           return false;
       }
       char[] chars = str.toCharArray();
       char[] charsExp = exp.toCharArray();
       return process(chars, charsExp, 0, 0);
    }

    /**
     * 递归方法：计算从chars的si位置往后和charsExp的ei位置往后是否匹配
     * 1、exp结束，str没有结束的返回false，如果str也结束了放回true
     * 2、exp没结束，并且下一个字符不是*的情况，exp和str当前字符必须匹配（exp要么和str当前位置相等，要么exp为“.”）
     * 3、exp下一个字符是*的，当前exp可以匹配可以不匹配，如果不匹配，说明exp当前的“X*”为0个，继续ei+2位置匹配即可
     *  当前如果匹配说明exp为“X*”或者“.*”,则判断si+1和ei+2往后是否匹配，si+2和ei+2往后是否匹配。。。只要匹配的都返回true
     * @param chars
     * @param charsExp
     * @param si
     * @param ei
     * @return
     */
    private static boolean process(char[] chars, char[] charsExp, int si, int ei) {
        if(ei == charsExp.length){
            // 如果si也结束，则结束，如果没有结束，则返回false
            return chars.length == si;
        }
        if(ei+1 != charsExp.length && charsExp[ei+1] != '*'){
            // 如果当前位置匹配了，则继续下一个迭代
            if(si != chars.length && (chars[si] == charsExp[ei] || charsExp[ei] == '.')){
                return process(chars, charsExp, si+1, ei+1);
            }else {
                return false;
            }
        }
        // 到这里的时候就是为*的情况了,只要当前“X*”和si匹配，就继续循环
        while (si != chars.length && (chars[si] == charsExp[ei] || charsExp[ei] == '.')){
            // 当前的si和ei是匹配的并且ei+1是*，判断*之后的部分是否和si之后的部分匹配，只要匹配就返回true，否则查看si+1是否匹配ei之后的部分
            if(process(chars, charsExp, si, ei+2)){
                return true;
            }
            si++;
        }
        // 其实这里直接返回false就行了,如果process(chars, charsExp, si, ei+2);还能==true，在while里面应该就已经返回了
        return false;
        // 这里就是一直没有匹配到，就假设ei的“X*”为0个X的情况
        //return process(chars, charsExp, si, ei+2);
    }


    /**
     * 求str1和exp是否符合格式
     * str中不能有.和*
     * exp中*不能开头，不能有**连续的情况
     * @param str
     * @param exp
     * @return
     */
    public static boolean isValid(String str, String exp){
        try {
            char[] chars = str.toCharArray();
            char[] expChars = exp.toCharArray();

            for (char c : chars){
                if(c == '.' || c == '*'){
                    return false;
                }
            }
            for (int i = 0; i < expChars.length; i ++){
                if((i == 0 && expChars[i] == '*')
                        || (expChars[i-1] == '*' && expChars[i] == '*')){
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 方法二：
     * 动态规划
     * @param str
     * @param exp
     * @return
     */
    public static boolean isMatchDP(String str, String exp){
        try {
            if(str == null || str.length() == 0){
                return false;
            }
            if (!isValid(str, exp)){
                return false;
            }
            char[] chars = str.toCharArray();
            char[] charsExp = exp.toCharArray();
            // 初始化底边
            boolean[][] dp = initDpMap(chars, charsExp);
            // 倒着填充
            for (int j = charsExp.length-1; j >= 0; j--){
                // 这从倒数第二行开始，倒数第一行已经填充完毕
                for (int i = chars.length-2; i >= 0; i--){
                    // 第一种情况已经结束，这里判断第二种情况和第三种情况
                    if(j+1 < charsExp.length && charsExp[j+1] != '*'){
                        // 当前必须匹配
                        dp[i][j] = (chars[i] == charsExp[j] || charsExp[j] == '.') && dp[i+1][j+1];
                    }else {
                        // 当前行往下走
                        int si = i;
                        while (si < chars.length && (chars[si] == charsExp[j] || charsExp[j] == '.')){
                            if(dp[si][j]){
                                dp[i][j] = true;
                                break;
                            }
                            si++;
                        }
                        if(!dp[i][j]){
                            dp[i][j] = dp[si][j+2];
                        }
                    }
                }
            }
            return dp[0][0];
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 初始化map
     * 初始化最后一行，最后一列
     * @param s
     * @param e
     * @return
     */
    public static boolean[][] initDpMap(char[] s, char[] e){
        int slen = s.length;
        int elen = e.length;
        // 从i，j开始往下匹配是否能够匹配成功
        boolean[][] res = new boolean[slen+1][elen+1];
        // 当前状态全部成功
        res[slen][elen] = true;
        // 隔一个循环，必须是X*，否则后面的都是false
        for (int j = elen - 2; j > -1; j --){
            if(e[j] != '*' && e[j] == '*'){
                res[slen][j] = true;
            }else{
                break;
            }
        }
        // 设置res[slen-1][elen-1]
        if (slen > 0 && elen > 0){
            if(s[slen-1] == e[elen-1] || e[elen-1] == '.'){
                res[slen][elen] = true;
            }
        }
        return res;
    }
}
