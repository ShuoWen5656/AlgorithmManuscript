package classespackage.stackAndQueue;

import java.util.Stack;

/**
 * @author swzhao
 * @date 2021/11/17 10:27 下午
 * @Discreption <>用栈给栈排序
 */
public class SortStackByStack {

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

    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
        // 42531入栈顺序
        int[] ints = {4, 2, 5, 3, 1};
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < ints.length; i++){
            stack.push(ints[i]);
        }
        System.out.printf("当前栈：%s", stack);
        SortStackByStack.sort(stack);
        System.out.printf("排序后：%s", stack);
    }

}
