package classespackage.stringproblem;

/**
 * @author swzhao
 * @data 2022/5/12 21:48
 * @Discreption <>翻转字符串
 */
public class RotateWord {


    public static char[] rotateWord(char[] chars){
        try {
            if(chars == null || chars.length == 0){
                return null;
            }
            // 每一个单词的第一个字母所在的位置
            int first = 0;
            // 以最小单位开始反转
            chars = reverseChar(chars, 0, chars.length - 1);
            for (int i = 0; i < chars.length; i++){
                if(chars[i] == ' '){
                    // 统计当前字符串
                    reverseChar(chars, first, i-1);
                    // 重置
                    first = i+1;
                }
            }
            // 结尾没有空格结尾了，需要翻转一下
            reverseChar(chars, first, chars.length - 1);
            return chars;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 二轮测试：翻转字符串,内部不翻转
     */
    public static void reverseCP1(char[] chars) {
        if (chars == null || chars.length == 0) {
            return;
        }
        // 先翻转
        reverseChar(chars, 0, chars.length-1);
        int left = -1, right = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                // 可能是开头，也可能是中间
                left = i == 0 || chars[i-1] == ' ' ? i : left;
                // 可能是中间，也可能是结尾
                right = i == chars.length-1 || chars[i+1] == ' ' ? i : -1;
            }
            if (left != -1 && right != -1) {
                // 左右都找到了，直接翻转
                reverseChar(chars, left, right);
                // 重置
                left = -1;
                right = -1;
            }
        }
    }


    /**
     * 翻转char字符串的一部分
     * @param chars
     * @return
     */
    public static char[] reverseChar(char[] chars, int left, int right){
        if(chars == null || chars.length == 0 || left > right){
            return chars;
        }
        // 左边和右边调换
        while (left < right){
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left ++;
            right--;
        }
        return chars;
    }


    /**
     *  将chars左边size个元素和右边调换位置
     * 全部逆序后各自逆序即可
     * @param chars
     * @return
     */
    public static char[] reverseBySize(char[] chars, int size){
        try {
            if(chars == null || chars.length == 0){
                return chars;
            }
            reverseChar(chars, 0, chars.length-1);
            reverseChar(chars, 0, chars.length - size - 1);
            reverseChar(chars, chars.length - size, chars.length-1);
            return chars;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 问题二： 二轮测试，方法一
     */
    public static void reverseBySizeCp2(char[] chars, int size) {
        if (chars == null || chars.length == 0) {
            return;
        }
        reverseChar(chars, 0, size - 1);
        reverseChar(chars, size, chars.length-1);
        reverseChar(chars, 0, chars.length-1);
    }

    /**
     * 方法二：
     *
     * @param chars
     * @param size
     * @return
     */
    public static char[] reverseBySize2(char[] chars, int size){
        try {
            if(chars == null || chars.length == 0){
                return chars;
            }
            // 两头坐标
            int start = 0;
            int end = chars.length - 1;
            // 左右大小
            int lpart = size;
            int rpart = chars.length - size;
            // 较小的part
            int s = Math.min(lpart, rpart);
            // 差值
            int d = lpart - rpart;
            while (true){
                // 以较小的size相互替换
                exchange(chars, start, end, s);
                if(d == 0){
                    // 左边右边size相同
                    break;
                }else if(d > 0){
                    // 左边大
                    // 变start
                    start = s - 1;
                    lpart = d;
                }else{
                    // 右边大
                    end = end - s;
                    rpart = -d;
                }
                // 更新s
                s = Math.min(lpart, rpart);
                d = lpart - rpart;
            }
            return chars;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 二轮测试：递归法
     * @param chars
     * @param size
     */
    public static void reverseBySize2Cp2(char[] chars, int size) {
        if (chars == null || chars.length == 0 || size <= 0) {
            return;
        }
        reverseBySize2CpHelper(chars, 0, size-1, size, chars.length-1);
    }

    /**
     * 递归主体
     * @param chars
     * @param left1
     * @param right1
     * @param left2
     * @param right2
     */
    private static void reverseBySize2CpHelper(char[] chars, int left1, int right1, int left2, int right2) {
        // 先做一个长度差值
        int len1 = right1 - left1 + 1;
        int len2 = right2 - left2 + 1;
        // 将值复制一份，最好不要使用参数
        int left1New = left1;
        int right1New = right1;
        int left2New = left2;
        int right2New = right2;
        int offset = Math.abs(len1 - len2);

        // 注意这里只有右边长的时候才需要offset
        if (len2 > len1) {
            left2New += offset;
        }
        // 开始替换:这里其实一个判断就可以
        while (left1New <= right1New && left2New <= right2New) {
            char tmp = chars[left1New];
            chars[left1New] = chars[left2New];
            chars[left2New] = tmp;
            left1New++;
            left2New++;
        }
        // 替换完成后,开始准备下一轮递归
        if (len1 > len2) {
            reverseBySize2CpHelper(chars, left1New, right1, left2, right2);
        }else if (len1 == len2){
            return;
        }else {
            reverseBySize2CpHelper(chars, left1, right1, left2, left2 + offset - 1);
        }
    }

    public static void main(String[] args) {
        char[] chars = "pig loves dog".toCharArray();

        reverseCP1(chars);
    }



    public static void exchange(char[] chars, int start, int end, int size){
        int i = end - size + 1;
        int index = 0;
        while (i++ < chars.length){
            char tem = chars[i];
            chars[i] = chars[index];
            chars[index++] = tem;
        }
    }






}
