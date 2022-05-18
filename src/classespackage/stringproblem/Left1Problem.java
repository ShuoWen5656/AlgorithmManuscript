package classespackage.stringproblem;

/**
 * @author swzhao
 * @data 2022/5/17 20:03
 * @Discreption <> 0左边必有1的二进制字符串数量
 */
public class Left1Problem {

    /**
     * 给定长度num，求0左边必有1的字符串数量
     * 递归方式：
     * @param num
     * @return
     */
    public static int getNum1(int num){
        try {
            if(num < 1){
                return 0;
            }
            return process(1 ,num);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    private static int process(int i, int num) {
        if (i == num){
            return 1;
        }else if(i == num - 1){
            return 0;
        }else{
            return process(i+1, num) + process(i+2, num);
        }
    }

    /**
     * 第二种斐波那契数列
     * @param num
     * @return
     */
    private static int getNum2(int num){
        if(num < 1){
            return 0;
        }
        int[] dp = new int[num+1];
        dp[num] = 1;
        dp[num-1] = 2;
        for (int i = num-2; i > 0; i--){
            dp[i] = dp[i+1] + dp[i+2];
        }
        return dp[1];
    }


    /**
     * 时间复杂度最低的方法：O（logn）
     * 斐波那契书列矩阵乘法
     * @param num
     * @return
     */
    public static int getNum3(int num){
        try {
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

}
