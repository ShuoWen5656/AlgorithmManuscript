package classespackage.stringproblem;

import classespackage.Constants;

/**
 * @author swzhao
 * @data 2022/5/14 10:14
 * @Discreption <> 问题一：添加最少字符使字符串整体都是回文字符串
 * 问题二： 问题一的基础上多给一个参数strLps,strLps为该字符串中的最长回文序列
 */
public class Change2PalindRoomString {


    /**
     * 问题一：时间空间O(n^2)
     * @param str
     * @return
     */
    public static String change2PalindStringForOneParam(String str){
        try{
            if(str == null || str.length() == 0){
                return Constants.EMPTY_STR;
            }
            char[] chars = str.toCharArray();
            // 1、首先利用动态规划计算出当前字符串最少需要添加多少个字符才能够变成回文字符串
            // dp[i][j]代表str[i...j]最少需要多少字符变成回文字符串
            int[][] dp = getDP(str);
            // 根据dp生成回文字符串
            int resLen = dp[0][str.length() - 1];
            // 左边字符
            int left = 0;
            // 右边字符
            int right = resLen-1;
            // dp坐标
            int i = 0;
            int j = resLen-1;
            char[] res = new char[resLen];
            while (left <= right){
                if(chars[i] == chars[j]){
                    // 左右相等的情况,直接转成i-1和j-1的情况
                    res[left++] = chars[i];
                    res[right--] = chars[i];
                    // i和j--
                    i--;
                    j--;
                }else{
                    // 不相等的情况下，查看dp当前值从哪里来的，
                    if(dp[i][j] - 1 == dp[i][j-1]){
                        // 从左边来的: 说明真实的在右边，假的在左边
                        res[right--] = res[left++] = chars[j--];
                    }else{
                        // 从下边来的：说明真实的在左边，假的在右边
                        res[right--] = res[left++] = chars[i++];
                    }
                }
            }
            return String.valueOf(res);
        }catch (Exception e){
            e.printStackTrace();
            return Constants.EMPTY_STR;
        }

    }

    /**
     * 获取动态规划表
     * @param str
     * @return
     */
    private static int[][] getDP(String str) {
        int[][] dp = new int[str.length()][str.length()];
        dp[0][0] = 0;
        char[] chars = str.toCharArray();
        for (int i = 1; i < str.length(); i ++){
            // 初始化该列
            dp[i][i] = 0;
            // 两个字符的需要添加一个变成回文字符串
            if(chars[i] == chars[i-1]){
                // 两个字符相等，不需要插入任何字符
                dp[i-1][i] = 0;
            }else{
                // 两个字符不相等，插入一个字符即可
                dp[i-1][i] = 1;
            }
            // 从下往上遍历
            for (int j = i-2; j >= 0; j--){
                // 如果两端相等，则为dp[i-1][j-1]
                if(chars[i] == chars[j]){
                    dp[j][i] = dp[j-1][i-1];
                }else{
                    // 不相等则为dp[j][i-1]和dp[j-1][i]中较小的+1
                    // 两端不相等，有两种情况，一种chars[i-1...j] 变成回文字符串后再右边边加一个char[j]，一种chars[i...j-1]变成回文字符串后，左边加一个
                    dp[j][i] = Math.min(dp[j][i-1], dp[j-1][i-1]) + 1;
                }
            }
        }
        return dp;
    }


    /**
     * 问题二
     * @param str
     * @param strLps
     * @return
     */
    public static String change2PalindStringForTwoParam(String str, String strLps){
        try {
            if(str == null || str.length() == 0){
                return Constants.EMPTY_STR;
            }
            char[] chars = str.toCharArray();
            // 回文序列
            char[] charLps = strLps.toCharArray();
            // 需要拨几层
            int mid = charLps.length/2;
            // 当前层的最外边,左边的和右边的
            int preIndexLeft = 0;
            int preIndexRight = chars.length - 1;
            // res左边已经放入多少了
            int resIndex = 0;
            char[] res = new char[2*chars.length - charLps.length];
            for (int i = 0; i <= mid; i++){
                // 临时index
                int tempLeft = preIndexLeft;
                int tempRight = preIndexRight;
                // 以当前层为界开始拨
                char charLp = charLps[i];
                // 首先找到charLp在chars中的位置,并找到左边半段
                while (chars[tempLeft] != charLp){
                    tempLeft++;
                }
                while (chars[tempRight] != charLp){
                    tempRight--;
                }
                // 将左边和右边的分别处理之后放入res
                dealRes(chars, preIndexLeft, tempLeft, preIndexRight, tempRight, res, resIndex);
                // 更新res坐标
                resIndex = resIndex + tempLeft - preIndexLeft + 1 + tempRight - preIndexRight + 1;
                // 更新下一个边界
                preIndexRight = tempRight + 1;
                preIndexLeft = tempLeft + 1;
            }
            return String.valueOf(res);
        }catch (Exception e){
            e.printStackTrace();
            return Constants.EMPTY_STR;
        }
    }

