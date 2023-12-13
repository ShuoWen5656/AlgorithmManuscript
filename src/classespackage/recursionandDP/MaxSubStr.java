package classespackage.recursionandDP;

import classespackage.CommonUtils;

/**
 * @author swzhao
 * @date 2022/4/24 9:58 上午
 * @Discreption <>最长公共子串问题
 */
public class MaxSubStr {

    /**
     * 方法一：
     * 经典动态规划
     * @param str1
     * @param str2
     * @return
     */
    public static String getMaxSubStr(String str1, String str2){
        try {
            if(str1 == null || str1.length() == 0
                    || str2 == null|| str2.length() == 0){
                return null;
            }
            char[] chars1 = str1.toCharArray();
            char[] chars2 = str2.toCharArray();
            // dp[i][j]为以chars1[i] 和以chars[j]结尾的公共子串长度
            int[][] dp = new int[chars1.length][chars2.length];
            int max = 0;
            int row = 0;
            int col = 0;
            // 初始化列
            for (int i = 0; i < dp.length; i++){
                if(chars1[i] == chars2[0]){
                    dp[i][0] = 1;
                }
                if(dp[i][0] > max){
                    max = dp[i][0];
                    row = i;
                    col = 0;
                }
            }
            // 初始化行
            for (int j = 0; j < dp[0].length; j++){
                if(chars2[j] == chars1[0]){
                    dp[0][j] = 1;
                }
                if(dp[0][j] > max){
                    max = dp[0][j];
                    row = 0;
                    col = j;
                }
            }
            for (int i = 1; i < dp.length; i++){
                for (int j = 1; j < dp[0].length; j++){
                    if(chars1[i] == chars2[j]){
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }else{
                        dp[i][j] = 0;
                    }
                    if(dp[i][j] > max){
                        max = dp[i][j];
                        row = i;
                        col = j;
                    }
                }
            }
            // 从i处往前走max个
            return str1.substring(row - max + 1, row + 1);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 优化空间到O（1）
     * 循环从左上角开始往右下角方法开始遍历
     * @param str1
     * @param str2
     * @return
     */
    public static String getMaxSubStr2(String str1, String str2){
        if(str1 == null || str1.length() == 0
                || str2 == null|| str2.length() == 0){
            return null;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        // 游标
        int row = 0;
        int col = chars2.length - 1;
        // 遍历中的最大值
        int max = 0;
        // 最大值得行数
        int end = 0;
        // 上一个的长度dp[i-1][j-1]
        int lastLen = 0;
        // ij相等且都等于0时，更换方向
        boolean ijChange = false;
        // 这里的结束条件为row超过行数后结束
        while (row < chars1.length){
            // 循环判断
            int i = row;
            int j = col;
            // 不管是否更换方向，结束条件都是某一方超过边界
            while (j >= chars2.length || i >= chars1.length){
                if(chars1[i] == chars2[j]){
                    lastLen++;
                }else{
                    lastLen = 0;
                }
                if(lastLen > max){
                    max = lastLen;
                    end = i;
                }
            }
            // 判断方向
            if(col == 0 && !ijChange){
                // col 到头了，ij需要换方向了
                ijChange = true;
            }
            if(ijChange){
                row ++;
                col = 0;
            }else{
                col--;
                row = 0;
            }
        }
        // 从i处往前走max个
        return str1.substring(row - max + 1, row + 1);
    }


    /**
     * 获取最长子数组
     * @param str1
     * @param str2
     * @return
     */
    public static String getMaxSubArrCP(String str1, String str2) {
        if (str1 == null || str1.length() == 0
                || str2 == null || str2.length() == 0) {
            return null;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[][] dp = new int[chars1.length][chars2.length];
        int[] maxIJ = getDpCP(chars1, chars2, dp);
        // 将最大值的坐标拿出来
        int maxI = maxIJ[0];
        int maxJ = maxIJ[1];
        int maxLen = chars1.length > chars2.length ? chars1.length : chars2.length;
        int index = 0;
        char[] res = new char[maxLen];
        while (maxI >= 0 && maxJ >= 0) {
            if (chars1[maxI] == chars2[maxJ]) {
                res[index++] = chars1[maxI];
                maxI--;
                maxJ--;
            }else {
                break;
            }
        }
        CommonUtils.reverseChar(res, 0, index);
        return String.valueOf(res, 0, index);
    }

    /**
     * 生成dp矩阵
     * 返回最大值的两个坐标
     * @param chars1
     * @param chars2
     * @return
     */
    private static int[] getDpCP(char[] chars1, char[] chars2, int[][] dp) {
        // dp[i][j] 表示，chars[i]为底和chars[2]为底的公共子数组长度，区别于chars1[i]为底的，chars2[j]为底的数组的公共子数组长度
        //int[][] dp = new int[chars1.length][chars2.length];
        int maxI = 0;
        int maxJ = 0;
        int max = Integer.MIN_VALUE;
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i-1][0] == 1 || chars1[i] == chars2[0] ? 1 : 0;
        }
        for (int j = 1; j < dp[0].length; j ++) {
            dp[0][j] = dp[0][j-1] == 0 || chars1[0] == chars2[j] ? 1 : 0;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (chars1[i] != chars2[j]) {
                    dp[i][j] = 0;
                }else {
                    int count = 1;
                    int iIndex = i;
                    int jIndex = j;
                    while (iIndex >= 0 && jIndex >= 0) {
                        if (chars1[iIndex] == chars2[jIndex]) {
                            count++;
                            iIndex--;
                            jIndex--;
                        }else {
                            break;
                        }
                    }
                    if (count > max) {
                        maxI = i;
                        maxJ = j;
                        max = count;
                    }
                    dp[i][j] = count;
                }
            }
        }
        return new int[]{maxI, maxJ};
    }

    /**
     * 降低空间复杂度版本
     * @param str1
     * @param str2
     * @return
     */
    private static String getMaxSubArrCPReduceMem(String str1, String str2) {
        if (str1 == null || str1.length() == 0
                || str2 == null || str2.length() == 0) {
            return null;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int max = Integer.MIN_VALUE;
        int maxI = 0;
        int maxJ = 0;
        int i = 0;
        int j = chars2.length-1;
        int count = chars1.length + chars2.length -1;
        while (count-- >= 0) {
            int temI = i;
            int temj = j;
            int temMax = 0;
            int temMaxI = 0;
            int temMaxJ = 0;
            int last = 0;
            while (temI < chars1.length && temj < chars2.length) {
                if (chars1[temI] == chars2[temj]) {
                    last++;
                }else {
                    last = 0;
                }
                if (last > temMax) {
                    temMax = last;
                    temMaxI = temI;
                    temMaxJ = temj;
                }
                temI++;
                temj++;
            }
            if (temMax > max) {
                maxI = temMaxI;
                maxJ= temMaxJ;
                max= temMax;
            }
            if (j != 0) {
                // 还在第一行
                j--;
            }else {
                i++;
            }
        }
        // 将最大值的坐标拿出来
        int maxLen = chars1.length > chars2.length ? chars1.length : chars2.length;
        int index = 0;
        char[] res = new char[maxLen];
        while (maxI >= 0 && maxJ >= 0) {
            if (chars1[maxI] == chars2[maxJ]) {
                res[index++] = chars1[maxI];
                maxI--;
                maxJ--;
            }else {
                break;
            }
        }
        CommonUtils.reverseChar(res, 0, index);
        return String.valueOf(res, 0, index);
    }

    public static void main(String[] args) {
        getMaxSubArrCPReduceMem("1AB2345CD", "12345EF");
    }

}
