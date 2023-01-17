package classespackage.bitoperation;

import classespackage.CommonUtils;

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


    /**
     * 二轮测试:在只有一个奇数次数的情况下
     * @param arr
     * @return
     */
    public static int getOddTimeNumCp1(int[] arr) {
        int res = 0;
        for (int i : arr) {
            res ^= i;
        }
        return res;
    }

    /**
     * 二轮测试：在有两个奇数次的数下
     * @param arr
     * @return
     */
    public static int[] getOddTimeNum2Cp2(int[] arr) {
        int res = 0;
        // 得到异或结果
        for (int i : arr) {
            res ^= i;
        }
        // 获取res最左边的1,因为arr中两个奇数次的数坑定不相等
        int rightOne = res & (~res + 1);
        // 结果容器
        int[] ress = new int[2];
        int tem = res;
        for (int i : arr) {
            // 只有rightOne位为0的才行
            if ((rightOne & i) == 0) {
                tem ^= i;
            }
        }
        // 到这里，res已经是其中一个结果了
        ress[0] = tem;
        ress[1] = tem ^ res;
        return ress;
    }



    public static void main(String[] args) {
        CommonUtils.printArr(getOddTimeNum2Cp2(new int[]{1,1,1,2,2,2,3,3,5,5,8,8}));
    }





}
