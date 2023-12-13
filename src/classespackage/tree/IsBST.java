package classespackage.tree;

import classespackage.CommonUtils;
import dataConstruct.MyTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author swzhao
 * @date 2022/4/9 9:51 上午
 * @Discreption <>判断一棵二叉树是否为搜索二叉树和完全二叉树
 */
public class IsBST {

    /**
     * 判断一棵树是否是搜索二叉树
     * 1、用任意一种中序遍历遍历树为有序的即可
     * 这里用morris算法
     * @param root
     */
    public static boolean isBST(MyTreeNode root){
        try {
            if(root == null){
                return true;
            }
            boolean res = true;
            MyTreeNode cur1 = root;
            MyTreeNode cur2 = null;
            // pre为中序遍历顺序的前一个节点，永远比后一个节点要小才行
            MyTreeNode pre = null;
            while (cur1 != null){
                cur2 = cur1.getLeft();
                if(cur2 != null){
                    // 寻找cur1的左子树的最右边节点
                    while (cur2.getRight() != cur1 && cur2.getRight() != null){
                        cur2 = cur2.getRight();
                    }
                    // 第一阶段
                    if(cur2.getRight() == null){
                        cur2.setRight(cur1);
                        cur1 = cur1.getLeft();
                        continue;
                    }else{
                        // 第二阶段,恢复二叉树
                        cur2.setRight(null);
                    }
                }
                // 能进到这里的：第一阶段cur2为null了，cur1为最左边的节点了；第二阶段的每次左子树遍历完毕后到这里返回
                if(pre != null && pre.getData() > cur1.getData()){
                    res = false;
                }
                pre = cur1;
                cur1 = cur1.getRight();
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断是否是完全二叉树
     * 1、层序遍历树
     * 2、判断每一个树如果只有右子树没有左孩子，则返回false，
     * 2、判断每一个树如果只有左孩子没有右孩子，则其后面的树都必须是叶子节点
     * @param root
     * @return
     */
    public static boolean isCBT(MyTreeNode root){
        Queue<MyTreeNode> queue = new LinkedList<>();
        // 是否遍历到了叶子节点
        boolean leaf = false;
        queue.offer(root);
        while (!queue.isEmpty()){
            MyTreeNode poll = queue.poll();
            MyTreeNode left = poll.getLeft();
            MyTreeNode right = poll.getRight();
            if(leaf){
                if(left != null || right != null){
                    return false;
                }
            }else{
                if(left == null && right != null){
                    return false;
                }else if((left != null && right == null) || (left == null && right == null)){
                    // 叶子节点或者已经到最后一个有子节点的节点了
                    leaf = true;
                }
            }
        }
        return true;
    }


    /**
     * 判断是否是搜索二叉树
     * @param root
     * @return
     */
    public static boolean myIsBST(MyTreeNode root){
        if (root == null){
            return true;
        }
        MyTreeNode cur = root;
        int last = Integer.MIN_VALUE;
        while (cur != null){
            MyTreeNode left = cur.getLeft();
            if (left != null){
                while (left.getRight() != null && left.getRight() != cur){
                    left = left.getRight();
                }
                if (left.getRight() == null){
                    left.setRight(cur);
                    cur = cur.getLeft();
                    continue;
                }
                if (cur.getData() < last){
                    return false;
                }else {
                    last = cur.getData();
                }
                System.out.println(cur.getData());
                cur = cur.getRight();
                left.setRight(null);
            }else{
                if (cur.getData() < last){
                    return false;
                }else {
                    last = cur.getData();
                }
                System.out.println(cur.getData());
                cur = cur.getRight();
            }
        }
        return true;
    }

    /**
     * 判断是否是完全二叉树
     * @param root
     * @return
     */
    public static boolean myIsCBT(MyTreeNode root){
        if (root == null){
            return true;
        }
        boolean isLeft = false;
        Queue<MyTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            MyTreeNode poll = queue.poll();
            if (poll.getLeft() == null && poll.getRight() != null){
                // 没有左孩子但是有右孩子的直接返回false
                return false;
            }
            if (isLeft){
                // 当前节点只能是叶子节点
                if (poll.getLeft() != null || poll.getRight() != null){
                    return false;
                }
            }else {
                if (poll.getLeft() == null || poll.getRight() == null){
                    isLeft = true;
                }
            }
            if (poll.getLeft() != null){
                queue.offer(poll.getLeft());
            }
            if (poll.getRight() != null){
                queue.offer(poll.getRight());
            }
        }
        return true;
    }






    public static void main(String[] args) {
        //MyTreeNode root = CommonUtils.getTreeForEdge();
        //
        //CommonUtils.printTree(root);
        //
        //
        //System.out.println(myIsBST(root));


        MyTreeNode root = CommonUtils.getCompleteBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});


        CommonUtils.printTree(root);

        System.out.println(isCBT(root));



    }


}
