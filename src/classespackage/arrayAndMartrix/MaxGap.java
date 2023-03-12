package classespackage.arrayAndMartrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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


    /**
     * 二轮测试：获取数组排序后相邻之间的最大值
     * @param arr
     * @return
     */
    public static int getMaxSubCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        // 桶个数
        int bNum = arr.length+1;
        // 桶列表,这里长度不会改变的使用数组类型
        Record[] records = new Record[bNum + 1];
        init(records);
        Record maxAndMin = getMaxAndMin(arr);
        int max = maxAndMin.getMaxValue();
        int min = maxAndMin.getMinValue();
        // 桶中的范围
        int dis = (int)Math.ceil((max - min + 1) / (bNum-1));
        // 最大值放入最后一个桶
        records[bNum].updateMaxOrMin(max);
        // 剩下的开始分类放入桶中
        for (int i = 0; i < arr.length; i++) {
            if (max == arr[i]) {
                continue;
            }
            // 桶号
            int bucketNum = (arr[i] - min) / dis;
            records[bucketNum].updateMaxOrMin(arr[i]);
        }
        // 找到桶中第一个不为空的index
        int noEmpty = 0;
        while (records[noEmpty].isEmpty()) {
            noEmpty++;
        }
        // 记录上一个非空位置
        int lastNoEmpty = noEmpty;
        int res = 0;
        // 循环找到除当前位置外的非空桶
        while (noEmpty < records.length) {
            if (noEmpty != lastNoEmpty && !records[noEmpty].isEmpty()){
                // 找到一个
                res = Math.max(res, records[noEmpty].getMinValue() - records[lastNoEmpty].getMaxValue());
                lastNoEmpty = noEmpty;
            }
            noEmpty++;
        }
        return res;
    }

    /**
     * 初始化
     * @param records
     */
    private static void init(Record[] records) {
        for (int i = 0; i < records.length; i++) {
            records[i] = new Record(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }

    /**
     * 获取arr中的最值
     * @param arr
     * @return
     */
    private static Record getMaxAndMin(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(arr[i], min);
            max = Math.max(arr[i], max);
        }
        return new Record(max, min);
    }


    /**
     * 每一个桶只记录最大值和最小值就行
     */
    protected static class Record {
        private Integer maxValue;
        private Integer minValue;

        private LinkedList<Integer> bucket;

        public Record(Integer maxValue, Integer minValue) {
            this.maxValue = maxValue;
            this.minValue = minValue;
        }

        /**
         * 拓展构造函数
         * @param maxValue
         * @param minValue
         * @param bucket
         */
        public Record(Integer maxValue, Integer minValue, LinkedList<Integer> bucket) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.bucket = bucket;
        }

        public Integer getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(Integer maxValue) {
            this.maxValue = maxValue;
        }

        public Integer getMinValue() {
            return minValue;
        }

        public void setMinValue(Integer minValue) {
            this.minValue = minValue;
        }

        /**
         * 判断当前值是否能够更新最值
         * @param value
         */
        public void updateMaxOrMin(int value) {
            this.maxValue = Math.max(this.maxValue, value);
            this.minValue = Math.min(this.minValue, value);
        }

        /**
         * 当前桶是否为空
         * 最值没有更新过
         */
        public boolean isEmpty() {
            return this.maxValue == Integer.MIN_VALUE && this.minValue == Integer.MAX_VALUE;
        }

    }


    public static void main(String[] args) {
        System.out.println(getMaxSubCp1(new int[]{1,3,9,10}));
    }


}
