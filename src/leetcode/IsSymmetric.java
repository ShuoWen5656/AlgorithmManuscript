package leetcode;

/**
 * @author swzhao
 * @date 2023/11/10 10:07 下午
 * @Discreption <> 对称二叉树
 */
public class IsSymmetric {


    public boolean solution(TreeNode root) {
        return process(root.left, root.right);
    }

    private boolean process(TreeNode p, TreeNode q) {
        if (q == null && p == null) {
            return true;
        }else if ((p == null && q != null) || (p != null && q == null) ||  p.val != q.val){
            return false;
        }
        return process(p.left, q.right) && process(p.right, q.left);
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
