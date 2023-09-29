package leetcode;

/**
 * @author swzhao
 * @data 2023/9/29 9:39
 * @Discreption <> 多数元素
 */
public class MajorityElement {


    public static void main(String[] args) {
        System.out.println(solution1(new int[]{2,2,1,1,1,2,2}));
    }

    public static int solution1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 当前候选元素
        int curCandidate = 0;
        // 候选元素剩余数量
        int curTimes = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == curCandidate) {
                // 和当前候选元素相等
                curTimes++;
            }else {
                // 不相等
                if (curTimes != 0) {
                    // 说明还有余额
                    curTimes--;
                }else {
                    // 余额不足，更换候选人
                    curCandidate = nums[i];
                    curTimes = 1;
                }
            }
        }
        // 候选者虽然是出现次数最多的但是过半还要看看
        curTimes = 0;
        for (int i = 0 ; i < nums.length; i++) {
            if (nums[i] == curCandidate) {
                curTimes++;
            }
        }
        if (curTimes > nums.length/2) {
            return curCandidate;
        }else{
            return 0;
        }
    }

}
