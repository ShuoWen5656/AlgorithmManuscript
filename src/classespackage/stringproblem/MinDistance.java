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
     * 二轮测试：问题一
     * @return
     */
    public static int minDisCp1(char[] chars, char c1, char c2) {
        if (chars == null || chars.length == 0) {
            return -1;
        }
        if (c1 == c2) {
            return 0;
        }
        // c1 最近出现的位置
        int last1 = -1;
        // c2 最近出现的位置
        int last2 = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != c1 && chars[i] != c2) {
                continue;
            }
            if (chars[i] == c1) {
                last1 = i;
                if (last2 != -1) {
                    min = Math.min(min,i - last2);
                }
            }
            if (chars[i] == c2) {
                last2 = i;
                if (last1 != -1) {
                    min = Math.min(min, i - last1);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }


    public static void main(String[] args) {
        //System.out.println(minDisCp1(new char[]{'1', '3', '3', '3', '2', '3', '1'}, '1', '2'));
        MinDistanceCp2 minDistanceCp2 = new MinDistanceCp2(new char[]{'1', '3', '3', '3', '2', '3', '1'});
        minDistanceCp2.getMinDis('1', '2');
    }


    /**
     * 进阶问题使用工具类实现
     */
    public static class MinDistanceCp2{
        private Map<Character, Map<Character, Integer>> map;

        public MinDistanceCp2(char[] chars) {
            this.map = new HashMap<>();
            init(chars);
        }

        /**
         * 初始化统计最短距离填充map
         * @param chars
         */
        private void init(char[] chars) {
            if (chars == null || chars.length == 0) {
                return;
            }
            for (int i = 0; i < chars.length; i++) {
                if (!map.containsKey(chars[i])) {
                    map.put(chars[i], new HashMap<>());
                    // 距离自己是0
                    map.get(chars[i]).put(chars[i], 0);
                }
                dealChar(chars, i);
            }
        }

        /**
         * 遍历所有数组，更新最近距离
         * @param index
         * @Param chars
         */
        private void dealChar(char[] chars, int index) {
            for (int i = 0; i < index; i++) {
                int dis = index - i;
                Map<Character, Integer> mapI = this.map.get(chars[i]);
                Map<Character, Integer> mapIndex = this.map.get(chars[index]);
                // 更新i处的
                if (mapI.get(chars[index]) == null || mapI.get(chars[index]) > dis) {
                    mapI.put(chars[index], dis);
                }
                // 更新index
                if (mapIndex.get(chars[i]) == null || mapIndex.get(chars[i]) > dis) {
                    mapIndex.put(chars[i], dis);
                }
            }
        }

        /**
         * 获取最短距离
         * @param c1
         * @param c2
         * @return
         */
        private int getMinDis(char c1, char c2) {
            if (map.get(c1) == null || map.get(c2) == null) {
                return 0;
            }
            return map.get(c1).get(c2) == null ? -1 : map.get(c1).get(c2);
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
