package classespackage;

import dataConstruct.MyTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author swzhao
 * @date 2022/3/11 8:50 下午
 * @Discreption <>序列化二叉树和反序列化二叉树
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

}
