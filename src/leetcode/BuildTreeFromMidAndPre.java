package leetcode;

import java.util.HashMap;

/**
 * @author swzhao
 * @data 2023/11/11 14:30
 * @Discreption <> 从前序与中序遍历序列构造二叉树
 */
public class BuildTreeFromMidAndPre {


    public static void main(String[] args) {
        solution(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
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
