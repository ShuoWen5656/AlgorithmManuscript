package leetcode;

/**
 * @author swzhao
 * @data 2023/10/2 12:44
 * @Discreption <> 买卖股票的最佳时机
 *  买卖股票的最佳时机 II
 */
public class MaxProfit {


    public static void main(String[] args) {
        int[] ints = {1,2,3,4,5};
        System.out.println(process2(ints));
    }


    /**
     * 问题一：
     * @param prices
     * @return
     */
    public static int process1(int[] prices) {
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < prices.length; i++){
            // 计算到目前位置的最小值
            min = Math.min(min, prices[i]);
            // 如果今天卖，那么利润最大就是最小值买的那天
            res = Math.max(res, prices[i] - min);
        }
        return res;
    }


    /**
     * 问题二：
     * @param prices
     * @return
     */
    public static int process2(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        // 利润
        int res = 0;
        // 买点
        int mai1 = 0;
        // 卖点
        int mai2 = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] <= prices[i-1]) {
                // 跌，卖点,计算利润，并且重新等待买的时机
                res += prices[mai2] - prices[mai1];
                mai1 = mai2;
                mai1++;
                mai2++;
            }else {
                // 涨，买点
                mai2++;
            }
        }

        // 最后可能没有跌点，需要收尾
        res += prices[mai2] - prices[mai1];
        return res;
    }








}
