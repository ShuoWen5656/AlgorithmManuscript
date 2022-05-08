package classespackage.stringproblem;

import classespackage.Constants;

/**
 * @author swzhao
 * @data 2022/5/8 10:22
 * @Discreption <> 字符串的统计字符串， aabc = a_2_b_1_c_1
 * 补充题目：给定字符串的统计字符串，和一个index，返回统计字符串原字符串的第index个字符是什么
 */
public class CountStr {

    /**
     * 题目一：
     * 1、给一个初始的res， 和一个计数的num
     * 2、如果chars[i] == chars[i+1] ,num++，否则res + num + "_"
     * @param str
     * @return
     */
    public static String countStr(String str){
        try {
            if(str == null || str.length() == 0){
                return null;
            }
            char[] chars = str.toCharArray();
            String res = String.valueOf(chars[0]) + "_";
            int num = 1;
            for (int i = 1; i < chars.length; i ++){
                if(chars[i] == chars[i-1]){
                    // 和上一个相同
                    num++;
                }else{
                    // 不相同说明需要统计了
                    // 下一个字母开始进行统计了，应该也要先加入到res中
                    res += num + "_" + chars[i] + "_";
                    num = 1;
                }
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Constants.EMPTY_STR;
        }
    }

    /**
     * 根据统计字符串获取给定位置的字符
     * @param str
     * @return
     */
    public static char getIndexChar(String str, int index){
        try {
            if(str == null || str.length() == 0){
                return 0;
            }
            char[] chars = str.toCharArray();
            // 当前遍历到的字符是什么
            char cur = 0;
            // 当前是统计状态还是非统计状态
            boolean state = false;
            // 当前如果是统计状态，则统计的数值是多少
            int num = 0;
            // 当前已经遍历到哪一个字符了
            int res = 0;
            for (int i = 0; i < chars.length; i++){
                // 判断当前是否是切换统计状态的字符
                if(chars[i] == '_'){
                    state = !state;
                    continue;
                }
                if(state){
                    // 当前是统计状态
                    num = num * 10 + (chars[i]-'0');
                }else{
                    // 说明统计状态结束，res更新
                    res += num;
                    if(res > index){
                        // 说明index在0-res之间了，并且是刚刚超过的
                        return cur;
                    }
                    // 能到这里说明还没有到index，需要继续遍历，初始化num和cur为下一个需要统计的字符
                    cur = chars[i];
                    num = 0;
                }
            }
            // 这里需要注意，最后一个数字不会切换状态需要再判断一下
            res += num;
            if(res > index){
                return cur;
            }
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }



}
