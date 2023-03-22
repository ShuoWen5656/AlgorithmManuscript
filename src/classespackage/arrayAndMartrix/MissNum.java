package classespackage.arrayAndMartrix;

import classespackage.CommonUtils;

/**
 * @author swzhao
 * @data 2022/6/27 21:56
 * @Discreption <> 数组中未出现的最小正整数
 * 给定一个无序整型数组，找到数组中未出现的最小正整数
 */
public class MissNum {

    public static int missNum(int[] array){
        try {
            int l = 0;
            int r = array.length;
            while (l < r){
                if (array[l] == l+1){
                    l++;
                }else if (array[l] < l || array[l] > r || array[array[l] - 1] == array[l]){
                    array[l] = array[--r];
                }else {
                    CommonUtils.swap(array, l, array[l] - 1);
                }
            }
            return l+1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 二轮测试：计算数组中未出现的最小正整数
     * @param arr
     * @return
     */
    public static int getMissNumCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        // 期望arr = [1,2,3,4,5,6...]，l代表截止目前已经存在的范围[1,l],r代表截止目前，后面无论多么理想，范围最优[1...r]
        int l = 0, r = arr.length;
        while (l < r) {
            if (arr[l] == l+1) {
                // arr[l]在理想的位置
                l++;
            }else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
                // arr[l]不在期望的区间内，或者在期望区间内但是不在当前位置
                // 期望的范围-1，因为出现了期望之外的值或者有重复值
                r--;
                arr[l] = arr[r];
            }else{
                // 出现了期望的值，但是不在应该在的位置上
                CommonUtils.swap(arr, l, arr[l] - 1);
            }
        }
        return l + 1;
    }


    public static void main(String[] args) {
        System.out.println(getMissNumCp1(new int[]{-1,2,3,4}));
    }

}
