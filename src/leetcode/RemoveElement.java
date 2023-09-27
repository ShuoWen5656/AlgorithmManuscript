package leetcode;

/**
 * @author swzhao
 * @data 2023/9/27 21:49
 * @Discreption <> 移除元素
 * 移除num中val相等的元素并返回数组长度
 */
public class RemoveElement {

    public static void main(String[] args) {
        int[] ints = {3, 2, 2, 3};
        removeElement(ints, 3);
    }



    public static int removeElement(int[] nums, int val) {
        int cur = 0;
        int pre = 0;
        for (; pre < nums.length; pre++) {
            if (nums[pre] != val) {
                nums[cur++] = nums[pre];
            }
        }
        return cur;
    }




}
