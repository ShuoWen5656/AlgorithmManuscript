package classespackage.stringproblem;

/**
 * @author swzhao
 * @data 2022/5/24 22:07
 * @Discreption <> 回文最少分割数
 */
public class MinCut {


    /**
     * 主方法
     * @param str
     * @return
     */
    public static int minCut(String str){
        try {
            if(str == null || str.length() == 0){
                return 0;
            }
            char[] chars = str.toCharArray();
            // dp[i]代表从chars[i...len-1]最少需要分割几次
            int[] dp = new int[chars.length];
            dp[chars.length-1] = 0;
            // p[i][j]代表从chars[i...j]是否是回文字符串
            boolean[][] p = new boolean[chars.length][chars.length];
            p[chars.length-1][chars.length-1] = true;
            for (int i = chars.length; i >= 0; i++){
                // 循环从dp[i]开始往后计算chars[i...j]为回文字符串时是否dp[j+1]最小
                for (int j = i; j < chars.length; j++){
                    if(j == i){
                        // 就一个字符一定为true
                        p[i][j] = true;
                    }else if(i-j == 1){
                        // 两个字符的情况应该都相等
                        if(chars[i] == chars[j]){
                            p[i][j] = true;
                        }else {
                            p[i][j] = false;
                        }
                    }else {
                        // 2个字符以上的情况，chars[i] == chars[j] 并且 p[i+1][j-1] 为true时为true
                        if(chars[i] == chars[j] && p[i+1][j-1]){
                            p[i][j] = true;
                        }else {
                            p[i][j] = false;
                        }
                    }
                    // 每当 p[i][j] = true时就可以计算dp[j+1] + 1了
                    dp[i] = Math.min(dp[i], dp[j+1]+1);
                }
            }
            return dp[0];
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 二轮测试：回文最少分割数
     * @param str
     * @return
     */
    public static int minCutCp1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        // dp[i]代表从i...len-1的最少回文分割数
        int[] dp = new int[chars.length];
        // map[i][j] 代表char[i...j]是否是回文字符串
        boolean[][] map = new boolean[chars.length][chars.length];
        // 初始化dp,只有一个字符时不需要分割
        dp[chars.length-1] = 0;
        // 初始化map，所有单字符都是字符串
        for (int i = 0; i < map.length; i++) {
            map[i][i] = true;
        }
        for (int i = chars.length-2; i >= 0; i--) {
            // 首先自身为回文串，所以先初始化一下
            dp[i] = dp[i+1]+1;
            // 枚举j
            for (int j = i; j < chars.length; j++) {
                // 直接判断i...j是否是回文字符串
                if (isPalindrome(chars, i, j, map)) {
                    // 是回文字符串，记录一下
                    map[i][j] = true;
                    // 这里需要判断一下越界，如果j已经是最后一个了，那么i到j又是回文，则不用分割
                    dp[i] = Math.min(dp[i], j+1 == chars.length? 0 : dp[j+1] + 1);
                }
            }
        }
        return dp[0];
    }

    /**
     * 判断当前chars[i...j]是否是回文字符串
     * 默认i小于j
     * @param chars
     * @param i
     * @param j
     * @param map
     * @return
     */
    private static boolean isPalindrome(char[] chars, int i, int j, boolean[][] map) {
        return i==j
                || (i+1 == j && chars[i] == chars[j])
                || chars[i] == chars[j] && map[i+1][j-1];
    }

    public static void main(String[] args) {
        System.out.println(minCutCp1("ACDCDCDAD"));
    }


}
