package classespackage.tree;

import classespackage.CommonUtils;
import dataConstruct.MyTreeNode;

/**
 * @author swzhao
 * @date 2022/4/13 9:34 上午
 * @Discreption <>二叉树节点间的最大距离问题
 */
public class MaxDistance {

    /**
     * 主方法
     * @return
     */
    public static int getMaxDistance(MyTreeNode root){
        try{
            // 第一个变量保存子节点的最长长度，第二个变量保存到子节点的最长长度
            int[] record = new int[1];
            return postOrder(root, record);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 1、后续遍历
     * 每一次遍历返回两个信息
     * 1、子节点的最长节点长度（返回值）
     * 2、到子节点的最长长度（深度）
     * @param root
     * @param record
     * @return
     */
    public static int postOrder(MyTreeNode root, int[] record){
        if(root == null){
            record[0] = 0;
            return 0;
        }
        int lmax = postOrder(root.getLeft(), record);
        int maxFromLeft = record[0];
        int rmax = postOrder(root.getRight(), record);
        int maxFromRight = record[0];
        int curMax = maxFromLeft + 1 + maxFromRight;
        record[0] = Math.max(maxFromLeft, maxFromRight) + 1;
        return Math.max(Math.max(lmax, rmax), curMax);
    }


    /**
     * 主方法
     * @param root
     * @return
     */
    public static int getMaxPath(MyTreeNode root){
        if (root == null){
            return 0;
        }
        return myProcess(root, 0)[0];
    }

    /**
     *
     * @param root
     * @param level
     * @return int[0] 当前树的最大距离，int[1] 当前树的最大深度
     */
    private static int[] myProcess(MyTreeNode root, int level) {
        if (root == null){
            // 最大距离为0， 当前深度为0
            return new int[]{-1, 0};
        }
        // 先获取左右子树的最大距离和深度
        int[] leftR = myProcess(root.getLeft(), level + 1);
        int leftMax = leftR[0];
        int leftH = leftR[1];
        int[] rightR = myProcess(root.getRight(), level + 1);
        int rightMax = rightR[0];
        int rightH = rightR[1];
        // 开始计算当前节点的最大距离
        int curMax = Math.max(Math.max(leftMax, rightMax), leftH + rightH + 1);
        // 计算当前层深度
        int curH = Math.max(leftH, rightH) + 1;
        return new int[]{curMax, curH};
    }


    public static void main(String[] args) {
        MyTreeNode root = CommonUtils.getCompleteBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        CommonUtils.printTree(root);

        System.out.print("\n\n\n\n\n\n\n\n\n\n");
        int maxPath = getMaxPath(root);
        System.out.println(maxPath);
    }

}
