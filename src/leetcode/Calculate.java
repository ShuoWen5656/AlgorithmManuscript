package leetcode;

import java.util.Stack;

/**
 * @author swzhao
 * @date 2023/10/29 11:02 上午
 * @Discreption <> 基本计算器
 */
public class Calculate {


    public static void main(String[] args) {
        System.out.println(solution("2147483647"));
    }

    public static int solution(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 用来存符号
        Stack<Integer> stack = new Stack<>();
        // 开始正数
        stack.push(1);
        char[] chars = s.toCharArray();
        int len = s.length();
        int index = 0;
        int res = 0;
        // 符号位
        int sign = 1;
        while (index < len) {
            if (chars[index] == ' ') {
                index ++;
            }else if (chars[index] == '+') {
                // 正数符号
                sign = stack.peek();
                index ++;
            }else if (chars[index] == '-') {
                // 负数符号反转
                sign = -stack.peek();
                index ++;
            }else if (chars[index] == '(') {
                // 将当前括号前面的符号入栈 sing
                stack.push(sign);
                index ++;
            }else if (chars[index] == ')') {
                // 代表已经计算完当前括号，将括号的符号弹出
                stack.pop();
                index ++;
            }else {
                // 数字需要单独计算
                int num = 0;
                while (index < len && (chars[index] >= '0' && chars[index] <= '9')) {
                    num = num * 10 + (chars[index] - '0');
                    index++;
                }
                // 直接加到结果
                res += sign * num;
            }
        }
        return res;
    }


}
