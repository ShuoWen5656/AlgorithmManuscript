package classespackage.other;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2023/4/2 9:51
 * @Discreption <> 设计有setall功能的哈希表
 */
public class SetAllHashMapCp1<K, V> {

    /**
     * 维护一个hash用来存储数据
     */
    private Map<K, MyValue<V>> map;

    /**
     * 模拟时间戳
     * 只要当前存在值进来，就增加1,删除值不会减少
     */
    private Long time;

    /**
     * setall操作值
     */
    private MyValue<V> setAllValue;


    public SetAllHashMapCp1() {
        this.time = 0L;
        this.map = new HashMap<>(16);
    }

    /**
     * put操作
     * 正常put操作，增加一个时间戳就行了
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        this.map.put(key, new MyValue<>(this.time++, value));
    }


    /**
     * get操作
     * 取得时候需要比较一下setall的时间戳
     * 如果在时间戳前面，则返回setall的值
     * 如果在后面则返回原值
     * be careful null value!
     * @param key
     * @return
     */
    public V get(K key) {
        if (!isContainsKey(key)) {
            return null;
        }
        MyValue<V> vMyValue = map.get(key);
        return this.setAllValue == null
                || vMyValue.getTime() > this.setAllValue.getTime() ? vMyValue.getValue() : this.setAllValue.getValue();
    }

    /**
     * Provide a normal getting operation
     * @param key
     * @return
     */
    public V nomalGet(K key) {
        MyValue<V> vMyValue = map.get(key);
        return vMyValue == null ? null : vMyValue.getValue();
    }


    /**
     * 是否存在该key
     * @param key
     * @return
     */
    public boolean isContainsKey(K key) {
        return map.containsKey(key);
    }

    /**
     * setall功能
     * @param value
     */
    public void setAll(V value) {
        this.setAllValue = new MyValue<>(this.time++, value);
    }


    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
        SetAllHashMapCp1<String, String> mapCp1 = new SetAllHashMapCp1<>();
        mapCp1.put("1", "!!");
        mapCp1.put("2", "@@");
        mapCp1.setAll("9999999");
        mapCp1.put("33", "##");
        mapCp1.put("44", "$$");
    }


    /**
     * 维护一个value和当前value所带的时间戳
     * @param <V>
     */
    private class MyValue<V> {


        private Long time;

        private V value;

        public MyValue(Long time, V value) {
            this.time = time;
            this.value = value;
        }

        public Long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

}
