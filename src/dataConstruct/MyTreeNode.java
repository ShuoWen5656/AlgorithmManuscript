package dataConstruct;

/**
 * @author swzhao
 * @date 2021/12/2 9:52 下午
 * @Discreption <>公共数据结构：二叉树节点
 */
public class MyTreeNode {

    /**
     * 左节点
     */
    public MyTreeNode left;
    /**
     * 右节点
     */
    public MyTreeNode right;
    /**
     * 节点值
     */
    public Integer data;

    /**
     * 二叉树的父级节点（正常二叉树没有这个变量，此变量为了特殊数据结构而设计）
     */
    public MyTreeNode parent;

    public MyTreeNode(Integer data) {
        this.data = data;
    }

    public MyTreeNode getLeft() {
        return left;
    }

    public void setLeft(MyTreeNode left) {
        this.left = left;
    }

    public MyTreeNode getRight() {
        return right;
    }

    public void setRight(MyTreeNode right) {
        this.right = right;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public MyTreeNode getParent() {
        return parent;
    }

    public void setParent(MyTreeNode parent) {
        this.parent = parent;
    }
}
