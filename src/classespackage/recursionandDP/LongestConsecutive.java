package classespackage.recursionandDP;

import dataConstruct.DisJointSetCP;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2022/5/2 10:41
 * @Discreption <> 数组中的最长连续序列
 */
public class LongestConsecutive {

    /**
     * 该解法有误，没有将边界值更新
     * @param array
     * @return
     */
    public static int longestArrayLen(int[] array){
        try {
            if(array == null || array.length == 0){
                return 0;
            }
            // 数组中出现的数字-该数字所在连续数组的最长数组长度
            Map<Integer, Integer> map = new HashMap<>();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < array.length; i++){
                // 如果map已经存在该键值，不需要放入
                if (map.containsKey(array[i])){
                    continue;
                }else{
                    map.put(array[i], 1);
                }
                Integer curValue = map.get(array[i]);
                // 这里开始判断新的键值是否需要合并
                if (map.containsKey(array[i] + 1)){
                    // 上面有值
                    Integer leftValue = map.get(array[i] + 1);
                    map.put(array[i], leftValue + curValue);
                    map.put(array[i] + 1, leftValue + curValue);
                }
                if (map.containsKey(array[i] - 1)){
                    // 下面有值
                    Integer rightValue = map.get(array[i] - 1);
                    map.put(array[i], rightValue + curValue);
                    map.put(array[i] - 1, rightValue + curValue);
                }
                if (map.get(array[i]) > max){
                    max = map.get(array[i]);
                }
            }
            return max;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 二轮测试：map法
     * @param arr
     * @return
     */
    public static int maxSeqLenCp1(int[] arr) {
        if(arr == null || arr.length == 0){
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 1;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                // 已经出现过的就不计算了
                continue;
            }
            // 先将当前值放入
            map.put(arr[i], 1);
            // 检查前面有没有连续的
            if (map.containsKey(arr[i] - 1)) {
                max = Math.max(max, merge(map, arr[i] - 1, arr[i]));
            }
            // 检查后面有没有连续的
            if (map.containsKey(arr[i] + 1)) {
                max = Math.max(max, merge(map, arr[i], arr[i] + 1));
            }
        }
        return max;
    }

    /**
     * 合并map，只更新边界值为最大值，因为中间值不会拿出来再判断了
     * @param map
     * @param pre
     * @param next
     * @return
     */
    private static int merge(HashMap<Integer, Integer> map, int pre, int next) {
        int left = pre - map.get(pre) + 1;
        int right = map.get(next) + next - 1;
        int len = right - left + 1;
        // 更新边界值
        map.put(left, len);
        map.put(right, len);
        return len;
    }

    public static void main(String[] args) {
        System.out.println(maxSeqLenCp1(new int[]{100, 4, 200, 1,3,2}));
    }


}
