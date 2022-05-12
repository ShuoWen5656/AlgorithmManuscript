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
     * 将chars左边size个元素和右边调换位置
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
