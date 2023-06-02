package classespackage.other;

import classespackage.stackAndQueue.catDogQueue.Pet;

/**
 * @author swzhao
 * @data 2023/6/2 20:34
 * @Discreption <> KMP算法
 */
public class KMP {


    /**
     * 二轮测试：获取match字符串在str出现的第一个位置
     * @param str
     * @param match
     * @return
     */
    public static Integer getIndexFromStr(String str, String match) {
         if (str == null || match == null || str.length() == 0 || match.length() == 0) {
             return -1;
         }
        char[] ss = str.toCharArray();
        char[] ms = match.toCharArray();

        int[] nextArr = getNextArr(match);

        int sIndex = 0;
        int mIndex = 0;

        while (sIndex < ss.length && mIndex < ms.length) {
            if (ss[sIndex] == ms[mIndex]) {
                sIndex++;
                mIndex++;
            }else if (nextArr[mIndex] == -1) {
                // 说明比较的是开头
                sIndex++;
            }else {
                // sindex不变
                mIndex = nextArr[mIndex];
            }
        }
        return mIndex == ms.length ? sIndex - mIndex : -1;
    }

    private static int[] getNextArr(String match) {
        if (match.length() == 1) {
            return new int[]{-1};
        }
        char[] chars = match.toCharArray();
        int[] next = new int[chars.length];

        next[0] = -1;
        next[1] = 0;

        int pos = 2;
        int ci = 0;

        while (pos < next.length) {
            if (chars[pos-1] == chars[ci]) {
                // 将index作为长度set进去了,这里比较巧妙
                next[pos++] = ++ci;
            }else if (ci > 0){
                // 说明还没到chars[0]的位置，还可以继续循环
                ci = next[ci];
            }else {
                // 说明已经到头了,不存在前后缀相同的情况
                next[pos++] = 0;
            }
        }
        return next;
    }


    public static void main(String[] args) {
        System.out.println(getIndexFromStr("abaddf", "add"));
    }


}
