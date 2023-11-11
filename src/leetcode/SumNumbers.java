package leetcode;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author swzhao
 * @data 2023/11/11 17:36
 * @Discreption <> 求根节点到叶节点数字之和
 */
public class SumNumbers {



    public static int solution(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<String> res = process(root);
        int result = 0;
        for (int i = 0; i < res.size(); i++) {
            result += Integer.parseInt(res.get(i));
        }
        return result;
    }

    private static List<String> process(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        if (root.left == null && root.right == null) {
            // 叶子节点
            ArrayList<String> list = new ArrayList<>();
            list.add(String.valueOf(root.val));

            return list;
        }else {
            List<String> leftList = process(root.left);
            List<String> rightList = process(root.right);
            ArrayList<String> res = new ArrayList<>();
            for (String s : leftList) {
                res.add(root.val + s);
            }
            for (String s : rightList) {
                res.add(root.val + s);
            }
            return res;
        }
        
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
