package classespackage.other;

import java.util.Random;
import java.util.function.Function;

/**
 * @author swzhao
 * @data 2022/6/28 22:22
 * @Discreption <> 从5随机到7随机及其拓展
 */
public class RandomExtention {

    /**
     * 问题1： 等概率随机数1-7，只能用random1To5来实现
     * @return
     */
    public static int random1To7(){
        int r = 0;
        while (r <= 0 || r > 20){
            // 首先实现0-4 的等概率
            int x = random1To5()-1;
            // 再实现0，5，...20 的等概率
            int y = random1To5() * 5;
            // x+y 就是0,1,2,3,4,。。。24 的等概率
            r = x + y;
        }
        // m再0,20之间时出来,因为0-20一共3个7，所以等概率
        return r%7;
    }

    /**
     * 等概率随机产生01， 只能利用random01P(0.83)
     * @return
     */
    public static int rand01(){
        // 利用了第一次的随机数等于第二次的随机数概率为50%
        int num;
        do{
            num = random01P(0.83);
        }while (num == random01P(0.83));
        return num;
    }


    /**
     * 等概率获取0-3
     * @return
     */
    public static int rand0To3(){
        return rand01() * 2 + rand01();
    }


    public static int rand0To6(){
        int num;
        do {
            // 0-15,插空儿
            num = rand0To3() * 4 + rand0To3();
            // 筛的过程
        }while (num < 12);
        // 小于12为止
        return num%6;
    }


    /**
     * 通过1-m等概率随机方法，生成等概率1-n,1-m的方法random1ToM
     * @param n
     * @param m
     * @return
     */
    public int rand1ToN(int m, int n){
        int[] nMSys = getMSystemNum(n - 1, m);
        int[] randNum = getRanMSysNumLessN(nMSys, m);
        return getNumFromMSys(randNum, m) + 1;
    }

    /**
     * 将m进制转10进制
     * @param randNum
     * @param m
     * @return
     */
    private int getNumFromMSys(int[] randNum, int m) {
        int res = 0;
        for (int i = 0; i < randNum.length; i++){
            res = res * m + randNum[i];
        }
        return res;
    }

    private int[] getRanMSysNumLessN(int[] nMSys, int m) {
        int[] res = new int[nMSys.length];
        int start = 0;
        // 找到第一个不为0的地方
        while (nMSys[start] == 0){
            start ++;
        }
        int index = start;
        boolean lastEqual = true;
        while (index != nMSys.length){
            res[index] = random1ToM(m) - 1;
            if (lastEqual){
                if (res[index] > nMSys[index]){
                    index = start;
                    lastEqual = true;
                    continue;
                }else {
                    lastEqual = res[index] == nMSys[index];
                }
            }
            index ++;
        }
        return res;
    }

    /**
     * 将value转m进制数
     * @param value
     * @param m
     * @return
     */
    private int[] getMSystemNum(int value, int m) {
        int[] res = new int[32];
        // 从后往前赋值
        int index = res.length - 1;
        while (value != 0){
            res[index--] = value%m;
            value = value/m;
        }
        return res;
    }





    /**
     * 01概率p
     * @param p
     * @return
     */
    public static int random01P(double p){
        return Math.random() > p ? 1 : 0;
    }




    /**
     * 等概率随机数1-5随机数
     * @return
     */
    public static int random1To5(){
        return (int) (Math.random() * 5) + 1;
    }

    /**
     * 等概率随机数1-m的随机数
     * @param m
     * @return
     */
    public static int random1ToM(int m){
        return (int) (Math.random() * m) +1;
    }





    public static void main(String[] args) {
        System.out.println(String.format("%.2f", 0.0124124124));
    }
}
