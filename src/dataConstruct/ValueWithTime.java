package dataConstruct;

/**
 * @author swzhao
 * @data 2022/7/6 20:19
 * @Discreption <> 特殊数据结构：值带有time变量
 */
public class ValueWithTime<V> {

    private V data;
    private long time;

    public ValueWithTime(V data, long time) {
        this.data = data;
        this.time = time;
    }

    public V getData() {
        return data;
    }

    public long getTime() {
        return time;
    }
}
