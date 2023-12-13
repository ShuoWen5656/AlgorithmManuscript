package classespackage.arrayAndMartrix;

import classespackage.CommonUtils;

import java.util.Arrays;

/**
 * @author swzhao
 * @data 2022/6/15 21:17
 * @Discreption <> 不重复打印排序数组中相加和为给定值的所有二元组和三元组
 */
public class PrintUniquePair {


    /**
     * 打印二元组
     * @param array
     * @param k
     */
    public static void printPairForTwo(int[] array, int k){
        try {
            int left = 0, right = array.length-1;
            while (left <= right){
                int sum = array[left] + array[right];
                if (sum == k){
                    if(left!=0 && array[left] != array[left-1]){
                        System.out.println(String.format("%d, %d\n", array[left], array[right]));
                    }
                    left++;
                    right--;
                }else if(sum < k){
                    left++;
                }else{
                    right--;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 打印三元组：通过固定一个元素，剩下的元素求二元组和即可
     * @param array
     * @param k
     */
    public static void printPairForThird(int[] array, int k){
        try {
            int left = 0;
            int right = array.length-1;
            for (int i = 0; i < array.length; i++){
                left = i;
                int target = k - array[i];
                while (left <= right){
                    int sum = array[left] + array[right];
                    if (sum == target){
                        if(left!=0 && array[left] != array[left-1]){
                            System.out.println(String.format("%d, %d, %d\n", array[i] ,array[left], array[right]));
                        }
                        left++;
                        right--;
                    }else if(sum < k){
                        left++;
                    }else{
                        right--;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 二轮测试：打印有序数组中相加为k的二元组
     */
    public static int[][]  printPair2Cp1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int left = 0;
        int right = arr.length - 1;
        int[][] res = new int[arr.length][2];
        int index = 0;
        while (left < right) {
            if (arr[left] + arr[right] == k) {
                res[index] = new int[]{arr[left], arr[right]};
                index++;
                //System.out.println(new StringBuilder().append(arr[left]).append(",").append(arr[right]).toString());
                left++;
            }else if (arr[left] + arr[right] < k) {
                left++;
            }else if (arr[left] + arr[right] > k) {
                right--;
            }
            // 小于，left需要加大,但是不重复,注意边界值
            while (left < arr.length && left > 0 && arr[left] == arr[left-1]) {
                left++;
            }
            while (right > 0 && right < arr.length-1 && arr[right+1] == arr[right]) {
                right--;
            }
        }
        return res;
    }


    /**
     * 二轮测试：打印有序数组中相加和为k的三元组
     * @param arr
     * @param k
     */
    public static int[][] printPair3Cp1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int index = 0;
        int[][] res3 = new int[arr.length][3];
        int r3Index = 0;
        while (index < arr.length) {
            int cur = arr[index];
            int sub = k - cur;
            // 求arr[cur+1...len+1]中和为sub的二元组
            int[][] res = printPair2Cp1(Arrays.copyOfRange(arr, index+1, arr.length), sub);
            int resIndex = 0;
            while (res != null && res.length != 0 && res[resIndex][0] != 0 && 0 != res[resIndex][1]) {
                // 有效数组，输出
                res3[r3Index++] = new int[]{cur, res[resIndex][0], res[resIndex][1]};
                resIndex++;
            }
            index++;
            while (index > 0 && index < arr.length-1 && arr[index-1] == arr[index]) {
                index++;
            }
        }
        return res3;
    }


    public static void main(String[] args) {
        CommonUtils.printArr2D(printPair3Cp1(new int[]{-8,-4,-3,0,1,2,4,5,8,9}, 10));
    }


}
