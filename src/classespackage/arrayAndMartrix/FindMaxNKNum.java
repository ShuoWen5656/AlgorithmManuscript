package classespackage.arrayAndMartrix;

import classespackage.stackAndQueue.HannioTower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author swzhao
 * @data 2022/6/10 19:35
 * @Discreption <>在数组中找到出现次数大于n/k的数
 * 原题：在O(n) 空间O（1）的情况下找到次数大于半数的数
 */
public class FindMaxNKNum {

    /**
     * 找到大于半数的数
     * 主要思路:2个两个的将不同的数进行去除，大于半数的数一定最后能够剩余下来
     * @param array
     * @return
     */
    public static int findMaxHalf(int[] array){
        try {
            int cand = 0;
            int times = 0;
            for (int i = 0; i < array.length; i++){
                if(times == 0){
                    // 当前没有候选者
                    cand = array[i];
                    times = 1;
                }else if (array[i] == cand){
                    // 当前等于候选人，候选人增加一个点
                    times++;
                }else {
                    // 当前不等于候选人，候选人抵消一个点
                    times--;
                }
            }
            // 这一步为了解决都不相同的时候cand也应该不符合条件
            times = 0;
            for (int i = 0; i < array.length; i++){
                if(array[i] == cand){
                    times ++;
                }
            }
            if(times > array.length/2){
                return cand;
            }else {
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 找到数量大于k/n的数
     * 思路相同：一次去掉k个不相同的数，最后剩下的都是候选者
     * @param arary
     * @param k
     */
    public static void printMaxKNum(int[] arary, int k){
        try {
            // cand->times
            Map<Integer, Integer> map = new HashMap<>(3);
            for (int i = 0; i < arary.length; i++){
                if (map.containsKey(arary[i])){
                    // map已经出现过array[i]，则不能抵消
                    map.put(map.get(arary[i]), map.get(arary[i])+1);
                }else {
                    // map没有这个值
                    if(map.size() <= k-1){
                        map.put(arary[i], 1);
                    }else{
                        // map已经满了,说明找到三个不同的组合一次
                        dealMap(map, arary[i]);
                    }
                }
            }
            boolean hasprint = false;
            // 候选者已经出现，求每一个候选者是否真的超过了数量
            Map<Integer, Integer> real = getReal(map, arary);
            for (Integer key : real.keySet()){
                if (real.get(key) > arary.length/k){
                    hasprint = true;
                    System.out.println(key);
                }
            }
            System.out.println(hasprint? "" : "no such number!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取候选者在array中出现的次数
     * @param map
     * @param arary
     * @return
     */
    private static Map<Integer, Integer> getReal(Map<Integer, Integer> map, int[] arary) {
        Map<Integer, Integer> real = new HashMap<>();
        for (int i = 0; i < arary.length; i++){
            if(map.containsKey(arary[i])){
                if(real.containsKey(arary[i])){
                    real.put(arary[i], real.get(arary[i])+1);
                }else {
                    real.put(arary[i], 1);
                }
            }
        }
        return real;
    }

    /**
     * 将候选者都自减，如果为0的times删除
     * @param map
     * @param num
     */
    private static void dealMap(Map<Integer, Integer> map, int num) {
        // 如果当前key的次数为1说明需要被移除map
        List<Integer> removeKey = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == 1){
                // 要删除的key
                removeKey.add(entry.getKey());
            }
            // 候选者自减
            entry.setValue(entry.getValue()-1);
        }
        for (Integer key : removeKey){
            map.remove(key);
        }
    }


}
