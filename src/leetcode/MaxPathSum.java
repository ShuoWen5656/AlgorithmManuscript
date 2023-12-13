package leetcode;

/**
 * @author swzhao
 * @date 2023/11/12 12:19 下午
 * @Discreption <> 二叉树中的最大路径和
 */
public class MaxPathSum {


    public static int MAX_NUM = Integer.MIN_VALUE;

    public int solution(TreeNode root) {
        MAX_NUM = Integer.MIN_VALUE;
        porcess(root);
        return MAX_NUM;
    }


    public static int porcess(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左边的最大路径，必须经过root，小于0则舍弃
        int leftMax = Math.max(0, porcess(root.left));
        int rightMax = Math.max(0, porcess(root.right));

        // 计算当前路径和是否最大
        MAX_NUM = Math.max(MAX_NUM, root.val + leftMax + rightMax);
        // 只能取一边的最大值
        return root.val + Math.max(leftMax , rightMax);
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
