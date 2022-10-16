package classespackage;

import classespackage.tree.PrintTreeDirect;
import dataConstruct.DoubleNode;
import dataConstruct.LinkNode;
import dataConstruct.MyTreeNode;
import dataConstruct.MyTreeNodePlus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    /**
     * 交换两个元素
     * @param array
     * @param index1
     * @param index2
     * @param <T>
     */
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


    /**
     * 获取一个有负数的二叉树
     * @return
     */
    public static MyTreeNode getTreeForNextNode(){
        MyTreeNode myTreeNode6 = new MyTreeNode(6);
        MyTreeNode myTreeNode4 = new MyTreeNode(4);
        MyTreeNode myTreeNode7 = new MyTreeNode(7);
        MyTreeNode myTreeNode2 = new MyTreeNode(2);
        MyTreeNode myTreeNode5 = new MyTreeNode(5);
        MyTreeNode myTreeNode9 = new MyTreeNode(9);
        MyTreeNode myTreeNode1 = new MyTreeNode(1);
        MyTreeNode myTreeNode3 = new MyTreeNode(3);
        MyTreeNode myTreeNode8 = new MyTreeNode(8);
        MyTreeNode myTreeNode10 = new MyTreeNode(10);

        myTreeNode6.setLeft(myTreeNode3);
        myTreeNode3.setParent(myTreeNode6);
        myTreeNode6.setRight(myTreeNode9);
        myTreeNode9.setParent(myTreeNode6);
        myTreeNode3.setLeft(myTreeNode1);
        myTreeNode1.setParent(myTreeNode3);
        myTreeNode3.setRight(myTreeNode4);
        myTreeNode4.setParent(myTreeNode3);
        myTreeNode1.setRight(myTreeNode2);
        myTreeNode2.setParent(myTreeNode1);
        myTreeNode4.setRight(myTreeNode5);
        myTreeNode5.setParent(myTreeNode4);
        myTreeNode9.setLeft(myTreeNode8);
        myTreeNode8.setParent(myTreeNode9);
        myTreeNode9.setRight(myTreeNode10);
        myTreeNode10.setParent(myTreeNode9);
        myTreeNode8.setLeft(myTreeNode7);
        myTreeNode7.setParent(myTreeNode8);


        return myTreeNode6;
    }


    public static void main(String[] args) {
        MyTreeNode root = getSearchMyTreeNode();
        //MyTreeNode target = findFromTree(root, 9);


        Map<MyTreeNode, MyTreeNode> myTreeNodeMap = convertTree2Map(root);
        System.out.println(myTreeNodeMap);


        //MyTreeNode searchMyTreeNode = getSearchMyTreeNode();
        //int treeHeight = getTreeHeight(searchMyTreeNode, 0);
        //LinkNode linkNodeListByArr = getLinkNodeListByArr(new int[]{2, 4, 6, 8});
        //int linkNodeLenth = getLinkNodeLenth(linkNodeListByArr, null);
    }


    /**
     * 找到target并返回引用
     * 先序遍历
     * @param root
     * @param target
     * @return
     */
    public static MyTreeNode findFromTree(MyTreeNode root, int target){
        if (root == null){
            return null;
        }
        MyTreeNode cur = root;
        MyTreeNode targetT = null;
        while (cur != null){
            MyTreeNode left = cur.getLeft();
            if (left != null){
                while (left.getRight() != null && left.getRight() != cur){
                    left = left.getRight();
                }
                if (left.getRight() == null){
                    // 1
                    // 输出
                    if (cur.getData().equals(target)){
                        targetT = cur;
                    }
                    left.setRight(cur);
                    cur = cur.getLeft();
                    continue;
                }
                //3
                cur = left.getRight();
                left.setRight(null);
            }
            //2
            // 输出
            if (cur.getData().equals(target)){
                targetT = cur;
            }
            cur = cur.getRight();
        }
        return targetT;
    }


    /**
     * 将树转化为map
     * key为当前节点，value为当前节点的父节点
     * @param root
     * @return
     */
    public static Map<MyTreeNode, MyTreeNode> convertTree2Map(MyTreeNode root){
        if (root == null){
            return null;
        }

        Map<MyTreeNode, MyTreeNode> map = new HashMap<>();
        processForConvert(root, null, map);
        return map;
    }

    private static void processForConvert(MyTreeNode cur, MyTreeNode parent, Map<MyTreeNode, MyTreeNode> map) {
        if (cur == null){
            return;
        }
        map.putIfAbsent(cur, parent);
        processForConvert(cur.getLeft(), cur, map);
        processForConvert(cur.getRight(), cur, map);
    }


    /**
     * tarjan并查集结合算法所用树（后期通过先序和中序遍历形成树）
     * @return
     */
    public static MyTreeNode getTreeForTarjan(){
        //首先做一个12345
        MyTreeNode root = getCompleteBinaryTree(new int[]{1, 2, 3, 4, 5});
        // 3->6
        MyTreeNode node3 = root.getRight();
        node3.setRight(new MyTreeNode(6));
        MyTreeNode node6 = node3.getRight();
        node6.setLeft(new MyTreeNode(9));
        MyTreeNode node5 = root.getLeft().getRight();
        node5.setLeft(new MyTreeNode(7));
        node5.setRight(new MyTreeNode(8));

        return root;
    }


    /**
     * 可视化二叉树
     * @param root
     */
    public static void printTree(MyTreeNode root){
        PrintTreeDirect.myPrint(root);
    }


    /**
     * 通过先序遍历和中序遍历构建二叉树（可以满足子节点指向父节点的需求）
     * @param arrPre
     * @param arrMid
     * @param hasParent
     * @return
     */
    public static MyTreeNode buildTreeFromArr(int[] arrPre, int[] arrMid, boolean hasParent){
        if (arrPre == null || arrMid == null
                || arrPre.length != arrMid.length) {
            throw new RuntimeException("入参数组不合法");
        }
        return processForPreAndMid(arrPre, arrMid, 0, arrPre.length-1, 0, arrMid.length-1, hasParent);
    }

    /**
     * 先序和中序的递归方法(创建树的工具通用工具方法)
     * @param arrPre
     * @param arrMid
     * @param preStart
     * @param preEnd
     * @param midStart
     * @param midEnd
     * @return
     */
    private static MyTreeNode processForPreAndMid(int[] arrPre, int[] arrMid,
                                                  int preStart, int preEnd, int midStart, int midEnd,  boolean hasParent) {
        if (preStart > preEnd || midStart > midEnd){
            return null;
        }
        if (preStart == preEnd || midStart == midEnd){
            return new MyTreeNode(arrPre[preStart]);
        }
        // 当前根节点value
        int curValue = arrPre[preStart];
        MyTreeNode root = new MyTreeNode(curValue);
        // 当前层在中序遍历的index
        int curIndexInMid = findIndex(curValue, arrMid);
        // 右边子树最后一个节点，这里为了好区分就多了一个变量
        int lastRightIndexInPre = curIndexInMid - midStart + preStart;
        MyTreeNode left = processForPreAndMid(arrPre, arrMid, preStart + 1, lastRightIndexInPre, midStart, curIndexInMid-1, hasParent);
        MyTreeNode right = processForPreAndMid(arrPre, arrMid, lastRightIndexInPre + 1, preEnd, curIndexInMid + 1, midEnd, hasParent);
        root.setLeft(left);
        root.setRight(right);
        if (hasParent){
            // 子节点需要指向父母（特殊需求）
            if (left != null){
                left.setParent(root);
            }
            if (right != null){
                right.setParent(right);
            }
        }

        return root;
    }

    /**
     * 克隆一份树
     * @param root
     * @return
     */
    public static MyTreeNode cloneTree(MyTreeNode root){
        if (root == null){
            return null;
        }
        MyTreeNode newRoot = new MyTreeNode(root.getData());
        // 将左右子树clone一下
        newRoot.setLeft(cloneTree(root.getLeft()));
        newRoot.setRight(cloneTree(root.getRight()));
        return newRoot;
    }



    /**
     * 找到内容所在index
     * @param value
     * @param array
     * @return
     */
    public static int findIndex(int value, int[] array){
        for (int i = 0; i < array.length ; i++){
            if(array[i] == value){
                return i;
            }
        }
        return 0;
    }

    /**
     * 打印arr
     * @param arr
     */
    public static void printArr(int[] arr) {
        for (int a : arr){
            System.out.print(a);
        }
        System.out.println();
    }

    /**
     * 获取arr的两个边界值
     * [0]最小值、[1]最大值
     * @param arr
     * @return
     */
    public static int[] getBoundary(int[] arr) {
        if (arr == null){
            return arr;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int v : arr){
            if (v >= max){
                max = v;
            }
            if (v <= min){
                min  = v;
            }
        }
        return new int[]{min, max};
    }
}
