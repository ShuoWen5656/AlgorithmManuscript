package leetcode;

/**
 * @author swzhao
 * @date 2023/11/23 9:58 下午
 * @Discreption <> 将有序数组转换为二叉搜索树
 */
public class SortedArrayToBST {

    public static void main(String[] args) {

    }

    public static TreeNode solution(int[] nums) {
        return process(nums, 0, nums.length-1);
    }

    private static TreeNode process(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start]);
        }else if (start > end){
            return null;
        }
        int midIndex = (start + end)/2;
        TreeNode cur = new TreeNode(nums[midIndex]);
        cur.left = process(nums, start, midIndex-1);
        cur.right = process(nums, midIndex+1, end);
        return cur;
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
