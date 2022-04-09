package classespackage.tree;

import dataConstruct.MyTreeNode;

/**
 * @author swzhao
 * @date 2022/4/7 10:43 下午
 * @Discreption <>判断二叉树是否为平衡二叉树
 */
public class IsBalance {

    /**
     * 主方法
     * 1、容器array，长度1，保存是否是平衡二叉树
     * 2、左子树判断是否是平衡二叉树，右子树判断是否是平衡二叉树，两边做减法，如果大于1直接返回false，如果小于1，返回较大的那个
     * @param root
     * @return
     */
    public static boolean isBalance(MyTreeNode root){
        try{
            boolean[] res = new boolean[1];
            res[0] = true;
            getHeight(root, 1, res);
            return res[0];
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 获取二叉树的高度
     * 通过后续遍历收集左子节点和右子节点的高度做减法即可
     * 每次返回后先判断res是否还是true，如果不是直接返回，如果是，再判断右边，如果都是则再进行减法
     * @param root
     * @param level
     * @param res
     * @return
     */
    public static int getHeight(MyTreeNode root, int level, boolean[] res){
        if(root == null){
            return level;
        }
        int leftH = getHeight(root.getLeft(), level + 1, res);
        if(!res[0]){
            // 左子树不是，返回即可
            return level;
        }
        int rightH = getHeight(root.getRight(), level + 1, res);
        if(!res[0]){
            // 右子树不是平衡树，返回即可
            return level;
        }
        // 到这里两个都是平衡树，则判断当前节点是否是平衡树
        if(Math.abs(leftH - rightH) > 1){
            res[0] = false;
        }
        // 不管是否是平衡树，都返回最大的那个即可
        return Math.max(leftH, rightH);
    }

}
