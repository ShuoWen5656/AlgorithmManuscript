package leetcode;

import java.util.Arrays;

/**
 * @author swzhao
 * @date 2023/12/10 11:31 上午
 * @Discreption <> 买卖股票的最佳时机 IV
 */
public class MaxProfitII {

    public static void main(String[] args) {
        solution(2, new int[]{3,2,6,5,0,3});
    }


    public static int solution(int k, int[] prices) {
        int[] buys = new int[k+1];
        int[] sells = new int[k+1];
//        int buy1 = -prices[0], sell1 = 0;
        // 初始化
//        Arrays.fill(buys, -prices[0]);
        buys[0] = -prices[0];
        sells[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                buys[j] = Math.max(buys[j], j == 1 ? -prices[i] : sells[j-1] - prices[i]);
                sells[j] = Math.max(sells[j], buys[j] + prices[i]);
            }
//            buy1 = Math.max(buy1, -prices[i]);
//            sell1 = Math.max(sell1, buy1 + prices[i]);
//            buy2 = Math.max(buy2, sell1 - prices[i]);
//            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sells[k];
    }



}
