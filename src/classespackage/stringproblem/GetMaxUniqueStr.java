package classespackage.stringproblem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2022/5/19 22:15
 * @Discreption <>找到字符串中最长无重复字符串子串
 */
public class GetMaxUniqueStr {

    /**
     * 主方法
     * @param str
     */
    public static int getMaxUniqueStr(String str){
        try {
            if(str == null || str.length() == 0){
                return 0;
            }
            char[] chars = str.toCharArray();
            // 字符-》字符最近出现的位置
            Map<Character, Integer> map = new HashMap<>();
            // 位置i-1结尾的最长不重复字符串的前一位
            int pre = -1;
            // 结果
            int len = 0;
            for (int i = 0; i < chars.length; i++){
                char cur = chars[i];
                // 获取该字符上一个出现的位置，可能为null
                Integer preIndex = map.get(cur);
                if(preIndex == null || preIndex <= pre){
                    // 在之前没有出现过或者在pre左边,pre不用变，len更新，map更新
                    len = Math.max(len, i-pre);
                    map.put(cur, i);
                }else{
                    // 出现过，并且在pre右边时，需要从preIndex截断，新长度从preIndex+1开始到i
                    pre = preIndex;
                    len = Math.max(len, i-pre);
                    map.put(cur, i);
                }

            }
            return len;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


}
