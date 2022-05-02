package classespackage.recursionandDP;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2022/5/2 10:41
 * @Discreption <> 数组中的最长连续序列
 */
public class LongestConsecutive {

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

}
