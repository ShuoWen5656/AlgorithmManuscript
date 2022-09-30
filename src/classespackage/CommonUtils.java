package classespackage;

import classespackage.tree.PrintTreeDirect;
import dataConstruct.DoubleNode;
import dataConstruct.LinkNode;
import dataConstruct.MyTreeNode;
import dataConstruct.MyTreeNodePlus;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author swzhao
 * @Date 2022/2/13 21:02
 * @Discription<>公共方法
 */
public class CommonUtils<T> {

    /**
     * 交换1、2索引值
     * @param array
     * @param index1
     * @param index2
     */
    public static void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * 获取中间值，如果为偶数，则取前一个
     * @param left
     * @param right
     */
    public static int getMid(int left, int right){
        return (left + right) /2;
    }



    public static <T> void swapPlus(T[] array, int index1, int index2){
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static int getMaxValue(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++){
            max = Math.max(max, arr[i]);
        }
        return max;
    }


    /**
     * 形成一个单链表返回头节点
     * @param arr
     * @return
     */
    public static LinkNode getLinkNodeListByArr(int[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }
        LinkNode head = new LinkNode(arr[0]);
        LinkNode cur = head;
        for (int i = 1; i < arr.length; i++){
            cur.setNext(new LinkNode(arr[i]));
            cur = cur.getNext();
        }
        return head;
    }

    /**
     * 形成一个双向链表返回
     * @param arr
     * @return
     */
    public static DoubleNode getDoubleNodeListByArr(int[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }
        DoubleNode head = new DoubleNode(String.valueOf(arr[0]));
        DoubleNode cur = head;
        for (int i = 1; i < arr.length; i++){
            DoubleNode newNode = new DoubleNode(String.valueOf(arr[i]));
            cur.setNext(newNode);
            newNode.setLast(cur);
            cur = cur.getNext();
        }
        return head;
    }


    /**
     * 打印单链表结构
     * @param linkNode
     */
    public static void printLinkNode(LinkNode linkNode){
        LinkNode cur = linkNode;
        while (cur != null){
            System.out.println(cur.getValue());
            cur = cur.getNext();
        }
    }


    /**
     * 获取单链表的长度
     * @param head
     * @param loop 成环的话 成环点
     * @return
     */
    public static int getLinkNodeLenth(LinkNode head, LinkNode loop){
        int len = 0;
        LinkNode cur =  head;
        // 第一次遇到入环点
        boolean isFirst = true;
        if (loop == null){
            while (cur != null){
                len++;
                cur = cur.getNext();
            }
        }else {
            while (cur != loop || isFirst){
                if (cur == loop){
                    isFirst = false;
                }
                len++;
                cur = cur.getNext();
            }
        }
        return len;
    }

    /**
     * 根据value获取第一个node
     * @return
     */
    public static LinkNode findFirstNodeByValue(LinkNode head ,int value){
        LinkNode cur = head;
        while (cur != null){
            if (value == cur.getValue()){
                return cur;
            }
            cur = cur.getNext();
        }
        return null;
    }


    /**
     * 堆遍历正序的树
     * @return
     */
    public static MyTreeNode getTreeForEdge(){
        MyTreeNode[] myTreeNodes = new MyTreeNode[17];
        for (int i = 1; i < myTreeNodes.length; i++){
            MyTreeNode myTreeNode = new MyTreeNode(i);
            myTreeNodes[i] = myTreeNode;
        }

        myTreeNodes[1].setLeft(myTreeNodes[2]);
        myTreeNodes[1].setRight(myTreeNodes[3]);
        myTreeNodes[2].setLeft(myTreeNodes[4]);
        myTreeNodes[3].setLeft(myTreeNodes[5]);
        myTreeNodes[3].setRight(myTreeNodes[6]);
        myTreeNodes[4].setLeft(myTreeNodes[7]);
        myTreeNodes[4].setRight(myTreeNodes[8]);
        myTreeNodes[5].setLeft(myTreeNodes[9]);
        myTreeNodes[5].setRight(myTreeNodes[10]);
        myTreeNodes[8].setRight(myTreeNodes[11]);
        myTreeNodes[9].setLeft(myTreeNodes[12]);
        myTreeNodes[11].setLeft(myTreeNodes[13]);
        myTreeNodes[11].setRight(myTreeNodes[14]);
        myTreeNodes[12].setLeft(myTreeNodes[15]);
        myTreeNodes[12].setRight(myTreeNodes[16]);

        return myTreeNodes[1];

    }


