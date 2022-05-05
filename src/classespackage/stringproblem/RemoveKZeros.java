package classespackage.stringproblem;

/**
 * @author swzhao
 * @data 2022/5/5 9:10
 * @Discreption <> 去掉字符串中连续出现k个零的字串
 */
public class RemoveKZeros {

    public static String removeZeros(String str, int k){
        if (str == null || str.length() == 0){
            return null;
        }
        char[] chars = str.toCharArray();
        // 发现0时，start记录子串中第一个出现的0的位置
        int start = -1;
        // 出现不是‘0’时子串中出现零的个数
        int count = 0;
        for (int i = 0; i < chars.length; i++){
            if(chars[i] == '0'){
                start = start == -1 ? i : start;
                count ++;
            }else{
                if(start != -1 && count == k){
                    // 从start删除后count位
                    while (count-- >= 0){
                        chars[start++] = 0;
                    }

                }
                // 初始化下一个子串
                start = -1;
                count = 0;

            }
        }
        // 如果‘0’结尾需要判断一下0的个数是否也符合条件
        if(count == k){
            while (count-- >= 0){
                chars[start++] = 0;
            }
        }
        return String.valueOf(chars);
    }


}
