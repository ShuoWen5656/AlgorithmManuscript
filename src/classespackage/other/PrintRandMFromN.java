package classespackage.other;

import classespackage.CommonUtils;

/**
 * @author swzhao
 * @data 2022/7/14 21:31
 * @Discreption <> 从N个数中等概率打印M个数
 */
public class PrintRandMFromN {


    /**
     * 主方法
     * @param array
     * @param m
     */
    public static void pringRandM(int[] array, int m){
        if (array == null || array.length == 0){
            return;
        }
        // 假如打印的m大于了数组长度也只会等概率打印数组长度
        int min = Math.min(array.length, m);
        // 打印个数
        int count = 0;
        while (count < min){
            // 等概率选择一个数
            int i = (int) (Math.random() * (array.length-count));
            // 打印这个数
            System.out.println(array[i]);
            // 将这个数交换到尾部，剩下的继续选举
            CommonUtils.swap(array, i, array.length-count-1);
            count++;
        }


    }


    /**
     * 在长度为n的数组中等概率打印m个数
     * @param arr
     * @param m
     * @return
     */
    public static  int[] printRandM(int[] arr, int m) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] res = new int[m];
        int count = m-1;
        int len = arr.length;
        while (count >= 0) {
            // 取值
            int i = (int) (Math.random() * len);
            res[count] = arr[i];
            CommonUtils.swap(arr, i, len-1);
            len--;
            count--;
        }
        return res;
    }


    public static void main(String[] args) {
        CommonUtils.printArr(printRandM(new int[]{3,5,2,1,6,7}, 3));
    }



}
