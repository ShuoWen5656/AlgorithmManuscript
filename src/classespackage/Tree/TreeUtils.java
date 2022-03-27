package classespackage.Tree;

import dataConstruct.MyTreeNode;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @author swzhao
 * @date 2022/3/7 9:49 下午
 * @Discreption <>树工具类，用来遍历和一些常规操作
 */
public class TreeUtils {

    /**
     * 先序遍历，注：这里的先后是根的遍历顺序
     * @param root
     * @param nodeList 结果放进nodeList
     */
    public static void preOrderRecur(MyTreeNode root, List<MyTreeNode> nodeList){
        try {
            if(root == null){
                return;
            }
            nodeList.add(root);
            preOrderRecur(root.getLeft(), nodeList);
            preOrderRecur(root.getRight(), nodeList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 中序遍历
     * @param root
     * @param nodeList 结果放进nodeList
     */
    public static void inOrderRecur(MyTreeNode root, List<MyTreeNode> nodeList){
        try {
            if(root == null){
                return;
            }
            inOrderRecur(root.getLeft(), nodeList);
            nodeList.add(root);
            inOrderRecur(root.getRight(), nodeList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 后序遍历
     * @param root
     * @param nodeList 结果放进nodeList
     */
    public static void posOrderRecur(MyTreeNode root, List<MyTreeNode> nodeList){
        try {
            if(root == null){
                return;
            }
            posOrderRecur(root.getLeft(), nodeList);
            posOrderRecur(root.getRight(), nodeList);
            nodeList.add(root);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // ====================使用递归方式实现的都可以通过栈方式实现===================

    /**
     * 利用栈结构实现先序遍历
     * 1、顶节点进
     * 2、循环弹出栈顶节点，加入nodeList，将右边孩子先放入栈，再将左孩子放入栈
     * 3、只要栈不为空，继续遍历
     * @param root
     * @param nodeList
     */
    public static void preOrderRecurByStack(MyTreeNode root, List<MyTreeNode> nodeList){
        try {
            Stack<MyTreeNode> temNodes = new Stack<>();
            // 根放入
            temNodes.push(root);
            while (!temNodes.isEmpty()){
                // 弹出栈顶
                MyTreeNode cur = temNodes.pop();
                nodeList.add(cur);
                if(cur.getRight() != null){
                    temNodes.push(cur.getRight());
                }
                if(cur.getLeft() != null){
                    temNodes.push(cur.getLeft());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 利用栈结构实现中序遍历
     * 1、首先将root的左子节点和左子节点的子节点。。。都放进去。
     * 2、弹出栈顶，查询当前节点的右子节点是否null，如果不是null，则继续将右子节点的左子节点和左子节点的子节点。。。都放进去
     * 3、重复第二步。
     * @param root
     * @param nodeList
     */
    public static void inOrderRecurByStack(MyTreeNode root, List<MyTreeNode> nodeList){
        try {
            Stack<MyTreeNode> temNodes = new Stack<>();
            // 将左子节点和左子节点的子节点都放进去
            putNodeIntoLeft(root, temNodes);
            while (!temNodes.isEmpty()){
                // 弹出栈顶
                MyTreeNode cur = temNodes.pop();
                nodeList.add(cur);
                if(cur.getRight() != null){
                    putNodeIntoLeft(cur.getRight(), temNodes);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 将左子节的左子节点的左子节点全部放入stack
     * @param root
     * @param stack
     */
    public static void putNodeIntoLeft(MyTreeNode root, Stack<MyTreeNode> stack){
        try{
            if(root == null){
                return;
            }
            MyTreeNode cur = root;
            while (cur != null){
                stack.push(cur);
                cur = cur.getLeft();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 1、双栈结构，root弹出时 放入s2，s1进root的右节点和左节点
     * 2、直到S1为空停止，S2顺序为后续遍历
     * @param root
     * @param nodeList
     */
    public static void posOrderRecurByStack(MyTreeNode root, List<MyTreeNode> nodeList){
        try {
            Stack<MyTreeNode> nodeStack1 = new Stack<>();
            Stack<MyTreeNode> nodeStack2 = new Stack<>();
            nodeStack1.push(root);
            while (!nodeStack1.isEmpty()){
                MyTreeNode cur = nodeStack1.pop();
                MyTreeNode left = cur.getLeft();
                MyTreeNode right = cur.getRight();
                if(left != null){
                    nodeStack1.push(right);
                }
                if(right != null){
                    nodeStack1.push(right);
                }
                nodeStack2.push(cur);
            }
            Iterator<MyTreeNode> iterator = nodeStack2.iterator();
            while (iterator.hasNext()){
                nodeList.add(iterator.next());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 使用中右左的方式存入栈中，再返回回来
     * @param root
     * @param nodeList
     */
    public static void posOrderRecurByStack2(MyTreeNode root, List<MyTreeNode> nodeList){
        try {
            Stack<MyTreeNode> nodeStack1 = new Stack<>();
            nodeStack1.push(root);
            while (!nodeStack1.isEmpty()){
                MyTreeNode cur = nodeStack1.pop();
                // 放入右节点
                nodeList.add(cur);
                if(cur.getLeft() != null){
                    nodeStack1.push(cur.getLeft());
                }
                if(cur.getRight() != null){
                    nodeStack1.push(cur.getRight());
                }
            }
            Collections.reverse(nodeList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
