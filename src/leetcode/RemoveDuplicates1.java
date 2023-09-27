package leetcode;

/**
 * @author swzhao
 * @data 2023/9/23 17:31
 * @Discreption <>  删除有序数组中的重复项 I
 * 要求空间复杂度O（1）
 */
public class RemoveDuplicates1 {


    public static void main(String[] args) {
        int[] ints = {1,2};
        removeDuplicates1(ints);
    }



    public static int removeDuplicates1(int[] nums) {
        int cur = 0;
        int pre = 0;
        for (; pre < nums.length; pre++) {
            if (cur < 1 || nums[cur-1] != nums[pre]) {
                nums[cur++] = nums[pre];
            }
        }
        return cur;
    }




}
