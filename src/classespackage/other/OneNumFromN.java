package classespackage.other;

import org.omg.CORBA.MARSHAL;

import java.util.Map;

/**
 * @author swzhao
 * @data 2022/7/15 19:36
 * @Discreption <> 1到n中1出现的次数
 */
public class OneNumFromN {


    /**
     * 方法二：
     * @param n
     * @return
     */
    public static int oneNum2(int n){
        if (n < 1){
            return 0;
        }
        // 获取位数
        int len = getLen(n);
        // 获取最高位的基数
        int tem1 = (int)Math.pow(10, len-1);
        // 最高位
        int first = n/tem1;
        // 最高位是1的所有数的个数：100-114
        // 很显然如果first超过1，则封顶最高位tem1个，如果没超过，则从[tem1:n]个数就是最高位1的个数
        int firstOneNum = first == 1? n/tem1+1 : tem1;
        // 除了最高位以外的其他位,[0:99]这种，如果最高位是2，则需要计算[0-99]和[100:199]，也就是first的个数
        // tem1/10就是除了最高位不参与个数，剩下的数从个位是1，剩下的位任意组合，十位是1，剩下的任意组合.....
        // 最后累加一共len-1组,所以是(len-1) * (tem1/10)
        int otherOneNum = first * (len-1) * (tem1/10);
        return firstOneNum + otherOneNum + oneNum(n%tem1);
    }




    private static int getLen(int n) {
        int res = 0;
        while (n != 0){
            res++;
            n /= 10;
        }
        return res;
    }


    /**
     * 遍历
     * @param n
     * @return
     */
    public static int oneNum(int n){
        if (n < 1){
            return 0;
        }
        int res = 0;
        for (int i = 1; i <= n; i++){
            res += getNum(i);
        }
        return res;
    }

    /**
     * 获取i中的1的个数
     * @param i
     * @return
     */
    private static int getNum(int i) {
        int res = 0;
        int base = 10;
        while (i > 0){
            res++;
            i = i/base;
        }
        return res;
    }


    /**
     * 二轮测试： 给定整数num，求1-num中1出现的次数
     * @param num
     * @return
     */
    public static int oneNumCp1(int num) {
        if (num < 1) {
            return 0;
        }
        if (num < 10) {
            // 10以内的数只有一个1
            return 1;
        }
        // 计算位数
        int len = 0;
        int tem = num;
        while (tem != 0) {
            len ++;
            tem /= 10;
        }
        // 求起点
        tem = num;
        // 最高位
        int height = (int)(tem / Math.pow(10, len-1));
        // 当前轮的起点
        int start = (int)(tem - (height) * Math.pow(10, len-1)) + 1;
        // 计算当前层的1的个数
        int oneNum = 0;
        // 先计算最高位是1的情况
        if (height == 1) {
            // 最高位是1
            oneNum += start;
        }else {
            oneNum += Math.pow(10, len-1);
        }
        // 剩余的自由组合
        oneNum += Math.pow(10, len-2) * height * (len-1);
        return oneNum + oneNumCp1(start-1);
    }

    public static void main(String[] args) {
        System.out.println(oneNumCp1(114));
    }






}
