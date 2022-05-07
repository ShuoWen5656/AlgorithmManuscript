package classespackage.stringproblem;

import classespackage.Constants;

/**
 * @author swzhao
 * @data 2022/5/7 19:57
 * @Discreption <> 替换字符串中连续出现的指定字符串
 */
public class ReplaceFrom2To {


    public static String replace(String str, String from, String to){
        try {
            if (str  == null || str.length() == 0){
                return null;
            }
            int m = 0;
            char[] fromChar = from.toCharArray();
            char[] strChar = str.toCharArray();
            for (int i = 0; i < strChar.length; i++){
                if(strChar[i] != fromChar[m]){
                    // 不匹配的话从头开始匹配
                    m = 0;
                    continue;
                }else if (m == from.length()-1){
                    // 当前字符串匹配并且还是最后一个字符串，将str的i-m到i个字符替换成‘0’
                    for (int j = m-i; j < m; j ++){
                        strChar[j] = 0;
                    }
                    // 匹配完成继续下一轮匹配
                    m = 0;
                }else {
                    // 继续下一个字符的匹配
                    m ++;
                }
            }
            // 最后将‘0’的字符替换成to
            String res = Constants.EMPTY_STR;
            for (int i = 0; i < strChar.length; i++){
                if(strChar[i] != 0){
                    res.concat(String.valueOf(strChar[i]));
                }
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Constants.EMPTY_STR;
        }
    }
}
