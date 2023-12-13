package classespackage.arrayAndMartrix;

import classespackage.CommonUtils;

/**
 * @author swzhao
 * @data 2022/6/24 21:16
 * @Discreption <> 数组的partition调整
 */
public class ArrayPartitionMove {

    /**
     * 问题一：将有序数组变成右半边不重复且有序，左边不管
     * @param array
     */
    public static void partitionMove1(int[] array){
        try {
            if (array == null || array.length < 2){
                return;
            }
            // u为当前有序不重复序列的最后一个元素
            int u = 0;
            // i为游标
            int i = 1;
            while (i < array.length){
                // 如果等于，则i懂，u不懂，如果不等于，则将i位置和u+1的位置互换，i++
                if (array[i++] != array[u]){
                    CommonUtils.swap(array, i-1, ++u);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 问题二：将0,1,2组成的数组进行排序，0,1,2变成有序如0000,1111,22222
     * 变种：将红黄蓝色的球进行分类，顺序为红黄蓝
     * @param array
     */
    public static void partitionMove2(int[] array){
        try {
            if (array == null || array.length < 2){
                return;
            }
            int left = -1;
            int right = array.length;
            int index = 1;
            while (index != right){
                if (array[index] == 0){
                    // 应该在左边
                    CommonUtils.swap(array, ++left, index);
                }else if (array[index] == 1) {
                    // 应该在中间
                    index++;
                }else {
                    // 应该在右边
                    CommonUtils.swap(array, --right, index);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 二轮测试：数组的partition调整
     * @param arr
     */
    public static void partionMoveCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int cur = 1;
        // 从1位置开始找到需要更换的地方
        while (cur < arr.length && arr[cur] != arr[cur-1]) {
            cur++;
        }
        // 从next开始，找与cur不同的
        int next = cur+1;
        while (next < arr.length) {
            if (arr[next] == arr[cur-1]) {
                next++;
            }else {
                // 不相等,交换
                CommonUtils.swap(arr, next, cur);
                next++;
                cur++;
            }
        }
    }


    /**
     * 二轮测试：012数组排序
     * @param arr
     */
    public static void partitionMoveCp2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int left = 0;
        int right = arr.length-1;
        // left指向第一个不为0的
        while (arr[left] == 0){
            left++;
        }
        // right指向第一个不为2的
        while (arr[right] == 2) {
            right--;
        }
        int cur = left+1;
        // 从left+1开始遍历
        while (cur < right) {
            if (arr[cur] == 2) {
                CommonUtils.swap(arr, cur, right);
                right--;
            }else if (arr[cur] == 0) {
                CommonUtils.swap(arr, cur, left);
                left++;
            }else {
                cur++;
            }
        }
    }



    public static void main(String[] args) {
        int[] ints = {1, 2, 2, 2, 3, 3, 4, 5, 6, 6, 7, 7, 8, 8, 8, 9};
        int[] ints1 = {2, 2, 0, 1, 1, 0, 2};
        partitionMoveCp2(ints1);
        CommonUtils.printArr(ints1);
    }






}
