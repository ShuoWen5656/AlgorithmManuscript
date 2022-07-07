package classespackage.other;

import dataConstruct.ValueWithTime;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2022/7/6 20:18
 * @Discreption <> 设计有setAll功能的哈希表
 * 问题：设计一个能够在O(1)时间复杂度的情况下将所有的value都变成定值的hashmap
 */
public class SetAllHashMap<K, V> {

    /**
     * 实际存储结构
     */
    private Map<K, ValueWithTime<V>> map;
    /**
     * 全局time，用来自增基数的，每进入一个元素都自增一个
     */
    private long time;
    /**
     * 记录设置为全局值的值
     */
    private ValueWithTime<V> setAll;

    public SetAllHashMap() {
        this.map = new HashMap<>();
        this.time = 0;
        this.setAll = new ValueWithTime(null, -1);
    }


    /**
     * 是否存在键
     * @param key
     * @return
     */
    public boolean containsKey(K key){
        return map.containsKey(key);
    }


    /**
     * 放入键值对
     * @param key
     * @param value
     */
    public void put(K key, V value){
        map.put(key, new ValueWithTime<V>(value, time++));
    }


    /**
     * 将所有值都设置为value
     * @param value
     */
    public void setAll(V value){
        setAll = new ValueWithTime(value, time++);
    }

    public V get(K key){
        if (map.containsKey(key)){
            ValueWithTime<V> vValueWithTime = map.get(key);
            if (vValueWithTime.getTime() > setAll.getTime()){
                // 后面来的数据
                return vValueWithTime.getData();
            }else {
                // 之前的数据全部作废
                return setAll.getData();
            }
        }else {
            return null;
        }
    }



}
