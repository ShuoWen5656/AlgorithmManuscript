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

}
