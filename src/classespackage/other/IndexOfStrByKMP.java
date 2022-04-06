package classespackage.other;

/**
 * @author swzhao
 * @date 2022/4/2 12:41 下午
 * @Discreption <>KMP算法：计算str2在str1中出现的第一个位置，不存在返回-1
 */
public class IndexOfStrByKMP {


    /**
     * 1、首先获取match的nextArray：nextArray[i] 为以i-1为结尾的和0为开头的后缀和前缀数组最大匹配字符串长度
     * 2、循环匹配match和str，一直匹配到不相同的char时记录当时的index为j（开始为i，这里的index都是以str为array）
     * 3、查询nextArray[j]的值即为j之前的前后缀最长匹配长度
     * 4、前后缀最长匹配长度确定后将match（前缀）向右移动到与str匹配的地方（后缀），然后直接从str[j]开始往后继续匹配
     * @param str
     * @param match
     * @return
     */
    public static int getIndexOf(String str, String match){
        try {
            if(match.length() > str.length()){
                return -1;
            }
            int i = 0;
            int j = 0;
            char[] strChar = str.toCharArray();
            char[] matchChar = match.toCharArray();
            int[] nextArray = getNextArray(match);
            // 只需要遍历一边
            while (i < strChar.length){
                // 当前str的j应该与match的k开始比
                int k = j-i;
                while (k < matchChar.length && strChar[j] == matchChar[k]){
                    // 当前相等,一起移动
                    j++;
                    k++;
                    continue;
                }
                // 直到不相等或者k到头了全匹配了
                if(k == matchChar.length - 1){
                    // 全匹配,返回第一个匹配位置
                    return i;
                }else{
                    // 当前j不相等,获取j的最长匹配长度，映射到k
                    int next = nextArray[k];
                    // 获取应该移动的步数
                    int move = j - next;
                    for (; move > 0; move--){
                        i++;
                    }
                }
            }
            // while结束说明匹配完了，这个时候说明没有匹配上
            return -1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }


    /**
     * 获取匹配字符串的nextArray
     * @param match
     * @return
     */
    public static int[] getNextArray(String match){
        // 至少2个元素以上
        if(match.length() == 1){
            return new int[]{-1};
        }
        // 首先match【0】元素前面么有元素，所以应该默认往前走一个所以是-1，match【1】元素前面只有0元素，前后缀没法找，所以往后移动j个
        int[] next = new int[match.length()];
        next[0] = -1;
        next[1] = 0;
        // next数组的索引，作为下一个即将填装的地方
        int index = 2;
        // next数组的索引，作为需要比较的位置
        int cn = 0;
        char[] matchChar = match.toCharArray();
        while (index < match.length()){
            if(matchChar[cn] == matchChar[index - 1]){
                // 相等说明当前位置的最大匹配长度可以拓展一个
                next[index ++] = next[++cn] + 1;
                // 这句不好理解，位置++后就是长度也就是上一把的长度，但值是一样的
//                next[index ++] = ++cn;
            }else if(cn > 0){
                // 如果没有到最左边界，并且不相等，就获取前一个cn和当前的index-1位置比较
                cn = next[cn];
            }else{
                // 到边界了，说明没有获取到最长匹配，所以是0
                next[index ++] = 0;
            }
        }
        return next;
    }

}
