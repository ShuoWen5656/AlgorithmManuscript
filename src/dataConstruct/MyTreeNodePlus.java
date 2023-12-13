package dataConstruct;

/**
 * @author swzhao
 * @data 2022/7/23 20:07
 * @Discreption <> 二叉树数据结构：能够接受用户自定义类型
 */
public class MyTreeNodePlus<T> {

    /**
     * 二叉树中的值
     */
    private T value;
    /**
     * 右节点
     */
    private MyTreeNodePlus<T> right;
    /**
     * 左节点
     */
    private MyTreeNodePlus<T> left;
    /**
     * 父节点
     */
    private MyTreeNodePlus<T> parent;


    /**
     * 构造器只接受value参数
     * @param value
     */
    public MyTreeNodePlus(T value) {
        this.value = value;
    }


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public MyTreeNodePlus<T> getRight() {
        return right;
    }

    public void setRight(MyTreeNodePlus<T> right) {
        this.right = right;
    }

    public MyTreeNodePlus<T> getLeft() {
        return left;
    }

    public void setLeft(MyTreeNodePlus<T> left) {
        this.left = left;
    }

    public MyTreeNodePlus<T> getParent() {
        return parent;
    }

    public void setParent(MyTreeNodePlus<T> parent) {
        this.parent = parent;
    }


}
