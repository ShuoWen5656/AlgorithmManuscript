package dataConstruct;

/**
 * @author swzhao
 * @data 2022/7/8 19:55
 * @Discreption <> 加强版双端队列节点
 * 能够放入泛型
 */
public class DoubleNodePlus<V> {

    /**
     * 可以放任何数值
     */
    private V value;
    /**
     * 下一个
     */
    private DoubleNodePlus next;
    /**
     * 上一个
     */
    private DoubleNodePlus last;


    public DoubleNodePlus(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public DoubleNodePlus getNext() {
        return next;
    }

    public void setNext(DoubleNodePlus next) {
        this.next = next;
    }

    public DoubleNodePlus getLast() {
        return last;
    }

    public void setLast(DoubleNodePlus last) {
        this.last = last;
    }
}
