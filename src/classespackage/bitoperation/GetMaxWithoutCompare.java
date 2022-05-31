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



}
