package leetcode;

import java.util.*;

/**
 * @author swzhao
 * @date 2023/11/13 9:44 下午
 * @Discreption <> 求每一层的平均值
 */
public class AverageOfLevels {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        solution(root);
    }
    public static List<Double> solution(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Double> res = new ArrayList<>();
        res.add(Double.valueOf(root.val));
        long tmp = 0;

        Deque<TreeNode> helper = new LinkedList<>();
        helper.addLast(root);
        TreeNode last = root;
        while (!helper.isEmpty()) {
            TreeNode curNode = helper.pollFirst();
            if (curNode.left != null){
                helper.addLast(curNode.left);
                tmp += curNode.left.val;
            }
            if (curNode.right != null) {
                helper.addLast(curNode.right);
                tmp += curNode.right.val;
            }
            if (curNode == last) {
                // 计算平均值
                if (!helper.isEmpty()) {
                    res.add(Double.valueOf(tmp)/Double.valueOf(helper.size()));
                    tmp = 0;
                    last = helper.peekLast();
                }
            }
        }
        return res;
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
