package classespackage.bitoperation;

/**
 * @author swzhao
 * @data 2022/5/30 22:50
 * @Discreption <>不用额外变量交换两个整数的值
 */
public class ChangeNum {


    public static void changeNum(int a, int b){
        try {
            // 三句话交换ab的值
            a = a^b;
            b = a^b;
            a = a^b;
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }


    /**
     * 二轮测试：由于值传递，所以这里用数组引用
     * @param ints
     */
    public static void changeNumCp1(int[] ints) {
        if (ints.length < 2) {
            return;
        }
        int a = ints[0];
        int b = ints[1];
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        ints[0] = b;
        ints[1] = a;
    }



}
