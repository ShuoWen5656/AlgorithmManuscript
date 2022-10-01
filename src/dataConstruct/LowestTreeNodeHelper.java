package dataConstruct;

import classespackage.stackAndQueue.catDogQueue.Pet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2022/10/1 11:43
 * @Discreption <> 在二叉树中找到两个节点的最近公共祖先（方法三）
 * 工具类
 */
public class LowestTreeNodeHelper {


    /**
     * key 任意树中节点
     * value 当前节点到任意节点的最近公共祖先
     */
    private Map<MyTreeNode, Map<MyTreeNode, MyTreeNode>> map;


    private MyTreeNode head;

    /**
     * 给定树后开始初始化操作
     * @param head
     */
    public LowestTreeNodeHelper(MyTreeNode head) {
        this.map = new HashMap<>();
        this.head = head;
        initMap();
        fillMap();
    }




    /**
     * 将数据结构分配内存
     */
    private void initMap() {
        if (head == null){
            return;
        }
        initProcess(head);
    }

    private void initProcess(MyTreeNode head) {
        if (head == null){
            return;
        }
        map.putIfAbsent(head, new HashMap<>());
        initProcess(head.getLeft());
        initProcess(head.getRight());
    }

    /**
     * 计算所有节点之间的最近公共祖先
     * 1、每一个节点与自己的子节点公共祖先都是自己
     * 2、当前节点的左右子节点中的每一对子节点的祖先都是自己
     */
    private void fillMap() {
        // 先序遍历处理每一个子节点
        fill(this.head);
    }

    /**
     * 先序遍历处理每一个子节点
     * @param head
     */
    private void fill(MyTreeNode head) {
        if (head == null){
            return;
        }
        // 1、将当前节点作为头结点,每一个子节点和当前头结点的公共节点都是当前头结点
        initHead(head, head.getLeft());
        initHead(head, head.getRight());

        // 2、将当前节点的左节点和右节点之间进行遍历，公共祖先都是当前节点
        // ** 这里只需要一边就行，put的时候将另一边的也put进来
        initChild(head, head.getLeft(), head.getRight());

        // 处理左右节点
        fill(head.getLeft());
        fill(head.getRight());
        //initChild(head, head.getRight(), head.getLeft());
    }

    /**
     * 将head以及head的子节点的左右子树填充
     * @param head
     * @param left
     * @param right
     */
    private void initChild(MyTreeNode head, MyTreeNode left, MyTreeNode right) {
        if (head == null){
            return;
        }
        if (left != null){
            // 将head的左子树中所有节点丰富起来
            initEdge(head, left, right);
            // 遍历左边的每一个节点，将右边的每一个节点都写入map
            initChild(head, left.getLeft(), right);
            initChild(head, left.getRight(), right);
            //initChild(left, left.getLeft(), left.getRight());
        }
        // 右子节点就不用了，因为左边填充时，右边也是相互的
    }

    /**
     * 初始化左右子树之间
     * 遍历other
     * @param head 公共祖先
     * @param cur
     * @param other
     */
    private void initEdge(MyTreeNode head, MyTreeNode cur, MyTreeNode other){
        if (other == null){
            return;
        }
        if (map.get(cur) != null && map.get(other) != null){
            Map<MyTreeNode, MyTreeNode> curMap = map.get(cur);
            Map<MyTreeNode, MyTreeNode> otherMap = map.get(other);
            curMap.put(other, head);
            otherMap.put(cur,head);
        }
        initEdge(head, cur, other.getLeft());
        initEdge(head, cur, other.getRight());
    }



    private void initHead(MyTreeNode head, MyTreeNode child) {
        if (child == null){
            return;
        }
        Map<MyTreeNode, MyTreeNode> childMap = map.get(child);
        Map<MyTreeNode, MyTreeNode> headMap = map.get(head);
        // 孩子节点
        childMap.put(head, head);
        headMap.put(child, head);
        initHead(head, child.getLeft());
        initHead(head, child.getRight());
    }

    /**
     * 获取r1和r2的公共祖先
     * @param r1
     * @param r2
     * @return
     */
    public MyTreeNode getLowestAncestor(MyTreeNode r1, MyTreeNode r2){
        if (!map.containsKey(r1) || !map.containsKey(r2)){
            return null;
        }
        Map<MyTreeNode, MyTreeNode> r1Map = map.get(r1);
        return r1Map.get(r2);
    }

}
