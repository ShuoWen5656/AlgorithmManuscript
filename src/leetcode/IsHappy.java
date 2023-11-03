package leetcode;

import java.util.HashSet;

/**
 * @author swzhao
 * @date 2023/11/3 7:12 下午
 * @Discreption <> 快乐数
 */
public class IsHappy {


    public static void main(String[] args) {
        System.out.println(solution(19));
    }

    public static boolean solution(int n) {
        HashSet<Integer> integers = new HashSet<>();

        while (true) {
            if (integers.contains(n)) {
                // 说明出现过了,死循环
                return false;
            }else {
                int res = 0;
                int tem = n;
                while (tem != 0) {
                    int t = tem%10;
                    res += t*t;
                    tem /= 10;
                }
                if (res == 1) {
                    return true;
                }else {
                    integers.add(n);
                    n = res;
                }
            }
        }
    }

}
