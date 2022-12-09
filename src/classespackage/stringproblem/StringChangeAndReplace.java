package classespackage.stringproblem;

/**
 * @author swzhao
 * @data 2022/5/11 21:22
 * @Discreption <> 字符串的调整与替换
 * 问题一：将char类型数组中的‘ ’字符全部替换成%20三个字符，要求时间O（n），空间O（1）
 * 问题二：char类型数组由'*'和数字类型字符组成，将‘*’全部移动到前面去，并且要求时间O（n），空间O（1）
 */
public class StringChangeAndReplace {


    /**
     * 问题一：
     * @param chars
     * @return
     */
    public static char[] replace(char[] chars){
        try {
            if (chars == null || chars.length == 0){
                return null;
            }
            // 空格字符的数量
            int num = 0;
            // chars的非0长度
            int len = 0;
            // 这次的循环主要获取两个信息：1、空格的数量2、
            for (len = 0; len < chars.length && chars[len] != 0; len++){
                if(chars[len] == ' '){
                    num++;
                }
            }
//            char[] res = new char[chars.length + num * 2];
            int j = len + num *2 -1;
            for (int i = chars.length-1; i >= 0; i--){
                if(chars[i] == ' '){
                    // 空格替换成%20
                    chars[j--] = '0';
                    chars[j--] = '2';
                    chars[j--] = '%';
                }else{
                    // 不是空格的直接放进来
                    chars[j--] = chars[i];
                }
            }
            return chars;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 问题二：
     * @param chars
     * @return
     */
    public static char[] move(char[] chars){
        try {
            if(chars == null || chars.length == 0){
                return null;
            }
            // 最右边的*号位置
            int right = 0;
            // 找到最右边的*位置
            for (int i = chars.length-1; i >= 0; i--){
                if(chars[i] == '*' && right == 0){
                    right = i;
                }
            }
            for (int j = chars.length; j >= 0; j--){
                if (chars[j] != '*' && j < right){
                    // 需要将right替换成当前字符并将当前字符替换成*,right向前移动一个位置（这里right往前移动一定是*，因为j<right说明前面已经遍历过替换过）
                    chars[right--] = chars[j];
                    chars[j] = '*';
                }
//                if(chars[j] != '*' && j > right){
//                    // 在右边的数字不需要动
//                    continue;
//                }else if(j == right){
//
//                }
            }
            return chars;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 问题二：方法二
     * @param chars
     * @return
     */
    public static char[] move2(char[] chars){
        try {
            if(chars == null || chars.length == 0){
                return null;
            }
            // 最右边的*号位置
            int j = 0;
            // 将不是*的全部移动到最右边
            for (int i = chars.length-1; i >= 0; i--){
                if (chars[i] != '*'){
                    chars[j--] = chars[i];
                }
            }
            // 将剩下的全部变成*
            for (;j>-1;){
                chars[j--] = '*';
            }
            return chars;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 二轮测试：问题1
     * @param chars
     * @return
     */
    public static char[] pro1Cp1(char[] chars) {
        if (chars == null || chars.length == 0) {
            return null;
        }
        int index1 = chars.length-1;
        while (chars[index1] == 0) {
            index1--;
        }
        int index2 = chars.length-1;
        while (index1 >= 0) {
            if (chars[index1] == ' '){
                chars[index2--] = '%';
                chars[index2--] = '0';
                chars[index2--] = '2';
            }else {
                chars[index2--] = chars[index1];
            }
            chars[index1] = 0;
            index1--;
        }
        return chars;
    }


    /**
     * 二轮测试：问题二
     * @param chars
     * @return
     */
    public static char[] moveCp2(char[] chars){
        if (chars == null || chars.length == 0) {
            return null;
        }
        int index1 = chars.length-1;
        while (chars[index1] != '*') {
            index1--;
        }
        int index2 = index1;
        while (chars[index2] == '*') {
            index2--;
        }
        while (index2 >= 0) {
            if (chars[index2] != '*') {
                chars[index1--] = chars[index2];
                chars[index2] = '*';
            }
            index2--;
        }
        return chars;
    }


    public static void main(String[] args) {
        char[] chars1 = {'1', '2', '*', '3', '5'};
        char[] chars2 = new char[chars1.length * 3];
        for (int i = 0; i < chars1.length; i++) {
            chars2[i] = chars1[i];
        }
        System.out.println(moveCp2(chars1));
    }



}
