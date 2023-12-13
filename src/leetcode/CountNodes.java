package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/12 12:46 下午
 * @Discreption <>  完全二叉树的节点个数
 */
public class CountNodes {


    public static List<Integer> list;


    public static int solution(TreeNode root) {
        list = new ArrayList<>();
        process(root);
        return list.size();
    }

    private static void process(TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        process(root.left);
        process(root.right);
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
