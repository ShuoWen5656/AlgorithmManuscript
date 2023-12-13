package classespackage.recursionandDP;

/**
 * @author swzhao
 * @date 2022/4/26 10:53 上午
 * @Discreption <>字符串的交错组成
 * 判断aim是否时str1和str2的交错组成
 */
public class GetStrCross {

    /**
     * 普通dp解法
     * dp[i][j]表示str1[i]结尾的字符串与str2[j]结尾的字符串是否能交错组成aim[i+j-1]结尾的字符串
     * @return
     */
    public static boolean getStrCross1(String str1, String str2, String aim){
        try{
            if(str1 == null || str2 == null || aim == null){
                return false;
            }
            boolean[][] dp = new boolean[str1.length()+1][str2.length()+1];
            dp[0][0] = true;
            char[] chars1 = str1.toCharArray();
            char[] chars2 = str2.toCharArray();
            char[] charsAim = aim.toCharArray();
            for (int i = 1; i <= chars1.length; i++){
                if (chars1[i] == charsAim[i-1] && dp[i-1][0]){
                    dp[i][0] = true;
                }else{
                    dp[i][0] = false;
                }
            }
            for (int j = 1; j <= chars2.length; j++){
                if(chars2[j] == charsAim[j-1] && dp[0][j-1]){
                    dp[0][j] = true;
                }else{
                    dp[0][j] = false;
                }
            }
            for (int i = 1; i <= chars1.length; i++){
                for (int j = 1; j <= chars2.length; j++){
                    // 如果当前aim的i+j-1处时某个str的结尾，并且除掉i+j-1之后剩下的仍然是交错字符串时，当前位置可以组成交错字符串
                    if((dp[i-1][j] && charsAim[i+j-1] == chars1[i])
                            ||(dp[i][j-1] && charsAim[i+j-1] == chars2[j])){
                        dp[i][j] = true;
                    }else {
                        // 这里要么上边和左边都没办法组成，或者就是当前增加的字符既不是str1的结尾也不是str2的结尾，顺序不对，无法组成
                        dp[i][j] = false;
                    }
                }
            }
            return dp[chars1.length][chars2.length];
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 方法二：
     * 空间压缩
     * @param str1
     * @param str2
     * @param aim
     * @return
     */
    public static boolean getStrCross2(String str1, String str2, String aim){
        if(str1 == null || str2 == null || aim == null){
            return false;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        char[] charsAim = aim.toCharArray();
        char[] shortChars = chars1.length > chars2.length? chars2: chars1;
        char[] longChars = chars1.length > chars2.length? chars1: chars2;
        boolean[] dp = new boolean[shortChars.length + 1];
        dp[0] = true;
        for (int i = 1; i <= shortChars.length; i++){
            if(dp[i-1] && shortChars[i] == charsAim[i-1]){
                dp[i] = true;
            }else{
                dp[i] = false;
            }
        }
        for (int i = 1; i < chars1.length; i++){
            for (int j = 1; j < chars2.length; j++){
                if((dp[j-1] && shortChars[j] == charsAim[i+j-1])
                        ||(dp[j] && longChars[i] == charsAim[i+j-1])){
                    dp[j] = true;
                }else{
                    dp[j] = false;
                }
            }
        }
        return dp[shortChars.length];
    }


    /**
     * 递归方法
     * @param str1
     * @param str2
     * @param aim
     * @return
     */
    public static boolean getCrossStrCP(String str1, String str2, String aim) {
        if (str1 == null || str2 == null || aim == null
                || str1.length() + str2.length() != aim.length()) {
            return false;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        char[] chars3 = aim.toCharArray();
        return processForCrossStrCP(chars1, 0, chars2, 0, chars3, 0);
    }


    /**
     * 求从str1的start1开始，str2的start2开始判断是否为aim的start3之后的交叉序列
     * @param chars1
     * @param start1
     * @param chars2
     * @param start2
     * @param chars3
     * @param start3
     * @return
     */
    private static boolean processForCrossStrCP(char[] chars1, int start1, char[] chars2, int start2, char[] chars3, int start3) {
        if (start3 >= chars3.length)
        {
            // 结束
            return true;
        }
        // 三个数组游标
        int s1 = start1;
        int s2 = start2;
        int s3 = start3;
         while (s3 <= chars3.length-1 && s1 <= chars1.length-1 && s2 <= chars2.length-1) {
            if (chars3[s3] == chars1[s1] && chars3[s3] == chars2[s2]){
                // 都相等说明两个可能性,有一个过去就行
                return processForCrossStrCP(chars1, start1+1, chars2, start2, chars3, start3+1)
                        ||processForCrossStrCP(chars1, start1, chars2, start2+1, chars3, start3+1);
            }else if (chars3[s3] == chars1[s1]) {
                s1++;
            }else if (chars3[s3] == chars2[s2]) {
                s2++;
            }else {
                // 两个都不等于说明顺序不对
                return false;
            }
            s3++;
        }
        if (s3 >= chars3.length) {
            // 说明验证结束了
            return true;
        }else {
            int indexReamin = s1 > chars1.length ? s2 : s1;
            char[] remainChar = s1 > chars1.length?  chars2 : chars1;
            while (s3 < chars3.length) {
                if (chars3[s3++] != remainChar[indexReamin++]) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 动态规划方法
     * @param str1
     * @param str2
     * @param aim
     * @return
     */
    public static boolean getFCrossStrDPCP(String str1, String str2, String aim) {
        if (str1 == null || str2 == null || aim == null
                || str1.length() + str2.length() != aim.length()) {
            return false;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        char[] chars3 = aim.toCharArray();
        boolean[][] dp = new boolean[chars1.length+1][chars2.length+1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i-1][0] && chars1[i-1] == chars3[i-1];
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = dp[0][j-1] && chars2[j-1] == chars3[j-1];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (!dp[i-1][j] && !dp[i][j-1]) {
                    dp[i][j] = false;
                }else if (dp[i-1][j] && dp[i][j-1]){
                    dp[i][j] = true;
                }else if (dp[i-1][j]){
                    dp[i][j] = chars1[i-1] == chars3[i+j-1];
                }else {
                    dp[i][j] = chars2[j-1] == chars3[i+j-1];
                }
            }
        }
        return dp[chars1.length][chars2.length];
    }



    public static void main(String[] args) {
        System.out.println(getFCrossStrDPCP("AB", "123", "1A233"));
    }

}
