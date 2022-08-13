package secondround.stackandqueue;

import java.util.Stack;

/**
 * @author swzhao
 * @data 2022/8/12 19:45
 * @Discreption <> 设计一个有getMin功能的栈
 */
public class GetMinStack {

    /**
     * 数据栈
     */
    Stack<Integer> dataStack;
    /**
     * 最小值栈
     */
    Stack<Integer> minStack;


    public GetMinStack() {
        this.dataStack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(Integer num){
        if (minStack.isEmpty() || minStack.peek() > num){
            minStack.push(num);
        }
        dataStack.push(num);
    }


    public int pop(){
        if (dataStack.isEmpty()){
            throw new RuntimeException("no element");
        }
        Integer pop = dataStack.pop();
        if (pop.equals(minStack.peek())){
            minStack.pop();
        }
        return pop;
    }



    public int getMin(){
        if (minStack.isEmpty()){
            throw new RuntimeException("no element");
        }
        return minStack.peek();
    }




    public boolean isEmpty(){
        return dataStack.isEmpty();
    }

    public Stack<Integer> getDataStack() {
        return dataStack;
    }

    public Stack<Integer> getMinStack() {
        return minStack;
    }

    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
        GetMinStack getMinStack = new GetMinStack();
        try {
            getMinStack.push(2);
            getMinStack.push(1);
            getMinStack.push(4);
            getMinStack.push(5);
            getMinStack.push(3);
            printStack(getMinStack);
            while (!getMinStack.isEmpty()){
                System.out.println("当前最小值" + getMinStack.getMin());
                System.out.println(getMinStack.pop());
                // 打印当前栈状态
                System.out.println("当前栈状态");
                printStack(getMinStack);
            }
        }catch (Exception e){
            e.printStackTrace(System.err);
        }
    }


    public static void printStack(GetMinStack getMinStack){
        Stack<Integer> dataStack = getMinStack.getDataStack();
        Stack<Integer> minStack = getMinStack.getMinStack();
        for (int i = 0; i < dataStack.size(); i++){
            System.out.print(dataStack.get(i));
        }
        System.out.println("");
        for (int i = 0; i < minStack.size(); i++){
            System.out.print(minStack.get(i));
        }
        System.out.println();
    }


}
