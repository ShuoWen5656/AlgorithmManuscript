package leetcode;

/**
 * @author swzhao
 * @data 2023/9/23 17:31
 * @Discreption <>  删除有序数组中的重复项 II
 * 要求空间复杂度O（1）
 */
public class RemoveDuplicates {


    public static void main(String[] args) {
        int[] ints = {1,1,1,2,2,2,3,3};
        System.out.println(process2(ints));
    }





    /**
     * 最优解
     * @param nums
     * @param k
     * @return
     */
    public static int process(int[] nums, int k) {
        int u = 0;
        for (int x : nums) {
            if (u < k || nums[u - k] != x) nums[u++] = x;
        }
        return u;
    }

    public static int process2(int[] nums) {
        int cur = 0;
        for (int pre = 0; pre < nums.length; pre++) {
            if (cur < 2) {
                cur++;
                continue;
            }
            if (nums[pre] != nums[cur-2]) {
                nums[cur++] = nums[pre];
            }
        }
        return cur;
    }

    public static int solution(int[] nums) {
        if (nums.length <= 2) {
            // 不能小于2
            return nums.length;
        }
        int pre = 0;
        // 初始化pre,pre为num[cur]的下一个数的index
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[0]) {
                pre = i;
                break;
            }
        }
        if (nums[pre] == nums[0]) {
            // 说明都是一样的返回2即可
            return 2;
        }
        // 正式循环
        int i = 2;
        for (; i < nums.length; i++) {
            if (nums[i] >= nums[i-1] && nums[i] > nums[i-2]) {
                if (i >= pre) {
                    pre = i;
                    while (pre < nums.length && nums[pre] == nums[i]) {
                        pre++;
                    }
                }
                // 不相等说明没问题
                continue;
            }
            // 先让pre和当前不相等，然后交换
            // 初始化pre,pre为num[cur]的下一个数的index
            while (pre < nums.length && nums[pre] == nums[i]) {
                pre++;
            }
            // pre如果越界，说明结束了
            if (pre == nums.length) {
                return i;
            }
            swap(nums, i, pre++);
            i--;
        }
        return i;
    }

    public static void swap(int[] nums, int i1, int i2) {
        int tem = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tem;
    }
}
