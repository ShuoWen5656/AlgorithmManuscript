package leetcode;

/**
 * @author swzhao
 * @data 2023/10/4 8:58
 * @Discreption <> 加油站
 */
public class CanCompleteCircuit {

    public static void main(String[] args) {
        int[] gas = {5,1,2,3,4};
        int[] cost = {4,4,1,5,1};
        System.out.println(solution3(gas, cost));
    }


    public static int solution1(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0 || gas.length != cost.length) {
            return -1;
        }
        int res = -1;
        // helper[i]为从i处到i+1处需要增加或者减少多少油
        int[] helper = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            helper[i] = gas[i] - cost[i];
        }
        if (helper.length == 1 && helper[0] == 0) {
            // 特殊情况
            return 0;
        }
        // 起点必须是大于0的，否则无法到下一个点
        for (int i = 0; i < helper.length; i++) {
            if (helper[i] > 0) {
                // 只有大于0才有可能变成起点
                int tmp = i;
                // 当前所剩油
                int cur = 0;
                for (int j = 0; j < gas.length; j++) {
                    cur += helper[tmp % helper.length];
                    if (cur < 0) {
                        // 无法到达,但是i可以优化
                        break;
                    }
                    // 下一个加油站
                    tmp++;
                }
                // 循环完毕了cur没有消耗完毕，则可以到达
                if (cur >= 0) {
                    res = tmp % helper.length;
                }
            }
        }
        return res;
    }
    public static int solution3(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0 || gas.length != cost.length) {
            return -1;
        }
        // helper[i]为从i处到i+1处需要增加或者减少多少油
        int[] helper = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            helper[i] = gas[i] - cost[i];
        }
        if (helper.length == 1 && helper[0] == 0) {
            // 特殊情况
            return 0;
        }
        // 起点必须是大于0的，否则无法到下一个点
        for (int i = 0; i < helper.length;) {
            if (helper[i] > 0) {
                // 当前所剩油
                int cur = 0;
                for (int j = 0; j < gas.length; j++) {
                    // j相当于offset
                    cur += helper[(i + j) % helper.length];
                    if (cur < 0) {
                        // 无法到达,但是i可以优化
                        // 原因：首先起点一定是大于0的油量，如果到达某个地方油量为负，说明当前位置和起点之间不可能存在有效值，因为中间值肯定都要经历一遍负值
                        // 没有必要了
                        i += j+1;
                        break;
                    }
                }
                // 循环完毕了cur没有消耗完毕，则可以到达
                if (cur >= 0) {
                    return i;
                }
            }else {
                i++;
            }
        }
        return -1;
    }

    public static int solution2(int[] gas, int[] cost) {
        int start = 0;
        int len = gas.length;
        int res = -1;
        // helper[i]为从i处到i+1处需要增加或者减少多少油
        int[] helper = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            helper[i] = gas[i] - cost[i];
        }
        // 一次遍历，有跳跃的检查每一个点为起点
        while (start < len) {
            // 当前的油量
            int cur = 0;
            // 步长offset
            int cnt = 0;
            while (cnt < len) {
                cur += helper[(start + cnt) % len];
                if (cur < 0) {
                    break;
                }
                cnt++;
            }
            if (cnt == len) {
                // 到达终点了
                return start;
            }else {
                // 没到终点
                start += cnt + 1;
            }
        }
        return res;
    }



}
