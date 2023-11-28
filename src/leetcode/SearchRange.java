package leetcode;

/**
 * @author swzhao
 * @date 2023/11/27 9:31 下午
 * @Discreption <> 在排序数组中查找元素的第一个和最后一个位置
 */
public class SearchRange {

    public static void main(String[] args) {
        solution2(new int[]{1}, 1);
//        searchRange(new int[]{1}, 1);
    }


    public static int[] solution(int[] nums, int target) {
        int leftIndex = find(nums, true, target);
        int rightIndex = find(nums, false, target)-1;
        if (leftIndex <= rightIndex && leftIndex >= 0 && rightIndex < nums.length
                && nums[leftIndex] == nums[rightIndex]) {
            return new int[]{leftIndex, rightIndex};
        }
        return new int[]{-1, -1};
    }




    private static int find(int[] nums, boolean isLeft, int target) {
        int left = 0;
        int right = nums.length - 1;
        int res = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid-1;
                res = mid;
            }else if (nums[mid] == target){
                if (isLeft) {
                    right = mid-1;
                    res = mid;
                }else {
                    left = mid+1;
                }
            }else {
                left = mid + 1;
            }
        }
        return res;
    }

    public static int[] solution2(int[] nums, int target) {
        int leftIndex = find2(nums, true, target);
        int rightIndex = find2(nums, false, target);
        if (leftIndex == nums.length) {
//            不存在
            return new int[]{-1, -1};
        }
        return new int[]{leftIndex, rightIndex};
    }


    private static int find2(int[] nums, boolean isLeft, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid-1;
            }else if (nums[mid] == target){
                if (isLeft) {
                    right = mid-1;
                }else {
                    left = mid+1;
                }
            }else {
                left = mid + 1;
            }
        }
        // 总会有一个相等的，一个都没有说明没找到
        if (left >= 0 && left < nums.length && nums[left] == target){
            return left;
        }else if (right >= 0 && right < nums.length && nums[right] == target){
            return right;
        }else {
            return nums.length;
        }
    }



    public static int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public static int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }



}
