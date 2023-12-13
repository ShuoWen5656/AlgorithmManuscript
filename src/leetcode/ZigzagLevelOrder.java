package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/13 10:09 下午
 * @Discreption <> 二叉树的锯齿形层序遍历
 */
public class ZigzagLevelOrder {

    public static List<List<Integer>> solution(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();

        // <-
        boolean flag = true;
        List<Integer> tmpArray = new ArrayList<>();
        Deque<TreeNode> helper = new LinkedList<>();
        helper.addLast(root);
        TreeNode last = root;
        while (!helper.isEmpty()) {
            TreeNode curNode = flag ? helper.pollFirst() : helper.pollLast();
            tmpArray.add(curNode.val);
            if (flag) {
                if (curNode.left != null){
                    helper.addLast(curNode.left);
                }
                if (curNode.right != null) {
                    helper.addLast(curNode.right);
                }
            }else {
                if (curNode.right != null) {
                    helper.addFirst(curNode.right);
                }
                if (curNode.left != null){
                    helper.addFirst(curNode.left);
                }
            }
            if (curNode == last) {
                List<Integer> ints = new ArrayList<>();
                for (Integer i : tmpArray) {
                    ints.add(i);
                }
                res.add(ints);
                tmpArray.clear();
                // 更新last
                if (!helper.isEmpty()) {
                    last = flag ? helper.peekFirst() : helper.peekLast();
                }
                flag = !flag;
            }
        }
        return res;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    
}
