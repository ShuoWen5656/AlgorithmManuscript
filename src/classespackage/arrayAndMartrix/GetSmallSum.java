package classespackage.arrayAndMartrix;

import java.util.Arrays;

/**
 * @author swzhao
 * @data 2022/6/12 9:25
 * @Discreption <> 计算数组的小和
 */
public class GetSmallSum {

    /**
     * 主方法:
     * 归并方式计算
     * @param array
     * @return
     */
    public static int getSmallSun(int[] array){
        try {
            if (array == null || array.length == 0){
                return 0;
            }
            return func(Arrays.copyOf(array, array.length), 0, array.length-1);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 递归，主要返回当前层的小和
     * @param array
     * @param left
     * @param right
     * @return
     */
    private static int func(int[] array, int left, int right) {
        if (left == right){
            // 当前层小和为0
            return 0;
        }
        // 取中间值,偶数取后面的
        int mid = (left + right) / 2 + 1;
        //左组小和，右组小和，组间小和
        return func(array, left, mid) + func(array, mid+1, right) + merge(array, left, mid, right);

    }

    /**
     * 计算[left-mid]和[mid+1-right]之间的小和
     * @param array
     * @param left
     * @param mid
     * @param right
     * @return
     */
    private static int merge(int[] array, int left, int mid, int right) {
        // 复制一份左右起点索引
        int i = left;
        int j = mid;
        // 创建一个容器装当前left-right的从小到达排序结果
        int[] contains = new int[right - left + 1];
        // 容器索引
        int index = 0;
        // 记录小和
        int smallSum = 0;
        while (i < mid+1 && j < right+1){
            if(array[i] < array[j]){
                // 此时array的j后面都比j大，所以如果计算小和j后面有几个数，i就会被加几次
                smallSum += array[i] * (right-j+1);
                contains[index++] = array[i];
                i++;
            }else {
                // 左边大说明相对于j当前值，i一次都不用加
                contains[index++] = array[j];
                j++;
            }
        }
        // 将剩下的部分再装进容器
        while (i < mid+1 || j < right+1){
            contains[index++] = i < mid+1? array[i++] : array[j++];
        }
        // 将排序结果写入array中提供给接下来的递归使用
        index = 0;
        while (index < contains.length){
            array[left++] = contains[index++];
        }
        return smallSum;
    }


    /**
     * 二轮测试：计算arr的小和
     * 小和指的是从arr的第一个元素开始，前面比当前值小的数的和为一个小和
     * 一直到最后一个数时，所有的小和之和为结果。
     * @param arr
     * @return
     */
    public static int getSmallAddCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return processCp1(arr, 0, arr.length-1);
    }

    /**
     * 返回从left到right的小和
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int processCp1(int[] arr, int left, int right) {
        if (left == right) {
            // 只有一个数的时候，和不包含自己，所以返回0
            return 0;
        }
        // 前中位数
        int mid = (left + right)/2;
        // 左边
        int leftSum = processCp1(arr, left, mid);
        int rightSum = processCp1(arr, mid + 1, right);
        int mergeSum = mergeCp2(arr, left, mid, right);
        return leftSum + rightSum + mergeSum;
    }

    /**
     * 版本1：合并计算小和
     * 根据右边的数，循环判断左边的每一个数是否小于当前右边的数，小于说明需要增加小和，大于则不产生小和
     * 这个时间复杂度是n^2
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @return
     */
    private static int mergeCp1(int[] arr, int left, int mid, int right) {
        if (left == mid && mid == right) {
            return 0;
        }
        int sum = 0;
        for (int r = mid + 1; r <= right; r++) {
            int cur = arr[r];
            for (int l = left; l <= mid; l++) {
                // 比较是否比右边小
                if (arr[l] < arr[r]) {
                    // 产生小和
                    sum += arr[l];
                }
            }
        }
        return sum;
    }

    /**
     * 版本2：合并计算小和
     * 将归并排序融进来，但是会改变原有数组的顺序，除非给的是深拷贝副本
     * 这个时间复杂度是nlogn
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @return
     */
    private static int mergeCp2(int[] arr, int left, int mid, int right) {
        if (left == mid && mid == right) {
            return 0;
        }
        int l = left;
        int r = mid+1;
        // 容器用来装有序数组
        int[] container = new int[right - left + 1];
        int cindex = 0;
        int sum = 0;
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {
                // 1、先产生小和,这里因为有排序所以r之后的数都会比r大，所以小和应该要乘以后面的数量
                sum += arr[l] * (right - r + 1);
                // 2、放入容器
                container[cindex++] = arr[l];
                l++;
            }else {
                // 不产生小和,只执行排序
                container[cindex++] = arr[r];
                r++;
            }
        }
        // 结束后，将剩下的放入容器中
        if (l > mid) {
            // 左边到头，右边还有
            while (r <= right) {
                container[cindex++] = arr[r++];
            }
        }else {
            while (l <= mid) {
                container[cindex++] = arr[l++];
            }
        }
        // 将容器的新排序装回到arr中
        l = left;
        for (int i = 0; i < container.length; i++) {
            arr[l++] = container[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(getSmallAddCp1(new int[]{1,3,5,2,4,6}));
    }

}
