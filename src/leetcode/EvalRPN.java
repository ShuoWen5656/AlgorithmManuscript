package leetcode;

import java.util.Stack;

/**
 * @author swzhao
 * @date 2023/11/6 8:06 下午
 * @Discreption <> 逆波兰表达式求值
 */
public class EvalRPN {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"4","13","5","/","+"}));


    }


    public static int solution(String[] tokens) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (isNum(tokens[i])) {
                stack.push(tokens[i]);
            }else {
                String int1 = stack.pop();
                String int2 = stack.pop();
                // 说明是符号
                switch (tokens[i]) {
                    case "+":
                        stack.push(String.valueOf(Integer.parseInt(int2) + Integer.parseInt(int1)));
                        break;
                    case "-":
                        stack.push(String.valueOf(Integer.parseInt(int2) - Integer.parseInt(int1)));
                        break;
                    case "*":
                        stack.push(String.valueOf(Integer.parseInt(int2) * Integer.parseInt(int1)));
                        break;
                    case "/":
                        stack.push(String.valueOf(Integer.parseInt(int2) / Integer.parseInt(int1)));
                        break;
                    default:
                        break;
                }
            }
        }
        return Integer.valueOf(stack.pop());
    }


    public static boolean isNum(String s) {
        return !s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/");
    }
}

