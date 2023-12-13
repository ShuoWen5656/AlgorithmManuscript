package leetcode;

/**
 * @author swzhao
 * @date 2023/10/14 11:15 上午
 * @Discreption <> N字形变换
 */
public class ConvertN {

    public static void main(String[] args) {
        System.out.println(solution("ABCDE", 4));
    }


    public static String solution(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows == 0) {
            return "";
        }
        // 小于等于num的话直接的出答案
        if (s.length() <= numRows || numRows == 1) {
            return s;
        }
        // 第一个中点的位置,每次跳跃 num*2 - 2 个长度
        int mid = numRows-1;

        char[] chars = s.toCharArray();

        StringBuffer res = new StringBuffer();
        // 计算每一行
        for (int i = 0; i < numRows; i++) {
            // 先判断mid是否越界
            while (mid < chars.length) {
                // 添加第一个
                res.append(chars[mid - (numRows - 1 - i)]);
                // 添加第二个,不过第二个判断一下重合点
                if (i != 0 && i != numRows - 1 && mid + (numRows - 1 - i) < chars.length) {
                    res.append(chars[mid + (numRows - 1 - i)]);
                }
                // 下一个mid
                mid += (2 * numRows - 2);
            }
            // mid越界还有一部分尾巴
            if (mid - (numRows - 1 - i) < chars.length) {
                res.append(chars[mid - (numRows - 1 - i)]);
            }
            // 复位
            mid = numRows - 1;
        }

        return res.toString();
    }








}
