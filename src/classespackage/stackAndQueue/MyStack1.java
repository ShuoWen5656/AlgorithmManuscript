package classespackage.stackAndQueue;

import secondround.stackandqueue.GetMinStack;

import java.util.Stack;

/**
 * @Author swzhao
 * @Date 2021/11/20 21:32
 * @Discription<> 设计一个有geiMin功能的栈
 */
public class MyStack1 {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;


    public MyStack1() {
        this.dataStack = new Stack<>();
        this.minStack = new Stack<>();
    }

    /**
     * 弹出
     * @return
     */
    public int pop(){
        if(this.dataStack.isEmpty()){
            throw new RuntimeException("dataStack is Empty");
        }
        Integer integer = this.dataStack.pop();
        if(integer == this.getmin()){
            this.minStack.pop();
        }
        return integer;
    }

    /**
     * 进
     * @param num
     */
    public void push(Integer num){
        if(this.minStack.isEmpty() || this.getmin() >= num){
            this.minStack.push(num);
        }
        dataStack.push(num);
    }

    /**
     * 获取最小值
     * @return
     */
    public int getmin(){
        if(this.minStack.isEmpty()){
            throw new RuntimeException("minStack is Empty");
        }else{
            return this.minStack.peek();
        }
    }



    private boolean isEmpty() {
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
        MyStack1 getMinStack = new MyStack1();
        try {
            getMinStack.push(2);
            getMinStack.push(1);
            getMinStack.push(4);
            getMinStack.push(5);
            getMinStack.push(3);
            printStack(getMinStack);
            while (!getMinStack.isEmpty()){
                System.out.println("当前最小值" + getMinStack.getmin());
                System.out.println(getMinStack.pop());
                // 打印当前栈状态
                System.out.println("当前栈状态");
                printStack(getMinStack);
            }
        }catch (Exception e){
            e.printStackTrace(System.err);
        }
    }



    public static void printStack(MyStack1 getMinStack){
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
