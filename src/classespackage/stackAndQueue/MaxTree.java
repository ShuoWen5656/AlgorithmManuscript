package classespackage.stackAndQueue;

import dataConstruct.MyTreeNode;
import dataConstruct.MyTreeNodePlus;

import java.util.*;

/**
 * @author swzhao
 * @date 2021/12/2 9:47 下午
 * @Discreption <>构造数组的MaxTree：
 * 1、数组不能有重复元素
 * 2、树是二叉树，
 * 3、值最大的节点是树头
 * 时间On 空间On
 * 方法：
 * 1、每一个数的左边第一个比他大的和右边第一个比他大的，取较小的那个作为他的父节点
 * 2、第一个比他大的使用栈可以一次遍历获取，每一个数都pop栈直到比栈底空或者比自己大时，栈顶为自己的某一边第一个最大值
 */
public class MaxTree {


    /**
     * 辅助栈
     */
    private Stack<MyTreeNode> stackHelper;
    /**
     * 左边第一个比自己小的节点映射
     */
    private Map<MyTreeNode, MyTreeNode> leftMap;
    /**
     * 右边第一个比自己小的节点映射
     */
    private Map<MyTreeNode, MyTreeNode> rightMap;

    public MaxTree() {
        this.stackHelper = new Stack<>();
        this.leftMap = new HashMap<>();
        this.rightMap = new HashMap<>();
    }

