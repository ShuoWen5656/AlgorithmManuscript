package leetcode;

import java.util.HashMap;

/**
 * @author swzhao
 * @data 2023/9/23 17:18
 * @Discreption <>
 */
public class SumNum {

    public static void main(String[] args) {
        sumNum(new int[]{2,7,11,15}, 9);
    }
    /**
     * 两树之和
     * @param arr
     * @return
     */
    public static int[] sumNum(int[] arr, int target) {
        HashMap<Integer, Integer> helper = new HashMap<>();
        // 存储结果
        int[] res = null;
        for (int i = 0; i < arr.length; i++) {
            int sub = target - arr[i];
            if (helper.containsKey(sub)) {
                // 说明存在，找到了
                return new int[]{helper.get(sub), i};
            }else {
                helper.put(arr[i], i);
            }
        }
        // 没找到
        return null;
    }


}
