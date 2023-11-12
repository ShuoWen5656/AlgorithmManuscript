package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/12 12:28 下午
 * @Discreption <> 二叉搜索树迭代器
 */
public class BSTIterator {

    private TreeNode root;

    private int index = -1;

    private List<Integer> array;

    public BSTIterator(TreeNode root) {
        this.root = root;
        this.array = new ArrayList<>();
    }

    public int next() {
        if (index == -1) {
            init();
        }
        return array.get(index++);
    }

    private void process(TreeNode root) {
        if (root == null) {
            return;
        }
        process(root.left);
        array.add(root.val);
        process(root.right);
    }

    public void init() {
        process(root);
        index = 0;
    }

    public boolean hasNext() {
        if (index == -1) {
            init();
        }
        if (array.size() == 0) {
            return false;
        }
        return index < array.size();
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
