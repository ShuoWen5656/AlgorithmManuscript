package classespackage.arrayAndMartrix;

import classespackage.CommonUtils;
import classespackage.stackAndQueue.catDogQueue.Pet;

/**
 * @author swzhao
 * @data 2022/6/23 21:35
 * @Discreption <>不包含本位置值的累乘数组
 */
public class NoCurNumMultiArray {

    /**
     * 方法1：允许使用除法
     * @param array
     * @return
     */
    public static int[] process1(int[] array){
        try {
            // 记录0的个数
            int count = 0;
            // 记录非零的所有数的乘积
            int multi = 1;
            for (int i = 0; i < array.length; i++){
                if (array[i] == 0){
                    count++;
                }else {
                    multi *= array[i];
                }
            }
            // 结果
            int[] res = new int[array.length];
            if (count == 1){
                // 存在一个0，除了零以外其他都是0
                for (int j = 0; j < array.length; j++){
                    if (array[j] == 0){
                        res[j] = multi;
                    }
                }
            }else if (count == 0){
                // 不存在0
                for (int j = 0; j < array.length; j++){
                    res[j] = multi/array[j];
                }
            }
            // 如果多个零的话，不管什么情况都应该是0
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return new int[0];
        }
    }


    /**
     * 方法二：不准用乘法并且时间空间复杂度不变
     * 原理：每一个数的结果为改数左边的数组乘积和右边数组乘积的乘积
     * @param array
     * @return
     */
    public static int[] process2(int[] array){
        try {
            if (array == null || array.length < 2){
                return new int[0];
            }
            int[] res = new int[array.length];
            // 首先res存储从左到右的乘积, 即res[i]为array[0:i-1]的乘积
            for (int i = 0; i < array.length; i++){
                if (i == 0){
                    // 第一个数需要特殊处理
                    res[i] = array[0];
                    continue;
                }
                res[i] = res[i-1] * array[i];
            }
            // 从右边往左边的累乘积
            int tem = 1;
            // 再从右边往左边开始累乘,最后一个不能计算
            for (int i = array.length; i > 0; i++){
                res[i] = res[i-1] * tem;
                // 更新tem为当前值的右半边累乘
                tem *= array[i];
            }
            res[0] = tem;
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return new int[0];
        }
    }


    /**
     * 二轮测试：可以使用除法的方法
     * @param arr
     * @return
     */
    public static int[] getMultiCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        // 记录0的个数
        int count0 = 0;
        // 记录如果只有一个零时，零的位置
        int index0 = 0;
        // 累乘之和
        int all = 1;
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                count0++;
                index0 = i;
            }else {
                all *= arr[i];
            }
        }
        if (count0 == 1) {
            res[index0] = all;
        }else if (count0 == 0){
            // 没有零
            for (int i = 0; i < arr.length; i++) {
                res[i] = all/arr[i];
            }
        }
        return res;
    }


    /**
     * 二轮测试：不使用除法的方法
     * @param arr
     * @return
     */
    public static int[] getMultiCp2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int len = arr.length;
        // 记录到arr[i]时的累乘
        int[] r = new int[len];
        // l[i]代表从arr[i...len-1]的累乘
        int[] l = new int[len];
        r[0] = arr[0];
        l[len-1] = arr[len-1];
        // 填充r和l
        for (int i = 1; i < len; i++) {
            r[i] = r[i-1] * arr[i];
            l[len-i-1] = l[len-i] * arr[len-i-1];
        }
        // 计算结果
        int[] res = new int[len];
        // 两个边界值先写好
        res[0] = arr[0];
        res[len-1] = arr[len-1];
        for (int i = 1; i < res.length-1; i++) {
            res[i] = r[i-1] * l[i+1];
        }
        return res;
    }

    public static void main(String[] args) {
        CommonUtils.printArr(getMultiCp2(new int[]{2,3,1,4}));
    }




}
