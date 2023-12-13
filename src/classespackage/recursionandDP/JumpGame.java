package classespackage.recursionandDP;


/**
 * @author swzhao
 * @data 2022/5/1 11:52
 * @Discreption <> 跳跃游戏
 * 给定array数组，array[i]代表能够向右边跳跃的最长位置，求从左边开始跳跃到最右边需要跳跃多少步骤
 */
public class JumpGame {


    /**
     * 自己的方式：
     * 使用array动态递归数组，array[i]代表从当前位置出发到达终点的最少跳跃步骤
     * 空间O（n）时间最差O（n^2）
     * @return
     */
    public static int getJumpNum(int[] array){
        try{
            if(array == null || array.length == 0){
                return 0;
            }
            int[] dp = new int[array.length];
            // 从终点到终点不需要跳跃
            dp[array.length-1] = 0;
            // 当前节点距离终点的距离是多少
            int len = 1;
            for (int i = array.length-2; i > 0; i--){
                if(array[i] > len){
                    // 当前节点可以一步到终点
                    dp[i] = 1;
                    continue;
                }
                // 到这里说明当前位置不能直接到达终点，需要在能够跳跃到的范围内找到一个最少步骤的位置+1即可
                int min = Integer.MAX_VALUE;
                for (int j = i+1; j <= i + array[i]; j ++){
                    if (min > array[j]){
                        min = array[j];
                    }
                }
                dp[i] = min+1;
            }
            return dp[0];
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 书中解法
     * 时间O（n）空间O（1）
     * @param array
     * @return
     */
    public static int getJumpNum2(int[] array){
        try {
            if (array == null || array.length == 0){
                return 0;
            }
            // 到达当前位置已经跳了多少步
            int jump = 0;
            // 当前的jump可以跳的最远的地方
            int cur = 0;
            // 当前的jump再跳跃一次能够跳跃的最远地方
            int next = 0;
            for (int i = 0; i < array.length; i++){
                if(i > cur){
                    // 说明当前位置jump步已经跳不到了，需要next才能跳到，next是一定能够跳跃到的，因为next一直在更新
                    jump++;
                    cur = next;
                }
                // next一直更新为下一跳能够跳跃的最远距离
                next = Math.max(next, i + array[i]);
            }
            return jump;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 二轮测试：递归方法
     * @return
     */
    public static int jumpCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return processCp1(arr, 0);
    }

    private static int processCp1(int[] arr, int i) {
        // -1表示跳不过去
        int res = Integer.MAX_VALUE;
        if (i >= arr.length) {
            // 超出去了，无法达到，直接返回最大值
            return Integer.MAX_VALUE;
        }
        if (i == arr.length-1) {
            // 已经到了,返回0步即可
            return 0;
        }
        int step = arr[i];
        for (int j = 1; j <= step; j++) {
            res = Math.min(res, processCp1(arr, i+j));
        }
        return res == Integer.MAX_VALUE ? -1 : res + 1;
    }

    /**
     * 二轮测试：动态规划
     * @return
     */
    public static int dpCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        dp[arr.length-1] = 0;
        for (int i = arr.length-2; i >= 0; i--) {
            int step = arr[i];
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= step; j++) {
                min = Math.min(min, dp[i+j]);
            }
            dp[i] = min + 1;
        }
        return dp[0];
    }


    /**
     * 最优解
     * 时间 o（n) 空间 O(1)
     * @param arr
     * @return
     */
    public static int superCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int jump = 0;
        // 当前所在位置能够跳的最远距离
        int cur = 0;
        // 下一跳的位置的最大范围
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                // 说明当前位置到不了了
                jump ++;
                // 跳到下一跳
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return jump;
    }

    public static void main(String[] args) {
        System.out.println(superCp1(new int[]{3,2,3,1,1,4}));
    }

}
