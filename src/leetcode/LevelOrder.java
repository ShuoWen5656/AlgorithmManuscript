package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/13 10:01 下午
 * @Discreption <> 二叉树的层序遍历
 */
public class LevelOrder {



    public static List<List<Integer>> solution(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> tmpArray = new ArrayList<>();
        Deque<TreeNode> helper = new LinkedList<>();
        helper.addLast(root);
        TreeNode last = root;
        while (!helper.isEmpty()) {
            TreeNode curNode = helper.pollFirst();
            tmpArray.add(curNode.val);
            if (curNode.left != null){
                helper.addLast(curNode.left);
            }
            if (curNode.right != null) {
                helper.addLast(curNode.right);
            }
            if (curNode == last) {
                List<Integer> ints = new ArrayList<>();
                for (Integer i : tmpArray) {
                    ints.add(i);
                }
                res.add(ints);
                tmpArray.clear();
                // 计算平均值
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
