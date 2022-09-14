package classespackage.tree;

import classespackage.CommonUtils;
import classespackage.Constants;
import dataConstruct.MyTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author swzhao
 * @date 2022/3/11 8:50 下午
 * @Discreption <>二叉树的序列化和反序列化
 */
public class SerialTree {


    /**
     * 方法一：
     * 1、通过先序遍历，将null值变成 #！，将其他值变成data！
     * @param root
     * @return
     */
    public static String serial1(MyTreeNode root){
        if(root == null){
            return "#!";
        }
        Integer integer = root.getData();
        String ls = serial1(root.getLeft());
        String rs = serial1(root.getRight());
        return integer + "!" + ls + rs;
    }


    /**
     * 方法一的反序列化
     * @param serialStr
     * @return
     */
    public static MyTreeNode unserial1(String serialStr){
        // 变数组
        String[] split = serialStr.split("!");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < split.length; i++){
            queue.offer(split[i]);
        }
        return unserialByQueue(queue);
    }

    /**
     * 反序列化树结构
     * @param queue
     * @return
     */
    public static MyTreeNode unserialByQueue(Queue<String> queue){
        String str = queue.poll();
        if (Constants.END_MARK_OF_TREE.equals(str)){
            return null;
        }
        MyTreeNode myTreeNode = new MyTreeNode(Integer.valueOf(str));
        myTreeNode.setLeft(unserialByQueue(queue));
        myTreeNode.setRight(unserialByQueue(queue));
        return myTreeNode;
    }


    /**
     * 序列化方法二
     * 堆遍历放入str中
     * @param root
     * @return
     */
    public static String serial2(MyTreeNode root){
        if(root == null){
            return "#!";
        }
        String result = "";
        Queue<MyTreeNode> queue = new LinkedList<>();
        // 先放个头节点
        queue.offer(root);
        while (!queue.isEmpty()){
            MyTreeNode poll = queue.poll();
            MyTreeNode left = poll.getLeft();
            MyTreeNode right = poll.getRight();
            result += poll.getData() + "!";
            if(left == null){
                result += "#!";
            }else{
                queue.offer(left);
            }
            if(right == null){
                result += "#!";
            }else{
                queue.offer(right);
            }
        }
        return result;
    }


    /**
     * 反序列化方法2
     * 堆遍历
     * @param serialStr
     * @return
     */
    public static MyTreeNode unserial2(String serialStr){
        String[] split = serialStr.split("!");
        int index = 0;
        Queue<MyTreeNode> myTreeNodes = new LinkedList<>();
        // 头节点先进去
        MyTreeNode treeNode = getTreeNode(split[index++]);
        if(treeNode == null){
            return null;
        }
        myTreeNodes.offer(treeNode);
        while (!myTreeNodes.isEmpty()){
            MyTreeNode poll = myTreeNodes.poll();
            poll.setLeft(getTreeNode(split[index++]));
            poll.setRight(getTreeNode(split[index++]));
            if(poll.getLeft() != null){
                myTreeNodes.offer(poll.getLeft());
            }
            if(poll.getRight() != null){
                myTreeNodes.offer(poll.getRight());
            }
        }
        return treeNode;
    }

    public static MyTreeNode getTreeNode(String s){
        if(s == null){
            return null;
        }
        return new MyTreeNode(Integer.valueOf(s));
    }



    public static String mySerialize(MyTreeNode root){
        if (root == null){
            return null;
        }
        return mySerial(root);
    }

    private static String mySerial(MyTreeNode root) {
        if (root == null){
            return "#!";
        }
        String str = Constants.EMPTY_STR;
        Integer data = root.getData();
        str += String.format("%d!", data);
        str += mySerial(root.getLeft());
        str += mySerial(root.getRight());
        return str;
    }



    public static MyTreeNode unSerial(String serialStr){
        String[] strs = serialStr.split("!");
        int[] record = new int[1];
        record[0] = 0;
        MyTreeNode root = myUnSerial(strs, record);
        return root;
    }

    private static MyTreeNode myUnSerial(String[] strs, int[] record) {
        if (record[0] == strs.length){
            return null;
        }
        String cur = strs[record[0]];
        if ("#".equals(cur)){
            return null;
        }
        MyTreeNode myTreeNode = new MyTreeNode(Integer.parseInt(cur));
        record[0]++;
        MyTreeNode left = myUnSerial(strs, record);
        record[0]++;
        MyTreeNode right = myUnSerial(strs, record);
        myTreeNode.setLeft(left);
        myTreeNode.setRight(right);
        return myTreeNode;
    }



    public static String mySerialize2(MyTreeNode root){
        Queue<MyTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        String res = Constants.EMPTY_STR;
        while (!queue.isEmpty()){
            MyTreeNode cur = queue.poll();
            if (cur == null){
                res += "#!";
            }else {
                res += cur.getData()+"!";
                queue.offer(cur.getLeft());
                queue.offer(cur.getRight());
            }
        }
        return res;
    }






    public static MyTreeNode unSerial2(String serialStr){
        String[] str = serialStr.split("!");
        Queue<MyTreeNode> queue = new LinkedList<>();
        int[] record = new int[1];
        record[0] = 0;
        String rootData = str[record[0]++];
        MyTreeNode root = new MyTreeNode(Integer.valueOf(rootData));
        queue.offer(root);
        while (!queue.isEmpty()){
            String curData1 = str[record[0]++];
            String curData2 = str[record[0]++];
            MyTreeNode curNode = queue.poll();
            MyTreeNode data1Node;
            MyTreeNode data2Node;
            if ("#".equals(curData1)){
                data1Node = null;
            }else {
                data1Node = new MyTreeNode(Integer.parseInt(curData1));
                queue.offer(data1Node);
            }
            if ("#".equals(curData2)){
                data2Node = null;
            }else {
                data2Node = new MyTreeNode(Integer.parseInt(curData2));
                queue.offer(data2Node);
            }
            curNode.setLeft(data1Node);
            curNode.setRight(data2Node);
        }
        return root;
    }

    public static void main(String[] args) {
        MyTreeNode head = CommonUtils.getSearchMyTreeNode();

        String s = mySerialize2(head);

        System.out.println(s);

        MyTreeNode myTreeNode = unSerial2(s);
        PrintTreeDirect.myPrint(myTreeNode);

    }

}
