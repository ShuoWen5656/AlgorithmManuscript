package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/13 9:30 下午
 * @Discreption <> 二叉树的右视图
 */
public class RightSideView {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        solution(root);


    }

    public static List<Integer> solution(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> helper = new LinkedList<>();
        helper.addLast(root);
        TreeNode last = root;
        while (!helper.isEmpty()) {
            TreeNode curNode = helper.pollFirst();
            if (curNode.left != null){
                helper.addLast(curNode.left);
            }
            if (curNode.right != null) {
                helper.addLast(curNode.right);
            }
            if (curNode == last) {
                res.add(curNode.val);
                if (!helper.isEmpty()) {
                    last = helper.peekLast();
                }
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
