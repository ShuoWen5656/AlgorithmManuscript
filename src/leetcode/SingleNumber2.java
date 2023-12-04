package leetcode;

/**
 * @author swzhao
 * @date 2023/12/3 11:36 上午
 * @Discreption <> 只出现一次的数字 II
 */
public class SingleNumber2 {

    public static void main(String[] args) {
        solution2(new int[]{-401451,-177656,-2147483646,-473874,-814645,-2147483646,-852036,-457533,-401451,-473874,-401451,-216555,-917279,-457533,-852036,-457533,-177656,-2147483646,-177656,-917279,-473874,-852036,-917279,-216555,-814645,2147483645,-2147483648,2147483645,-814645,2147483645,-216555});
    }


//    public static int soluiton(int[] nums) {
////        for (int i = 0; i < nums.length; i++) {
////            nums[i] = nums[i] < 0? -nums[i] : nums[i];
////        }
//        // 将每一个数字的每一个位转三进制并相加在一起返回
//        int[] regular3 = convert2Regular3AndSum(nums);
//        // 转回10进制
//        int res = convert2Regular10(regular3);
//        return res;
//    }
//
//    private static int convert2Regular10(int[] regular3) {
//        int res = 0;
//        for (int i = 0; i < regular3.length-1; i++) {
//            res += regular3[i] * Math.pow(3, i);
//        }
//        return (regular3[regular3.length - 1] == 1 ?  -res : res);
//    }
//
//    private static int[] convert2Regular3AndSum(int[] nums) {
//        int[] res = new int[32];
//        for (int i = 0; i < nums.length; i++) {
//            int cur = nums[i];
//            int[] regular3 = convert2Regular3(cur);
//            // 合
//            for (int j = 0; j < regular3.length; j++) {
//                res[j] = (res[j] + regular3[j])%3;
//            }
//        }
//        return res;
//    }
//
//    private static int[] convert2Regular3(int cur) {
//        int[] res = new int[32];
//        long tmp = cur;
//        if (tmp < 0) {
//            res[res.length-1] = 1;
//            tmp = -tmp;
//        }
//        int index = 0;
//        while (tmp != 0) {
//            res[index++] = (int) tmp%3;
//            tmp = tmp/3;
//        }
//        return res;
//    }


    public static int solution2(int[] nums){
        int res = 0;
        // 每一位都相加得到的结果%3 就是结果了
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);
            }
            if (total % 3 != 0) {
                res |= (1 << i);
            }
        }
        return res;
    }



}
