package leetcode;

/**
 * @author swzhao
 * @date 2023/11/10 10:03 下午
 * @Discreption <> 翻转二叉树
 */
public class InvertTree {



    public TreeNode solution(TreeNode root) {
        return process(root);
    }

    private TreeNode process(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = process(right);
        root.right = process(left);;
        return root;
    }


    public class TreeNode {
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
