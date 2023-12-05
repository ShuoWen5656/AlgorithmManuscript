package leetcode;

/**
 * @author swzhao
 * @date 2023/12/4 9:56 下午
 * @Discreption <> 阶乘后的零
 */
public class TrailingZeroes {


    public static void main(String[] args) {
        solution(5);
    }

    public static int solution(int n) {
        int num = 1;
        int res = 0;
        while (n/(int)Math.pow(5, num) != 0) {
            res += n/Math.pow(5, num);
            num++;
        }
        return res;
    }
}
