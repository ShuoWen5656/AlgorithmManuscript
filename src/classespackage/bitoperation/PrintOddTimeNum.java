package classespackage.bitoperation;

/**
 * @author swzhao
 * @data 2022/6/3 9:22
 * @Discreption <> 在其他数都出现偶数次的数组中找到出现奇数次的数
 * 进阶问题：有两个数出现了奇数次，其他数都是偶数次，打印这两个数
 */
public class PrintOddTimeNum {


    /**
     * 全部异或一遍最后结果就是结果
     * @param array
     * @return
     */
    public static int printOddTimeNum(int[] array){
        int res = 0;
        for (int cur : array){
            res ^= cur;
        }
        return res;
    }


    /**
     * 获取两个出现奇数次的数字
     * @param array
     */
    public static void printOddTimeNum2(int[] array){
        try {
            int e0 = 0, tem = 0;
            // 先计算出两个数的异或结果
            for (int cur : array){
                e0 ^= cur;
            }
            // 此时e0是两个数的异或，取出最右边的1
            int rightOne = e0 & (~e0 + 1);
            for (int cur : array){
                if((cur&rightOne) != 0){
                    // 只跟tem位不为0的数进行异或，因为这一位跟1异或到最后能保证这一位变成0，就是数字a了。
                    // 1、异或是可逆的2、tem位为1的必定是两个结果中的一个，所以将另一个结果逆过程回来即可
                    tem ^= cur;
                }
            }
            System.out.println("first:" + e0 + "second:" + (e0^tem));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
