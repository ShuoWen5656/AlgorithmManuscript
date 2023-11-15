package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/14 9:56 下午
 * @Discreption <> 二叉搜索树中第K小的元素
 */
public class KthSmallest {


    public static int solution(TreeNode root, int k) {
        List<Integer> helper = new ArrayList<>();
        process(root, helper);
        return helper.get(k-1);
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
