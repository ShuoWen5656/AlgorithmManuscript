package classespackage.other;

/**
 * @author swzhao
 * @data 2022/6/30 21:28
 * @Discreption <> 有关阶乘的两个问题
 * 问题一：求n的阶乘中有多少个0
 * 问题二：求n的阶乘2进制表达式中的1的位置
 */
public class ZeroNumOr1IndexFromNFactorial {

    /**
     * 问题一：
     * n！中2x5才能得到一个0，因为2比5坑定多，所以只计算5的个数接口
     * @param num
     * @return
     */
    public static int zeroNum(int num){
        try {
            // 计算每一个数中5因子有几个
            int res = 0;
            int cur = 0;
            // 5个开始加
            for (int i = 5; i < num+1; i+=5){
                cur = i;
                while (cur % 5 == 0){
                    res++;
                    cur /= 5;
                }
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 方法二：
     * 原理：
     * N为125时
     * 含有5^3:125
     * 含有5^2：125，100,75,25
     * 含有5^1: 125.。。。。。。5
     * 所以一共含有的5的因子有
     * Z= N/5 + N/5^2 + N/5^3
     * @param num
     * @return
     */
    public static int zeroNum2(int num){
        try {
            if (num < 0){
                return 0;
            }
            int res = 0;
            while (num != 0){
                res += num/5;
                num /= 5;
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 原理和上面一样，求2的因子，每一个2的因子可以让1往右边移动一个单位
     * @param num
     * @return
     */
    public static int index1Num(int num){
        try {
            if (num < 0){
                return 0;
            }
            int res = 0;
            while (num != 0){
                num >>>= 1;
                res += num;
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据等比数列求和公式，二进制比较特殊，所以最后结论为
     * N的二进制表达式中1的个数为m
     * 因子2的总个数为N-m
     * 2因子的个数就是N的1的位置
     * @param num
     * @return
     */
    public static int index1Num2(int num){
        try {
            if (num < 0){
                return -1;
            }
            int ones = 0;
            int cur = num;
            while (cur != 0){
                ones += (cur & 1) == 0 ? 0 : 1;
                cur >>>= 1;
            }
            return num - ones;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }





}
