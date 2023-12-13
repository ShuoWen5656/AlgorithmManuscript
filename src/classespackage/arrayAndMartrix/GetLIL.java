package classespackage.arrayAndMartrix;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author swzhao
 * @data 2022/6/13 20:45
 * @Discreption <> 最长的可整合子数组的长度
 * 可整合子数组：该数组排序后相邻之间的数差值为1
 */
public class GetLIL {

    /**
     * 主方法：
     * 原理：不重复的情况下可整合数组的最大值和最小值差+1为数组长度
     * @param array
     * @return
     */
    public static int getLIL(int[] array){
        try {
            if (array == null || array.length == 0){
                return 0;
            }
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int len = 0;
            // 判重
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < array.length; i++){
                // 这里必须要从i到length，不能从0->i
                // 因为判重在j++时可以将之前的结果用上，break才能生效
                for (int j = i; j < array.length; j++){
                    if (set.contains(array[j])){
                        // 接下来的循环里面都有这个重复的值所以都不是可整合数组
                        break;
                    }
                    set.add(array[j]);
                    // 更新当前最值
                    max = Math.max(max, array[j]);
                    min = Math.min(min, array[j]);
                    // 判断array[i...j]是否是可整合数组
                    if(max - min + 1 == i - j + 1){
                        // 说明是整合数组
                        len = Math.max(len, i - j + 1);
                    }
                }
                set.clear();
            }
            return len;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 二轮测试：求代码可整合子数组的最长长度
     * 可整合数组定义：对数组排序后，数组中每相邻两个数之间的差值为1
     * @param arr
     * @return
     */
    public static Record getLILCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Record record = new Record();
        int min;
        int max;
        // 用来判重
        Set<Integer> set = new HashSet<>();
        // 遍历每一个子数组
        for (int i = 0; i < arr.length; i++) {
            // 每一轮需要清空最值
            min = arr[0];
            max = arr[0];
            set.clear();
            for (int j = i; j < arr.length; j++) {
                // 子数组为[i...j],先计算最值
                // 更新当前子数组的最大值或最小值
                max = Math.max(max, arr[j]);
                min  = Math.min(min, arr[j]);
                // 计算当前子数组是否是可整合数组
                if (set.contains(arr[j])) {
                    // 从j开始存在重复的,直接下一轮
                    break;
                }else if (max - min + 1 == j - i + 1) {
                    // 值域 == 长度
                    if (j - i + 1 > record.len) {
                        record.setLen(j - i + 1);
                        record.setArr(Arrays.copyOfRange(arr, i, j+1));
                    }
                    set.add(arr[j]);
                }
            }
        }
        return record;
    }


    /**
     * 存储最长子数组长度和对应子数组的实体类
     */
    static class Record {
        private int len;
        private int[] arr;

        public int getLen() {
            return len;
        }

        public void setLen(int len) {
            this.len = len;
        }

        public int[] getArr() {
            return arr;
        }

        public void setArr(int[] arr) {
            this.arr = arr;
        }

        @Override
        public String toString() {
            return "Record{" +
                    "len=" + len +
                    ", arr=" + Arrays.toString(arr) +
                    '}';
        }
    }



    public static void main(String[] args) {
        System.out.println(getLILCp1(new int[]{5,5,3,2,6,4,3}));
    }



}
