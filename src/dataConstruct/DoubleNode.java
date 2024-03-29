package dataConstruct;

/**
 * @author swzhao
 * @date 2021/12/18 9:59 下午
 * @Discreption <>双向链表
 */
public class DoubleNode {
    private String value;

    /**
     * 整数value
     */
    private Integer integerValue;

    /**
     * 下一个
     */
    private DoubleNode next;

    /**
     * 上一个
     */
    private DoubleNode last;



    public DoubleNode(Integer integer){
        this.integerValue = integer;
    }

    public DoubleNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DoubleNode getNext() {
        return next;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }

    public DoubleNode getLast() {
        return last;
    }

    public void setLast(DoubleNode last) {
        this.last = last;
    }

    public Integer getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(Integer integerValue) {
        this.integerValue = integerValue;
    }
}
