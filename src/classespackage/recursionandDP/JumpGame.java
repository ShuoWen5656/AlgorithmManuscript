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

}
