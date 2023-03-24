package classespackage.other;

/**
 * @author swzhao
 * @data 2022/6/29 21:29
 * @Discreption <> 一行代码求两个数的最大公约数
 */
public class GCD {


    /**
     * 辗转相除法（欧几里得）
     * 原理：
     * 1、两个数一定是最大公约数的倍数没问题
     * 2、所以两个数的差值一定是最大公约数的倍数，也就是大数字比小数字多了几倍的公约数
     * 3、所以余数一定就是公约数的倍数并且倍数一定比n的公约数倍数小，否则商+1
     * 4、这时n大于余数，两个又都是公约数的倍数，所以问题变成了n和余数之间的最大公约数问题（辗转）
     * 5、n比余数大几个公约数呢？辗转到余数为一倍的公约数时，n此时一定是余数的倍数，这时公约数就是较小的那个数了
     * 图解：
     * ||||||||||| 12个
     * |||||||| 8个
     * 求最大公约数
     * 12 = 8 * 0 + 4
     * 此时12由3个4组成，8由2个4组成，余数由一个4组成
     * 8 = 4*2 + 0
     * 此时余数为0， 最小公约数为4
     * @param m
     * @param n
     * @return
     */
    public static int gcd(int m, int n){
        return n == 0? m : gcd(n, m%n);
    }




    public static int gcdCp1(int m, int n) {
        return Math.max(m, n) % Math.min(m, n) == 0 ? Math.min(m, n) : gcdCp1(Math.min(m, n), Math.max(m, n) % Math.min(m, n));
    }


    public static void main(String[] args) {
        System.out.println(gcdCp1(22, 16));
    }


}
