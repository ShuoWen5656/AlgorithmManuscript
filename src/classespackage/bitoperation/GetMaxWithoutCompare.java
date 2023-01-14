package classespackage.bitoperation;

/**
 * @author swzhao
 * @data 2022/5/31 21:49
 * @Discreption <>不用任何比较判断找出两个数中较大的数
 */
public class GetMaxWithoutCompare {


    /**
     * 方法一：
     * 可能b为负数会导致溢出问题
     * @param a
     * @param b
     */
    public static int getMax(int a, int b){
        try {
            // 求一下差值
            int c = a - b;
            // 判断c的符号
            int ca = sign(c);
            return a * ca + b * filp(ca);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 方法二不存在溢出问题
     * @param a
     * @param b
     * @return
     */
    public static int getMax2(int a, int b){
        try {
            // 求差
            int c = a - b;
            // 求三个数的符号
            int sa = sign(a);
            int sb = sign(b);
            int sc = sign(c);
            // ab的符号是否不同
            int dif = sa ^ sb;
            // 符号是否相同
            int same = filp(dif);
            // 如果ab不相同，谁正数谁大，返回谁
            int returnA = dif * sa + same * sc;
            int returnB = filp(returnA);
            return a * returnA + b * returnB;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }



    /**
     * 返回异或结果
     * @param num
     * @return
     */
    public static int filp(int num){
            return num ^ 1;
    }


    /**
     * 返回num的符号:正数返回1，负数返回0
     * @param num
     * @return
     */
    public static int sign(int num){
        return filp((num>>>31) & 1);
    }


    /*************************************************************************************************
     *                                           二轮测试                                             *
     *************************************************************************************************/

    /**
     * 和1异或操作
     * @return
     */
    public static int flipCp1(int i) {
        return i ^ 1;
    }

    /**
     * 获取整数i的符号
     * 0：负数
     * 1：整数
     * @param i
     * @return
     */
    public static int signCp1(int i) {
        return flipCp1((i >>> 31) & 1);
    }

    /**
     * 获取a和b
     * 该方法将差值的符号作为依据返回较大的数，但是如果a为正数，b为负数就会存在溢出的可能，所以该方法并不通用
     * @param a
     * @param b
     * @return
     */
    public static int getMaxCp1(int a, int b) {
        // 取差值
        int c = a - b;
        // 取a和b的乘数
        int scA = signCp1(c);
        int scB = flipCp1(signCp1(c));
        return scA * a + scB * b;
    }

    public static void main(String[] args) {
        System.out.println(getMaxCp2(11, -2));
    }


    /**
     * 获取a和b较大的一个
     * 该方法增加了判断符号过程，所以不存在溢出问题
     * @param a
     * @param b
     * @return
     */
    public static int getMaxCp2(int a, int b) {
        // 求差值
        int c = a - b;
        // 计算符号
        int sc = signCp1(c);
        int sa = signCp1(a);
        int sb = signCp1(b);
        // 判断ab符号是否相同
        int diff = sa ^ sb;
        int same = flipCp1(diff);
        // a如果为大值，要么符号不同并且a为正数，要么符号相同sc为正数，返之b也相同
        return a * (diff * (sa) + same * (sc)) + b * (diff * (sb) + same * (filp(sc)));
    }












}
