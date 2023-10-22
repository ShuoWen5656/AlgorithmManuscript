package leetcode;

/**
 * @author swzhao
 * @date 2023/10/22 12:58 下午
 * @Discreption <> 盛最多水的容器
 */
public class MaxArea {


    public static void main(String[] args) {
        System.out.println(solution1((new int[]{1,8,6,2,5,4,8,3,7})));
    }


    public static int solution(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                max = Math.max(max, count(height, i, j));
            }
        }
        return max;
    }


    public static int solution1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length -  1;
        while (left < right) {
            max = Math.max(max, count(height, left, right));
            // 横向永远是减少的，所以两个边界谁小丢弃谁才能保证更大
            if (height[left] <= height[right]) {
                left++;
            }else {
                right--;
            }
        }
        return max;
    }

    /**
     * 计算面积
     * @param height
     * @param index1
     * @param index2
     * @return
     */
    public static int count(int[] height, int index1, int index2) {
        return Math.min(height[index1], height[index2]) * Math.abs(index1 - index2);
    }


}
