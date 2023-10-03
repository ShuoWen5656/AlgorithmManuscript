package leetcode;

import classespackage.other.GCD;

/**
 * @author swzhao
 * @data 2023/9/30 12:41
 * @Discreption <>轮转数组
 */
public class Rotate {

    public static void main(String[] args) {
        int[] ints = {-1,-100,3,99};
        rotate(ints, 2);
    }


    public static void rotate(int[] nums, int k) {
        if (k == 0) {
            return;
        }
        int count = gcd(nums.length, k);
        for (int i = 0; i < count; i++) {
            // 当前索引
            int curIndex = i;
            // 下一个index
            int nextIndex = i;
            int nextNum = nums[curIndex];
            boolean flags = false;
            while (!flags) {
                // 计算当前位置的下一个位置
                nextIndex = (curIndex + k) % nums.length;

                int tmp = nums[nextIndex];
                nums[nextIndex] = nextNum;
                nextNum = tmp;

                curIndex = nextIndex;
                // 判断是否cur回到原位
                flags = curIndex == i;
            }
        }
    }

    public static int gcd(int k, int n){
        return k%n == 0? n : gcd(Math.max(n, k%n), Math.min(n, k%n));
    }

}
