package leetcode;

/**
 * @author swzhao
 * @date 2023/10/6 9:50 下午
 * @Discreption <> 接雨水
 */
public class Trap {


    public static void main(String[] args) {
        int[] ints = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(solution(ints));

    }

    /**
     * 最优解
     * 1、从左到右获取极大值
     * 2、从右到左获取极小值
     * 3、右边的和左边较小的值-height[i] 求和即可
     * @param height
     * @return
     */
    public static int solution(int[] height) {
        if (height == null || height.length == 0 || height.length == 1 || height.length == 2) {
            return 0;
        }
        int[] left = new int[height.length];
        left[0] = height[0];
        int[] right = new int[height.length];
        right[height.length-1] = height[height.length-1];
        int res = 0;

        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(height[i], left[i-1]);
        }

        for (int i = height.length-2; i >= 0; i--) {
            right[i] = Math.max(height[i], right[i+1]);
        }

        for (int i = 0; i < height.length; i++) {
            res += Math.min(left[i], right[i]) - height[i];
        }

        return res;
    }



    /**
     * 接雨水
     * @param height
     * @return
     */
    public static int solution2(int[] height) {
        if (height == null || height.length == 0 || height.length == 1 || height.length == 2) {
            return 0;
        }
        // dp[i]代表height[i]柱子为底时能够接多少雨水
        int[] dp = new int[height.length];
        // 两个柱子没办法接雨水
        dp[0] = 0;
        dp[1] = 0;
        int maxGlobalIndex = height[0] > height[1] ? 0 : 1;
        for (int i = 2; i < height.length; i++) {
            if (height[i] <= height[i-1] || maxGlobalIndex == i-1) {
                // 如果当前的柱子短于前面的，说明当前柱子做出不了什么贡献，只能跟前面接的水一样
                // 如果目前为止的最大值是自己前面的柱子，说明自己的结果跟前面的柱子一样木桶效应
                dp[i] = dp[i-1];
            }else {
                if (height[i] >= height[maxGlobalIndex]) {
                    // 超过的话直接用
                    dp[i] = dp[maxGlobalIndex] + countWater(height, maxGlobalIndex, i);
                }else {
                    // 没超过的话，找到前面第一个比自己大的计算
                    int pre = i-1;
                    while (pre > 0 && height[pre] < height[i]) {
                        pre--;
                    }
                    dp[i] = dp[pre] + countWater(height, pre, i);
                }
            }
            // 记录目前为止的最大值的index
            if (height[maxGlobalIndex] <= height[i]) {
                maxGlobalIndex = i;
            }
        }
        return dp[height.length - 1];
    }



    private static int countWater(int[] height, int start, int end) {
        // 计算出较小的一边
        int min = height[start] < height[end] ? start : end;
        // 计算需要减掉的块
        int sum = 0;
        for (int i = start+1; i < end; i++) {
            sum += Math.min(height[i], height[min]);
        }
        return (end - start - 1) * height[min] - sum;
    }



}
