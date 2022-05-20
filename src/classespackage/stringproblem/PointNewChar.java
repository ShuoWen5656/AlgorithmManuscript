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