    /**
     *  按照arr的顺序形成一个完全二叉树
     * @return
     */
    public static MyTreeNode getCompleteBinaryTree(int[] arr){
        if (arr == null){
            return null;
        }
        List<MyTreeNode> treeList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++){
            treeList.add(new MyTreeNode(arr[i]));
        }
        MyTreeNode head = null;
        for (int i = 0; i < treeList.size(); i++){
            MyTreeNode cur = treeList.get(i);
            if (i == 0){
                head = cur;
            }
            int leftIndex = 2*i + 1;
            int rightIndex = 2*i + 2;
            if (leftIndex < treeList.size()){
                MyTreeNode left = treeList.get(leftIndex);
                cur.setLeft(left);
            }
            if (rightIndex < treeList.size()){
                MyTreeNode right = treeList.get(rightIndex);
                cur.setRight(right);
            }
        }
        return head;
    }

    /**
     * 获取树当前节点所在高度
     * @param root
     * @param curLevel
     * @return
     */
    public static int getTreeHeight(MyTreeNode root, int curLevel){
        if (root == null){
            return curLevel;
        }else {
            return Math.max(getTreeHeight(root.getLeft(), curLevel+1), getTreeHeight(root.getRight(), curLevel+1));
        }
    }



    /**
     * 获取一个搜索二叉树
     * @return
     */
    public static MyTreeNode getSearchMyTreeNode(){
        MyTreeNode myTreeNode6 = new MyTreeNode(6);
        MyTreeNode myTreeNode4 = new MyTreeNode(4);
        MyTreeNode myTreeNode7 = new MyTreeNode(7);
        MyTreeNode myTreeNode2 = new MyTreeNode(2);
        MyTreeNode myTreeNode5 = new MyTreeNode(5);
        MyTreeNode myTreeNode9 = new MyTreeNode(9);
        MyTreeNode myTreeNode1 = new MyTreeNode(1);
        MyTreeNode myTreeNode3 = new MyTreeNode(3);
        MyTreeNode myTreeNode8 = new MyTreeNode(8);

        myTreeNode6.setLeft(myTreeNode4);
        myTreeNode6.setRight(myTreeNode7);
        myTreeNode4.setLeft(myTreeNode2);
        myTreeNode4.setRight(myTreeNode5);
        myTreeNode7.setRight(myTreeNode9);
        myTreeNode2.setLeft(myTreeNode1);
        myTreeNode2.setRight(myTreeNode3);
        myTreeNode9.setLeft(myTreeNode8);

        return myTreeNode6;
    }
    /**
     * 获取一个有负数的二叉树
     * @return
     */
    public static MyTreeNode getTreeForSum(){
        MyTreeNode myTreeNode_3 = new MyTreeNode(-3);
        MyTreeNode myTreeNode3 = new MyTreeNode(3);
        MyTreeNode myTreeNode_9 = new MyTreeNode(-9);
        MyTreeNode myTreeNode1 = new MyTreeNode(1);
        MyTreeNode myTreeNode0 = new MyTreeNode(0);
        MyTreeNode myTreeNode2 = new MyTreeNode(2);
        MyTreeNode myTreeNode11 = new MyTreeNode(1);
        MyTreeNode myTreeNode111 = new MyTreeNode(1);
        MyTreeNode myTreeNode6 = new MyTreeNode(6);

        myTreeNode_3.setLeft(myTreeNode3);
        myTreeNode_3.setRight(myTreeNode_9);

        myTreeNode3.setLeft(myTreeNode1);
        myTreeNode3.setRight(myTreeNode0);

        myTreeNode_9.setLeft(myTreeNode2);
        myTreeNode_9.setRight(myTreeNode11);
        myTreeNode0.setLeft(myTreeNode111);
        myTreeNode0.setRight(myTreeNode6);
        return myTreeNode_3;
    }

    public static void main(String[] args) {
        MyTreeNode searchMyTreeNode = getSearchMyTreeNode();
        int treeHeight = getTreeHeight(searchMyTreeNode, 0);
        //LinkNode linkNodeListByArr = getLinkNodeListByArr(new int[]{2, 4, 6, 8});
        //int linkNodeLenth = getLinkNodeLenth(linkNodeListByArr, null);
    }

    /**
     * 可视化二叉树
     * @param root
     */
    public static void printTree(MyTreeNode root){
        PrintTreeDirect.myPrint(root);
    }


}
