package classespackage.other;

/**
 * @author swzhao
 * @data 2022/7/19 21:54
 * @Discreption <> 在有序旋转数组中找到最小值
 * 问题：可能存在重复的递增数组中，数组可能旋转了，比如1234567 旋转为了 3456712，找到其中的最小值（断点），旋转只旋转半圈
 */
public class GetMinFromRotate {


    /**
     * 主方法：二分法查找
     * @param array
     * @return
     */
    public static int getMinFromRotate(int[] array){
        if (array == null || array.length == 0){
            return 0;
        }
        int low = 0;
        int high = array.length-1;
        int mid = 0;
        while (low < high){
            if (low == high-1){
                break;
            }
            // low小于high情况，low一定是最小值
            if (array[low] < array[high]){
                return array[low];
            }
            mid = (low+high)/2;
            // 大于中点 mid已经旋转“过半了” 6712345
            if (array[low] > array[mid]){
                // 在low-mid
                high = mid;
                continue;
            }
            // 还没“过半” 3456712
            if (array[high] < array[mid]){
                high = mid;
                continue;
            }
        }
        // 这种处理的是相等的情况array[low] = array[hight] = array[mid]
        while (low < mid){
            if (array[low] == array[mid]){
                low++;
            }else if (array[low] < array[mid]){
                return array[low];
            }else{
                high = mid;
                break;
            }
        }

        return Math.min(array[low], array[high]);
    }


    /**
     * 二轮测试：旋转数组的二分法查找
     * @param arr
     * @return
     */
    public static int getMinCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int low = 0;
        int mid = 0;
        int high = arr.length - 1;
        while (low < high) {
            if (low == high - 1) {
                break;
            }
            if (arr[low] < arr[high]) {
                return arr[low];
            }
            mid = (low + high) / 2;
            if (arr[low] > arr[mid]) {
                high = low;
                continue;
            }
            if (arr[low] < arr[mid]) {
                low = mid;
                continue;
            }
            while (low < mid) {
                if (arr[low] < arr[mid]) {
                    return arr[low];
                }else if (arr[low] == arr[mid]) {
                    low++;
                    continue;
                }else {
                    high = mid;
                    break;
                }
            }
        }
        return Math.min(arr[low], arr[high]);
    }


    public static void main(String[] args) {
        System.out.println(getMinCp1(new int[] {1,2,3,4,5}));
    }



}
