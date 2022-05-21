package classespackage.stringproblem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2022/5/21 10:17
 * @Discreption <>最小包含子串的长度
 * 找到str1中最小包含str2的长度，其中最小长度中不要求顺序和str2一样
 */
public class ContainsStr2MinLenth {


    /**
     * 主方法
     * @param str1
     * @param str2
     * @return
     */
    public static int getMinLength(String str1,String str2){
        try {
            if(str1 == null || str2 == null
                    || str1.length() < str2.length()){
                return 0;
            }
            char[] chars1 = str1.toCharArray();
            char[] chars2 = str2.toCharArray();
            Map<Character, Integer> map = initMap(str2);
            // 左边界
            int left = 0;
            // 右边界
            int right = 0;
            // 当前str1还亏欠str2几个字符
            int match = chars2.length;
            // 记录最小长度
            int res = 0;
            for (;right < chars1.length; right++){
                // 处理map
                if(map.containsKey(chars1[right])){
                    // 已经出现过的key，这里还一个回去
                    Integer integer = map.get(chars1[right]);
                    // 这里不会有不在str2中的字符数量大于0的情况，str1只会多还，不会少还不存在的字符
                    if(integer > 0){
                        // 说明当前字符还有亏欠，还一个
                        match--;
                    }
                    map.put(chars1[right], integer-1);
                }else {
                    // str2中没有的字符串，等于str1多还了一个字符
                    map.put(chars1[right], -1);
                }
                // 处理总亏欠,如果当前有亏欠，则right右边移动
                // 如果match == 0，没有亏欠了，进入左移动状态
                while (match == 0){
                    // 一值移动到match有亏欠停下
                    char char1 = chars1[left++];
                    // 查看是否亏欠
                    if(map.get(char1) + 1 > 0){
                        // 如果当前字符去掉后（map+1），有所亏欠了，则left停止移动
                        // 更新match,并且更新当前不亏钱状态的最小长度
                        res = Math.min(res, right - left + 1);
                        match++;
                        break;
                    }
                }
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 初始化str2，
     * @param str
     * @return
     */
    public static Map<Character, Integer> initMap(String str){
        // str2的字符 -> 目前str1亏欠str2的字符数量
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++){
            if(map.containsKey(chars[i])){
                Integer integer = map.get(chars[i]);
                map.put(chars[i], integer++);
            }else {
                map.put(chars[i], 1);
            }
        }
        return map;
    }

}
