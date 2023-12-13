package leetcode;

import java.util.Stack;

/**
 * @author swzhao
 * @date 2023/11/6 7:53 下午
 * @Discreption <> 最小栈
 */
public class MinStack2 {

    private Stack<Integer> stackMaster;
    private Stack<Integer> stackSlaver;


    public MinStack2() {
        stackMaster = new Stack<>();
        stackSlaver = new Stack<>();
    }

    public void push(int val) {
        if (stackMaster.isEmpty()) {
            stackMaster.push(val);
            stackSlaver.push(val);
            return;
        }
        stackMaster.push(val);
        if (val <= stackSlaver.peek()){
            stackSlaver.push(val);
        }
    }

    public void pop() {
        Integer pop = stackMaster.pop();
        if (pop.equals(stackSlaver.peek())) {
            stackSlaver.pop();
        }
    }

    public int top() {
        return stackMaster.peek();
    }

    public int getMin() {
        return stackSlaver.peek();
    }


    public static void main(String[] args) {
        MinStack2 minStack2 = new MinStack2();
        minStack2.push(512);
        minStack2.push(-1024);
        minStack2.push(-1024);
        minStack2.push(512);

        minStack2.pop();
        minStack2.getMin();
        minStack2.pop();
        minStack2.getMin();
        minStack2.pop();
        minStack2.getMin();
    }

}