    /**
     * 获取最大值树节点
     * @param arrays
     * @return
     */
    public MyTreeNode getMaxTree(Integer[] arrays){
        try{
            List<MyTreeNode> myTreeNodes = new ArrayList<>();
            for (Integer num : arrays){
                myTreeNodes.add(new MyTreeNode(num));
            }
            fillMap(myTreeNodes, "left");
            fillMap(myTreeNodes, "right");
            // 通过map比较得出最后的树结构
            MyTreeNode head = null;
            for (MyTreeNode myTreeNode : myTreeNodes){
                MyTreeNode left = leftMap.get(myTreeNode);
                MyTreeNode right = rightMap.get(myTreeNode);
                if(left == null && right == null){
                    // 都是null，则最大值出现
                    head = myTreeNode;
                    continue;
                }else if(left == null){
                    // 右边是父节点,查看父节点哪边有空位
                    if(right.getLeft() == null){
                        right.setLeft(myTreeNode);
                    }else{
                        right.setRight(myTreeNode);
                    }
                }else if(right == null){
                    if(left.getLeft() == null){
                        left.setLeft(myTreeNode);
                    }else{
                        left.setRight(myTreeNode);
                    }
                }else{
                    // 都不是null,则比较小的那个作为父节点
                    if(left.getData() < right.getData()){
                        if(left.getLeft() == null){
                            left.setLeft(myTreeNode);
                        }else{
                            left.setRight(myTreeNode);
                        }
                    }else{
                        if(right.getLeft() == null){
                            right.setLeft(myTreeNode);
                        }else{
                            right.setRight(myTreeNode);
                        }
                    }

                }
            }
            return head;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 填装map
     */
    private void fillMap(List<MyTreeNode> myTreeNodes, String type) throws Exception{
        Map<MyTreeNode, MyTreeNode> map = new HashMap<>();
        // 首先遍历全部节点，转成node,顺便获取两个map
        if("left".equals(type)){
            map = leftMap;
            for (MyTreeNode node : myTreeNodes){
                // 循环挖比自己小的值，直到比自己大的值出现(其实就是自己左边第一个比自己大的值)，跳出循环
                while (!stackHelper.isEmpty() && node.getData() > stackHelper.peek().getData()){
                    // 挖出栈顶并且当前栈顶的左边第一个最大值就是下一个栈顶
                    poPAndPutMap(stackHelper, map);
                }
                //直到比自己大的值出现了
                stackHelper.push(node);
            }
        }else{
            map = rightMap;
            for (int i = myTreeNodes.size()-1; i >= 0; i--){
                MyTreeNode node = myTreeNodes.get(i);
                while (!stackHelper.isEmpty() && node.getData() > stackHelper.peek().getData()){
                    // 挖出栈顶并且当前栈顶的左边第一个最大值就是下一个栈顶
                    poPAndPutMap(stackHelper, map);
                }
                //直到比自己大的值出现了
                stackHelper.push(node);
            }
        }
        // 到这里栈里面维护了从顶到底 从小到大的顺序，或者为空，这时遍历栈
        while (!stackHelper.isEmpty()){
            poPAndPutMap(stackHelper, map);
        }
    }

    /**
     * 弹出顶并将下个顶放入map中
     * @param stackHelper
     * @param map
     */
    private void poPAndPutMap(Stack<MyTreeNode> stackHelper, Map<MyTreeNode, MyTreeNode> map) throws Exception{
        MyTreeNode node = stackHelper.pop();
        if(stackHelper.isEmpty()){
            map.put(node, null);
        }else{
            map.put(node, stackHelper.peek());
        }

    }


    public static MyTreeNode getMytreeNode(int[] arr){
        MyTreeNode[] myTreeNodes = new MyTreeNode[arr.length];
        MyTreeNode head = null;
        Stack<MyTreeNode> stack = new Stack<>();
        Map<MyTreeNode, MyTreeNode> treeNodeMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++){
            myTreeNodes[i] = new MyTreeNode(arr[i]);
            treeNodeMap.put(myTreeNodes[i], new MyTreeNode(-1));
            while (!stack.isEmpty() && arr[i] > stack.peek().getData()){
                stack.pop();
            }
            if (stack.isEmpty()){
                treeNodeMap.get(myTreeNodes[i]).setLeft(null);
            }else {
                treeNodeMap.get(myTreeNodes[i]).setLeft(stack.peek());
            }
            stack.push(myTreeNodes[i]);
        }
        stack.clear();
        for (int i = arr.length-1; i >= 0; i--){
            MyTreeNode cur = myTreeNodes[i];
            while (!stack.isEmpty() && cur.getData() > stack.peek().getData()){
                stack.pop();
            }
            if (stack.isEmpty()){
                treeNodeMap.get(cur).setRight(null);
            }else{
                treeNodeMap.get(cur).setRight(stack.peek());
            }
            stack.push(cur);
        }

        for (MyTreeNode myTreeNode : treeNodeMap.keySet()){
            MyTreeNode leftAndRight = treeNodeMap.get(myTreeNode);
            MyTreeNode left = leftAndRight.getLeft();
            MyTreeNode right = leftAndRight.getRight();
            if (left == null && right == null){
                head = myTreeNode;
            }else if (left == null || right == null){
                MyTreeNode parent = left == null? right : left;
                if (parent.getLeft() == null){
                    parent.setLeft(myTreeNode);
                }else {
                    parent.setRight(myTreeNode);
                }
            }else {
                MyTreeNode parent = left.getData() > right.getData() ? right : left;
                if (parent.getLeft() == null){
                    parent.setLeft(myTreeNode);
                }else {
                    parent.setRight(myTreeNode);
                }
            }
        }
        return head;
    }


    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
        // 详解版
        MaxTree maxTree = new MaxTree();
        MyTreeNode maxTree1 = maxTree.getMaxTree(new Integer[]{3, 4, 5, 1, 2});
        printNode(maxTree1);

        //// 简化版
        //MyTreeNode mytreeNode = getMytreeNode(new int[]{3, 4, 5, 1, 2});
        //// 遍历二叉树
        //printNode(mytreeNode);
    }

    private static void printNode(MyTreeNode mytreeNode) {
        if (mytreeNode == null){
            return;
        }
        System.out.println(mytreeNode.getData());
        printNode(mytreeNode.getLeft());
        printNode(mytreeNode.getRight());
    }


}
