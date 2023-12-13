package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/14 9:59 下午
 * @Discreption <>
 */
public class IsValidBST {

    public static boolean solution(TreeNode root, int k) {
        List<Integer> helper = new ArrayList<>();
        process(root, helper);
        for (int i = 1; i < helper.size(); i++) {
            if (helper.get(i) <= helper.get(i-1)) {
                return false;
            }
        }
        return true;
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
