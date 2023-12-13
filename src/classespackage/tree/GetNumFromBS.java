package classespackage.tree;

import classespackage.CommonUtils;
import dataConstruct.MyTreeNode;

/**
 * @author swzhao
 * @date 2022/4/16 9:54 上午
 * @Discreption <> 统计完全二叉树的节点数，要求时间复杂度低于O（n），n为二叉树节点
 */
public class GetNumFromBS {


    /**
     * 快速求完全二叉树节点数
     * @param root
     * @return
     */
    public static int getNumFromBs(MyTreeNode root){
        if(root == null){
            return 0;
        }
        int height = getHeight(root);
        // 优化：直接进递归，从第一层
//        MyTreeNode right = root.getRight();
        return getNumByRecur(root, 1, height);
        // 找左节点的最左节点和右节点的最左节点，先找右节点的最左节点
//        MyTreeNode right = root.getRight();
//        MyTreeNode tem = right;
//        int level = 1;
//        while (tem != null){
//            level++;
//            tem = tem.getLeft();
//        }
//        if(level == height){
//            // 右边达到最深，左边应该是完全二叉树,左边直接计算出来，右边递归
//            return (1 << (height -1)) + getNumByRecur(right, 1, height);
//        }else{
//            // 右边是完全二叉树, 左边递归
//            return (1 << (height - 2)) + getNumByRecur(root.getLeft(), 1, height);
//        }
    }


    /**
     * 通过递归遍历树的节点，遍历高度为height
     * @param root
     * @param level
     * @param height
     * @return
     */
    public static int getNumByRecur(MyTreeNode root, int level, int height){
        if(root == null || level > height){
            return 0;
        }
        MyTreeNode right = root.getRight();
        MyTreeNode tem = right;
        while (tem != null){
            level++;
            tem = tem.getLeft();
        }
        if(level == height){
            return (1 << (height - 1)) + getNumByRecur(root.getRight(), level + 1, height);
        }else{
            return getNumByRecur(root.getLeft(), level + 1, height) + (1 << (height - 2));
        }
    }

    public static int getHeight(MyTreeNode root){
        if(root == null){
            return 0;
        }
        int left = getHeight(root.getLeft());
        int right = getHeight(root.getRight());
        return Math.max(left, right) + 1;
    }



    public static int myGetNum(MyTreeNode root){
        if (root == null){
            return 0;
        }
        return process(root, 0, getHeight(root));
    }

    private static int process(MyTreeNode root, int level, int height) {
        if (root == null){
            // 当前子树有0个节点
            return 0;
        }
        // 判断左子树中的最右边是否能够达到最底层
        MyTreeNode left = root.getLeft();
        int levelCp = level + 1;
        while (left != null){
            levelCp ++;
            left = left.getRight();
        }
        if (levelCp == height){
            // 左子树是满二叉树
            return (1 << (height - level - 1)) - 1 + process(root.getRight(), level + 1, height) + 1;
        }else {
            // 右子树是满二叉树
            return process(root.getLeft(), level + 1, height) + (1 << (height - level - 2)) - 1 + 1;
        }
    }

    public static void main(String[] args) {
        MyTreeNode root = CommonUtils.getCompleteBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        int num = myGetNum(root);
        System.out.println(num);


    }


}
