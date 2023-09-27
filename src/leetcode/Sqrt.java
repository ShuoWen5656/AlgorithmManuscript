package leetcode;

/**
 * @author swzhao
 * @data 2023/9/27 22:11
 * @Discreption <>x的平方根
 */
public class Sqrt {

    public static int sqrt(int x) {
        long res = 0;
        while (res*res <= x) {
            res++;
        }
        return (int)res-1;
    }


    public static int sqrt2(int x) {
        int left = 0;
        int right = x;
        int res = -1;
        while (left <= right) {
            int mid = (left+right)/2;
            if ((long)mid * mid <= x) {
                res = mid;
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(sqrt2(8));
    }

}
