package leetcode;

import java.util.HashMap;

/**
 * @author swzhao
 * @data 2023/11/11 15:08
 * @Discreption <>
 */
public class BuildTreeMidPost {



    public static void main(String[] args) {
        solution(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});
    }

    public static TreeNode solution(int[] inorder, int[] postorder) {
        // 用map做个预处理
        HashMap<Integer, Integer> valueIndexIn = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valueIndexIn.put(inorder[i], i);
        }

        return process(postorder, 0, postorder.length-1, inorder, 0, inorder.length-1, valueIndexIn);
    }

    private static TreeNode process(int[] postOrder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> valueIndexIn) {
        if (postStart > postEnd || postStart >= postOrder.length || postStart < 0 || postEnd >= postOrder.length || postEnd < 0) {
            return null;
        }
        if (postStart == postEnd) {
            return new TreeNode(postOrder[postEnd]);
        }
        // 当前根节点
        TreeNode treeNode = new TreeNode(postOrder[postEnd]);
        // 获取中点的index
        int indexMid = valueIndexIn.get(postOrder[postEnd]);
        int lenLeft = indexMid - inStart;
        treeNode.left = process(postOrder, postStart, postStart + lenLeft - 1 , inorder, inStart, indexMid-1, valueIndexIn);

        treeNode.right = process(postOrder, postStart + lenLeft, postEnd-1, inorder, indexMid + 1, inEnd, valueIndexIn);

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
