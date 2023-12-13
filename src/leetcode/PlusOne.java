package leetcode;

import java.util.Arrays;

/**
 * @author swzhao
 * @date 2023/12/4 9:22 下午
 * @Discreption <> 加一
 */
public class PlusOne {


    public static void main(String[] args) {
        solution(new int[]{9,9,9, 9});
    }

    public static int[] solution(int[] digits) {
        int c = 0;
        int len = digits.length-1;
        int[] res = new int[len + 2];
        while (len >= 0) {
            int cur = digits[len];
            int sum = cur + c;
            if (len == digits.length - 1) {
                // 第一位需要加1
                sum += 1;
            }
            res[len+1] = sum % 10;
            c = sum/10;
            len--;
        }
        // 结束
        if (c == 1) {
            res[0] = 1;
            return res;
        }else {
            return Arrays.copyOfRange(res, 1, res.length);
        }
    }

}
