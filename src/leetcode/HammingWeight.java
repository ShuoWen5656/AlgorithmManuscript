package leetcode;

/**
 * @author swzhao
 * @date 2023/12/3 10:57 上午
 * @Discreption <> 位1的个数
 */
public class HammingWeight {


    public static void main(String[] args) {
        solution(11);
    }

    public static int solution(int n) {
        int count = 0;
        int res = 0;
        while (count < 32 && n != 0) {
            if ((n & 1) == 1) {
                res ++;
            }
            n >>>= 1;
            count++;
        }
        return res;
    }

}
