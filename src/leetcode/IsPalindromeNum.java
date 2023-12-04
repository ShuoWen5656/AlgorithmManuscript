package leetcode;

/**
 * @author swzhao
 * @date 2023/12/3 12:54 下午
 * @Discreption <> 回文数
 */
public class IsPalindromeNum {


    public static void main(String[] args) {
        solution(1000000001);
    }

    public static boolean solution(int num) {
        if (num < 0) {
            return false;
        }
        // 计算位数
        int base = 1;
        if (num >= 1000000000) {
            base = 1000000000;
        }else {
            while (num / base != 0) {
                base *= 10;
            }
            base /= 10;
        }
        // 保证num是正数
        while (num != 0) {
            int left = num / base;
            int right = num %10;
            if (left != right) {
                return false;
            }
            num -= left * base;
            num /= 10;
            base /= 100;
        }
        return true;
    }

}
