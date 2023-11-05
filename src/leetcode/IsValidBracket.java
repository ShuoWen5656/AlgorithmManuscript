package leetcode;

import java.util.Stack;

/**
 * @author swzhao
 * @date 2023/11/5 11:46 上午
 * @Discreption <> 有效的括号
 */
public class IsValidBracket {


    public static void main(String[] args) {
        System.out.println(solution("((()))"));
    }

    public static boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        Stack<Character> helper = new Stack<>();

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(' || chars[i] == '[' || chars[i] == '{') {
                stack.push(chars[i]);
            }else {
                // 闭合
                char other = '(';
                if (chars[i] == ']') {
                    other = '[';
                }else if (chars[i] == '}') {
                    other = '{';
                }
                if (stack.isEmpty() || stack.peek() != other) {
                    // 闭合必须要匹配
                    return false;
                }
                stack.pop();

//                // 找other
//                while (!stack.isEmpty() && stack.peek() != other) {
//                    helper.push(stack.pop());
//                }
//                if (stack.isEmpty()) {
//                    // 说明没有闭合
//                    return false;
//                }else {
//                    // 弹出
//                    stack.pop();
//                    // 将剩下的再放进来
//                    while (!helper.isEmpty()) {
//                        stack.push(helper.pop());
//                    }
//                }
            }
        }
        return stack.isEmpty();
    }
}
