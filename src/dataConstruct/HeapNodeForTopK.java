package dataConstruct;

/**
 * @author swzhao
 * @data 2022/7/27 19:47
 * @Discreption <> 用于topk问题的heapNode
 */
public class HeapNodeForTopK {

    /**
     * 值
     */
    private int value;
    /**
     * 所在第几行，arr1的index
     */
    private int row;
    /**
     * arr2的index
     */
    private int col;

    public HeapNodeForTopK(int value, int row, int col) {
        this.value = value;
        this.row = row;
        this.col = col;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
