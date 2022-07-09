package dataConstruct;

/**
 * @author swzhao
 * @data 2022/7/8 19:58
 * @Discreption <> 双端队列数据结构：维护一个双端队列
 * 能够添加和删除节点，并且能够让任意节点能够移动到队尾，用于LRU算法数据结构
 */
public class NodeDoubleLinkedList<V> {


    /**
     * 双端队列的头节点
     */
    private DoubleNodePlus<V> head;
    /**
     * 双端队列的尾节点
     */
    private DoubleNodePlus<V> tail;


    /**
     * 直接实例化即可
     */
    public NodeDoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }


    /**
     * 添加一个节点到尾部
     * @param node
     */
    public void add(DoubleNodePlus node){
        if (node == null){
            return;
        }
        if (this.head == null){
            // 第一个节点
            this.head = node;
            this.tail = node;
        }else {
            node.setLast(this.tail);
            this.tail.setNext(node);
            this.tail = node;
        }
    }

    /**
     * 将当前节点放进tail中
     * @param node
     */
    public void moveToTail(DoubleNodePlus node){

        if (node == this.tail || node == null){
            return;
        }
        if (node == head){
            // head后移
            this.head = node.getNext();
            this.head.setLast(null);
        }else {
            // 将node剔除出来
            node.getLast().setNext(node.getNext());
            node.getNext().setLast(node.getLast());
        }
        // node尾巴清理干净
        node.setNext(null);
        // 加入尾巴
        add(node);
    }


    /**
     * 将头部移除
     * @return
     */
    public DoubleNodePlus<V> removeHead(){
        DoubleNodePlus res = this.head;
        if (this.head == null){
            return null;
        }
        if (this.head == this.tail){
            // 就一个元素
            this.head = null;
            this.tail = null;
        }else {
            // 移除头结点
            this.head = this.head.getNext();
            this.head.setLast(null);
            res.setNext(null);
        }
        return res;
    }

}
