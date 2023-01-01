package classespackage.stringproblem;

import classespackage.stackAndQueue.catDogQueue.Pet;

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
                        || (expChars[i] == '*' && expChars[i-1] == '*')){
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


    /**
     * 二轮测试：类正则配置str
     * @param str
     * @param exp
     * @return
     */
    public static boolean isMatchCp1(String str, String exp) {
        if (!isValid(str, exp)) {
            return false;
        }
        char[] charsStr = str.toCharArray();
        char[] charsExp = exp.toCharArray();
        //return processCp1(charsStr, charsExp, 0, 0);
        return processDpCp1(charsStr, charsExp);
    }

    /**
     * 递归主体：主要判断startExp开始往后和，startStr是否能够匹配上
     * @param charsStr
     * @param charsExp
     * @param startStr
     * @param startExp
     * @return
     */
    private static boolean processCp1(char[] charsStr, char[] charsExp, int startStr, int startExp) {
        if (!isValid(String.valueOf(charsStr, startStr, charsStr.length-startStr), String.valueOf(charsExp, startExp,charsExp.length-startExp))) {
            return false;
        }
        int index1 = startStr;
        int index2 = startExp;
        while (index1 < charsStr.length && index2 < charsExp.length) {
            if (charsExp[index2] == '.' && index2 + 1 < charsExp.length && charsExp[index2+1] == '*'){
                // .* 情况
                index2+=2;
                while (!processCp1(charsStr, charsExp, index1, index2)) {
                    index1++;
                }
            }else if (charsExp[index2] == '.' && (index2 + 1 >= charsExp.length || charsExp[index2+1] != '*')) {
                // 单 . 的情况
                index1++;
                index2++;
            }else if (index2 + 1 < charsExp.length && charsExp[index2+1] == '*'){
                // X*的情况
                index2+=2;
                char x = charsStr[index1];
                while (index1 < charsStr.length && x == charsStr[index1] && !processCp1(charsStr, charsExp, index1, index2)) {
                    index1++;
                }
            } else {
                // 非. 情况
                if (charsStr[index1] != charsExp[index2]) {
                    return false;
                }else {
                    index1++;
                    index2++;
                }
            }
        }
        // exp结束，str必须结束
        if (index2 == charsExp.length) {
            return index1 == charsStr.length;
        }else {
            // str结束，exp只能.*或者结束
            return index2 == charsExp.length || (index2+1 < charsExp.length && charsExp[index2] == '.' && charsExp[index2+1] == '*');
        }
    }

    /**
     * 方法二：动态规划
     * @param charsStr
     * @param charsExp
     * @return
     */
    private static boolean processDpCp1(char[] charsStr, char[] charsExp) {
        // dp[i][j]代表 charStr从i开始到结尾，是否匹配charsExp从j开始到结尾
        boolean[][] dp = new boolean[charsStr.length+1][charsExp.length+1];
        dp[charsStr.length][charsExp.length] = true;
        for (int i = charsExp.length-2; i >= 0; i-=2) {
            // exp必须A*B*...模式
            if (charsExp[i] != '*' && charsExp[i+1] == '*'){
                dp[charsStr.length][i] = true;
            }else {
                break;
            }
        }
        dp[charsStr.length-1][charsExp.length-1] = charsExp[charsExp.length-1] == '.' || charsExp[charsExp.length-1] == charsStr[charsStr.length-1];
        for (int i = charsStr.length-1; i >= 0; i--) {
            for (int j = charsExp.length-2; j >= 0; j--) {
                if (charsExp[j+1] != '*') {
                    // 不是*，那么要么是.，要么相等
                    dp[i][j] = charsExp[j] == '.' || charsExp[j] == charsStr[i];
                }else {
                    // 是*的情况
                    int startStr = i;
                    while (startStr < charsStr.length && (charsStr[startStr] == charsExp[j] || charsExp[j] == '.')) {
                        if (dp[startStr][j+2]) {
                            dp[i][j] = true;
                            break;
                        }
                        startStr++;
                    }
                    // 这里是因为dp的长度要长一个，上述循环由于条件中不能超过len，所以最后一个边界无法到达，这里需要再赋值一次
                    if (!dp[i][j]) {
                        dp[i][j] = dp[startStr][j+2];
                    }
                }

            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        System.out.println(isMatchCp1("abb", "ab*"));
    }

}