    /**
     * 将左边和右边处理后放入res
     * @param chars
     * @param preIndexLeft
     * @param tempLeft
     * @param preIndexRight
     * @param tempRight
     * @param res
     * @param resIndex
     */
    private static void dealRes(char[] chars, int preIndexLeft, int tempLeft, int preIndexRight, int tempRight, char[] res, int resIndex) {
        // 计算需要放入的长度
        int len = resIndex + tempLeft - preIndexLeft + 1 + tempRight - preIndexRight + 1;
        // 存储要翻转的字符
        char[] temChar = new char[len];
        int index = 0;
        int temIndex = preIndexLeft;
        // 左边+右边
        while (temIndex <= tempLeft){
            temChar[index++] = chars[temIndex++];
        }
        temIndex = tempRight;
        while (temIndex <= preIndexRight){
            temChar[index++] = chars[temIndex++];
        }
        // 翻转
        char[] reverseChars = reverseStr(String.valueOf(temChar));
        // 将翻转的放res左半边
        index = resIndex;
        temIndex = 0;
        while (temIndex <= reverseChars.length-1){
            res[index++] = reverseChars[temIndex++];
        }
        // 右边+左边
        index = 0;
        temIndex = tempRight;
        while (temIndex <= preIndexRight){
            temChar[index++] = chars[temIndex++];
        }
        temIndex = preIndexLeft;
        while (temIndex <= tempLeft){
            temChar[index++] = chars[temIndex++];
        }
        reverseChars = reverseStr(String.valueOf(temChar));
        // 将翻转的放入右半边
        index = res.length - (len + resIndex) - 1;
        temIndex = 0;
        while (temIndex <= reverseChars.length-1){
            res[index++] = reverseChars[temIndex++];
        }
    }


    /**
     * 二轮测试：问题一
     * @return
     */
    public static String getPalindromeCp1(String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        char[] chars = string.toCharArray();
        int[][] dp = getDpCp1(chars);
        char[] res = new char[dp[0][dp.length - 1] + chars.length];
        // 开始添加字符
        int i = 0, j = chars.length-1;
        int i2 = 0, j2 = res.length-1;
        int count = dp[0][chars.length-1];
        while (i <= j) {
            if (chars[i] == chars[j]) {
                // 相等直接复制
                res[i2++] = chars[i++];
                res[j2--] = chars[j--];
            }else {
                // 不相等看dp
                char tmp = 0;
                if (dp[i+1][j] <= dp[i][j-1]) {
                    tmp = chars[i--];
                }else {
                    tmp = chars[j--];
                }
                res[i2++] = tmp;
                res[j2--] = tmp;

            }
            count--;
        }
        return String.valueOf(res);
    }


    /**
     * chars变成回文字符串所添加的最少字符数量
     * @param chars
     * @return
     */
    private static int[][] getDpCp1(char[] chars) {
        int[][] dp = new int[chars.length][chars.length];
        // 先填斜线
        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = 0;
        }
        for (int j = 1; j < chars.length; j++) {
            for (int i = j-1; i >= 0 ; i--) {
                if (chars[i] == chars[j]) {
                    // 左右边相等的情况,最小值-1，注意0的情况
                    int min = Math.min(dp[i + 1][j], dp[i][j - 1]);
                    // 如果min为0说明原来就是回文字符串，这波多了一个字符就需要再添一个，否则原来的减少一个
                    dp[i][j] = min == 0? 1 : min - 1;
                }else {
                    //左右不相等的情况
                    int min = Math.min(dp[i + 1][j], dp[i][j - 1]);
                    // 不相等说明又多了一个累赘
                    dp[i][j] = min == 0? 1 : min + 1;
                }
            }
        }
        return dp;
    }

    /**
     * 二轮测试：问题二
     * @param string
     * @param stringP
     * @return
     */
    public static String getpalindromeCP2(String string, String stringP) {
        if (string == null || string.length() == 0
                || stringP == null || stringP.length() == 0) {
            return null;
        }
        char[] chars = string.toCharArray();
        char[] chars1 = stringP.toCharArray();
        char[] res = new char[2 * chars.length - chars1.length];
        int i = 0, j = chars.length-1;
        int i1 = 0, j1 = chars1.length-1;
        int i2 = 0, j2 = res.length-1;
        while (i2 <= j2) {
            if (chars[i] != chars1[i1]) {
                res[i2++] = chars[i];
                res[j2--] = chars[i];
                i++;
            }else if (chars[j] != chars1[j1]) {
                res[i2++] = chars[j];
                res[j2--] = chars[j];
                j--;
            }else {
                // 两个都相等了
                res[i2++] = chars[i];
                res[j2--] = chars[j];
                i++;
                j--;
                i1++;
                j1--;
            }
        }
        return String.valueOf(res);
    }







    public static void main(String[] args) {
        System.out.println(getpalindromeCP2("A1B21C", "121"));
    }



    /**
     * 翻转字符串
     * @param str
     * @return
     */
    private static char[] reverseStr(String str){
        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length-1;
        while (left <= right){
            char tem = chars[left];
            chars[left] = chars[right];
            chars[right] = tem;
            left++;
            right--;
        }
        return chars;
    }
}
