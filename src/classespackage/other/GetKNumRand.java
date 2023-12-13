package classespackage.other;

import classespackage.CommonUtils;

/**
 * @author swzhao
 * @data 2022/7/5 21:22
 * @Discreption <> 蓄水池算法
 */
public class GetKNumRand {

    /**
     * 1-k的随机数
     * @param k
     * @return
     */
    public static int rand(int k){
        return (int)(Math.random() * k) + 1;
    }

    /**
     * 返回res，使得0-max每一个数都等概率进入k
     * @param k
     * @param max
     * @return
     */
    public static int[] getKNumRand(int k, int max){
        if (k < 1 || max < 1){
            return null;
        }
        int[] res = new int[Math.min(k, max)];
        // 先放入前k个
        for (int i = 0; i < res.length; i++){
            res[i] = i+1;
        }
        // 剩下的按照规则放入
        for (int i = k + 1; i < max; i ++){
            // 首先计算当前值是否要放进res中
            // 当0-i随机数小于k的时候也就是k/i的概率进入res
            if (rand(i) < k){
                // 随机从res中选择一个位置放入
                res[rand(k)] = i;
            }
        }
        return res;
    }



    /**
     * 二轮测试：蓄水池算法
     * @param k
     * @param max
     * @return
     */
    public static int[] getKNumRandCp1(int k, int max) {
        int[] container = new int[k];
        // 初始化
        //for (int i = 0; i < max; i++) {
        //    container[i] = i + 1;
        //}
        for (int i = 0; i < max; i++) {
            if (i < k) {
                container[i] = i + 1;
                continue;
            }
            // 如果随机数没有超过k，则随机替换一个
            if (rand(i) <= k) {
                container[randCp1(k) - 1] = i;
            }
        }
        return container;
    }


    public static int randCp1(int num) {
        return (int)(Math.random() * num + 1);
    }


    public static void main(String[] args) {
        CommonUtils.printArr(getKNumRandCp1(5, 9));
    }


}
