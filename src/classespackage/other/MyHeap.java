package classespackage.other;

import dataConstruct.MyTreeNode;
import dataConstruct.MyTreeNodePlus;

import java.util.Comparator;

/**
 * @author swzhao
 * @data 2022/7/23 19:45
 * @Discreption <> 设计一个没有扩容负担的堆结构
 */
public class MyHeap<K> {

    /**
     * 头节点
     */
    private MyTreeNodePlus<K> head;
    /**
     * 二叉树最后一个节点
     */
    private MyTreeNodePlus<K> tail;
    /**
     * 二叉树的大小
     */
    private int size;
    /**
     * 比较器，用户自定义比较方式
     */
    private Comparator<K> comp;


    /**
     * 构造器只接收比较方法(可以做大根堆或者小根堆)，元素需要通过add方法添加
     * @param comp
     */
    public MyHeap(Comparator<K> comp) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.comp = comp;
    }

    public K getHead(){
        return head == null ? null : head.getValue();
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 添加一个值到堆中
     * @param value
     */
    public void add(K value){
        MyTreeNodePlus<K> newNode = new MyTreeNodePlus<>(value);
        if (size == 0){
            // 第一个node
            this.head = newNode;
            this.tail = newNode;
            size = 1;
            return;
        }
        MyTreeNodePlus<K> node = this.tail;
        MyTreeNodePlus<K> parent = node.getParent();
        // parent不是null并且当前node是左孩子
        while (parent != null && node != parent.getLeft()){
            node = parent;
            parent = parent.getParent();
        }
        // 到这里node的parent要么是null要么node是左孩子
        MyTreeNodePlus<K> nodeToAdd = null;
        if (parent == null){
            // 这时候是完全满二叉树，应该另起一行
            nodeToAdd = mostLeft(head);
            nodeToAdd.setLeft(newNode);
            newNode.setParent(nodeToAdd);
        }else if (parent.getRight() == null){
            // 说明右边空位可以放newnode
            parent.setRight(newNode);
            newNode.setParent(parent);
        }else {
            // 这种需要夸祖先节点添加
            // 获取右孩子的最左边节点
            nodeToAdd = mostLeft(node.getRight());
            nodeToAdd.setLeft(newNode);
            newNode.setParent(nodeToAdd);
        }
        this.tail = newNode;
        // 进行堆调整
        heapInsertModify();
        size++;
    }

    /**
     * 对堆新增值进行调整，小值往上升
     */
    private void heapInsertModify() {
        MyTreeNodePlus<K> node = tail;
        MyTreeNodePlus<K> parent = node.getParent();
        if (parent != null && comp.compare(node.getValue(), parent.getValue()) < 0){
            // 这里是第一波交换，tail需要变成第一波交换的那个值
            tail = parent;
        }
        // 上浮过程,node和parent开始交换
        while (parent != null && comp.compare(node.getValue(), parent.getValue()) < 0){
            swapClosedTwoNodes(parent, node);
            parent = node.getParent();
        }
        // 查看是否替换了头结点
        if (head.getParent() != null){
            // 头结点需要更新
            this.head = head.getParent();
        }
        // 查看是否尾结点需要交换
    }

    /**
     * 交换相邻的两个节点
     * 简单的方法可以交换数字直接
     * @param parent
     * @param node
     */
    private void swapClosedTwoNodes(MyTreeNodePlus<K> parent, MyTreeNodePlus<K> node) {
        if (parent == null || node == null){
            return;
        }
        // 按照每一个节点三个变量都要考虑,left,right,parent
        MyTreeNodePlus<K> pp = parent.getParent();
        MyTreeNodePlus<K> pl = parent.getLeft();
        MyTreeNodePlus<K> pr = parent.getRight();
        MyTreeNodePlus<K> nl = node.getLeft();
        MyTreeNodePlus<K> nr = node.getRight();
        node.setParent(pp);
        if (pp != null){
            if (pp.getLeft() == parent){
                pp.setLeft(node);
            }else {
                pp.setRight(node);
            }
        }
        // node和par互换
        parent.setParent(node);
        if (node == parent.getLeft()){
            node.setLeft(parent);
            node.setRight(pr);
            if (pr != null){
                pr.setParent(node);
            }
        }else {
            node.setRight(parent);
            node.setLeft(pl);
            if (pl != null){
                pl.setParent(node);
            }
        }
        if (nl != null){
            nl.setParent(parent);
        }
        if (nr != null){
            nr.setParent(parent);
        }
        // node的孩子给parent
        parent.setLeft(nl);
        parent.setRight(nr);
    }

    /**
     * 获取当前节点的最左边节点
     * @param node
     * @return
     */
    private MyTreeNodePlus<K> mostLeft(MyTreeNodePlus<K> node) {
        while (node.getLeft() != null){
            node = node.getLeft();
        }
        return node;
    }


    /**
     * 弹出一个值
     * @return
     */
    public K popHead(){
        if (size == 0){
            return null;
        }
        // 弹出头结点
        MyTreeNodePlus<K> res = this.head;
        if (size == 1){
            this.head = null;
            this.tail = null;
            size = 0;
            return res.getValue();
        }
        // 老尾巴节点，应该作为新的头结点
        MyTreeNodePlus<K> oldTail = popLastAndSetPreviousLast();
        if (size == 1){
            // 弹出后只有一个节点了
            this.head = oldTail;
            this.tail = oldTail;
            return res.getValue();
        }
        // 将oldtail作为新的头结点
        MyTreeNodePlus<K> left =  this.head.getLeft();
        MyTreeNodePlus<K> right = this.head.getRight();
        this.head.setLeft(null);
        this.head.setRight(null);
        oldTail.setRight(right);
        if (right != null){
            right.setParent(oldTail);
        }
        oldTail.setLeft(left);
        if (left != null){
            left.setParent(oldTail);
        }
        heapify(oldTail);
        return res.getValue();
    }

    private void heapify(MyTreeNodePlus<K> oldTail) {
        MyTreeNodePlus<K> left = oldTail.getLeft();
        MyTreeNodePlus<K> right = oldTail.getRight();
        MyTreeNodePlus<K> most = oldTail;
        while (left != null){
            if (left != null && comp.compare(left.getValue(), most.getValue()) < 0){
                // 左边需要上浮
                most = left;
            }
            if (right != null && comp.compare(right.getValue(), most.getValue()) < 0){
                // 说明右边更小
                most = right;
            }
            // 交换
            if (most != oldTail){
                swapClosedTwoNodes(oldTail, most);
            }else {
                break;
            }
            left = oldTail.getLeft();
            right = oldTail.getRight();
            most = oldTail;
        }
        // 交换完毕
        if (oldTail.getParent() == tail){
            // 尾巴被交换需要更新尾巴
            tail = oldTail;
        }
        // 获取头结点
        while (oldTail.getParent() != null){
            oldTail = oldTail.getParent();
        }
        // 新头结点
        this.head = oldTail;
    }


    /**
     * 弹出队头节点并设置tail为新的队头节点
     * @return
     */
    private MyTreeNodePlus<K> popLastAndSetPreviousLast() {
        MyTreeNodePlus<K> node = this.tail;
        MyTreeNodePlus<K> parent = node.getParent();
        // 找到前一个节点作为新的last
        // 首先找到parent为null的或者是右节点的
        while (parent != null && node != parent.getLeft()){
            node = parent;
            parent = parent.getParent();
        }
        // 到这里说明node是head或者node是右节点
        MyTreeNodePlus<K> newTail = null;
        if (parent == null){
            // head，这种情况node是这层唯一一个节点
            newTail = mostRight(head);
            this.tail = newTail;
            // 最后一个节点切出来
            node = this.tail;
            parent = node.getParent();
            node.setParent(null);
            if (node == parent.getLeft()){
                parent.setLeft(null);
            }else {
                parent.setRight(null);
            }
        }else {
            // parent左孩子的最右边节点
            newTail = mostRight(parent.getLeft());
            // 切掉最后的孩子
            node = tail;
            parent = node.getParent();
            node.setParent(null);
            if (node == parent.getLeft()){
                parent.setLeft(null);
            }else{
                parent.setRight(null);
            }
        }
        size--;
        return node;
    }

    /**
     * 最右节点
     * @param node
     * @return
     */
    private MyTreeNodePlus<K> mostRight(MyTreeNodePlus<K> node) {
        while (node.getRight() != null){
            node = node.getRight();
        }
        return node;
    }


}
