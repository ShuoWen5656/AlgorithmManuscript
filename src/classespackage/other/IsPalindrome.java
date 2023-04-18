package classespackage.other;

/**
 * @author swzhao
 * @data 2022/7/18 21:22
 * @Discreption <> 判断一个数是否是回文数
 */
public class IsPalindrome {

    /**
     * 主方法
     * @return
     */
    public static boolean isPalindrome(int num){
        // 最小值先排除
        if (num == Integer.MIN_VALUE){
            return false;
        }
        num = Math.abs(num);
        int base = 1;
        // 将base打到和num位数一样
        while (num/base >= 10){
            base*=10;
        }
        while (num != 0){
            if (num % 10 != num/base){
                return false;
            }
            // 去掉头尾
            num = num % base;
            num = num /10;
            base /= 100;
        }
        return true;
    }


    /**
     * 二轮测试：判断一个数是否是回文数
     * @param num
     * @return
     */
    public static boolean isPalindRomeCp1(int num) {
        if (num == Integer.MIN_VALUE) {
            return false;
        }
        num = Math.abs(num);
        int base = 1;
        while (num / base != 0) {
            base *= 10;
        }
        // 让base的位数和num的相同
        base /= 10;
        while (num != 0) {
            if (num / base != num % 10) {
                return false;
            }
            num -= (num / base) * base;
            num /= 10;
            base /= 100;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(isPalindRomeCp1(13331));
    }



}
