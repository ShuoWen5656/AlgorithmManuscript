package dataConstruct;

/**
 * @author swzhao
 * @data 2022/6/21 22:47
 * @Discreption <> 数据结构：堆节点
 */
public class HeapNode {

    /**
     * 节点值
     */
    int value;
    /**
     * 所在数组Id
     */
    int arrayID;
    /**
     * 在所在数组的第几个地方
     */
    int index;



    public HeapNode(int value, int arrayID, int index) {
        this.value = value;
        this.arrayID = arrayID;
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getArrayID() {
        return arrayID;
    }

    public void setArrayID(int arrayID) {
        this.arrayID = arrayID;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
