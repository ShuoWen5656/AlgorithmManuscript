package leetcode;

/**
 * @author swzhao
 * @date 2023/12/3 12:44 下午
 * @Discreption <> 数字范围按位与
 */
public class RangeBitwiseAnd {


    /**
     * 超出时间限制的解法
     * @param left
     * @param right
     * @return
     */
    public static int solution(int left, int right) {
        int res = left;
        left++;
        while (left <= right) {
            res &= left;
            left++;
        }
        return res;
    }


    /**
     * 寻找公共前缀
     * @param left
     * @param right
     * @return
     */
    public static int solution2(int left, int right) {
        // 记录不公共的部分有多长
        int count = 0;
        while (left < right) {
            left >>= 1;
            right >>= 1;
            count++;
        }
        return left << count;
    }




}
