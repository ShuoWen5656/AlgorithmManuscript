package classespackage.stringproblem;

import classespackage.Constants;

/**
 * @author swzhao
 * @data 2022/5/20 19:50
 * @Discreption <>找到被指的新类型字符
 */
public class PointNewChar {


    public static String getPoint(String str, int k){
        try {
            if(str == null || str.length() == 0){
                return Constants.EMPTY_STR;
            }
            char[] chars = str.toCharArray();
            if (k >= chars.length){
                return Constants.EMPTY_STR;
            }
            char[] resChar = new char[2];
            // 从k-1开始往左边找到第一个小写字符
            int temIndex = 0;
            for (int i = k-1; i >= 0; i--){
                if(!isUpper(chars[i])){
                    // 第一个遇到的小写字符
                    if((k-i+1)%2 == 0){
                        // 偶数个大写字符
                        if(!isUpper(chars[k])){
                            // k位置小写，不能与前面的组合否则没法玩，只能单独
                            resChar[0] = chars[k];
                            break;
                        }else{
                            // k位置大写，不能单独结对，只能跟后面的结对，
                            resChar[0] = chars[k];
                            resChar[1] = chars[k+1];
                            break;
                        }
                    }else{
                        // 基数个大写字符
                        resChar[0] = chars[k-1];
                        resChar[1] = chars[k];
                        break;
                    }
                }
            }


            return String.valueOf(resChar);
        }catch (Exception e){
            e.printStackTrace();
            return Constants.EMPTY_STR;
        }
    }


    /**
     * 二轮测试：找到指向的新型字符串
     * @param str
     * @param k
     * @return
     */
    public static String getPointCp1(String str, int k) {
        if (str == null || str.length() == 0
                || k > str.length() || k <= 0) {
            return null;
        }
        // 将第k个转成index数组索引
        int index = k-1;
        char[] chars = str.toCharArray();
        // 从k-2开始往前数有几个连续的大写字母
        int uNum = 0;
        while (index >= 0 && isUpper(chars[index])) {
            index--;
            uNum++;
        }
        index = k;
        if (uNum%2 == 0) {
            // 偶数,说明不能拆
            if (isUpper(chars[index])) {
                // 当前位置大写
                return String.valueOf(new char[]{chars[index], chars[index+1]});
            }else {
                // 当前位置小写
                return String.valueOf(new char[]{chars[index]});
            }
        }else {
            // 奇数
            return String.valueOf(new char[]{chars[index-1], chars[index]});
        }

    }


    public static void main(String[] args) {
        System.out.println(getPointCp1("aaABCDEcBCg", 10));
    }


    public static boolean isUpper(char c) throws RuntimeException{
        if(c >= 'A' && c <= 'Z'){
            return true;
        }else if(c >= 'a' && c <= 'z'){
            return false;
        }else {
            throw new  RuntimeException("Invalid Char!");
        }
    }
}
