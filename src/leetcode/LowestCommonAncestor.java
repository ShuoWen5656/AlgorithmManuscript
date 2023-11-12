package leetcode;

/**
 * @author swzhao
 * @date 2023/11/12 12:50 下午
 * @Discreption <> 二叉树的最近公共祖先
 */
public class LowestCommonAncestor {





    public TreeNode solution(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode[] treeNodes = new TreeNode[1];
        process(root, p, q, treeNodes);
        return treeNodes[0];
    }

    private boolean[] process(TreeNode root, TreeNode p, TreeNode q, TreeNode[] treeNodes) {
        if (root == null) {
            return new boolean[]{false, false};
        }
        boolean[] res = new boolean[2];
        // 左边
        boolean[] process = process(root.left, p, q, treeNodes);
        boolean[] process1 = process(root.right, p, q, treeNodes);
        res[0] = process[0] || process1[0] || (p == root);
        res[1] = process[1] || process1[1] || (q == root);
        if (treeNodes[0] != null) {
            return res;
        }
        if (res[0] && res[1]) {
            treeNodes[0] = root;
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
