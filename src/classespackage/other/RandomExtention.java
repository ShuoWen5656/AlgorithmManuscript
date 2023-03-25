package classespackage.other;

import classespackage.CommonUtils;

import java.util.Random;
import java.util.function.Function;

/**
 * @author swzhao
 * @data 2022/6/28 22:22
 * @Discreption <> 从5随机到7随机及其拓展
 * 由一个等概率的1-m的随机算法，得到另一个1-n的随机算法
 * 关键词：插空儿、筛
 * 1、插空儿：
 * · 1-m的随机算法-1为等概率的0-（m-1的算法）
 * · 0-m的随机算法*m就变成0-（m-1）*m的随机算法
 * · (random(0-m-1)-1)* m + random(0-m-1)就为等概率的0-m(m-1)的随机数生成
 * 2、筛选，这时选出小于(int)m(m-1)/n * n的数再 %7即可
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
            int y = (random1To5()-1) * 5;
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
        // 获取n的m进制表示方法
        int[] nMSys = getMSystemNum(n - 1, m);
        // 等概率产生一个0-nMSys的数，只不过是m进制表达的
        int[] randNum = getRanMSysNumLessN(nMSys, m);
        // 将m进制转化成n进制
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

    /**
     * 等概率产生一个0-nMsys的数，只不过是m进制表达的
     * @param nMSys
     * @param m
     * @return
     */
    private int[] getRanMSysNumLessN(int[] nMSys, int m) {
        // 结果保存
        int[] res = new int[nMSys.length];
        int start = 0;
        // 找到第一个不为0的地方
        while (nMSys[start] == 0){
            start ++;
        }
        int index = start;
        boolean lastEqual = true;
        while (index != nMSys.length){
            // m进制的每一位都是等概率生成的并且小于m
            res[index] = random1ToM(m) - 1;
            // 这里是因为最高位生成的数不能比实际的数大，也就是res不能大于nMSys
            // 如果大于，本次生成作废，重新从start位开始生成
            // 直到不大于的时候index可以开始计算下一位，但是还要看上一位生成的是否和实际相等，如果相等，则index++生成的数依然还要进行比对
            // 如果上一个位比实际小，那么接下来生成的数不管比实际大还是小，整体都会比实际小
            if (lastEqual){
                // lastEqual表示高位最后一个相等的位出现时，lastEqual为false
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
            // 余数为结果
            res[index--] = value%m;
            // 商为下一次的被除数
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


    /**
     * 随机返回1到5之间的数
     * @return
     */
    public static int random1to5Cp1() {
        return (int) (Math.random() * 5 + 1);
    }

    /**
     * 以p的概率出现0和1
     * @param p
     * @return
     */
    public static int randomP01Cp1(double p) {
        return Math.random() < p ? 0 : 1;
    }


    /**
     * 随机返回1到7之间的数
     * 要求：1、仅使用random1to5实现2、必须等概率
     * 方法：插空法、筛选法
     * @return
     */
    public static int random1to7Cp1() {
        int p = 0;
        while ((p = (random1to5Cp1() - 1) * 5 + (random1to5Cp1() - 1)) > 20) {
            p = (random1to5Cp1() - 1) * 5 + (random1to5Cp1() - 1);
        }
        return (int)(p%7) + 1;
    }

    /**
     * 等概率实现1到6的随机函数
     * 要求1、仅使用randomP01Cp1实现2、等概率
     * @return
     */
    public static int random1to6Cp1() {
        // 0-6
        int p = 0;
        while ((p = get03Cp1()*2 + (get01Cp1() + 1)) > 6) {
            p = get03Cp1()*2 + (get01Cp1() + 1);
        }
        return (int)(p%6) + 1;
    }

    /**
     * 将不等概率的变成等概率返回0和1
     * @return
     */
    private static int get01Cp1() {
        int num1 = 0, num2 = 0;
        while (((num1 = randomP01Cp1(0.83)) ^ (num2 = randomP01Cp1(0.83))) != 1);
        return num1;
    }

    /**
     * 等概率获取0123
     * @return
     */
    private static int get03Cp1() {
        return get01Cp1() * 2 + get01Cp1();
    }


    /**
     * 给定一个m，返回1-m等概率出现
     * @param m
     * @return
     */
    private static int get1ToM(int m) {
        return (int)(Math.random() * m) + 1;
    }

    /**
     * 通过等概率的1-m，求等概率的1-n
     * @param m
     * @param n
     * @return
     */
    private static int get1TonFromM(int m, int n) {
        // 首先求用m进制表示的n-1
        int[] mSysN = getSysMNum(n-1, m);
        // 产生一个小于mSysN的m进制的数
        int[] mSys1ToN = getSysM1ToN(mSysN, m);
        // 再转回到10进制
        return get10SysFromM(mSys1ToN, m) + 1;
    }

    /**
     * 从m进制转回到10进制
     * @param mSys1ToN
     * @param m
     * @return
     */
    private static int get10SysFromM(int[] mSys1ToN, int m) {
        int index = mSys1ToN.length-1;
        int res = 0;
        while (index >= 0) {
            res += Math.pow(m, (mSys1ToN.length-1) - index) * mSys1ToN[index];
            index--;
        }
        return res;
    }

    /**
     * 等概率随机获取一个小于mSysN的值
     * @param mSysN
     * @param m
     * @return
     */
    private static int[] getSysM1ToN(int[] mSysN, int m) {
        // 找到左边第一个不为0的
        int start = 0;
        while (mSysN[start] == 0) {
            start++;
        }
        // 生成数游标
        int index = start;
        int[] res = new int[32];
        // 表示上一个值是否和mSysN相等，如果相等，则这波还要继续生成，如果不相等说明已经大于了直接随机生成即可
        boolean isLastEquals = true;
        // 从高位开始随机获取
        while (index != mSysN.length) {
            // 先生成
            res[index] = get1ToM(m)-1;
            // 判断该位置的上一个是否相等
            if (isLastEquals) {
                // 相等则该位置需要比较
                if (res[index] > mSysN[index]) {
                    // 说明生成的超过了，归位,全部重来
                    index = start;
                    isLastEquals = true;
                    continue;
                }else {
                    isLastEquals = res[index] == mSysN[index];
                }
            }
            // 该位置不需要比较
            index++;
        }
        return res;
    }

    /**
     * 用m进制表示value
     * @param value
     * @param m
     * @return
     */
    private static int[] getSysMNum(int value, int m) {
        int[] res = new int[32];
        int mod = value;
        int tem = value;
        int index = res.length-1;
        while (tem != 0) {
            res[index--] = mod % m;
            tem = mod/m;
            mod = mod%m;
        }
        return res;
    }



    public static void main(String[] args) {
        int[] ints = new int[5];
        for (int i = 0; i < 100 ; i++ ){
            //System.out.println(get1TonFromM(7, 5));
            ints[get1TonFromM(7,5)-1] ++;
            //System.out.println();
        }
        CommonUtils.printArr(ints);
        //System.out.println(String.format("%.2f", 0.0124124124));
    }
}
