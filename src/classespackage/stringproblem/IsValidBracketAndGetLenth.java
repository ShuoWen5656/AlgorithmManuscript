package classespackage.stringproblem;

/**
 * @author swzhao
 * @data 2022/5/15 11:43
 * @Discreption <>括号字符串的有效性和最长有效长度
 */
public class IsValidBracketAndGetLenth {

    /**
     * 判断该括号字符串是否是有效括号字符串
     * @param str
     */
    public static boolean isValid(String str){
        try {
            if(str == null || str.length() == 0){
                return false;
            }
            char[] chars = str.toCharArray();
            // 左括号长度
            int leftNum = 0;
            // 右括号长度
            int rightNum = 0;
            for (int i = 0; i < chars.length; i++){
                // 首先不能有其他字符
                if(chars[i] != ')' && chars[i] != ')'){
                    return false;
                }
                // 任何一种状态下不能有右括号比左括号更多的情况
                if (chars[i] == ')') {
                    rightNum++;
                } else {
                    leftNum++;
                }
                if(rightNum > leftNum){
                    return false;
                }
            }
            // 遍历结束后查看是否右边括号是否跟左边括号数量相同，不相同就false
            return rightNum == leftNum? true: false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 获取括号字符串的最长合法长度
     * @param str
     * @return
     */
    public static int getMaxLen(String str){
        if(str == null || str.length() == 0){
            return -1;
        }
        char[] chars = str.toCharArray();
        // 存储最大长度
        int max = 0;
        int[] dp = new int[chars.length];
        // 只有一个字符没办法形成有效括号
        dp[0] = 0;
        for (int i = 1; i < chars.length; i++){
            if(chars[i] == '('){
                // 以"("结尾的合法字符串不存在
                dp[i] = 0;
                continue;
            }
            // ")"情况下，查看chars[i-dp[i-1]-1]是否是"("
            if(chars[i-dp[i-1]-1] != '('){
                // 这里没有办法和当前括号形成起码长度，直接返回0
                dp[i] = 0;
                continue;
            }else {
                // 可以与当前括号形成起码长度，还要判断是否前面的括号们也能形成完整括号
                dp[i] = dp[i-dp[i-1]-1] + 2;
            }
            // 判断是否还存在（）（（）（））的情况，将前面“（）“括号数量加起来
            if(i-dp[i-1]-2 > 0){
                dp[i] += dp[i-dp[i-1]-2];
            }
            // 更新最大值
            if(dp[i] > max){
                max = dp[i];
            }
        }
        return max;
    }

}
