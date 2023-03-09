package classespackage.arrayAndMartrix;

import classespackage.CommonUtils;

/**
 * @author swzhao
 * @data 2022/6/14 22:34
 * @Discreption <> 自然数数组的排序
 * 介绍：一个数组长度为n保存了1..n的数字，并且不重复，将数组进行排序
 */
public class SortNNumArray {


    /**
     * 方法一：
     * 替换
     * @param array
     */
    public static void sort1(int[] array){
        try {
            int tem = 0;
            for (int i = 0; i < array.length; i++){
                // 只要没有归位就一直交换即可
                while (array[i] != i+1){
                    int index = array[i] - 1;
                    tem = array[index];
                    array[index] = array[i];
                    array[i] = tem;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 方法二：有点不好理解
     * 将要被替换的值保存起来，while里面不能出现i，因为i不会变，
     * 一直循环替换直到替换到array[i]正确为止
     * @param array
     */
    public static void sort2(int[] array){
        try {
            int tem = 0;
            int next = 0;
            for (int i = 0; i < array.length; i++){
                tem  = array[i];
                // 虽然while中没有改变数字i，但是轮回回来arr[i]会改变，最终跳出循环
                while (array[i] != i + 1){
                    // 要被替换的数字
                    next = array[tem-1];
                    array[tem-1] = tem;
                    tem = next;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 二轮测试（方法一）：排序自然数组
     * @param arr
     * @return
     */
    public static int[] sortNNumCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int cur = arr[0], index = 0;
        while (index < arr.length) {
            // 找到当前值应该在的位置
            int should = cur - 1;
            // 判断所在位置是否正确
            if (arr[should] == cur) {
                // 正确则判断下一个值
                index++;
                if (index == arr.length) {
                    break;
                }else {
                    cur = arr[index];
                }
            }else {
                // 不正确则将should位置取出来
                int next = arr[should];
                // 将should位置覆盖
                arr[should] = cur;
                // next值作为接下来判断的值
                cur = next;
            }
        }
        return arr;
    }

    /**
     * 二轮测试（方法二）：交换方式排序自然数
     * @param arr
     * @return
     */
    public static int[] sortNumCp2(int[] arr) {
        if (arr == null || arr.length == 0){
            return null;
        }
        int index = 0;
        while (index < arr.length) {
            if (arr[index]-1 == index) {
                index++;
                continue;
            }
            // 不符合要求的交换位置
            int cur = arr[index];
            int tem = arr[cur-1];
            arr[cur-1] = cur;
            arr[index] = tem;
        }
        return arr;
    }


    ///**
    // * 二轮测试：使用位操作交换数减少一个32位的内存
    // * @param arr
    // * @return
    // */
    //public static int[] sortNumCp3(int[] arr) {
    //    if (arr == null || arr.length == 0) {
    //        return null;
    //    }
    //    for (int i = 0; i < arr.length; i++) {
    //        while (arr[i] != i+1) {
    //            // 交换
    //            arr[i] = arr[i] ^ arr[arr[i] - 1];
    //            // 现在arr[arr[i] - 1]已经是arr[i]了
    //            arr[arr[i] - 1] = arr[i] ^ arr[arr[i] - 1];
    //            arr[i] = arr[i] ^ arr[arr[i] - 1];
    //        }
    //    }
    //    return arr;
    //}


    public static void main(String[] args) {
        int[] arr = new int[]{3,5,2,1,4,6,7};
        //sortNumCp3(arr);
    }




}
