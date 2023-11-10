package leetcode;

/**
 * @author swzhao
 * @date 2023/11/10 9:56 下午
 * @Discreption <> 相同的树
 */
public class IsSameTree {





    public boolean solution(TreeNode p, TreeNode q) {
        return process(p, q);
    }

    private boolean process(TreeNode p, TreeNode q) {
        if (q == null && p == null) {
            return true;
        }else if ((p == null && q != null) || (p != null && q == null) ||  p.val != q.val){
            return false;
        }
        return process(p.left, q.left) && process(p.right, q.right);
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
