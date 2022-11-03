package classespackage.recursionandDP;

import sun.applet.Main;

/**
 * @author swzhao
 * @date 2022/4/25 10:24 上午
 * @Discreption <> 最小编辑代价
 * 给定两个字符串和插入、删除、替换三个操作的代价，计算从str1->str2的最小代价
 */
public class EditMinCost {

    /**
     * 方法一：
     * 使用动态规划方法计算
     * dp[i][j] 表示char1[i] 结尾的字符串到chars2[j]结尾的字符串的最小代价
     * @param str1
     * @param str2
     * @param ic
     * @param dc
     * @param rc
     * @return
     */
    public static int getMinCost(String str1, String str2, int ic, int dc, int rc){
        try {
            if(str1 == null || str2 == null){
                return 0;
            }
            char[] chars1 = str1.toCharArray();
            char[] chars2 = str2.toCharArray();
            int row = chars1.length + 1;
            int col = chars2.length + 1;
            // dp前面需要有空字符串站位
            int[][] dp = new int[row][col];
            // 第一列
            for (int i = 0; i < row; i++){
                dp[i][0] = i * dc;
            }
            // 第一行
            for (int j = 0; j < col; j++){
                dp[0][j] = j * ic;
            }
            for (int i = 1; i < row; i++){
                for (int j = 1; j < col; j++){
                    if(chars1[i] == chars2[j]){
                        dp[i][j] = dp[i-1][j-1];
                    }else{
                        // 替换
                        dp[i][j] = dp[i-1][j-1] + rc;
                    }
                    // 删除再增加，str1短的到str2长的需要插入一个字符，字符可以自己定，所以由于ij的字符不相等，所以先删除一个再插入一个j一样的字符，多个删除的步骤
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + dc);
                    // 先到j-1长度的步数再插入一个char2[j]所需要的代价
                    dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + ic);
                }
            }
            return dp[row-1][col-1];
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 方法二：
     * 空间压缩
     * @param str1
     * @param str2
     * @param ic
     * @param dc
     * @param rc
     * @return
     */
    public static int getMinCost2(String str1, String str2, int ic, int dc, int rc){
        try {
            if(str1 == null || str2 == null){
                return 0;
            }
            char[] chars1 = str1.toCharArray();
            char[] chars2 = str2.toCharArray();
            // 长数组作为纵向，短数组作为横向
            char[] longArray = chars1.length > chars2.length ? chars1 : chars2;
            char[] shortArray = chars1.length > chars2.length ? chars2 : chars1;
            if(chars1.length < chars2.length){
                // str1要作为横向，只需要将ic和dc的代价交换一下即可
                int tem = ic;
                ic = dc;
                dc = tem;
            }
            int[] dp = new int[shortArray.length+1];
            for (int i = 1; i < shortArray.length; i++){
                dp[i] = i * ic;
            }
            for (int i = 1; i < longArray.length; i++){
                // 左上角
                int pre = dp[0];
                for (int j = 1; j < shortArray.length; j++){
                    // 更改之前的保留用作下一个的左上角
                    int tem = dp[j];
                    if(longArray[i] == shortArray[j]){
                        dp[i] = pre;
                    }else {
                        // 替换
                        dp[i] = pre + rc;
                    }
                    dp[i] = Math.min(dp[i], tem + dc);
                    dp[i] = Math.min(dp[i], dp[i-1] + ic);
                    pre = tem;
                }
            }
            return dp[shortArray.length];
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 经典动态规划方法
     * 计算str1到str2的最小代价
     * @param str1 字符串1
     * @param str2 字符串2
     * @param ins 插入操作的代价
     * @param del 删除操作的代价
     * @param up 更新操作的代价
     * @return
     */
    public static int minEditCP1(String str1, String str2, int ins, int del, int up) {
        if (str1 == null || str2 == null
                || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[][] dp = new int[chars1.length][chars2.length];
        // 如果相等，代价为0，如果不相等，一种是替换，一种是先删除后增加
        dp[0][0] = chars1[0] == chars2[0] ? 0 : Math.min(up, ins+del);
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i-1][0] + del;
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = dp[0][j-1] + ins;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <  dp[0].length; j++) {
                // 不相等的时候，可以替换、先删除后增加，单增加
                // 替换情况,相等的时候不用替换
                int upMin = dp[i-1][j-1] + (chars1[i] == chars2[j] ? 0 : up);
                // 先删除后增加
                int delMin = dp[i-1][j] + del;
                int insMin = dp[i][j-1] + ins;
                dp[i][j] = Math.min(upMin, Math.min(delMin, insMin));

            }
        }
        return dp[chars1.length-1][chars2.length-1];
    }



    public static int minEditCPReduceMem(String str1, String str2, int ins, int del, int up) {
        if (str1 == null || str2 == null
                || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[] dp = new int[chars2.length];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i-1] + ins;
        }
        for (int i = 1; i < chars1.length; i++) {
            int tem = dp[0];
            dp[0] += del;
            for (int j = 1; j < dp.length; j++) {
                int upMin = tem + (chars1[i] == chars2[j] ? 0 : up);
                int insMin = dp[j-1] + ins;
                int delMin = dp[j] + del;
                // 更新前将老值给tem
                tem = dp[j];
                dp[j] = Math.min(upMin, Math.min(delMin, insMin));
            }
        }
        return dp[chars2.length-1];
    }

    public static void main(String[] args) {
        System.out.println(minEditCPReduceMem("adc", "abc", 5, 3 , 2));


    }




}
