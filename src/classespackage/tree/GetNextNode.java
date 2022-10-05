package classespackage.tree;

import classespackage.CommonUtils;
import dataConstruct.MyTreeNode;


/**
 * @author swzhao
 * @date 2022/4/10 12:52 下午
 * @Discreption <>在二叉树中找到一个节点的后继节点（二叉树的中序遍历的下一个节点为后继节点）
 * 一种简单的方式从该节点找到根节点后直接进行中序遍历后从数组中找到该节点的下一个节点即可，时间O（n）空间O(n)
 */
public class GetNextNode {

    /**
     * 进化解法：如果当前节点和后续节点在树中的距离为l，则时间和空间为O(L)
     * 1、给定节点node，node有右孩子，则后继节点为右孩子的最左节点
     * 2、如果node没有右孩子，则判断node是否时左子节点，如果是，则父节点为后继节点
     * 3、如果不是左孩子，则循环网上找父节点，直到找到一个父节点是左孩子节点的时候，当前节点为后继节点
     * @param root
     */
    public static MyTreeNode getNextNode(MyTreeNode root){
        try {
            if (root == null){
                return null;
            }
            if(root.getRight() != null){
                // 有右孩子，从右孩子开始找到最左边的节点
                MyTreeNode res = root.getRight();
                while (res.getLeft() != null){
                    res = res.getLeft();
                }
                return res;
            }else if(root.getParent() == null){
                // 没有右子节点的根节点么有后续节点
                return null;
            }else if(root.getParent().getLeft() == root){
                // 自己是左子节点
                return root.getParent();
            }else{
                // 自己是右子节点的，循环往上找到左子节点
                MyTreeNode res = root.getParent();
                while (res.getParent()!= null && res.getParent().getLeft() != res){
                    // 父节点为null的为根节点，不是null的左孩子又不是自己的，就继续往上找
                    res = res.getParent();
                }
                return res;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取后继节点
     * @param node
     * @return
     */
    public static MyTreeNode getNextNodeCP(MyTreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.getRight() != null) {
            // 有右子树的情况,找到右子树的最左节点
            MyTreeNode right = node.getRight();
            while (right.getLeft() != null) {
                right = right.getLeft();
            }
            return right;
        } else {
            // 无右子树情况
            MyTreeNode parent = node.getParent();
            if (parent == null) {
                // node 为根节点
                return null;
            } else {
                if (node == parent.getLeft()) {
                    // 自己是左子树
                    return parent;
                } else {
                    // 自己是右子树,找到第一个左子树parent或者找到根节点
                    while (parent != null) {
                        MyTreeNode parent1 = parent.getParent();
                        if (parent1 != null && parent == parent1.getLeft()){
                            return parent1;
                        }else {
                            parent = parent1;
                        }
                    }
                    return null;
                }
            }
        }
    }


    public static void main(String[] args) {
        MyTreeNode root = CommonUtils.getTreeForNextNode();
        MyTreeNode node5 = CommonUtils.findFromTree(root, 10);
        MyTreeNode nextNodeCP = getNextNodeCP(node5);
        System.out.println(nextNodeCP.getData());

    }


}
