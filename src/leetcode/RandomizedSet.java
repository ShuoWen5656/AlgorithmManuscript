package leetcode;

import java.util.*;

/**
 * @author swzhao
 * @data 2023/10/3 10:53
 * @Discreption <>O(1) 时间插入、删除和获取随机元素
 */
public class RandomizedSet {


    /**
     * 保存值和对应的索引
     */
    private Map<Integer, Integer> valueIndexMap;

    /**
     * 值直接往后面加即可，不用插入
     */
    private List<Integer> valueList;



    public RandomizedSet() {
        valueIndexMap = new HashMap<>();
        valueList = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (valueIndexMap.containsKey(val)) {
            return false;
        }
        valueList.add(val);
        valueIndexMap.put(val, valueList.size()-1);
        return true;
    }

    public boolean remove(int val) {
        if (!valueIndexMap.containsKey(val)) {
            return false;
        }
        // 要删除的索引
        Integer removeIndex = valueIndexMap.get(val);
        Integer swapIndex = valueList.size()-1;
        // 要删除的值
        Integer removeValue = val;
        Integer swapValue = valueList.get(swapIndex);

        // 交换
        valueList.set(removeIndex, swapValue);
        valueList.set(swapIndex, removeValue);
        valueIndexMap.put(val, swapIndex);
        valueIndexMap.put(swapValue, removeIndex);

        valueIndexMap.remove(val);
        valueList.remove(valueList.size()-1);
        return true;
    }

    public int getRandom() {
        // 直接返回一个随机的索引
        Random random = new Random();
        return valueList.get(random.nextInt(valueList.size()));
    }






}
