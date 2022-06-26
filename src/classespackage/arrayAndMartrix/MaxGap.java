package classespackage.arrayAndMartrix;

/**
 * @author swzhao
 * @data 2022/6/26 10:33
 * @Discreption <> 数组排序之后相邻数的最大差值
 * 计算出数组排序之后的相邻数之间的最大差值
 */
public class MaxGap {

    /**
     * 主方法
     * @param array
     * @return
     */
    public static int maxGap(int[] array){
        try {
            if (array == null || array.length < 1){
                return 0;
            }
            // 首先获取array的最大值和最小值以及长度
            int len = array.length;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int num : array){
                max = Math.max(max, num);
                min = Math.min(min, num);
            }
            // 将每一个数放进bucket桶中
            // 表示当前桶是否含有数字，如果有则计算最大值和最小值
            boolean[] hasNums = new boolean[len + 1];
            int[] maxArray = new int[len + 1];
            int[] minArray = new int[len + 1];
            int bid = 0;
            for (int i = 0; i < len; i++){
                bid = bucket(array[i], min, max, len);
                maxArray[bid] = hasNums[bid] ? Math.max(maxArray[bid], array[i]) : array[i];
                minArray[bid] = hasNums[bid] ? Math.min(minArray[bid], array[i]) : array[i];
                hasNums[bid] = true;
            }
            // 从第一个不是空的bucket开始往后计算每一个桶之间的max和min之间的间距，找到最大值
            int i = 0;
            // 前一个桶的最大值
            int lastMax = 0;
            // 保存最大间距结果
            int res = 0;
            while (i < len){
                if (hasNums[i++]){
                    lastMax = maxArray[i-1];
                    break;
                }
            }
            // 计算每一个桶间距
            for (; i < len; i++){
                if (hasNums[i]){
                    // 当前桶的最小值减去前一个桶的最大值,
                    // 为什么不用maxArray[i-1]呢因为有可能有的桶是空的，所以这里用lastmax只记录有值的bucket
                    res = Math.max(res, minArray[i] - lastMax);
                    lastMax = maxArray[i];
                }
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 计算当前num应该放在哪个桶中
     * @param num 当前数
     * @param min array中的最小值
     * @param max array中的最大值
     * @param len 单桶长度，这里是N+1
     * @return
     */
    public static int bucket(long num, long min, long max, long len){
        return (int)((num - min)/((max-min)/len));
    }


}
