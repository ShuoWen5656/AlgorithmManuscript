package leetcode;

import java.util.HashMap;

/**
 * @author swzhao
 * @data 2023/11/11 15:49
 * @Discreption <> 二叉树展开为链表
 */
public class Flatten {

    public static void main(String[] args) {
        TreeNode solution = solution(new int[]{1,2,3,4,5,6}, new int[]{3,2,4,1,5,6});
        solution(solution);
    }


    public static void solution(TreeNode root) {


        process(root);
    }

    /**
     * 将树变成链表
     * @param root
     * @return
     */
    private static void process(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        process(left);
        process(right);

        if (left == null ) {
            return;
        }
        TreeNode tail = left;
        while (tail.right != null) {
            tail = tail.right;
        }
        root.right = left;
        root.left = null;
        tail.right = right;
        return;
    }

    public static TreeNode solution(int[] preorder, int[] inorder) {
        // 用map做个预处理
        HashMap<Integer, Integer> valueIndexIn = new HashMap<>();
        for (int i = 0; i < preorder.length; i++) {
            valueIndexIn.put(inorder[i], i);
        }

        return process(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, valueIndexIn);
    }

    private static TreeNode process(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> valueIndexIn) {
        if (preStart > preEnd || preStart >= preorder.length || preStart < 0 || preEnd >= preorder.length || preEnd < 0) {
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }
        // 当前根节点
        TreeNode treeNode = new TreeNode(preorder[preStart]);
        // 获取中点的index
        int indexMid = valueIndexIn.get(preorder[preStart]);
        int lenLeft = indexMid - inStart;
        treeNode.left = process(preorder, preStart + 1, preStart + lenLeft, inorder, inStart, indexMid-1, valueIndexIn);

        treeNode.right = process(preorder, preStart + lenLeft + 1, preEnd, inorder, indexMid + 1, inEnd, valueIndexIn);

        return treeNode;
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
