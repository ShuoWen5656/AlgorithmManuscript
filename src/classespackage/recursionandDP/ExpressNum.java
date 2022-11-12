package classespackage.recursionandDP;

import classespackage.stackAndQueue.catDogQueue.Pet;

import java.util.Arrays;

/**
 * @author swzhao
 * @date 2022/4/29 11:30 上午
 * @Discreption <>表达式得到期望结果的组成种数
 * 这里面的组合是计算顺序，而不是能够更换计算位置
 */
public class ExpressNum {

    /**
     * 获取desired的express组合种数
     * 暴力递归，从左往右，左边为。。的种数x右边为。。的种数相加
     * 时间O(N!)
     * @param express
     * @param desired
     * @return
     */
    public static int getNum(String express, boolean desired){
        try {
            if(express == null || express.length() ==0){
                return 0;
            }
            char[] expressChar = express.toCharArray();
            // 这里需要判断奇数位是计算符号，偶数位是数字
            if(!isValid(expressChar)){
                return 0;
            }
            if(expressChar.length == 1){
                // 结束条件
                if(desired && expressChar[0] == '1'){
                    return 1;
                }else {
                    return 0;
                }
            }
            // 分割操作符左边最后一位和右边第一位
            int l = 0;
            int r = 0;
            int res = 0;
            // 根据操作符进行计算
            for (int i = 1; i < expressChar.length - 1; i +=2){
                l = i;
                r = i + 1;
                switch (expressChar[i]){
                    case '^':
                        // 异或
                        if(desired){
                            res += getNum(String.valueOf(Arrays.copyOfRange(expressChar, 0, l)), false)
                                    * getNum(String.valueOf(Arrays.copyOfRange(expressChar, r, expressChar.length)), true)
                                    + getNum(String.valueOf(Arrays.copyOfRange(expressChar, 0, l)), true)
                                    * getNum(String.valueOf(Arrays.copyOfRange(expressChar, r, expressChar.length)), false);
                        }else{
                            res += getNum(String.valueOf(Arrays.copyOfRange(expressChar, 0, l)), false)
                                    * getNum(String.valueOf(Arrays.copyOfRange(expressChar, r, expressChar.length)), false)
                                    + getNum(String.valueOf(Arrays.copyOfRange(expressChar, 0, l)), true)
                                    * getNum(String.valueOf(Arrays.copyOfRange(expressChar, r, expressChar.length)), true);
                        }
                    case '|':
                        if(desired){
                            res += getNum(String.valueOf(Arrays.copyOfRange(expressChar, 0, l)), true)*getNum(String.valueOf(Arrays.copyOfRange(expressChar, r, expressChar.length)), true)
                                    +getNum(String.valueOf(Arrays.copyOfRange(expressChar, 0, l)), true)*getNum(String.valueOf(Arrays.copyOfRange(expressChar, r, expressChar.length)), false)
                                    +getNum(String.valueOf(Arrays.copyOfRange(expressChar, 0, l)), false)*getNum(String.valueOf(Arrays.copyOfRange(expressChar, r, expressChar.length)), true);
                        }else{
                            res += getNum(String.valueOf(Arrays.copyOfRange(expressChar, 0, l)), false)*getNum(String.valueOf(Arrays.copyOfRange(expressChar, r, expressChar.length)), false);
                        }
                    case '&':
                        if(desired){
                            if(desired){
                                res += getNum(String.valueOf(Arrays.copyOfRange(expressChar, 0, l)), true)*getNum(String.valueOf(Arrays.copyOfRange(expressChar, r, expressChar.length)), true);
                            }else{
                                res += getNum(String.valueOf(Arrays.copyOfRange(expressChar, 0, l)), false)*getNum(String.valueOf(Arrays.copyOfRange(expressChar, r, expressChar.length)), true)
                                        +getNum(String.valueOf(Arrays.copyOfRange(expressChar, 0, l)), true)*getNum(String.valueOf(Arrays.copyOfRange(expressChar, r, expressChar.length)), false)
                                        +getNum(String.valueOf(Arrays.copyOfRange(expressChar, 0, l)), false)*getNum(String.valueOf(Arrays.copyOfRange(expressChar, r, expressChar.length)), false);
                            }
                        }
                    default:
                        return 0;
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
     * 动态规划方法
     * 一个t矩阵和一个f矩阵，t[i][j] 代表从i到j的结果为true的种数
     * f[i][j] 代表从i到j的结果为false的种数
     * 空间O（n^2）,时间O（n^3）
     * @return
     */
    public static int getNum2(String express, boolean desired){
        try{
            if(express == null || express.length() ==0){
                return 0;
            }
            char[] expressChar = express.toCharArray();
            // 这里需要判断奇数位是计算符号，偶数位是数字
            if(!isValid(expressChar)){
                return 0;
            }
            int[][] t = new int[expressChar.length][expressChar.length];
            int[][] f = new int[expressChar.length][expressChar.length];
            t[0][0] = expressChar[0] == '0' ? 0 : 1;
            f[0][0] = expressChar[0] == '1' ? 0 : 1;
            for (int i = 2; i < expressChar.length; i+=2){
                t[i][i] = expressChar[i] == '0' ? 0: 1;
                f[i][i] = expressChar[i] == '1' ? 0: 1;
                for (int j = i-2; j >= 0; j -= 2){
                    // 从i,i开始往上面走
                    for (int k = j; k <= i; k += 2){
                        // 加的总数需要根据当前分割符号来定
                        if(expressChar[k+1] == '^'){
                            // 为true的，两边不一样即可
                            t[j][i] += t[j][k]*f[k+2][i] + f[j][k]*t[k+2][i];
                            // 为false，两边一样即可
                            f[j][i] += t[j][k]*t[k+2][i] + f[j][k]*f[k+2][i];
                        }else if (expressChar[k+1] == '|'){
                            // 为true的，只要一个为true就行
                            t[j][i] += t[j][k]*t[k+2][i] + t[j][k]*f[k+2][i] + f[j][k]*t[k+2][i];
                            // false就只有一种情况，两边都是false
                            f[j][i] += f[j][k]*f[k+2][i];
                        }else if (expressChar[k+1] == '&'){
                            // 为true，两边都是true才行
                            t[j][i] += t[j][k]*t[k+2][i];
                            // false只要右一个false就是false
                            f[j][i] += f[j][k]*f[k+2][i] + t[j][k]*f[k+2][i] + f[j][k]*t[k+2][i];
                        }
                    }
                }
            }
            return desired ? t[0][expressChar.length-1] : f[0][expressChar.length-1];
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 校验方法：
     * 1、奇数位是 ^、|、& 三种字符
     * @param expressChar
     * @return
     */
    public static boolean isValid(char[] expressChar){
        if(expressChar.length == 0 || expressChar.length >> 1 == 0){
            return false;
        }
        for (int i = 0; i < expressChar.length-1; i += 2){
            // 计算偶数位是否时0、1
            if (expressChar[i] != '0' && expressChar[i] != '1'){
                return false;
            }
        }
        for (int i = 1; i < expressChar.length-1; i+=2){
            if(expressChar[i] != '^' && expressChar[i] != '|' && expressChar[i] != '&'){
                return false;
            }
        }
        return true;
    }


    /**
     * 二轮测试：暴力递归
     * @param str
     * @return
     */
    public static int getNumCp1(String str, boolean aim) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        if (!isValid(chars)) {
            return 0;
        }
        return processForCp1(chars, 0, chars.length-1, aim ? '1' : '0');
    }

    /**
     * 递归主体
     * @param chars
     * @param start
     * @param end
     * @return
     */
    private static int processForCp1(char[] chars, int start, int end, char aim) {
        if (start > end) {
            System.out.println(start + ", " + end);
        }
        if (start == end) {
            // 只有一个元素
            return chars[start] == aim ? 1 : 0;
        }
        int res = 0;
        for (int i = start + 1; i <= end-1; i +=2) {
            // aim影响了符号两边的判断
            if (aim == '1') {
                // 目标是true
                if (chars[i] == '&') {
                    // 两边必须是1
                    res += processForCp1(chars, start, i-1, '1') * processForCp1(chars, i+1, end, '1');
                }else if (chars[i] == '|') {
                    // 有一边是1即可
                    res += processForCp1(chars, start, i-1, '1') * processForCp1(chars, i+1, end, '1');
                    res += processForCp1(chars, start, i-1, '1') * processForCp1(chars, i+1, end, '0');
                    res += processForCp1(chars, start, i-1, '0') * processForCp1(chars, i+1, end, '1');
                }else if (chars[i] == '^') {
                    // 两边不一样就行
                    res += processForCp1(chars, start, i-1, '1') * processForCp1(chars, i+1, end, '0');
                    res += processForCp1(chars, start, i-1, '0') * processForCp1(chars, i+1, end, '1');
                }
            }else {
                // 目标是false
                if (chars[i] == '&') {
                    // 两边不一样即可
                    res += processForCp1(chars, start, i-1, '1') * processForCp1(chars, i+1, end, '0');
                    res += processForCp1(chars, start, i-1, '0') * processForCp1(chars, i+1, end, '1');
                }else if (chars[i] == '|') {
                    // 都是0才行
                    res += processForCp1(chars, start, i-1, '0') * processForCp1(chars, i+1, end, '0');
                }else if (chars[i] == '^') {
                    // 两边一样
                    res += processForCp1(chars, start, i-1, '1') * processForCp1(chars, i+1, end, '1');
                    res += processForCp1(chars, start, i-1, '0') * processForCp1(chars, i+1, end, '0');
                }
            }
        }
        return res;
    }


    /**
     * 动态规划方法
     * @param str
     * @param aim
     * @return
     */
    public static int dpCp1(String str, boolean aim) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char aimC = aim ? '1' : '0';
        char[] chars = str.toCharArray();
        if (!isValid(chars)) {
            return 0;
        }
        // 创建两张表，一张为结果为true种数表，另一个是结果为false种数表
        int[][] trueDp = new int[chars.length][chars.length];
        int[][] falseDp = new int[chars.length][chars.length];
        // 填空斜对角
        for (int i = 0; i < chars.length; i+=2) {
            trueDp[i][i] = chars[i] == '1' ? 1 : 0;
            falseDp[i][i] = chars[i] == '0' ? 1 : 0;
        }
        for (int j = 2; j < chars.length; j+=2) {
            for (int i = j-2; i >= 0; i-=2) {
                trueDp[i][j] = 0;
                falseDp[i][j] = 0;
                for (int k = i+1; k <= j-1; k +=2) {
                    // k代表当前范围内的符号索引
                    if (chars[k] == '&') {
                        trueDp[i][j] += trueDp[i][k-1] * trueDp[k+1][j];

                        falseDp[i][j] += falseDp[i][k-1] * trueDp[k+1][j];
                        falseDp[i][j] += trueDp[i][k-1] * falseDp[k+1][j];
                    }else if (chars[k] == '|') {
                        trueDp[i][j] += falseDp[i][k-1] * trueDp[k+1][j];
                        trueDp[i][j] += trueDp[i][k-1] * falseDp[k+1][j];

                        falseDp[i][j] += falseDp[i][k-1] * falseDp[k+1][j];
                    }else if (chars[k] == '^') {
                        trueDp[i][j] += falseDp[i][k-1] * trueDp[k+1][j];
                        trueDp[i][j] += trueDp[i][k-1] * falseDp[k+1][j];

                        falseDp[i][j] += falseDp[i][k-1] * falseDp[k+1][j];
                        falseDp[i][j] += trueDp[i][k-1] * trueDp[k+1][j];
                    }
                }
            }
        }
        // 这里好理解些就不写节省空间的版本了，dp可以减小一半
        if (aimC == '1') {
            return trueDp[0][chars.length-1];
        }else {
            return falseDp[0][chars.length-1];
        }
    }



    public static void main(String[] args) {
        System.out.println(dpCp1("1^0|0|1", false));


    }


}
