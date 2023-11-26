package leetcode;

/**
 * @author swzhao
 * @date 2023/11/26 1:42 下午
 * @Discreption <> 搜索旋转排序数组
 */
public class SearchSortArray {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3,5,1}, 3));
    }


    public static int solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return target == nums[0] ? 0 : -1;
        }
        // 至少两个元素
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] < nums[right]) {
            // 升序的
            return find(nums, left, right, target);
        }
        int res = -1;
        // 接下来就不是升序的了
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                res = mid;
                break;
            }else {
                if (nums[mid] > nums[right]) {
                    if (target < nums[mid] && target >= nums[left]) {
                        right = mid;
                        res = find(nums, left, right, target);
                        break;
                    }else {
                        left = mid + 1;
                    }
                }else {
                    if (target > nums[mid] && target <= nums[right]) {
                        left = mid + 1;
                        res = find(nums, left, right, target);
                        break;
                    }else {
                        right = mid;
                    }
                }
            }
            if (res != -1) {
                break;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return res;
    }


    public static int find(int[] nums, int start, int end, int target) {
        if (start == end) {
            return nums[start] == target ? start : -1;
        }
        // 至少两个元素
        int res = -1;
        int left = start;
        int right = end;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                res = mid;
                break;
            }else if (target > nums[mid]) {
                left = mid + 1;
            }else  {
                right = mid;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return res;
    }

}
