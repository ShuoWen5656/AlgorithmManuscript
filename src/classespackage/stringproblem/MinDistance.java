package classespackage.stringproblem;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2022/5/13 19:40
 * @Discreption <>数组中两个字符串的最小距离
 */
public class MinDistance {

    /**
     * 判断str1和str2在数组中的最小距离
     * @param strs
     * @param str1
     * @param str2
     * @return
     */
    public static int minDistance(String[] strs, String str1, String str2){
        try {
            if(strs == null || str1 == null || str2 == null){
                return -1;
            }
            // 最近出现的index
            int last1 = -1;
            // 最近出现的index
            int last2 = -1;
            // 最小距离值
            int minDis = Integer.MAX_VALUE;
            for (int i = 0; i < strs.length; i++){
                if(str1.equals(strs[i])){
                    last1 = i;
                    if(last2 != -1){
                        minDis = Math.min(minDis, last1 - last2);
                    }
                }else if(str2.equals(strs[i])){
                    last2 = i;
                    if(last1 != -1){
                        minDis = Math.min(minDis, last2 - last1);
                    }
                }
            }
            return minDis == Integer.MAX_VALUE? -1 : minDis;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 该类为内部类，能够将查询最小距离的速度加快到O(1),但是准备工作时间O(n^2),空间O(n^2)
     */
    public class RecordForDistance{
        // str1 -> （str2， 最小距离）
        Map<String, Map<String, Integer>> map;



        public RecordForDistance(String[] strs) {
            // 制作map
            map = new HashMap<>();
            // 记录当前已经出现的str的最近一次index
            Map<String, Integer> indexMap = new HashMap<>();
            for (int i = 0; i < strs.length; i++){
                update(indexMap, strs[i], i);
                // 记录当前str最近出现的地方
                indexMap.put(strs[i], i);
            }
        }

        /**
         * 制作当前str的map
         * @param indexMap
         * @param str
         * @param i
         */
        private void update(Map<String, Integer> indexMap, String str, int i) {
            if(!map.containsKey(str)){
                map.put(str, new HashMap<>());
            }
            Map<String, Integer> str1Map = map.get(str);
            for (Map.Entry<String, Integer> entry : indexMap.entrySet()){
                // str2
                String key = entry.getKey();
                // 最近出现的地方
                Integer value = entry.getValue();
                // 相等的直接略过
                Map<String, Integer> str2Map = map.get(key);
                if(!str.equals(key)){
                    if(str1Map.containsKey(key)){
                        // 已经有和key之间的距离记录了，比较出最小的更新即可
                        // 如果str1Map有，那么str2Map也一定有
                        // 先前的距离
                        Integer pre = str1Map.get(key);
                        // 当前的距离
                        Integer cur = i - value;
                        str1Map.put(str, Math.min(pre, cur));
                        str2Map.put(key, Math.min(pre, cur));
                    }else{
                        // 如果还没有记录过，则记录当前距离
                        str1Map.put(str, i - value);
                        str2Map.put(key, i - value);
                    }
                }
            }
        }

        /**
         * 创建实例后直接调用该方法即可
         * @param strs
         * @param str1
         * @param str2
         * @return
         */
        public int getMinDistance(String[] strs, String str1, String str2){
            try {
                if(str1 == null || str2 == null
                        || !map.containsKey(str1) || !map.containsKey(str2)
                        || !map.get(str1).containsKey(str2) || !map.get(str2).containsKey(str1)){
                    return -1;
                }
                if(str1.equals(str2)){
                    return 0;
                }
                return map.get(str1).get(str2);
            }catch (Exception e){
                e.printStackTrace();
                return -1;
            }
        }
    }



}
