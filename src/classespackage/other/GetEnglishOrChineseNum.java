package classespackage.other;

import classespackage.CommonUtils;
import classespackage.Constants;
import classespackage.stackAndQueue.catDogQueue.Pet;

import java.util.concurrent.CompletionService;

/**
 * @author swzhao
 * @data 2022/7/16 12:29
 * @Discreption <> 数字的英文表达和中文表达
 */
public class GetEnglishOrChineseNum {

    /**
     * 获取数字的英文表达
     * @param num
     * @return
     */
    public static String getEnglishNum(int num){
        // 1、处理0的特殊情况
        if (num == 0){
            return "Zero";
        }
        String res = Constants.EMPTY_STR;
        // 2、处理负数的特殊情况
        if (num < 0){
            res += "Negative, ";
        }
        // 3、处理Integer.MIN_VALUE的情况
        if (num == Integer.MIN_VALUE){
            // 因为最小值就是20亿几千几百。。。,所以先处理最高位
            res += "Two Billion, ";
            // 20亿剔除，剩下的数进行常规运算
            num %= -2000000000;
        }
        num = Math.abs(num);
        String[] hightName = {"Billion", "Million", "Thousand", ""};
        int highIndex = 0;
        // 从10亿开始往回算
        int high = 1000000000;
        int cur = 0;
        while (num != 0){
            cur = num / high;
            // 下一波要计算的剩下的数
            num = num % high;
            if (cur != 0){
                // 说明有几个hight
                res += num1To999(cur) + hightName[highIndex] + (num == 0? "" : ",");
            }
            highIndex++;
            high /= 1000;
        }
        return res;
    }


