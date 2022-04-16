package classespackage.tree;

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

}
