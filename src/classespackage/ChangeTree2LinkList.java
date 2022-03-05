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
     * 方法2，时间复杂O(N)空间O（h），h为树的高度
     * @param root
     * @return 这里返回值不是双向链表结构，但是MytreeNode结构left指向前一个节点，right指向后一个节点
     */
    public static MyTreeNode convert2(MyTreeNode root){
        if (root == null){
            return null;
        }
        MyTreeNode tail = convert2DoubleNode(root);
        MyTreeNode head = tail.getRight();
        head.setRight(null);
        return head;
    }


    /**
     * 递归转换root为双端二叉树
     * @param root
     * @return 返回双向链表的尾巴结点，并且尾巴结点的next指向头结点，便于链接，后续需要调整成前一个节点
     */
    public static MyTreeNode convert2DoubleNode(MyTreeNode root){
        if(root == null){
            return null;
        }
        // 获取子节点转换后的尾巴结点
        MyTreeNode leftTailNode = convert2DoubleNode(root.getLeft());
        MyTreeNode rightTailNode = convert2DoubleNode(root.getRight());
        // 获取两边的头结点
        MyTreeNode leftHeadNode = leftTailNode == null? null : leftTailNode.getRight();
        MyTreeNode rightHeadNode = rightTailNode == null? null : rightTailNode.getRight();
        if(leftTailNode != null && rightTailNode != null){
            // 两边链接
            leftTailNode.setRight(root);
            root.setLeft(leftTailNode);
            root.setRight(rightHeadNode);
            rightHeadNode.setLeft(root);
            rightTailNode.setRight(leftHeadNode);
            return rightTailNode;
        }else if (leftTailNode != null){
            // 右边是null，只连左边
            leftTailNode.setRight(root);
            root.setLeft(leftTailNode);
            // 现在root是尾巴
            root.setRight(leftHeadNode);
            return root;
        }else if (rightTailNode != null){
            // 左边是null
            rightTailNode.setRight(root);
            root.setRight(rightHeadNode);
            rightHeadNode.setLeft(root);
            return rightTailNode;
        }else{
            root.setRight(root);
            return root;
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
