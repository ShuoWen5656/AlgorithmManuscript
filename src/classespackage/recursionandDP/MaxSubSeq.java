package classespackage.recursionandDP;

/**
 * @author swzhao
 * @date 2022/4/23 3:53 下午
 * @Discreption <>最长公共子序列问题：两个str，返回公共的最长子序列
 */
public class MaxSubSeq {


    public static String getMaxSubSeq(String str1, String str2){
        if(str1 == null || str1.length() == 0
                || str2 == null|| str2.length() == 0){
            return null;
        }
        int[][] dp = getDP(str1, str2);
        return getSeq(dp, str1, str2);
    }

    /**
     * 获取dp表
     * @param str1
     * @param str2
     * @return
     */
    public static int[][] getDP(String str1, String str2){
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0){
            return null;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        // 横坐标chars2的值，纵坐标chars1的值，（i，j）为char1和char2结尾的子序列之间的最长公共子序列
        int[][] dp = new int[chars1.length][chars2.length];
        dp[0][0] = 1;
        // 初始化dp
        for (int i = 1; i < dp.length; i++){
            if(chars1[i] == chars2[0] || dp[i-1][0] == 1){
                dp[i][0] = 1;
            }
        }
        for (int j = 1; j < dp[0].length; j++){
            if(chars2[j] == chars1[0] || dp[0][j-1] == 1){
                dp[0][j] = 1;
            }
        }
        // 1、如果char1[i]与char2[j]相等了，则dp[i][j] = dp[i-1][j-1] + 1 和dp[i-1][j]、dp[i][j-1]中较大的
        // 2、如果不相等，dp[i-1][j]、dp[i][j-1]中较大的
        // 提问：是否可能有dp[i-1][j] + 1的情况？比如多个str1[i]后，公共子序列增加了？
        // 回答：不会的，这种情况会在dp[i][k]的时候发现str[i]与str2[k]相同的时候+1的
        for (int i = 1; i < dp.length; i++){
            for (int j = 1; j < dp[0].length; j++){
                if(chars1[i] == chars2[j]){
                    int tem = dp[i-1][j-1] + 1;
                    dp[i][j] = Math.max(tem, Math.max(dp[i-1][j], dp[i][j-1]));
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp;
    }

    public static String getSeq(int[][] dp, String str1, String str2){
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int len = dp[chars1.length-1][chars2.length-1];
        char[] res = new char[len];
        // 初始化坐标
        int top = chars1.length-1;
        int left = chars2.length-1;
        while (top > 0 && left > 0){
            int value = dp[top][left];
            int value2 = dp[top - 1][left - 1];
            if(value == value2 + 1){
                res[len--] = chars1[top];
                top--;
                left--;
            }else{
                int valuet = dp[top - 1][left];
                int valuel = dp[top][left-1];
                if(valuet > valuel){
                    top--;
                    continue;
                }else{
                    left--;
                    continue;
                }
            }
        }
        return String.valueOf(res);
    }


}
