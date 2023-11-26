package leetcode;

/**
 * @author swzhao
 * @date 2023/11/26 12:49 下午
 * @Discreption <> 搜索插入位置
 */
public class SearchInsert {

    public static void main(String[] args) {
        solution(new int[]{1,3}, 2);
    }



    public static int solution(int[] nums, int target) {
        if (nums == null || nums.length == 0 || nums[0] >= target)  {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                res = mid;
                break;
            }else if (target > nums[mid]){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        // 判断是否找到了
        if (res == -1) {
            res = nums[left] < target ? left + 1 : left;
        }
        return res;
    }


}
