package leetcode;

/**
 * @author swzhao
 * @data 2023/11/11 16:46
 * @Discreption <> 路径总和
 */
public class HasPathSum {



    public static boolean soution(TreeNode root, int targetSum) {
        return process(root, targetSum);
    }

    private static boolean process(TreeNode root, int targetSum) {
        if (root == null){
            return false;
        }
        // 计算剩余
        targetSum -= root.val;
        if (root.left == null && root.right == null && targetSum == 0) {
            // 叶子节点并且减掉后剩余0才能true
            return true;
        }else {
            // 只要一个有就行
            return process(root.left, targetSum) || process(root.right, targetSum);
        }
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
