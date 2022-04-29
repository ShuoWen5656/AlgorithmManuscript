package classespackage.recursionandDP;

/**
 * @author swzhao
 * @date 2022/4/28 10:11 上午
 * @Discreption <>数组字符串转换为字母组合的种数
 */
public class Num2Chars {


    /**
     * 给定数字字符串，获取数字转为字母组合的所有种数
     * 使用递归 时间O（2^n）n为str长度
     * @param str
     * @return
     */
    public static int getNum(String str){
        if(str == null || str.length() == 0){
            return 0;
        }
        char[] chars = str.toCharArray();
        return process1(chars, 0);
    }

    /**
     * 递归方法：
     * 计算index的种数有两种情况：
     * 1、index本身就是0，无法转换，返回上一层应该是0种
     * 2、index是1-9的数字，直接转换成A-I，然后再计算index作为十位数字时，index+1处是否能跟index相互组合变成J-Z的字母
     *    如果可以，则最后的种数需要多一个index和index+1的种数，也就是index+2的种数（前index+1已经完成）
     * @param chars
     * @param index 代表chars的第index个字符，该字符之前的所有字符已经转换完毕，计算当前字符开始到结束一共有多少种数，计算完毕返回上一层
     * @return
     */
    public static int process1(char[] chars, int index){
        if(chars.length == index){
            // 这里说明index已经到chars的最后一个位置了，直接返回1种方式
            return 1;
        }
        if(chars[index] == '0'){
            // 当前index为0，无法组成任何字母
            return 0;
        }
        // 先计算以当前数字单独组成的字母
        int res = process1(chars, index + 1);
        // 如果index和index+1组成的数字在10-26之间，则需要添加一个两个数字组成的字符种数
        if(index + 1 < chars.length && (chars[index] - '0') * 10 + chars[index+1] - '0' < 27){
            res += process1(chars, index + 2);
        }
        return res;
    }

    /**
     * 类似于斐波那契数列的倒装 P（i） = P（i+1）+P（i+2）
     * @param str
     * @return
     */
    public static int getNum2(String str){
        try {
            if(str == null || str.length() == 0){
                return 0;
            }
            char[] chars = str.toCharArray();
            // 当前计算结果,计算前为p(i+1)，计算后为p(i)
            int cur = chars[chars.length-1] == '0'? 0:1;
            // p(i+2)
            int next = 1;
            // 临时变量
            int tmp = 0;
            for (int i = chars.length-2; i >= 0; i--){
                if(chars[i] == '0'){
                    next = cur;
                    cur = 0;
                }else{
                    // 暂存给next
                    tmp = cur;
                    if((chars[i] - '0') * 10 + chars[i+1]-'0' < 27){
                        cur += next;
                    }
                }
                next = tmp;
            }
            return cur;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }



}
