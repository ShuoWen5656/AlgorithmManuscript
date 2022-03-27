package classespackage.stackAndQueue;

import java.util.Stack;

/**
 * @author swzhao
 * @date 2021/11/17 10:27 下午
 * @Discreption <>用栈给栈排序
 */
public class SortStackByStack {
//    private Stack<Integer> help;

    /**
     * 用栈排序
     * @param stack
     */
    public static void sort(Stack<Integer> stack){
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()){
            Integer curr = stack.pop();
            while (!help.isEmpty() && help.peek() < curr){
                stack.push(help.pop());
            }
            help.push(curr);
        }
        while (!help.isEmpty()){
            stack.push(help.pop());
        }
    }
}
