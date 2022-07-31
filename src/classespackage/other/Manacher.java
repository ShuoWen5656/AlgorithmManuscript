package classespackage.other;

import java.util.Map;

/**
 * @author swzhao
 * @data 2022/7/31 10:40
 * @Discreption <> Manacher算法
 * 进阶问题：添加最少的字符串到当前串的后面使得当前串变成回文字符串
 */
public class Manacher {


    /**
     * 将字符串加上#号
     * 1、AA = #A#A#
     * 2、BAB = #B#A#B#
     * @param str
     * @return
     */
    public static char[] manacherChar(String str){
        if (str  == null || str.length() == 0){
            return null;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        // 加上#号的容器
        char[] res = new char[chars.length * 2 + 1];
        for (int i = 0; i < chars.length; i++){
            res[i] = (i & 1) == 0? '#' : chars[++index];
        }
        return res;
    }

    /**
     * 主方法：返回最大回文字符串长度
     * @return
     */
    public static int maxLCPLength(String str){
        if (str  == null || str.length() == 0){
            return 0;
        }
        // 添加#号
        char[] chars = manacherChar(str);
        // 准备变量
        // pArr为chars中每一个变量为中轴的情况下，最长的回文字符串半径长度
        int[] pArr = new int[chars.length];
        // 当前i之前的所有pArr中扩到右边界最长的地方
        int pR = -1;
        // 最长地方的中轴位置
        int index = -1;
        // 结果
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < chars.length; i++){
            // 计算当前回文字符串的起点
            // 三种情况:
            // 1、pArr[i]完全在index的范围内
            // 2、pArr[i]边界和index重叠
            // 3、pArr[i]左边在index的左边的外面
            pArr[i] = i < pR ? Math.min(pArr[2*index-1], pR-i) : 1;
            while (i + pArr[i] < chars.length && i - pArr[i] > -1){
                if (chars[pArr[i]] == chars[i - pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            // 扩充完毕,更新pr
            if (i+pArr[i] > pR){
                pR = pArr[i] + i;
                index = i;
            }
            // 更新max
            Math.max(max, pArr[i]);
        }
        return max-1;
    }

    /**
     * 进阶问题：
     * @param str
     * @return
     */
    public static char[] shortTestEnd(String str){
        if (str  == null || str.length() == 0){
            return null;
        }
        // 添加#
        char[] chars = manacherChar(str);
        int[] pArr = new int[chars.length];
        int pR = -1;
        int index = -1;
        // pr到达end中的最大回文序列长度
        int maxEndLen = -1;
        for (int i = 0; i < chars.length; i++){
            pArr[i] = i < pR ? Math.min(pArr[index*2+1], pR-i) : 1;
            while (i + pArr[i] < chars.length && i - pArr[i] > -1){
                if (chars[pArr[i]] == chars[i-pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if (pR < i + pArr[i]){
                pR = i + pArr[i];
                index = i;
            }
            // 扩容完毕后判断是否到达终点
            if (pR == chars.length){
                maxEndLen = pArr[i];
            }
        }
        // 截取前面的chars前面的逆序加入res
        char[] res = new char[str.length() - maxEndLen + 1];
        for (int i = 0; i < res.length; i++){
            res[i] = str.charAt(str.length() - maxEndLen+1 - i);
        }
        return res;
    }





}
