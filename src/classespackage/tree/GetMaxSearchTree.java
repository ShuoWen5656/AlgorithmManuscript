package classespackage.tree;

import classespackage.CommonUtils;
import dataConstruct.MyTreeNode;

/**
 * @author swzhao
 * @date 2022/3/27 10:35 上午
 * @Discreption <>找到二叉树中的最大搜索二叉子树
 */
public class GetMaxSearchTree {


    /**
     * 主入口
     * @param head
     * @return
     */
    public static MyTreeNode getMaxSearchTree(MyTreeNode head){
        try {
            // 定义一个全局变量存储返回值0:最大值1：最小值2：节点下最大搜索树的节点数量
            int[] record = new int[3];
            return getSearchTreeByHead(head, record);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 递归函数
     * 1、每一次递归需要搜集四个信息：
     *      · 该节点最大搜索树的头节点（通过下一次递归获取）
     *      · 该节点下的最大值 左子节点看这个
     *      · 该节点下的最小值 右子节点看这个
     *      · 该节点下最大搜索树的节点数量
     * 2、判断情况：
     *      · 情况一：左边最大搜索树头节点为左子节点，并且左边最大值小于当前值，右边相反，返回cur，并更新节点数量
     *      · 情况二：左边最大搜索树头节点不是左子节点，或者右边不满足条件，返回左右边节点数量较大的那个的结果
     * @param root
     * @param record
     * @return
     */
    public static MyTreeNode getSearchTreeByHead(MyTreeNode root, int[] record){
        if(root == null){
            // 最大值给最小，为了满足条件
            record[0] = Integer.MIN_VALUE;
            record[1] = Integer.MAX_VALUE;
            // 返回数量为0
            record[2] = 0;
            return null;
        }
        MyTreeNode left = root.getLeft();
        MyTreeNode right = root.getRight();
        Integer value = root.getData();
        // 左边的结果
        int[] leftRes = new int[3];
        // 右边的结果
        int[] rightRes = new int[3];
        // 获取左边的四个信息
        MyTreeNode leftSearchTree = getSearchTreeByHead(left, record);
        // 将全局变量复制一份
        leftRes[0] = record[0];
        leftRes[1] = record[1];
        leftRes[2] = record[2];
        MyTreeNode rightSearchTree = getSearchTreeByHead(right, record);
        rightRes[0] = record[0];
        rightRes[1] = record[1];
        rightRes[2] = record[2];
        // 判断情况
        if(leftSearchTree == left
                && leftRes[0] < value
                && rightSearchTree == right
                && rightRes[1] > value){
            // 符合情况一
            record[0] = rightRes[0];
            record[1] = leftRes[1];
            record[2] = leftRes[2] + rightRes[2] + 1;
            return root;
        }else{
            // 情况二：返回左边或者右边子树数目较大的那个
            if(leftRes[2] > rightRes[2]){
                // 左边大
                record = leftRes;
                return leftSearchTree;
            }else{
                // 右边大，或者相等
                record = rightRes;
                return rightSearchTree;
            }
        }
    }


    public static MyTreeNode getBST(MyTreeNode root){
        if (root == null){
            return null;
        }
        int[] record = new int[3];
        return process(root, record);
    }


    /**
     * 每一个节点遍历都要返回四个数据：
     * 1、以该节点为root的最大搜索二叉树节点的root节点
     * 2、搜索二叉子树节点数量
     * 3、搜索二叉子树的最大值
     * 4、搜索二叉子树的最小值
     * @param root
     * @param record
     * @return
     */
    private static MyTreeNode process(MyTreeNode root, int[] record) {
        if (root == null){
            record[0] = 0;
            record[1] = Integer.MIN_VALUE;
            record[2] = Integer.MAX_VALUE;
            return null;
        }
        MyTreeNode leftMaxRoot = process(root.getLeft(), record);
        int leftSize = record[0];
        int leftMax = record[1];
        int leftMin = record[2];
        MyTreeNode rightMaxRoot = process(root.getRight(), record);
        int rightSize = record[0];
        int rightMax = record[1];
        int rightMin = record[2];
        if (leftMaxRoot == root.getLeft() && rightMaxRoot == root.getRight()
                && leftMax < root.getData() && rightMin > root.getData()){
            // 自己就是一颗二叉树
            record[0] = leftSize + rightSize + 1;
            record[1] = rightMax == Integer.MIN_VALUE ? root.getData() : rightMax;
            record[2] = leftMin == Integer.MAX_VALUE ? root.getData() : leftMin;
            return root;
        }else {
            // 比较左边和右边哪个大
            if (leftSize >= rightSize){
                record[0] = leftSize;
                record[1] = leftMax;
                record[2] = leftMin;
                return leftMaxRoot;
            }else{
                record[0] = rightSize;
                record[1] = rightMax;
                record[2] = rightMin;
                return rightMaxRoot;
            }
        }
    }

    public static void main(String[] args) {
        MyTreeNode root = CommonUtils.getSearchMyTreeNode();
        MyTreeNode node1 = new MyTreeNode(1);
        MyTreeNode node9 = new MyTreeNode(9);
        node1.setRight(root);
        node1.setLeft(node9);
        PrintTreeDirect.myPrint(node1);
        System.out.print("\n\n\n\n\n\n\n\n\n");
        PrintTreeDirect.myPrint(getBST(node1));
    }



}
