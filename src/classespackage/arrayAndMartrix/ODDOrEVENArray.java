package classespackage.arrayAndMartrix;

import classespackage.CommonUtils;

/**
 * @author swzhao
 * @data 2022/6/16 21:55
 * @Discreption <> 奇数下标都是奇数或者偶数下表都是偶数
 */
public class ODDOrEVENArray {

    /**
     * 调整array，使得奇数下标都是奇数或者偶数下表都是偶数
     * 不断的判断最后一个数是奇数还是偶数，如果是奇数就放到奇数index，否则放到偶数index
     * @param array
     */
    public static void modify(int[] array){
        try {
            int even = 0;
            int odd = 1;
            int end = array.length-1;
            while (end >= even && end >= odd){
                if ((array[end]&1) == 0){
                    // 偶数
                    CommonUtils.swap(array, end, even);
                    even+=2;
                }else {
                    CommonUtils.swap(array, end, odd);
                    odd+=2;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 二轮测试：完成奇数下表都是奇数或者偶数下表都是偶数
     * @param arr
     * @return
     */
    public static int[] changeCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        // 当前需要被替换的index
        int evenIndex = 0, oddIndex = 1;
        // 游标
        int next = arr.length-1;
        while (evenIndex <= next && oddIndex <= next) {
            if ((arr[next] & 1) == 1) {
                // 奇数
                CommonUtils.swap(arr, next, oddIndex);
                oddIndex+=2;
            }else {
                // 偶数
                CommonUtils.swap(arr, next, evenIndex);
                evenIndex+=2;
            }
            //next--;
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5};
        modify(ints);
        CommonUtils.printArr(ints);
    }



}
