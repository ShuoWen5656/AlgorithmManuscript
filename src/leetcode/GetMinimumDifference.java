package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/14 9:32 下午
 * @Discreption <> 二叉搜索树的最小绝对差
 */
public class GetMinimumDifference {


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(239);
        treeNode.left = new TreeNode(104);
        treeNode.left.right = new TreeNode(227);
        treeNode.right = new TreeNode(701);
        treeNode.right.right = new TreeNode(911);
        solution(treeNode);

    }



    public static int solution(TreeNode root) {
        List<Integer> helper = new ArrayList<>();
        process(root, helper);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < helper.size(); i++) {
            res = Math.min(res, Math.abs(helper.get(i) - helper.get(i-1)));
        }
        return res;
    }

    private static void process(TreeNode root, List<Integer> helper) {
        if (root == null) {
            return;
        }
        process(root.left, helper);
        helper.add(root.val);
        process(root.right, helper);
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
