package classespackage.stringproblem;

import classespackage.Constants;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author swzhao
 * @data 2022/5/18 21:21
 * @Discreption <>拼接所有字符串产生字典顺序最小的大写字符串
 */
public class GetLowestString {


    /**
     * 该方法将String看作26进制的数
     * str1 + str2 = int(str1) * 26^str2位数 + str2
     * str1 + str2 < str2 + str1 和 str2 + str3 < str3 + str2 => str1 + str3 < str3 + str1
     * 通过这种比较方式让最后array中的所有str都能够保持str1 + str2 < str2 + str1
     */
    public static class StringComparator implements Comparator<String> {

        @Override
        public int compare(String str1, String str2) {
            return (str1 + str2).compareTo(str2 + str1);
        }
    }


    /**
     * 主方法
     * @param strs
     * @return
     */
    public static String getLowest(String[] strs){
        if(strs == null || strs.length == 0){
            return null;
        }
        Arrays.sort(strs, new StringComparator());
        String res = Constants.EMPTY_STR;
        for (int i = 0; i < strs.length; i++){
            res += strs[i];
        }
        return res;
    }


    public static String getLowestCp1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        // str1和str2是反的
        Arrays.sort(strs, (str1, str2) -> {
            if ((str2 + str1).compareTo(str1+str2) < 0) {
                return 1;
            }else{
                return -1;
            }
        });
        String res = Constants.EMPTY_STR;
        for (int i = 0 ; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getLowestCp1(new String[]{"ba", "b"}));

    }


}
