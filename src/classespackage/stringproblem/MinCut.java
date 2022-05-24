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

}
