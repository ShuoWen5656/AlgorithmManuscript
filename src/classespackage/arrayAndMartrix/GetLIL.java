package classespackage.arrayAndMartrix;

import java.util.HashSet;
import java.util.Set;

/**
 * @author swzhao
 * @data 2022/6/13 20:45
 * @Discreption <> 最长的可整合子数组的长度
 * 可整合子数组：该数组排序后相邻之间的数差值为1
 */
public class GetLIL {

    /**
     * 主方法：
     * 原理：不重复的情况下可整合数组的最大值和最小值差+1为数组长度
     * @param array
     * @return
     */
    public static int getLIL(int[] array){
        try {
            if (array == null || array.length == 0){
                return 0;
            }
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int len = 0;
            // 判重
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < array.length; i++){
                // 这里必须要从i到length，不能从0->i
                // 因为判重在j++时可以将之前的结果用上，break才能生效
                for (int j = i; j < array.length; j++){
                    if (set.contains(array[j])){
                        // 接下来的循环里面都有这个重复的值所以都不是可整合数组
                        break;
                    }
                    set.add(array[j]);
                    // 更新当前最值
                    max = Math.max(max, array[j]);
                    min = Math.min(min, array[j]);
                    // 判断array[i...j]是否是可整合数组
                    if(max - min + 1 == i - j + 1){
                        // 说明是整合数组
                        len = Math.max(len, i - j + 1);
                    }
                }
                set.clear();
            }
            return len;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


}
