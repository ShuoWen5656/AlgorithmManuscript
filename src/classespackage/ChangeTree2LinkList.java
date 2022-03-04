package classespackage;

import dataConstruct.DoubleNode;
import dataConstruct.LinkNode;
import dataConstruct.MyTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author swzhao
 * @date 2022/3/4 9:34 下午
 * @Discreption <>将搜索二叉树转化为双向链表
 */
public class ChangeTree2LinkList {


    /**
     * 搜索二叉数转为双向链表
     * 解法一：中序遍历二叉树，放入容器中，再弹出制作节点即可。
     * @param root
     * @return
     */
    public static DoubleNode convert(MyTreeNode root){
        try {
            Queue<MyTreeNode> treeNodes = new LinkedList<>();
            inOrderToQueue(root, treeNodes);
            DoubleNode per = null;
            DoubleNode head = null;
            while (!treeNodes.isEmpty()){
                MyTreeNode curNode = treeNodes.poll();
                if(per == null){
                    // 头节点
                    DoubleNode node = new DoubleNode(String.valueOf(curNode.getData()));
                    per = node;
                    head = node;
                    continue;
                }
                // 当前节点互指
                DoubleNode node = new DoubleNode(String.valueOf(curNode.getData()));
                node.setLast(per);
                per.setNext(node);
                // per往下走一个
                per = node;
            }
            return head;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 先序遍历treenodes - 左中右,按照我的理解，将每一个叶子节点的子节点null作为子节点，当子节点为null，返回即可
     * @param root
     * @param treeNodes
     */
    public static void inOrderToQueue(MyTreeNode root, Queue<MyTreeNode> treeNodes){
        if(root == null){
            return;
        }
        // 先遍历左边的
        inOrderToQueue(root.getLeft(), treeNodes);
        // 遍历中间
        treeNodes.offer(root);
        inOrderToQueue(root.getRight(), treeNodes);
    }
}
