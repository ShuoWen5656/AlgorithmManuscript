package classespackage.bitoperation;

/**
 * @author swzhao
 * @data 2022/6/1 20:36
 * @Discreption <>只用位运算不用算数运算实现正数的加减乘除运算
 */
public class AddNegMultiDivide {


    /**
     * 两数相加
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b){
        try {
            // 不考虑进位的情况，a^b就是结果
            // 不考虑进位的结果
            int res = a ^ b;
            // 计算进位
            int carry = (a&b) << 1;
            while (carry != 0){
                int tem = res;
                res = res ^ carry;
                carry = (tem&carry) << 1;
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 两数相减：取补码
     * @param a
     * @param b
     * @return
     */
    public static int sub(int a, int b){
        // a+(-b)
        return add(a, negNum(b));
    }

    /**
     * a*b
     * @param a
     * @param b
     * @return
     */
    public static int multi(int a, int b){
        try {
            int res = 0;
            // b为被乘数，向右边移动一位，a就要左移一位
            while (b!=0){
                if((b&1) != 0){
                    res = add(res, a);
                }
                // b右移动
                b >>>= 1;
                a <<= 1;
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 触发运算
     * @param a
     * @param b
     * @return
     */
    public static int divide(int a, int b){
        try {
            if(b == 0){
                throw new Exception("除数为0");
            }
            if(a == Integer.MIN_VALUE && b == Integer.MIN_VALUE){
                return 1;
            }else if(b == Integer.MIN_VALUE){
                return 0;
            }else if(a == Integer.MIN_VALUE){
                // 先正常处除法
                int res = divide(add(a, 1), b);
                // 取出余数查看是否余数也刚好够一个b
                return res + div(sub(a, multi(res, b)), b);
            }else{
                return divide(a, b);
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 除法本地
     * 该方法不能处理int类型的最小值出发等情况，需要从主方法进入
     * @param a
     * @param b
     * @return
     */
    public static int div(int a, int b){
        // 先转成正数进行出发，最后再判断符号
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        // 循环32次，除数进行左移
        for (int i = 31; i > -1; i = sub(i, 1)){
            if (x>>i > y){
                // 说明a可以容下2^i个b
                res = res | 1 << i;
                // 商一个数后，被除数要减掉y*2^i
                x = sub(x, y << i);
            }
        }
        return res;
    }

    /**
     * 判断符号
     * @param n
     * @return
     */
    public static boolean isNeg(int n){
        return n < 0;
    }


    /**
     * 返回a的补码:除了符号位以外，剩下的位取反+1，
     * 其实等价于该数的正数连通符号为直接取反+1
     * @param a
     * @return
     */
    public static int negNum(int a){
        return addCp1(~a, 1);
    }


    /**
     * a+b
     * @param a
     * @param b
     * @return
     */
    public static int addCp1(int a, int b) {
        int sum = a;
        while (b != 0) {
            // 无进位结果
            int x = sum ^ b;
            // 进位
            b = (sum & b) << 1;
            sum = x;
        }
        return sum;
    }

    /**
     * a-b
     * @param a
     * @param b
     * @return
     */
    public static int subCp1(int a, int b) {
        // a+ b的补码即可,取反+1，符号位一起计算
        return addCp1(a, negNum(b));
    }

    /**
     * 获取当前值的补码
     * @param a
     * @return
     */
    public static int negNumCp1(int a) {
        return addCp1(~a, 1);
    }

    /**
     * a * b
     * @param a
     * @param b
     * @return
     */
    public static int multiCp1(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = addCp1(res, a);
            }
            b >>>= 1;
            a <<= 1;
        }
        return res;
    }


    /**
     * 除法最通常的情况
     * 但是有个缺陷就是 补码的负数比正数多一个最小值，最小值无法转正数所以当最小值计算时需要另外判断
     * @param a
     * @param b
     * @return
     */
    public static int divCp1(int a, int b) {
        // 这里去一下符号
        int x = a < 0 ? negNumCp1(a) : a;
        int y = b < 0 ? negNumCp1(b) : b;
        // 商
        int res = 0;
        // 因为不能存在算数计算，所以这里--i也用的sub函数减法
        for (int i = 31; i >= 0; i = subCp1(i, 1)) {
            // 先尝试除数b左移位i位是否小于等于被除数a
            if ((x >> i) >= b) {
                // 说明商1
                res |= 1 << i;
                // 减
                x = subCp1(x, y << i);
            }
        }
        return res;
    }


    /**
     * 考虑了全部情况的除法
     * @return
     */
    public static int divideCp1(int a, int b) {
        if (b == 0) {
            throw new RuntimeException("除数为0");
        }
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        }else if (a != Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 0;
        }else if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            // 首先计算a+1/b
            int res1 = divCp1(a+1, b);
            // 再乘回去求和原来值的差
            int sub = a - multiCp1(res1, b);
            // 返回结果,如果偏差值刚好满一个b，那么说明res1 + 1
            return res1 + sub/b;
        }else {
            // 最正常的情况
            return divCp1(a, b);
        }
    }










    public static void main(String[] args) {

        System.out.println(add(4, 2));
    }




    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
