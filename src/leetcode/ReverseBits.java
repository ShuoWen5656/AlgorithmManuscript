package leetcode;

/**
 * @author swzhao
 * @date 2023/12/2 12:16 下午
 * @Discreption <> 颠倒二进制位
 */
public class ReverseBits {


    public static void main(String[] args) {
    }

    public static int solution(int n) {
        int[] array = new int[32];
        int index = array.length-1;
        while (index >= 0) {
            array[index--] = n & 1;
            n >>>= 1;
        }
        // 翻转
        int left = 0;
        int right = array.length-1;
        while (left <= right) {
            int tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
            left--;
            right--;
        }
        // 转回去
        index = array.length - 1;
        int res = 0;
        while (index >= 0) {
            if (array[index] == 1) {
                res += Math.pow(2, index);
            }
            index--;
        }
        return res;
    }


    public int solution2(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = res | ((n & 1) << (31-i));
            n >>>= 1;
        }
        return res;
    }




    public int reverseBits(int n) {
        int rev = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            rev |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return rev;
    }


}