    /**
     * 获取数字1-19的英文表达
     * @param num
     * @return
     */
    public static String num1To19(int num){
        if (num < 1 || num > 19){
            return Constants.EMPTY_STR;
        }
        // [1:19] 的英文表达
        String[] nums = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "...", "Nineteen"};
        return nums[num-1];
    }


    /**
     * 获取数字1-99的表达
     * @param num
     * @return
     */
    public static String num1To99(int num){
        if (num < 1 || num > 99){
            return Constants.EMPTY_STR;
        }
        if (num <= 19){
            return num1To19(num);
        }
        // 20-90
        String[] nums = new String[]{"Twenty", "Thirty",  "...", "Ninety"};

        // 获取10位
        int tyNum = num/10;
        return nums[tyNum-2] + num1To19(num%10);
    }

    /**
     * 获取1-999的表达
     * @param num
     * @return
     */
    public static String num1To999(int num){
        if (num < 1 || num > 999){
            return Constants.EMPTY_STR;
        }
        if (num <= 99){
            return num1To99(num);
        }
        return num1To19(num/100) + "Hundred" + num1To99(num % 100);
    }

    /*****************************************************************************************/

    /**
     * 获取数字的中文表达
     * @param num
     * @return
     */
    public static String getChineseNum(int num){
        if (num == 0){
            return Constants.ZERO_ZH;
        }
        // 判断正负
        String res = num < 0 ? "负" : Constants.EMPTY_STR;
        // 几个亿
        int yi = Math.abs(num/100000000);
        // 剩下的
        int rest = num%100000000;
        if (yi == 0){
            //没有过亿，直接返回
            return res + num1to99999999(num);
        }
        // 过亿
        res += num1to99999999(yi) + "亿";
        if (rest == 0){
            return res;
        }else {
            if (rest < 10000000){
                // 带零
                res += "零" + num1to99999999(rest);
            }else {
                res += num1to99999999(rest);
            }
        }
        return res;
    }


    /**
     * 1-9的中文表达
     * @param num
     * @return
     */
    public static String num1to9ZH(int num){
        if (num < 1 || num > 9){
            return Constants.EMPTY_STR;
        }
        String[] nums = {"一", "...", "九"};
        return nums[num-1];
    }

    /**
     * 1-99的中文表达
     * @param hasBai 是否有百位，如果有百位则 15 = 一十五，否则是 十五
     * @param num
     * @return
     */
    public static String num1to99ZH(int num, boolean hasBai){
        if (num < 1 || num > 99){
            return Constants.EMPTY_STR;
        }
        if (num <= 9){
            return num1to9ZH(num);
        }
        // 这两种方法比下面的2方法会少用内存
        int ten = num / 10;
        if (ten == 1 && !hasBai){
            // 没有百位并且是1几时，为十几
            return "十" + num1to9ZH(num);
        }else {
            // 十位不是1或者有百位,返回几十几
            return num1to9ZH(num) + "十" + num1to9ZH(num);
        }
        // 2、十几的枚举
        //String[] nums = {"十", "二十", "三十", "四十", "五十", "...", "九十"};
        //return nums[num/10-1] + num1to9ZH(num%10);
    }

    /**
     * 1-999的中文表达
     * @return
     */
    public static String num1to999ZH(int num){
        if (num < 1 || num > 999){
            return Constants.EMPTY_STR;
        }
        if (num <= 99){
            // 不带百位的
            return num1to99ZH(num, false);
        }
        // 接下来是带百位的
        String res = String.format("%s百", num1to9ZH(num/100));
        int rest = num%100;
        if (rest == 0){
            // 整百数
            return res;
        }else if (rest < 10){
            // 这里说明要几百零几
            res += "零" + num1to9ZH(num);
        }else {
            res += num1to99ZH(rest, true);
        }
        return res;
    }


    /**
     * 1-9999
     * @param num
     * @return
     */
    public static String num1to9999ZH(int num){
        if (num < 1 || num > 9999){
            return Constants.EMPTY_STR;
        }
        if (num <= 999){
            return num1to999ZH(num);
        }
        // 带千的, res = 几千
        String res = num/1000 + "千";
        int rest = num%1000;
        if (rest == 0){
            return res;
        }else if (rest < 100){
            // 有零
            res += "零" + num1to999ZH(num);
        }else {
            res += num1To999(num);
        }
        return res;
    }

    /**
     * 带万字
     * @param num
     * @return
     */
    public static String num1to99999999(int num){
        if (num < 1|| num > 99999999){
            return Constants.EMPTY_STR;
        }
        if (num <= 9999){
            return num1to9999ZH(num);
        }
        // 几万
        int wan = num/10000;
        // 剩下的
        int rest = num % 10000;
        String res = num1to9999ZH(wan) + "万";
        if (rest == 0){
            return res;
        }else {
            if (rest < 1000){
                // 几万零几
                res += "零" + num1to9999ZH(num);
            }else {
                res += num1to9999ZH(num);
            }
        }
        return res;
    }


    /*****************************************************************************************************************


    /**
     * 二轮测试:数字的英文表达
     * 英文都是3位一组：
     * 如   XXxx thousand XXXX(剩下的自行处理)
     * 其中XXXX的处理过程都是一样的
     * 注意几个特殊处理的地方
     * 1、零
     * 2、负数
     * 3、Integer.MIN_VALUE
     * Integer的最大值为20亿
     * @param num
     * @return
     */
    public static String getEnglishNumCp1(int num) {
        if (num == 0) {
            return "Zero";
        }
        String res = Constants.EMPTY_STR;
        if (num < 0) {
            res += "Negative, ";
        }
        if (num == Integer.MIN_VALUE) {
            res += "Tow Billion, ";
            num -= 2000000000;
        }
        String[] nams = {" Billion", " Million", " Thousand", ""};
        int initNum = 1000000000;
        int high = 0;
        while (num != 0) {
            int cur = num / initNum;
            if (cur != 0) {
                res += num1To999Cp1(cur) + nams[high] + ((num %= initNum) == 0 ? "" : ", ");
            }
            high++;
            initNum/=1000;
        }
        return res;
    }

    /**
     * 获取数字1-19的英文表达
     * @param num
     * @return
     */
    public static String num1To19Cp1(int num){
        if (num < 1 || num > 19){
            return Constants.EMPTY_STR;
        }
        // [1:19] 的英文表达
        String[] nums = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "FifTeen", "SixTeen", "SevenTeen", "Eighteen", "Nineteen"};
        return nums[num-1];
    }


    /**
     * 获取数字1-99的英文表达
     * @param num
     * @return
     */
    public static String num1To99Cp1(int num) {
        if (num < 1 || num > 99) {
            return Constants.EMPTY_STR;
        }
        // 从20开始的十位数表达
        String[] nums = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighteen", "Ninety"};
        return num < 20 ? num1To19Cp1(num) : (nums[num/10 - 2] + " " + num1To19Cp1(num%10));
    }

    /**
     * 获取数字1-999的英文表达
     * @param num
     * @return
     */
    public static String num1To999Cp1(int num) {
        if (num < 1 || num > 999) {
            return Constants.EMPTY_STR;
        }
        // 从20开始的十位数表达
        String[] nums = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        return num < 100 ? num1To99Cp1(num) : String.format("%s Hundred %s", nums[num/100 - 1], num1To99Cp1(num%100));
    }


    /*****************************************************************************************************************/


    /**
     * 二轮测试：中文表达
     * 1、零
     * 2、负数
     * 3、最小值
     * 4、判断这组结束后是否要加 零
     * @param num
     * @return
     */
    public static String getChineseNumCp1(int num) {
        if (num == 0) {
            return Constants.ZERO_ZH;
        }
        String res = Constants.EMPTY_STR;
        if (num < 0) {
            res += "负";
        }
        if (num == Integer.MIN_VALUE) {
            res += "二十亿";
            num -= 2000000000;
        }
        String[] nums = {"亿", "万", ""};
        int initNum = 100000000;
        int high = 0;
        while (num != 0) {
            int cur = num / initNum;
            if (cur != 0) {
                res += num1to9999ZHCp(cur) + nums[high];
                num %= initNum;
                // 判断是否需要加零
                if (initNum != 1 && (num % initNum)/(initNum/10) == 0) {
                    res += Constants.ZERO_ZH;
                }
            }
            initNum /= 10000;
            high++;
        }
        return res;
    }

    /**
     * 获取1到9
     * @param num
     * @return
     */
    public static String num1to9ZHCp1(int num) {
        if (num < 1 || num > 9) {
            return Constants.EMPTY_STR;
        }
        String[] nums = {"一", "二", "三","四","五","六","七","八", "九"};
        return nums[num-1];
    }

    /**
     * 获取1-99
     * 注意：
     * 如果含有百位，则应该在十前面加上一
     * 如111  = 一百一十一
     * @param num
     * @param hasBai
     * @return
     */
    public static String num1to99ZHCp1(int num, boolean hasBai) {
        if (num < 1 || num > 99) {
            return Constants.EMPTY_STR;
        }
        String[] nums = {"十", "二十", "三十", "四十", "五十", "六十", "七十", "八十", "九十"};
        String res = Constants.EMPTY_STR;
        if (num / 10 == 1 && hasBai) {
            res += "一";
        }
        return  res += nums[num/10 - 1] + num1to9ZHCp1(num%10);
    }

    /**
     * 获取1 - 999
     * @param num
     * @return
     */
    public static String num1to999ZHCp(int num) {
        if (num < 1 || num > 999) {
            return Constants.EMPTY_STR;
        }
        if (num < 100) {
            return num1to99ZHCp1(num, false);
        }
        String res = num1to9ZH(num / 100) + "百";
        num %= 100;
        if (num == 0) {
            return res;
        }
        res += num /10 == 0 ? "零" : "";
        res += num1to99ZHCp1(num, true);
        return res;
    }


    /**
     * 获取1 - 999
     * @param num
     * @return
     */
    public static String num1to9999ZHCp(int num) {
        if (num < 1 || num > 9999) {
            return Constants.EMPTY_STR;
        }
        if (num < 1000) {
            return num1to999ZHCp(num);
        }
        String res = num1to9ZH(num / 1000) + "千";
        num %= 1000;
        if (num == 0) {
            return res;
        }
        res += num /100 == 0 ? "零" : "";
        res += num1to999ZHCp(num);
        return res;
    }







    public static void main(String[] args) {
        System.out.println(getChineseNumCp1(1111111112));
    }




}
