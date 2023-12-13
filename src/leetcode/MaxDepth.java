package leetcode;

import java.util.Map;

/**
 * @author swzhao
 * @date 2023/11/10 9:53 下午
 * @Discreption <>二叉树的最大深度
 */
public class MaxDepth {



    public int solution(TreeNode root) {
        return process(root);
    }

    private int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(process(root.left), process(root.right)) + 1;
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
