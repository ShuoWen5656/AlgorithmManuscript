package classespackage.bitoperation;

/**
 * @author swzhao
 * @data 2022/6/2 21:17
 * @Discreption <> 整数的二进制表达中有多少个1
 */
public class CountBit {


    /**
     * 传统方式：方法一
     * @param a
     * @return
     */
    public static int count1(int a){
        try {
            int res = 0;
            while (a != 0){
                res += a & 1;
                a >>>= 1;
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 循环次数只和1有关系
     * @param a
     * @return
     */
    public static int count2(int a){
        try {
            int res = 0;
            while (a!=0){
                // 不好解释 自己举例子体会一下
                a &= (a-1);
                res++;
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }


    /**
     * 方法3：将最右边的1一个个剥离出来
     * @param a
     * @return
     */
    public static int count3(int a){
        try{
            int res = 0;
            while (a != 0){
                // 最右边的1右边都是0，所以补码只有最右边的1一定会被进位，或者被+1（个位时）&的时候除了最右边的1，其他全部都是反码
                a -= a&(~a+1);
                res++;
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 方法4：超自然方法,类似归并算法
     * @param a
     * @return
     */
    public static int count4(int a){
        try {
            a = (a & 0x55555555) + (a>>>1) & 0x55555555;
            a = (a & 0x33333333) + (a>>>2) & 0x33333333;
            a = (a & 0x0f0f0f0f) + (a>>>4) & 0x0f0f0f0f;
            a = (a & 0x00ff00ff) + (a>>>8) & 0x00ff00ff;
            a = (a & 0x0000ffff) + (a>>>16) & 0x0000ffff;
            return a;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

/********************************************************************
 *                          二轮测试                                 *
 ********************************************************************/


    /**
     * 计算整数中有多少个1
     * @return
     */
    public static int countCp1(int a) {
        int res = 0;
        while (a != 0) {
            // 抹掉最右边的1
            a &= (a-1);
            res++;
        }
        return res;
    }

    /**
     * 计算整数中有多少个1
     * @return
     */
    public static int countCp2(int a) {
        int res = 0;
        while (a != 0) {
            // 抹掉最右边的1
            a -= a&(~a+1);
            res++;
        }
        return res;
    }


    /**
     * 解法三：归并相加
     * @param a
     * @return
     */
    public static int countCp3(int a) {
        // 计算每两位有几个1
        a = (a & 0x55555555) + (a >>> 1 & 0x55555555);
        // 计算每四位有几个1
        a = (a & 0x33333333) + (a >>> 2 & 0x33333333);
        // 计算每8位有几个1
        a = (a & 0x0f0f0f0f) + (a >>> 4 & 0x0f0f0f0f);
        a = (a & 0x00ff00ff) + (a >>> 8 & 0x00ff00ff);
        a = (a & 0x0000ffff) + (a >>> 16 & 0x0000ffff);
        return a;
    }



    public static void main(String[] args) {
        System.out.println(countCp3(3));
    }



}
